package com.spring.springboot.jwt;

import com.spring.springboot.common.ResponseMap;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {
  // 捕捉shiro的异常
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(ShiroException.class)
  public ResponseMap handle401(ShiroException e) {
    return new ResponseMap(1, e.getMessage(), null);
  }

  // 捕捉UnauthorizedException
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseMap handle401() {
    return new ResponseMap(1, "Unauthorized", null);
  }

  // 捕捉其他所有异常
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseMap globalException(HttpServletRequest request, Throwable ex) {
    return new ResponseMap(1, ex.getMessage(), null);
  }

//  private HttpStatus getStatus(HttpServletRequest request) {
//    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//    if (statusCode == null) {
//      return HttpStatus.INTERNAL_SERVER_ERROR;
//    }
//    return HttpStatus.valueOf(statusCode);
//  }
}
