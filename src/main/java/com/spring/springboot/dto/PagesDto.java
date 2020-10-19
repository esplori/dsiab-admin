package com.spring.springboot.dto;

public class PagesDto {
 private String title;
 private String content;
 private String createDate;
 private int cate;
 private int id;
 private int page;
 private int pageSize;
 private int views;
 private int likes;
 private int comments;
 private String htmlContent;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public int getCate() {
    return cate;
  }

  public void setCate(int cate) {
    this.cate = cate;
  }

  public int getViews() {
    return views;
  }

  public void setViews(int views) {
    this.views = views;
  }

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  public String getHtmlContent() {
    return htmlContent;
  }

  public void setHtmlContent(String htmlContent) {
    this.htmlContent = htmlContent;
  }

  public int getComments() {
    return comments;
  }

  public void setComments(int comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return "PagesDto{" +
            "title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", createDate='" + createDate + '\'' +
            ", cate=" + cate +
            ", id=" + id +
            ", page=" + page +
            ", pageSize=" + pageSize +
            ", views=" + views +
            ", likes=" + likes +
            ", comments=" + comments +
            ", htmlContent='" + htmlContent + '\'' +
            '}';
  }
}
