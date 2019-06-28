package com.ascpm.hofund.jwt.client.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kong")
@Getter
@Setter
public class KongProps {

    private String baseUrl;
    private KongCredentialProps credential = new KongCredentialProps();
}
