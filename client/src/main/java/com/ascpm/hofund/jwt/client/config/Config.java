package com.ascpm.hofund.jwt.client.config;

import com.ascpm.hofund.jwt.client.props.KongCredentialProps;
import com.ascpm.hofund.jwt.client.props.KongProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("clientConfig")
@ComponentScan("com.ascpm.hofund.jwt.client")
@EnableConfigurationProperties({KongProps.class, KongCredentialProps.class})
public class Config {
}
