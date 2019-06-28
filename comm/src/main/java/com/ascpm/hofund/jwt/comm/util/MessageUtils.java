package com.ascpm.hofund.jwt.comm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtils {
    private static MessageSource messageSource;

    @Autowired
    public MessageUtils(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }

    public static String getMessage(String code) {
        return MessageUtils.getMessage(code, new Object[]{});
    }

    public static String getMessage(String code, Object... args) {
        return MessageUtils.messageSource.getMessage(code, args, Locale.KOREA);
    }
}
