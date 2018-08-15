package com.example.myproject.po;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import javax.persistence.Table;

/**
 * 收货地址
 */
@Data
@Table(name = "t_address")
@TableName("t_address")
public class Address {

    private long id;
    private String name;
    private String phone;
    private String address;
    private boolean defaults;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setDefaults(boolean defaults) {
        this.defaults = defaults;
    }

    public boolean getDefaults() {
        return defaults;
    }
}
