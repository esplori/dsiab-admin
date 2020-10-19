package com.spring.springboot.dao;

import com.spring.springboot.dto.AccountDto;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountDao {
  @Select("select * from user where username = ${name} and password = ${password}")
  public List<AccountDto> login2(AccountDto accountDto);

//  @Select("select * from user where username = ${username}")
  public AccountDto findName(String username);

  public boolean updateUserInfo(AccountDto accountDto);
}