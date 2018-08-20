package com.example.myproject.service;

import com.example.myproject.pojo.Address;

import java.util.List;

/**
 * 收货地址接口
 *
 * @author Mettre
 */
public interface DeliveryService {

    int insert(Address address);

    int update(Address address);

    List<Address> findByPage(String userId, Integer limit, Integer offset);

    int deleteAddress(String userId, String id);
}