package com.ascpm.hofund.jwt.comm.exception;

import com.ascpm.hofund.jwt.comm.util.MessageUtils;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class JwtException extends RuntimeException {

    private static final long serialVersionUID = -8878187821113758877L;

    private Throwable throwable;
    private Type type;

    @Getter
    @AllArgsConstructor
    public enum Type {
        UNAUTHORIZED("UNAUTHORIZED",
                MessageUtils.getMessage("exception.unauthorized"),
                MessageUtils.getMessage("exception.unauthorized.desc"),
                HttpStatus.UNAUTHORIZED),
        FORBIDDEN("FORBIDDEN",
                MessageUtils.getMessage("exception.forbidden"),
                MessageUtils.getMessage("exception.forbidden.desc"),
                HttpStatus.FORBIDDEN),
        ACCESS_DENIED("FORBIDDEN",
                MessageUtils.getMessage("exception.access.denied"),
                MessageUtils.getMessage("exception.access.denied.desc"),
                HttpStatus.FORBIDDEN),
        BAD_REQUEST("BAD_REQUEST",
                MessageUtils.getMessage("exception.bad.request"),
                MessageUtils.getMessage("exception.bad.request.desc"),
                HttpStatus.BAD_REQUEST),
        NOT_FOUND("NOT_FOUND",
                MessageUtils.getMessage("exception.not.found"),
                MessageUtils.getMessage("exception.not.found.desc"),
                HttpStatus.NOT_FOUND),
        METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED",
                MessageUtils.getMessage("exception.method.not.allowed"),
                MessageUtils.getMessage("exception.method.not.allowed.desc"),
                HttpStatus.METHOD_NOT_ALLOWED),
        INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR",
                MessageUtils.getMessage("exception.internal.server.error"),
                MessageUtils.getMessage("exception.internal.server.error.desc"),
                HttpStatus.INTERNAL_SERVER_ERROR);

        private String code;
        private String message;
        private String desc;
        private HttpStatus status;
    }
}
