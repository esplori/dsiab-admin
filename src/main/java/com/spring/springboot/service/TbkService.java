package com.spring.springboot.service;

import com.spring.springboot.dao.AccountDao;
import com.spring.springboot.dao.TbkDao;
import com.spring.springboot.dto.AccountDto;
import com.spring.springboot.dto.TbkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbkService {
  @Autowired
  TbkDao tbkDao;
  public void addTbkList(TbkDto tbkDto) {
    tbkDao.addTbkList(tbkDto);
  }
  public List<TbkDto> getList(int page) {
    return tbkDao.getList(page);
  }
  public int getListTotal() {
    return tbkDao.getListTotal();
  }
  public TbkDto getDetail(String id){
    return tbkDao.getDetail(id);
  }
}