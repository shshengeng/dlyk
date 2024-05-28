package com.example.dlykserver.util;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtils {

    /**
     * Use response to write the results to the front-end
     */
    public static void write(HttpServletResponse response, String result) {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(result);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //close writer
            if (writer != null) {
                writer.close();
            }
        }
    }
}
