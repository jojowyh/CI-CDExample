package com.test.controller;

import com.test.common.Result;
import com.test.pojo.DTO.AddressDTO;
import com.test.pojo.entity.Address;
import com.test.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags ="地址管理")
@RequestMapping("/address")
public class AddressController {


    @Autowired
    private AddressService addressService;

    /**
     * 查询当前用户所有地址
     * @return
     */
    @GetMapping("queryAll/{userId}")
    @ApiOperation("查询当前用户所有地址")
    public Result queryAll(@PathVariable Long userId){
        log.info("查询当前用户所有地址,用户id:{}",userId);
        return addressService.queryAll(userId);
    }

    /**
     * 添加用户地址
     * @param addressDTO
     * @return
     */
    @PostMapping("save")
    @ApiOperation("添加用户地址")
    public Result save(@RequestBody AddressDTO addressDTO){
        log.info("添加用户地址,请求参数:{}",addressDTO);
        return addressService.save(addressDTO);
    }


}
