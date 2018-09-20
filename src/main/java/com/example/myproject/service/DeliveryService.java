package com.example.myproject.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    Address findDefaultDelivery(String userId);

    List<Address> findByPage(String userId, Integer limit, Integer offset);

    int deleteAddress(String userId, String id);

    Page<Address> selectPageVo(Page<Address> page, String userId);
}