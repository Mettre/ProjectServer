package com.example.myproject.controller;

import com.example.myproject.service.AddressService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mettre
 */
@Slf4j
@RestController
@Api(description = "收货地址管理接口")
@RequestMapping("/xboot/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

}
