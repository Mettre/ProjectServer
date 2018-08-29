package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.DeliveryMapper;
import com.example.myproject.pojo.Address;
import com.example.myproject.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 收货地址接口实现
 *
 * @author Mettre
 */
@Slf4j
@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryMapper addressMapper;

    @Override
    public int insert(Address address) {
        return addressMapper.insert(address);
    }

    @Override
    public int update(Address address) {
        return addressMapper.update(address);
    }

    @Override
    public Address findDefaultDelivery(String userId) {
        return addressMapper.findDefaultDelivery(userId);
    }

    @Override
    public List<Address> findByPage(String userId, Integer limit, Integer offset) {
        return addressMapper.findByPage(userId, limit, offset);
    }

    @Override
    public int deleteAddress(String userId, String id) {
        return addressMapper.deleteAddress(userId, id);
    }

}