package com.spring.springboot.dto;

public class CommentsDto {
  private int id;
  private String username;
  private String content;
  private String createDate;
  private String parentId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "CommentsDto{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", content='" + content + '\'' +
            ", createDate='" + createDate + '\'' +
            ", parentId='" + parentId + '\'' +
            '}';
  }
}
