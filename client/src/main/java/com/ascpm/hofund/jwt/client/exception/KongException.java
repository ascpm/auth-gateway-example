package com.ascpm.hofund.jwt.client.exception;

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
public class KongException extends RuntimeException {

    private static final long serialVersionUID = -3380348118349558662L;

    private Type type;
    private HttpStatus status;

    @Getter
    @AllArgsConstructor
    public enum Type {
        CREATE_CONSUMER_CLIENT_ERROR("KONG_CREATE_CONSUMER_CLIENT_ERROR",
                MessageUtils.getMessage("exception.kong.create.consumer.client.error"),
                MessageUtils.getMessage("exception.kong.create.consumer.client.error.desc")),
        CREATE_CONSUMER_SERVER_ERROR("KONG_CREATE_CONSUMER_SERVER_ERROR",
                MessageUtils.getMessage("exception.kong.create.consumer.server.error"),
                MessageUtils.getMessage("exception.kong.create.consumer.server.error.desc")),
        CREATE_CREDENTIAL_CLIENT_ERROR("KONG_CREATE_CREDENTIAL_CLIENT_ERROR",
                MessageUtils.getMessage("exception.kong.create.credential.client.error"),
                MessageUtils.getMessage("exception.kong.create.credential.client.error.desc")),
        CREATE_CREDENTIAL_SERVER_ERROR("KONG_CREATE_CREDENTIAL_SERVER_ERROR",
                MessageUtils.getMessage("exception.kong.create.credential.server.error"),
                MessageUtils.getMessage("exception.kong.create.credential.server.error.desc")),
        DELETE_CREDENTIAL_CLIENT_ERROR("KONG_DELETE_CREDENTIAL_CLIENT_ERROR",
                MessageUtils.getMessage("exception.kong.delete.credential.client.error"),
                MessageUtils.getMessage("exception.kong.delete.credential.client.error.desc")),
        DELETE_CREDENTIAL_SERVER_ERROR("KONG_DELETE_CREDENTIAL_SERVER_ERROR",
                MessageUtils.getMessage("exception.kong.delete.credential.server.error"),
                MessageUtils.getMessage("exception.kong.delete.credential.server.error.desc"));

        private String code;
        private String message;
        private String desc;
    }
}
