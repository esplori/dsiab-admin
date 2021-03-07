package com.spring.springboot.dao;

import com.spring.springboot.dto.AccountDto;
import com.spring.springboot.dto.TbkDto;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbkDao {
  public void addTbkList(TbkDto tbkDto);
  public List<TbkDto> getList();
  public TbkDto getDetail(String id);
}