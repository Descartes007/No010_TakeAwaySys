package com.example.entity;

import java.io.Serializable;

/**
 * 收货地址表
 */
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;
    /** 收货地址 */
    private String address;
    /** 收货人 */
    private String user;
    /** 联系电话 */
    private String phone;
    /** 用户ID */
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}