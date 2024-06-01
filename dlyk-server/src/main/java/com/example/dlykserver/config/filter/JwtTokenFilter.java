package com.example.dlykserver.config.filter;

import com.example.dlykserver.constant.Constants;
import com.example.dlykserver.model.User;
import com.example.dlykserver.result.CodeEnum;
import com.example.dlykserver.result.R;
import com.example.dlykserver.service.RedisService;
import com.example.dlykserver.util.JSONUtils;
import com.example.dlykserver.util.JWTUtils;
import com.example.dlykserver.util.ResponseUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisService redisService;

    //spring boot框架的ioc容器中已经创建好了该线程池，可以注入直接使用
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //如果是登录请求，此时还没有生成jwt，那不需要对登录请求进行jwt验证
        if (request.getRequestURI().equals(Constants.LOGIN_URI)) {
            //不需要验证jwt ，让Filter链继续执行，也就是继续执行下一个Filter
            filterChain.doFilter(request, response);
        } else {
            String token = null;
            if (request.getRequestURI().equals(Constants.EXPORT_EXCEL_URI)) {
                //从请求路径的参数中获取token, 如果要导出Excel
                token = request.getParameter("Authorization");
            } else {
                //其他请求都是从请求头中获取token
                token = request.getHeader("Authorization");
                if (token != null && token.startsWith("Bearer ")) {
                    token =  token.substring(7);
                }
            }

            if (!StringUtils.hasText(token)) { //如果token值为空，返回R给前端
                //token验证未通过的统一结果
                R result = R.FAIL(CodeEnum.TOKEN_IS_EMPTY);
                //把R对象转成json
                String resultJSON = JSONUtils.toJSON(result);
                //把R以json返回给前端
                ResponseUtils.write(response, resultJSON);
                return;
            }

            //验证token有没有被篡改过，篡改后返回R给前端
            if (!JWTUtils.verifyJWT(token)) {
                //token验证未通过统一结果
                R result = R.FAIL(CodeEnum.TOKEN_IS_ERROR);
                //把R对象转成json
                String resultJSON = JSONUtils.toJSON(result);
                //把R以json返回给前端
                ResponseUtils.write(response, resultJSON);
                return;
            }

            //验证成功，token有值，拿到user对象
            User user = JWTUtils.parseUserFromJWT(token);
            String redisToken = (String) redisService.getValue(Constants.REDIS_JWT_KEY + user.getId());

            if (!StringUtils.hasText(redisToken)) { //redisToken的值为空的话
                //token验证未通过统一结果
                R result = R.FAIL(CodeEnum.TOKEN_IS_EXPIRED);
                //把R对象转成json
                String resultJSON = JSONUtils.toJSON(result);
                //把R以json返回给前端
                ResponseUtils.write(response, resultJSON);
                return;
            }

            //如果传过来的token与redis的token不一样
            if (!token.equals(redisToken)) {
                //token验证未通过的统一结果
                R result = R.FAIL(CodeEnum.TOKEN_IS_NONE_MATCH);
                //把R对象转成json
                String resultJSON = JSONUtils.toJSON(result);
                //把R以json返回给前端
                ResponseUtils.write(response, resultJSON);
                return;
            }

            //jwt验证通过了，那么在spring security的上下文环境中要设置一下，设置当前这个人是登录过的，你后续不要再拦截他了
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, user.getLoginPwd(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            //刷新一下token（异步处理，new一个线程去执行）
            /*new Thread(() -> {
                //刷新token
                String rememberMe = request.getHeader("rememberMe");
                if (Boolean.parseBoolean(rememberMe)) {
                    redisService.expire(Constants.REDIS_JWT_KEY + tUser.getId(), Constants.EXPIRE_TIME, TimeUnit.SECONDS);
                } else {
                    redisService.expire(Constants.REDIS_JWT_KEY + tUser.getId(), Constants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
                }
            }).start();*/

            //异步处理（更好的方式，使用线程池去执行）
            threadPoolTaskExecutor.execute(() -> {
                //刷新token
                String rememberMe = request.getHeader("rememberMe");
                if (Boolean.parseBoolean(rememberMe)) {
                    redisService.expire(Constants.REDIS_JWT_KEY + user.getId(), Constants.EXPIRE_TIME, TimeUnit.SECONDS);
                } else {
                    redisService.expire(Constants.REDIS_JWT_KEY + user.getId(), Constants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
                }
            });

            //验证jwt通过了 ，让Filter链继续执行，也就是继续执行下一个Filter
            filterChain.doFilter(request, response);
        }
    }
}
