package com.ascpm.hofund.jwt.comm.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class RequestUtils {
    public static String getIPAddress(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("X-KKB-IP"))
                .orElse(Optional.ofNullable(request.getHeader("X-FORWARDED-FOR"))
                        .orElse(request.getRemoteAddr()));
    }
}
