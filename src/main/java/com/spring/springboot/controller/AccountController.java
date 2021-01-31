package com.spring.springboot.controller;

import com.spring.springboot.common.ResponseMap;
import com.spring.springboot.dto.AccountDto;
import com.spring.springboot.jwt.*;
import com.spring.springboot.service.AccountService;
import com.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


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
  public ResponseMap updateUserInfo(@RequestBody AccountDto accountDto, ServletRequest servletRequest) {
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
  public ResponseMap getUserInfo(ServletRequest servletRequest) {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    String authorization = httpServletRequest.getHeader("Authorization");
    AccountDto user = accountService.getUserByname(JwtUtil.getUsername(authorization));
    user.setPassword("");
    return new ResponseMap(0, "查询成功", user);
  }
  /**
   * 上传
   *
   * @param
   * @return
   */
  @PostMapping("/upload")
  public Map upload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest req) {
    // 获取文件名
    // String fileName = uploadFile.getOriginalFilename();
    Map map = new HashMap();
//    String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
//    String realPath = "/www/wwwroot/file.dsiab.com/uploadFile";
    AccountDto user = accountService.getUserByname("admin");
    String realPath = user.getSourceRealUrl();
    System.out.println("realPath == " + realPath);

    // String format = sdf.format(new Date());
    File folder = new File(realPath);
    if (!folder.isDirectory()) {
      folder.mkdirs();
    }
    // 生成唯一名称
    String oldName = uploadFile.getOriginalFilename();
    String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());

    try {
      uploadFile.transferTo(new File(folder, newName));
      String filePath = user.getSourceUrl() + newName;
      map.put("location", filePath);
      return map;
    } catch (IOException e){
      e.printStackTrace();
    }
    map.put("location", "");
    return map;
  }
}
