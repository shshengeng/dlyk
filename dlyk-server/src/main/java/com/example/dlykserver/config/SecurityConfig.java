package com.example.dlykserver.config;

import com.example.dlykserver.config.filter.JwtTokenFilter;
import com.example.dlykserver.config.handler.MyAuthenticationFailureHandler;
import com.example.dlykserver.config.handler.MyAuthenticationSuccessHandler;
import com.example.dlykserver.config.handler.MyLogoutSuccessHandler;
import com.example.dlykserver.constant.Constants;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Resource
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        /**
         * Spring Security的默认表单登录处理是基于传统的表单提交
         * (application/x-www-form-urlencoded) 的方式来接收用户名和密码的。
         * 这意味着，默认情况下，Spring Security并不会处理JSON格式的用户名和密码
         */
        http.
                formLogin((formLogin) -> {
                    formLogin.loginProcessingUrl(Constants.LOGIN_URI)
                            .usernameParameter("loginAct")
                            .passwordParameter("loginPwd")
                            .successHandler(myAuthenticationSuccessHandler)
                            .failureHandler(myAuthenticationFailureHandler);
                })
                .authorizeRequests((authorize) -> {
                    authorize.requestMatchers(Constants.LOGIN_URI).permitAll()
                            .anyRequest().authenticated(); //任何请求都要认证
                })
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf( csrf -> csrf.disable())
                //solve 同源政策（Same-Origin Policy）
                //Cross-Origin Resource Sharing
                //CORS 允许服务器通过设置特定的 HTTP 头来指示浏览器是否允许跨域请求
                .cors( cors -> cors.configurationSource(corsConfigurationSource))
                .logout( logout -> logout.logoutUrl("/api/logout").logoutSuccessHandler(myLogoutSuccessHandler));


        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));  //allow any origins http://localhost:8080
        configuration.setAllowedMethods(Arrays.asList("*")); //allow any methods
        configuration.setAllowedHeaders(Arrays.asList("*")); //allow any headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
