package com.spring.springboot.controller;

import com.spring.springboot.common.ResponseMap;
import com.spring.springboot.dto.CateDto;
import com.spring.springboot.dto.CommentsDto;
import com.spring.springboot.dto.FileDto;
import com.spring.springboot.dto.PagesDto;
import com.spring.springboot.service.PagesSerice;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/pages")
public class PagesController {

  @Autowired
  PagesSerice pagesSerice;

  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

  /**
   * 查询列表
   *
   * @return
   */
  @PostMapping("/getList")
//  @Cacheable(value = "resultMap",key = "#pagesDto.id")
  public ResponseMap getList(@RequestBody PagesDto pagesDto) {
    Map<String, Object> resultMap = new HashMap<>();
    List<PagesDto> list = new ArrayList<>();
    int count = 0;
    if (pagesDto.getCate() > 0) {
      count = pagesSerice.getCount(pagesDto);
      list = pagesSerice.getList(pagesDto);
    } else {
      count = pagesSerice.getAllCount();
      list = pagesSerice.getAll(pagesDto);
    }
    resultMap.put("total", count);
    resultMap.put("result", list);
    return new ResponseMap(0, "查询成功", resultMap);
  }

  /**
   * 查询推荐阅读
   *
   * @return
   */
  @PostMapping("/getRecomList")
//  @Cacheable(value = "list",key = "#pagesDto.id")
  public ResponseMap getRecomList(@RequestBody PagesDto pagesDto) {
    List<PagesDto> list = pagesSerice.getRecomList(pagesDto);
    return new ResponseMap(0, "查询成功", list);
  }

  /**
   * 后台管理查询列表(也可以直接调pagesDao里面方法)
   *
   * @return
   */
  @PostMapping("/admin/getList")
  @RequiresAuthentication
  public ResponseMap adminGetList(@RequestBody PagesDto pagesDto) {
    Map<String, Object> resultMap = new HashMap<>();
    int count = pagesSerice.getAllCount();
    System.out.println("count=" + count);
    List<PagesDto> list = pagesSerice.getAll(pagesDto);
    resultMap.put("total", count);
    resultMap.put("result", list);
    return new ResponseMap(0, "查询成功", resultMap);
  }

  /**
   * 查询详情
   *
   * @param id
   * @return
   */
  @GetMapping("/getDetail/{id}")
//  public ResponseMap getOne(@RequestBody PagesDto pagesDtoParams) {
  public ResponseMap getOne(@PathVariable("id") String id) {
    // 浏览量加1
    PagesDto pagesDto  = pagesSerice.getDetail(Integer.parseInt(id));

    pagesSerice.updateViews(pagesDto);

    Map<String, Object> resultMap = new HashMap<>();

    resultMap.put("result", pagesSerice.getDetail(Integer.parseInt(id)));

    return new ResponseMap(0, "查询成功", resultMap);
  }

  /**
   * 后台管理查询详情
   *
   * @param id
   * @return
   */
  @GetMapping("/admin/getDetail")
  @RequiresAuthentication
  public ResponseMap adminGetOne(@RequestParam int id) {
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("result", pagesSerice.getOne(id));
    return new ResponseMap(0, "查询成功", resultMap);
  }

