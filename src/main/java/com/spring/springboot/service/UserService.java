package com.spring.springboot.service;

import com.spring.springboot.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

  @Autowired
  AccountService accountService;
  public AccountDto getUser(String username) {

    AccountDto user = accountService.getUserByname(username);
    return user;
  }
}