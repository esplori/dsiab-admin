package com.spring.springboot.common;

public class ResponseMap {
  // http 状态码
  private int code;

  // 返回信息
  private String msg;

  // 返回的数据
  private Object result;

  public ResponseMap(int code, String msg, Object data) {
    this.code = code;
    this.msg = msg;
    this.result = data;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object getData() {
    return result;
  }

  public void setData(Object data) {
    this.result = data;
  }
}
