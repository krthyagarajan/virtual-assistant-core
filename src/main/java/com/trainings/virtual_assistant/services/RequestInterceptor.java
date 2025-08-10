package com.trainings.virtual_assistant.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@AllArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {

    private JsonTokenService jsonTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        String url = request.getRequestURI();

        if(StringUtils.hasLength(url) && !url.contains("login")){
            String accessToken = request.getHeader("access-token");
            log.info("Access Token : {}", accessToken);
        }
        return true; // continue the request
    }
}
