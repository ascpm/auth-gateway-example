package com.ascpm.hofund.jwt.data.converter;

import com.ascpm.hofund.jwt.data.response.ExceptionResponse;
import com.ascpm.hofund.jwt.comm.exception.JwtException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class JwtExceptionConverter {

    public ExceptionResponse convert(JwtException source, String uri) {
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .status(source.getType().getStatus().value())
                .error(source.getType().getStatus().getReasonPhrase())
                .code(source.getType().getCode())
                .message(source.getType().getMessage())
                .path(uri)
                .build();
    }
}
