package com.ascpm.hofund.jwt.comm.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {
    public static String getExceptionMessage(Throwable e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }
}
