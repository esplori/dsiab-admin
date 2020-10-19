package com.spring.springboot.controller;

import com.spring.springboot.common.ResponseMap;
import com.spring.springboot.dto.AccountDto;
import com.spring.springboot.jwt.*;
import com.spring.springboot.service.AccountService;
import com.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/account")
public class AccountController {

  private UserService userService;

  @Autowired
  public void setService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  AccountService accountService;
  @PostMapping("/login")
  public ResponseMap login(@RequestBody AccountDto accountDto) {

    AccountDto userBean = userService.getUser(accountDto.getUsername());
    if (userBean.getPassword().equals(accountDto.getPassword())) {
      String token = JwtUtil.sign(accountDto.getUsername(),accountDto.getPassword());
      Map userInfo = new HashMap();
      userInfo.put("username", userBean.getUsername());
      userInfo.put("role", userBean.getRole());
      userInfo.put("token", token);
      userInfo.put("nickname", userBean.getNickname());
      userInfo.put("userdesc", userBean.getUserdesc());
      userInfo.put("avatar", userBean.getAvatar());
      return new ResponseMap(0, "登录成功", userInfo);
    } else {
      return new ResponseMap(1, "登录失败", null);
    }

  }

  @PostMapping("/updateUserInfo")
  public ResponseMap getUserInfo(@RequestBody AccountDto accountDto, ServletRequest servletRequest) {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    String authorization = httpServletRequest.getHeader("Authorization");
    accountDto.setUsername(JwtUtil.getUsername(authorization));
    boolean insertRes = accountService.updateUserInfo(accountDto);
    if (insertRes) {
      return new ResponseMap(0, "更新成功", insertRes);
    } else {
      return new ResponseMap(1, "更新失败", insertRes);
    }
  }

  @GetMapping("/getUserInfo")
  public ResponseMap updateUserInfo(ServletRequest servletRequest) {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    String authorization = httpServletRequest.getHeader("Authorization");
    AccountDto user = accountService.getUserByname(JwtUtil.getUsername(authorization));
    return new ResponseMap(0, "查询成功", user);
  }
}
