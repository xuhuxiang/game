package com.example.entity;

import javax.persistence.*;

/**
 * 商家
 */
@Table(name = "business")
public class Business extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /** 用户名 */
    @Column(name = "username")
    private String username;
    /** 密码 */
    @Column(name = "password")
    private String password;
    /** 头像 */
    @Column(name = "avatar")
    private String avatar;
    /** 手机号 */
    @Column(name = "phone")
    private String phone;
    /** 性别 */
    @Column(name = "sex")
    private String sex;
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    @Override
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


}
