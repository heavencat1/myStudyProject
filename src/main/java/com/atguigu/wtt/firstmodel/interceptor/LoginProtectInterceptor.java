package com.atguigu.wtt.firstmodel.interceptor;

import com.atguigu.wtt.firstmodel.enums.ResultCodeEnum;
import com.atguigu.wtt.firstmodel.utils.JwtHelper;
import com.atguigu.wtt.firstmodel.utils.Result;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class LoginProtectInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtHelper jwtHelper;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)){
            Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().print(json);
            //拦截
            return false;
        }else{
            //放行
            return true;
        }
    }


}


