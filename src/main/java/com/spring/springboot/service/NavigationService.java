package com.spring.springboot.service;

import com.spring.springboot.dao.NavigationDao;
import com.spring.springboot.dto.CateDto;
import com.spring.springboot.dto.PagesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavigationService {

  @Autowired(required = false)
  NavigationDao navigationDao;

  public List<PagesDto> getList(PagesDto pagesDto) {
    int page = pagesDto.getPage();
    int pageSize = pagesDto.getPageSize();
    page = (page - 1)*pageSize;
    pagesDto.setPage(page);
    return navigationDao.getList(pagesDto);
  }

  public List<PagesDto> getRecomList() {
    return navigationDao.getRecomList();
  }

  public List<PagesDto> getAll() {
    return navigationDao.getAll();
  }

  public PagesDto getOne(int  id) {
    return navigationDao.getOne(id);
  }

  public PagesDto getDetail(int  id) {
    return navigationDao.getDetail(id);
  }

  public boolean insertOne(PagesDto pagesDto) {
    boolean flag = false;
    try{
      navigationDao.insertOne(pagesDto);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }

  public boolean delOne(int id) {
    boolean flag = false;
    try{
      navigationDao.delOne(id);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }


  public boolean updateOne(PagesDto pagesDto) {
    boolean flag = false;
    try{
      navigationDao.updateOne(pagesDto);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }
  public int getCount(PagesDto pagesDto) {
    int count = navigationDao.getCount(pagesDto);
    System.out.print("count = " + count);
    return count;
  }
  public int getAllCount() {
    int count = navigationDao.getAllCount();
    System.out.print("count = " + count);
    return count;
  }

  public List getCate() {
    return navigationDao.getCate();
  }

  public boolean insertCate(CateDto cateDto) {
    boolean flag = false;
    try{
      navigationDao.insertCate(cateDto);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }

  public boolean updateCate(CateDto cateDto) {
    boolean flag = false;
    try{
      navigationDao.updateCate(cateDto);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }
  public boolean delCate(int id) {
    boolean flag = false;
    try{
      navigationDao.delCate(id);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }
}
