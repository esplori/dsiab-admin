package com.spring.springboot.service;

import com.spring.springboot.dto.CateDto;
import com.spring.springboot.dto.CommentsDto;
import com.spring.springboot.dto.PagesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagesSerice {

  @Autowired(required = false)
  com.spring.springboot.dao.PagesDao PagesDao;

  public List<PagesDto> getList(PagesDto pagesDto) {
    int page = pagesDto.getPage();
    int pageSize = pagesDto.getPageSize();
    page = (page - 1)*pageSize;
    pagesDto.setPage(page);
    return PagesDao.getList(pagesDto);
  }

  public List<PagesDto> getRecomList(PagesDto pagesDto) {
    return PagesDao.getRecomList(pagesDto);
  }

  public List<PagesDto> getAll(PagesDto pagesDto) {
    int page = pagesDto.getPage();
    int pageSize = pagesDto.getPageSize();
    page = (page - 1)*pageSize;
    pagesDto.setPage(page);
    return PagesDao.getAll(pagesDto);
  }

  public PagesDto getOne(int  id) {
    return PagesDao.getOne(id);
  }

  public PagesDto getDetail(int  id) {
    return PagesDao.getDetail(id);
  }

  public boolean insertOne(PagesDto pagesDto) {
    boolean flag = false;
    try{
      PagesDao.insertOne(pagesDto);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }

  public boolean delOne(int id) {
    boolean flag = false;
    try{
      PagesDao.delOne(id);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }


  public boolean updateOne(PagesDto pagesDto) {
    boolean flag = false;
    try{
      PagesDao.updateOne(pagesDto);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }
  public int getCount(PagesDto pagesDto) {
    int count = PagesDao.getCount(pagesDto);
    System.out.print("count = " + count);
    return count;
  }
  public int getAllCount() {
    int count = PagesDao.getAllCount();
    System.out.print("count = " + count);
    return count;
  }

  public List getCate() {
    return PagesDao.getCate();
  }

  public boolean insertCate(CateDto cateDto) {
    boolean flag = false;
    try{
      PagesDao.insertCate(cateDto);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }

  public boolean updateCate(CateDto cateDto) {
    boolean flag = false;
    try{
      PagesDao.updateCate(cateDto);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }
  public boolean delCate(int id) {
    boolean flag = false;
    try{
      PagesDao.delCate(id);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }
  public List search(String key) {
    return PagesDao.search(key);
  }
  public boolean updateViews(PagesDto pagesDto) {
    boolean flag = false;
    try{
      PagesDao.updateViews(pagesDto);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }

  public boolean insertComment(CommentsDto commentsDto) {
    boolean flag = false;
    try{
      PagesDao.insertComment(commentsDto);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }

  public List<CommentsDto> getComment(int  id) {
    return PagesDao.getComment(id);
  }

  public boolean delComment(int id) {
    boolean flag = false;
    try{
      PagesDao.delComment(id);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }

  public List<CommentsDto> getLatestComments() {
    return PagesDao.getLatestComments();
  }

  public boolean updateCommentNum(CommentsDto commentsDto) {
    boolean flag = false;
    try{
      PagesDao.updateCommentNum(commentsDto);
      flag = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }

}
