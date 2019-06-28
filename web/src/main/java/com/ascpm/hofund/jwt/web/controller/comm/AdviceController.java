package com.ascpm.hofund.jwt.web.controller.comm;

import com.ascpm.hofund.jwt.client.converter.KongExceptionConverter;
import com.ascpm.hofund.jwt.client.exception.KongException;
import com.ascpm.hofund.jwt.comm.exception.JwtException;
import com.ascpm.hofund.jwt.comm.util.ExceptionUtils;
import com.ascpm.hofund.jwt.data.converter.JwtExceptionConverter;
import com.ascpm.hofund.jwt.data.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class AdviceController {

    @Autowired
    private JwtExceptionConverter jwtExceptionConverter;

    @Autowired
    private KongExceptionConverter kongExceptionConverter;

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse throwableHandler(Throwable throwable,
                                              HttpServletRequest request) {
        log.info(ExceptionUtils.getExceptionMessage(throwable));
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .status(JwtException.Type.INTERNAL_SERVER_ERROR.getStatus().value())
                .error(JwtException.Type.INTERNAL_SERVER_ERROR.getStatus().getReasonPhrase())
                .code(JwtException.Type.INTERNAL_SERVER_ERROR.getCode())
                .message(JwtException.Type.INTERNAL_SERVER_ERROR.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ExceptionResponse httpRequestMethodNotSupportedExceptionHandler(
            HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
        log.info(ExceptionUtils.getExceptionMessage(exception));
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .status(JwtException.Type.METHOD_NOT_ALLOWED.getStatus().value())
                .error(JwtException.Type.METHOD_NOT_ALLOWED.getStatus().getReasonPhrase())
                .code(JwtException.Type.METHOD_NOT_ALLOWED.getCode())
                .message(JwtException.Type.METHOD_NOT_ALLOWED.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, NoSuchElementException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse httpMessageNotReadableExceptionHandler(
            HttpMessageNotReadableException exception, HttpServletRequest request) {
        log.info(ExceptionUtils.getExceptionMessage(exception));
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .status(JwtException.Type.BAD_REQUEST.getStatus().value())
                .error(JwtException.Type.BAD_REQUEST.getStatus().getReasonPhrase())
                .code(JwtException.Type.BAD_REQUEST.getCode())
                .message(JwtException.Type.BAD_REQUEST.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<ExceptionResponse> httpStatusCodeExceptionHandler(
            HttpStatusCodeException exception, HttpServletRequest request) {
        log.info(ExceptionUtils.getExceptionMessage(exception));
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .status(exception.getStatusCode().value())
                .error(exception.getStatusCode().getReasonPhrase())
                .code(JwtException.Type.BAD_REQUEST.getCode())
                .message(JwtException.Type.BAD_REQUEST.getMessage())
                .path(request.getRequestURI())
                .build(),
                exception.getStatusCode());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ExceptionResponse> jwtExceptionHandler(JwtException exception,
                                                                 HttpServletRequest request) {
        log.info(ExceptionUtils.getExceptionMessage(exception));
        return new ResponseEntity<>(jwtExceptionConverter.convert(exception, request.getRequestURI()),
                exception.getType().getStatus());
    }

    @ExceptionHandler(KongException.class)
    public ResponseEntity<ExceptionResponse> kongExceptionHandler(KongException exception,
                                                                  HttpServletRequest request) {
        log.info(ExceptionUtils.getExceptionMessage(exception));
        return new ResponseEntity<>(kongExceptionConverter.convert(exception, request.getRequestURI()),
                exception.getStatus());
    }
}
