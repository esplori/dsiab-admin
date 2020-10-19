package com.spring.springboot.intercaptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyIntercepotor implements HandlerInterceptor {

  @Override
  public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler){
//    System.out.println("request>>>preHandle===");
    return true;
  }

  @Override
  public void postHandle (HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
//    System.out.println("MyIntercepotor>>>postHandle");
  }

  @Override
  public void afterCompletion (HttpServletRequest request, HttpServletResponse response, Object handler, Exception e){
//    System.out.println("MyIntercepotor>>>afterHandle");
  }
}
