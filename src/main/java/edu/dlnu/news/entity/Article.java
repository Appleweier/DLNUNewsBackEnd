package edu.dlnu.news.entity;

import java.nio.charset.StandardCharsets;



public class Article {
    private Integer id;
    private String title;
    private String Content;
    private byte[] img;
    private String imgs;
    private String date;
    private String adminid;

    public Article() {
    }

    public Article(Integer id, String title, String content, byte[] img, String date, String adminid) {
        this.id = id;
        this.title = title;
        Content = content;
        this.img = img;
        this.date = date;
        this.adminid = adminid;
        this.imgs = new String(img, StandardCharsets.UTF_8);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }
}
