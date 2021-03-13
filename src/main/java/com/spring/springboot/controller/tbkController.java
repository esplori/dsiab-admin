package com.spring.springboot.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.springboot.common.ResponseMap;
import com.spring.springboot.dto.TbkDto;
import com.spring.springboot.service.TbkService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkItemInfoGetRequest;
import com.taobao.api.request.TbkShopGetRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import com.taobao.api.response.TbkItemInfoGetResponse;
import com.taobao.api.response.TbkShopGetResponse;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tbk")
public class tbkController {
  @Autowired
  TbkService tbkService;

  @GetMapping("/getShopList")
  public ResponseMap getShopList() throws ApiException, JsonProcessingException {
    String serverUrl = "http://gw.api.taobao.com/router/rest";
    String appKey = "31260360"; // 可替换为您的应用的AppKey
    String appSecret = "594d0caea9eae3173f9f25ef87bee08e"; // 可替换为您的应用的AppSecret
    TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
    TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
//    req.setStartDsr(10L);
//    req.setPageSize(20L);
//    req.setPageNo(1L);
//    req.setPlatform(1L);
//    req.setEndTkRate(1234L);
//    req.setStartTkRate(1234L);
//    req.setEndPrice(10L);
//    req.setStartPrice(10L);
//    req.setIsOverseas(false);
//    req.setIsTmall(false);
//    req.setSort("tk_rate_des");
//    req.setItemloc("杭州");
//    req.setCat("16,18");
    req.setQ("女装");
//    req.setMaterialId(2836L);
//    req.setHasCoupon(false);
//    req.setIp("13.2.33.4");
    req.setAdzoneId(110370750126L);
//    req.setNeedFreeShipment(true);
//    req.setNeedPrepay(true);
//    req.setIncludePayRate30(true);
//    req.setIncludeGoodRate(true);
//    req.setIncludeRfdRate(true);
//    req.setNpxLevel(2L);
//    req.setEndKaTkRate(1234L);
//    req.setStartKaTkRate(1234L);
//    req.setDeviceEncrypt("MD5");
//    req.setDeviceValue("xxx");
//    req.setDeviceType("IMEI");
//    req.setLockRateEndTime(1567440000000L);
//    req.setLockRateStartTime(1567440000000L);
//    req.setLongitude("121.473701");
//    req.setLatitude("31.230370");
//    req.setCityCode("310000");
//    req.setSellerIds("1,2,3,4");
//    req.setSpecialId("2323");
//    req.setRelationId("3243");
//    req.setPageResultKey("abcdef");
//    req.setUcrowdId(1L);
    List<TbkDgMaterialOptionalRequest.Ucrowdrankitems> list2 = new ArrayList<TbkDgMaterialOptionalRequest.Ucrowdrankitems>();
    TbkDgMaterialOptionalRequest.Ucrowdrankitems obj3 = new TbkDgMaterialOptionalRequest.Ucrowdrankitems();
    list2.add(obj3);
    obj3.setCommirate(1234L);
    obj3.setPrice("10.12");
    obj3.setItemId("542808901898");
    req.setUcrowdRankItems(list2);

    TbkDgMaterialOptionalResponse rsp = client.execute(req);
    Map<String, Object> resultMap = new HashMap<>();
    ObjectMapper objectMapper = new ObjectMapper();
    // 转成Json
    Map map = objectMapper.readValue(rsp.getBody(), Map.class);
    Map tbk_dg_material_optional_response = (Map) map.get("tbk_dg_material_optional_response");
    Map result_list = ((Map) tbk_dg_material_optional_response.get("result_list"));
    List map_data = ((List) result_list.get("map_data"));
//    System.out.println("map_data="+map_data);
    resultMap.put("result",map_data);
    List dataList = new ArrayList();
    for (int i =0; i<map_data.size();i++){

      Map map1 = (Map) map_data.get(i);
      TbkDto tbkDto = new TbkDto();
      System.out.println("map1="+map1);
      tbkDto.setId((Long) map1.get("item_id"));
      tbkDto.setPict_url((String) map1.get("pict_url"));
      tbkDto.setUrl((String) map1.get("url"));
      tbkDto.setTitle((String) map1.get("title"));
      tbkDto.setVolume((int) map1.get("volume"));
      tbkDto.setReserve_price((String) map1.get("reserve_price"));
      tbkDto.setZk_final_price((String) map1.get("zk_final_price"));

      tbkService.addTbkList(tbkDto);
      System.out.println("tbkDto="+tbkDto);
    }

    return new ResponseMap(0, "查询成功", resultMap);
  }

//  @GetMapping("/getList")
//  public ResponseMap getList()  {
//    Map resultMap= new HashMap();
//    List<TbkDto> tbkDtos = tbkService.getList();
//    resultMap.put("result", tbkDtos);
//    resultMap.put("total", tbkService.getListTotal());
//    return new ResponseMap(0, "查询成功", resultMap);
//  }
  @GetMapping("/getList/{page}")
  public ResponseMap getListPage(@PathVariable("page") String page)  {
    Map resultMap= new HashMap();
    List<TbkDto> tbkDtos = tbkService.getList(Integer.parseInt(page));
    resultMap.put("result", tbkDtos);
    resultMap.put("total", tbkService.getListTotal());
    return new ResponseMap(0, "查询成功", resultMap);
  }
  /**
   * 查询详情
   *
   * @param id
   * @return
   */
  @GetMapping("/getDetail/{id}")
  public ResponseMap getOne(@PathVariable("id") String id) {
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("result",tbkService.getDetail(id));
    return new ResponseMap(0, "查询成功", resultMap);
  }

}