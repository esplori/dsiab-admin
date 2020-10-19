package com.spring.springboot.dto;

public class AccountDto {
  private String username;

  private String password;

  private String role;

  private String permission;

  private String nickname;

  private String userdesc;

  private String avatar;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getUserdesc() {
    return userdesc;
  }

  public void setUserdesc(String userdesc) {
    this.userdesc = userdesc;
  }

  @Override
  public String toString() {
    return "AccountDto{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", role='" + role + '\'' +
            ", permission='" + permission + '\'' +
            ", nickname='" + nickname + '\'' +
            ", userdesc='" + userdesc + '\'' +
            ", avatar='" + avatar + '\'' +
            '}';
  }
}

