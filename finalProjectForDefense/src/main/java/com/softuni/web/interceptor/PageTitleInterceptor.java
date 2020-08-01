package com.softuni.web.interceptor;

import com.softuni.web.annotation.PageTitle;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PageTitleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String title="ST Auto";
        if (modelAndView!=null){
            if (handler instanceof HandlerMethod){
                PageTitle methodAnnotation=((HandlerMethod) handler).getMethodAnnotation(PageTitle.class);

                if (methodAnnotation!=null){
                    modelAndView.addObject("title",title+ " - "+methodAnnotation.value());
                }
            }
        }
    }
}