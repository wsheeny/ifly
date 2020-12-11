package com.wyc.rhapsody.backend.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 日志工具
 * <p>
 * 将异常信息写入文件中
 *
 * @author Knox
 */
public class ExceptionUtil {
    public static String getMessage(Exception e) {
        StringWriter stringWriter = null;
        PrintWriter printWriter = null;
        try {
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(printWriter);
            printWriter.flush();
            stringWriter.flush();
        } finally {
            if (stringWriter != null) {
                try {
                    stringWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (printWriter != null) {
                printWriter.close();
            }
        }
        return stringWriter.toString();
    }
}
