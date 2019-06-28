package com.ascpm.hofund.jwt.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration("dataConfig")
@ComponentScan("com.ascpm.hofund.jwt.data")
@EntityScan("com.ascpm.hofund.jwt.data.entity")
@EnableJpaRepositories("com.ascpm.hofund.jwt.data.repository")
public class Config {
}
