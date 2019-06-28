package com.ascpm.hofund.jwt.comm.util;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class LogUtils {
    public static String get(HttpServletRequest request, String info, String activity, Object data) {
        return MessageFormat.format("\n[호출 활동]\nTIME : {0}\nIP : {1}\nURL : {2}\nINFO : {3}\nACTIVITY : {4}\nDATA : {5}\n",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()),
                RequestUtils.getIPAddress(request),
                request.getRequestURI(),
                Optional.ofNullable(info).orElse(""),
                Optional.ofNullable(activity).orElse(""),
                Optional.ofNullable(data).map(Object::toString).orElse(""));
    }
}
