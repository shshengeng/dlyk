package com.example.dlykserver.config.handler;

import com.example.dlykserver.constant.Constants;
import com.example.dlykserver.result.CodeEnum;
import com.example.dlykserver.result.R;
import com.example.dlykserver.service.RedisService;
import com.example.dlykserver.util.JSONUtils;
import com.example.dlykserver.util.ResponseUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 退出成功处理器
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private RedisService redisService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //当用户执行注销操作后，authentication 对象将会被清除，因此在 myLogoutSuccessHandler 中无法再通过 authentication 对象获取用户信息
//        if (authentication != null) {
//            //退出成功，执行该方法，在该方法中返回json给前端，就行了
//            User user = (User) authentication.getPrincipal();
//
//            // 删除 redis 中用户的 jwt
//            redisService.removeValue(Constants.REDIS_JWT_KEY + user.getId());
//        }

        // 获取参数值
        String paramValue = request.getParameter("userId");

        // 将参数值转换为 Integer 类型
        Integer intValue = null;
        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                intValue = Integer.parseInt(paramValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        // 删除 redis 中用户的 jwt
        redisService.removeValue(Constants.REDIS_JWT_KEY + paramValue);

        //退出成功的统一结果
        R result = R.OK(CodeEnum.USER_LOGOUT);

        //把R对象转成json
        String resultJSON = JSONUtils.toJSON(result);

        //把R以json返回给前端
        ResponseUtils.write(response, resultJSON);
    }
}
