package com.ascpm.hofund.jwt.client.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class KongCredentialCreateResponse implements Serializable {

    private static final long serialVersionUID = 6864852745007326761L;

    private String consumerId;
    private long createdAt;
    private String id;
    private String key;
    private String secret;
}
