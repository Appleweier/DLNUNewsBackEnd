package edu.dlnu.news.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private Integer id;
    private String pwd;
    private String no;

    public User(Integer id) {
        this.id = id;
    }

    public User(String username, String pwd, String no) {
        this.username = username;
        this.pwd = pwd;
        this.no = no;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", pwd='" + pwd + '\'' +
                ", no='" + no + '\'' +
                '}';
    }

    public User() {
    }

    public User(String username, Integer id, String pwd, String no) {
        this.username = username;
        this.id = id;
        this.pwd = pwd;
        this.no = no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
