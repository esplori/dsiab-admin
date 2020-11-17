package com.spring.springboot.controller;

import com.spring.springboot.common.ResponseMap;
import com.spring.springboot.dto.CateDto;
import com.spring.springboot.dto.PagesDto;
import com.spring.springboot.service.NavigationService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/nav")
public class NavigationController {

  @Autowired
  NavigationService navigationService;


  /**
   * 查询列表
   *
   * @return
   */
  @PostMapping("/getList")
  public ResponseMap getList() {
    Map<String, Object> resultMap = new HashMap<>();
    List<PagesDto> list = new ArrayList<>();
    // count = navigationService.getAllCount();
    list = navigationService.getAll();
    System.out.print("list==:" + list);
    // resultMap.put("total", count);
    resultMap.put("result", list);
    return new ResponseMap(0, "查询成功", resultMap);
  }

  /**
   * 查询详情
   *
   * @param id
   * @return
   */
  @GetMapping("/getDetail")
  public ResponseMap getOne(@RequestParam int id) {

    Map<String, Object> resultMap = new HashMap<>();

    resultMap.put("result", navigationService.getDetail(id));

    return new ResponseMap(0, "查询成功", resultMap);
  }

  /**
   * 新增
   *
   * @param pagesDto
   * @return
   */
  @PostMapping("/addItem")
  @RequiresAuthentication
  public ResponseMap inertOne(@RequestBody PagesDto pagesDto) {
    System.out.println("pagesDto=" + pagesDto);
    boolean insertRes = navigationService.insertOne(pagesDto);
    if (insertRes) {
      return new ResponseMap(0, "新增成功", insertRes);
    } else {
      return new ResponseMap(1, "新增失败", insertRes);
    }

  }

  /**
   * 删除
   *
   * @param PagesDto
   * @return
   */
  @PostMapping("/deleteItem")
  @RequiresAuthentication
  public ResponseMap delOne(@RequestBody PagesDto PagesDto) {
    boolean delRes = navigationService.delOne(PagesDto.getId());
    if (delRes) {
      return new ResponseMap(0, "删除成功", delRes);
    } else {
      return new ResponseMap(1, "删除失败", delRes);
    }
  }

  /**
   * 更新
   *
   * @param pagesDto
   * @return
   */
  @PostMapping("/updateItem")
  @RequiresAuthentication
  public ResponseMap updateOne(@RequestBody PagesDto pagesDto) {
    System.out.println("pagesDto=" + pagesDto);
    boolean updateRes = navigationService.updateOne(pagesDto);
    if (updateRes) {
      return new ResponseMap(0, "更新成功", updateRes);
    } else {
      return new ResponseMap(0, "更新失败", updateRes);
    }
  }

  /**
   * 新增分类
   *
   * @param cateDto
   * @return
   */
  @PostMapping("/insertCate")
  @RequiresAuthentication
  public ResponseMap inertOne(@RequestBody CateDto cateDto) {
    boolean insertRes = navigationService.insertCate(cateDto);
    if (insertRes) {
      return new ResponseMap(0, "新增成功", insertRes);
    } else {
      return new ResponseMap(1, "新增失败", insertRes);
    }

  }

  /**
   * 查询分类
   *
   * @return
   */
  @GetMapping("/getCate")
  public ResponseMap getCate() {
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("result", navigationService.getCate());
    return new ResponseMap(0, "查询成功", resultMap);
  }

  /**
   * 更新分类
   *
   * @return
   */
  @PostMapping("/updateCate")
  public ResponseMap updateCate(@RequestBody CateDto cateDto) {
    System.out.println("catDto" + cateDto.toString());
    boolean updateRes = navigationService.updateCate(cateDto);
    if (updateRes) {
      return new ResponseMap(0, "更新成功", updateRes);
    } else {
      return new ResponseMap(0, "更新失败", updateRes);
    }
  }

  /**
   * 删除分类
   *
   * @return
   */
  @PostMapping("/delCate")
  @RequiresAuthentication
  public ResponseMap delCate(@RequestBody CateDto cateDto) {
    boolean delRes = navigationService.delCate(cateDto.getId());
    if (delRes) {
      return new ResponseMap(0, "删除成功", delRes);
    } else {
      return new ResponseMap(1, "删除失败", delRes);
    }
  }
}
