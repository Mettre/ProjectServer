package com.example.myproject.mapper;


import com.example.myproject.pojo.Address;

/**
 * 收货地址数据处理层
 *
 * @author Mettre
 */
public interface DeliveryMapper {

    int insert(Address address);

}