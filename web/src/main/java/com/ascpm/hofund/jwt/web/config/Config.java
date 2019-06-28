package com.ascpm.hofund.jwt.web.config;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Configuration("webConfig")
public class Config {
    @Bean
    public BasicErrorController basicErrorController(ErrorAttributes errorAttributes,
                                                     ServerProperties serverProperties) {
        return new BasicErrorController(errorAttributes, serverProperties.getError()) {
            @Override
            public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
                return Optional.ofNullable(getStatus(request))
                        .filter(it -> it.equals(HttpStatus.NOT_FOUND))
                        .map(it -> new ModelAndView(String.format("redirect:/exception/%s", it)))
                        .orElse(new ModelAndView("redirect:/exception"));
            }
        };
    }
}
