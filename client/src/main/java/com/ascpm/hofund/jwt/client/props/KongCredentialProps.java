package com.ascpm.hofund.jwt.client.props;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kong.credential")
@Getter
@Setter
@NoArgsConstructor
public class KongCredentialProps {

    private String createUri;
    private String deleteUri;
}
