package com.example.dlykserver.config;

import com.example.dlykserver.util.JSONUtils;
import com.example.dlykserver.util.ResponseUtils;
import com.example.dlykserver.result.R;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * handler of failure
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    //If the login fails, execute this method and return json to the front end in this method.
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //results of failed logins
        R result = R.FAIL(exception.getMessage());

        //convert r object to json string
        String resultJSON = JSONUtils.toJSON(result);

        //return json string to the front-end
        ResponseUtils.write(response, resultJSON);
    }
}
