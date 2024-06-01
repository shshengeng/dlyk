package com.example.dlykserver.config;

import com.example.dlykserver.constant.Constants;
import com.example.dlykserver.model.User;
import com.example.dlykserver.result.R;
import com.example.dlykserver.service.RedisService;
import com.example.dlykserver.util.JSONUtils;
import com.example.dlykserver.util.JWTUtils;
import com.example.dlykserver.util.ResponseUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private RedisService redisService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //登录成功，执行该方法，在该方法中返回json给前端，就行了
        User user = (User) authentication.getPrincipal();

        //1、生成jwt
        //把user对象转成json作为负载数据放入jwt
        String userJSON = JSONUtils.toJSON(user);
        String jwt = JWTUtils.createJWT(userJSON);

        //2、写入redis
        redisService.setValue(Constants.REDIS_JWT_KEY + user.getId(), jwt);

        //3、设置jwt的过期时间(如果选择了记住我，过期时间是7天，否则是30分钟)
        String rememberMe = request.getParameter("rememberMe");
        if (Boolean.parseBoolean(rememberMe)) {
            // 7 days
            redisService.expire(Constants.REDIS_JWT_KEY + user.getId(), Constants.EXPIRE_TIME, TimeUnit.SECONDS);
        } else {
            //30 minutes
            redisService.expire(Constants.REDIS_JWT_KEY + user.getId(), Constants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
        }

        //the result of successful login
        R result = R.OK(jwt);

        //convert r object to json string
        String resultJSON = JSONUtils.toJSON(result);

        //return json string to the front-end
        ResponseUtils.write(response, resultJSON);
    }
}
