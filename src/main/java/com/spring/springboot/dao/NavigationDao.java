package com.spring.springboot.dao;

import com.spring.springboot.dto.CateDto;
import com.spring.springboot.dto.CommentsDto;
import com.spring.springboot.dto.PagesDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NavigationDao {

  public int getCount(PagesDto pagesDto);

  public int getAllCount();

  public List<PagesDto> getList(PagesDto pagesDto);

  public List<PagesDto> getRecomList();

  public List<PagesDto> getAll(PagesDto pagesDto);

  public PagesDto getOne(@Param("id") int id);

  public PagesDto getDetail(@Param("id") int id);

  public boolean insertOne(PagesDto pagesDto);

  public boolean delOne(@Param("id") int id);

  public boolean updateOne(PagesDto pagesDto);

  public List getCate();

  public boolean updateCate(CateDto cateDto);

  public boolean delCate(@Param("id") int id);

  public boolean insertCate(CateDto cateDto);

  public List<PagesDto> search(String key);

  public boolean updateViews(PagesDto pagesDto);

  public boolean insertComment(CommentsDto commentsDto);

  public List<CommentsDto> getComment(int id);

  public boolean delComment(@Param("id") int id);

  public List<CommentsDto> getLatestComments();

  public boolean updateCommentNum(CommentsDto commentsDto);

}

