package com.spring.springboot.dto;

public class TbkDto {
  private Long id;
  private String title;
  private String pict_url;
  private String zk_final_price;
  private String reserve_price;
  private int volume;
  private String createDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPict_url() {
    return pict_url;
  }

  public void setPict_url(String pict_url) {
    this.pict_url = pict_url;
  }

  public String getZk_final_price() {
    return zk_final_price;
  }

  public void setZk_final_price(String zk_final_price) {
    this.zk_final_price = zk_final_price;
  }

  public String getReserve_price() {
    return reserve_price;
  }

  public void setReserve_price(String reserve_price) {
    this.reserve_price = reserve_price;
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    return "TbkDto{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", pict_url='" + pict_url + '\'' +
            ", zk_final_price='" + zk_final_price + '\'' +
            ", reserve_price='" + reserve_price + '\'' +
            ", volume=" + volume +
            ", createDate='" + createDate + '\'' +
            '}';
  }
}
