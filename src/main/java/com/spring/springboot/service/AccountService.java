package com.spring.springboot.service;

import com.spring.springboot.dao.AccountDao;
import com.spring.springboot.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
  @Autowired(required = false)
  AccountDao accountDao;

  public List<AccountDto> login(AccountDto accountDto ) {
    return accountDao.login2(accountDto);
  }

  public AccountDto getUserByname(String name) {
    return accountDao.findName(name);
  }

  public boolean updateUserInfo(AccountDto accountDto) {
    boolean flag = false;
    try{
      accountDao.updateUserInfo(accountDto);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }

}