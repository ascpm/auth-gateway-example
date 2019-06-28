package com.ascpm.hofund.jwt.client.converter;

import com.ascpm.hofund.jwt.data.response.ExceptionResponse;
import com.ascpm.hofund.jwt.client.exception.KongException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class KongExceptionConverter {

    public ExceptionResponse convert(KongException source, String uri) {
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .status(source.getStatus().value())
                .error(source.getStatus().getReasonPhrase())
                .code(source.getType().getCode())
                .message(source.getType().getMessage())
                .path(uri)
                .build();
    }
}