  /**
   * 新增
   *
   * @param pagesDto
   * @return
   */
  @PostMapping("/list")
  @RequiresAuthentication
  public ResponseMap inertOne(@RequestBody PagesDto pagesDto) {
    System.out.println("pagesDto=" + pagesDto);
    boolean insertRes = pagesSerice.insertOne(pagesDto);
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
    boolean delRes = pagesSerice.delOne(PagesDto.getId());
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
    boolean updateRes = pagesSerice.updateOne(pagesDto);
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
    boolean insertRes = pagesSerice.insertCate(cateDto);
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
    resultMap.put("result", pagesSerice.getCate());
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
    boolean updateRes = pagesSerice.updateCate(cateDto);
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
    boolean delRes = pagesSerice.delCate(cateDto.getId());
    if (delRes) {
      return new ResponseMap(0, "删除成功", delRes);
    } else {
      return new ResponseMap(1, "删除失败", delRes);
    }
  }
  /**
   * 获取所有图片
   * @return
   */
  @PostMapping("/getImageList")
  public ResponseMap getImageList() {
    File dir =new File("/data/wwwroot/default/uploadFile");//浏览F盘a文件夹下的所有内容
//    File dir =new File("/Users/xxn/Downloads/");//浏览F盘a文件夹下的所有内容
    File[] files=dir.listFiles();   //列出所有的子文件
    for(File file :files) {
      if(file.isFile()){
        //如果是文件，则输出文件名字
        System.out.println("fileName===" + file.getName());
      }else if(file.isDirectory()) {
        //如果是文件夹，则输出文件夹的名字，并递归遍历该文件夹
        System.out.println(file.getName());
//        listFile(file,"|--"+spance);//递归遍历
      }
    }
    return new ResponseMap(0, "更新成功", files);
  }
  /**
   * 上传
   *
   * @param
   * @return
   */
  @PostMapping("/upload")
  public String upload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest req) {
    // 获取文件名
    // String fileName = uploadFile.getOriginalFilename();

//    String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
    String realPath = "/data/wwwroot/default/uploadFile";
    System.out.println("realPath == " + realPath);

    // String format = sdf.format(new Date());
    File folder = new File(realPath);
    if (!folder.isDirectory()) {
      folder.mkdirs();
    }
    // 生成唯一名称
    String oldName = uploadFile.getOriginalFilename();
    String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());

    try {
      uploadFile.transferTo(new File(folder, newName));
      String filePath = "/uploadFile/" + newName;

      return filePath;
    } catch (IOException e){
      e.printStackTrace();
    }
    return "上传失败";
  }

  /**
   * 删除
   *
   * @param
   * @return
   */
  @PostMapping("/delFile")
  public ResponseMap delFile(@RequestBody FileDto fileDto) {
    String realPath = "/data/wwwroot/default";
    System.out.println("realPath == " + realPath);
    File file = new File(realPath + fileDto.getId());
    if (file.exists()) {
      file.delete();
      System.out.println("文件已经被成功删除");
      return new ResponseMap(0, "更新成功", file);
    }
    return new ResponseMap(1, "更新失败", file);
  }

  /**
   * 搜索
   *
   * @param key
   * @return
   */
  @GetMapping("/search")
  public ResponseMap search(@RequestParam String key) {
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("result", pagesSerice.search(key));
    return new ResponseMap(0, "查询成功", resultMap);
  }

  @PostMapping("/insertComment")
  public ResponseMap insertComment(@RequestBody CommentsDto commentsDto) {
    boolean insertRes = pagesSerice.insertComment(commentsDto);
    pagesSerice.updateCommentNum(commentsDto);
    if (insertRes) {
      return new ResponseMap(0, "提交成功", insertRes);
    } else {
      return new ResponseMap(1, "提交失败", insertRes);
    }
  }

  @GetMapping("/getComment")
  public ResponseMap getComment(@RequestParam int id) {
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("result", pagesSerice.getComment(id));
    return new ResponseMap(0, "查询成功", resultMap);
  }

  @PostMapping("/delComment")
  public ResponseMap delComment(@RequestBody CommentsDto commentsDto) {
    boolean res = pagesSerice.delComment(commentsDto.getId());
    if (res) {
      return new ResponseMap(0, "提交成功", res);
    } else {
      return new ResponseMap(1, "提交失败", res);
    }
  }

  @GetMapping("/getLatestComments")
  public ResponseMap getLatestComments() {
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("result", pagesSerice.getLatestComments());
    return new ResponseMap(0, "查询成功", resultMap);
  }







  /**
   * 下载
   *
   * @param
   * @return
   */
//  @PostMapping("/download")
//  public String download(HttpServletRequest request, HttpServletResponse response) {
//    // 获取文件名
//    try {
//      String fileName = "555.png";
//      String filePath = "./uploadFile/";
//      String path = filePath + fileName;
//      File file = new File(path);
//      if (file.exists()) {
//        response.setContentType("application/force-download");// 设置强制下载不打开
//        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
//        byte[] buffer = new byte[1024];
//        FileInputStream fis = null;
//        BufferedInputStream bis = null;
//        try {
//          fis = new FileInputStream(file);
//          bis = new BufferedInputStream(fis);
//          OutputStream os = response.getOutputStream();
//          int i = bis.read(buffer);
//          while (i != -1) {
//            os.write(buffer, 0, i);
//            i = bis.read(buffer);
//          }
//          return "下载成功";
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return "下载失败";
//  }
}
