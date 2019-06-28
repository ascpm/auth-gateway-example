package com.ascpm.hofund.jwt.business.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration("businessConfig")
@ComponentScan({"com.ascpm.hofund.jwt.business", "com.ascpm.hofund.jwt.data"})
@EnableTransactionManagement
public class Config {
}
