package com.example.myproject.po;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * 用户
 */
@Data
@Table(name = "t_user")
@TableName("t_user")
public class Users {

    private String userId;
    private String name;
    private int phone;
    private int age;
    private int sex;
    private String avatar;
    private Date createTime;
    private Date lastLogin;
    private int type;
}
