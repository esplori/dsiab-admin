package com.spring.springboot.controller;

import com.spring.springboot.dto.PagesDto;
import com.spring.springboot.service.PagesSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ThymeleafController {

  @Autowired
  PagesSerice pagesSerice;

  @GetMapping("/")
  public String index() {
    return "redirect:/index?page=1&pageSize=20";
  }

  @GetMapping("/index")
  public String list(Model model, @RequestParam(required = true) int page,@RequestParam(required = true) int pageSize) {
    PagesDto pagesDto = new PagesDto();
    pagesDto.setPage(page);
    pagesDto.setPageSize(pageSize);
    List<PagesDto> list = pagesSerice.getAll(pagesDto);
    // System.out.println(list.toString());
    model.addAttribute("list", list);
    model.addAttribute("prePage", page - 1);
    model.addAttribute("nextPage", page + 1);
    return "home/index";
  }
  @GetMapping("/detail")
  public String detail(Model model, @RequestParam int id) {
    PagesDto pagesDto  = pagesSerice.getDetail(id);
    model.addAttribute("page", pagesDto);
    return "home/detail";
  }
}
