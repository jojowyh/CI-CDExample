package com.test.service.impl;

import com.test.common.Result;
import com.test.mapper.AddressMapper;
import com.test.pojo.DTO.AddressDTO;
import com.test.pojo.entity.Address;
import com.test.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{


    /**
     * 查询当前用户所有地址
     * @return
     */
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Result queryAll(Long userId) {
        Address address = new Address();
        address.setUserId(userId);

        List <Address> addressesList=addressMapper.queryByCondition(address);
        return Result.success(addressesList);

    }

    /**
     * 添加用户地址
     * @param addressDTO
     * @return
     */
    @Override
    @Transactional
    public Result save( AddressDTO addressDTO) {
        Address address =new Address();
        address.setUserId(addressDTO.getUserId());
        List<Address> addressesList = addressMapper.queryByCondition(address);
        BeanUtils.copyProperties(addressDTO,address);
        if (addressesList == null || addressesList.isEmpty()){
            address.setIsDefaul(1);
            addressMapper.save(address);
            return Result.success("添加成功,设为默认地址");
        }
        address.setIsDefaul(0);
        addressMapper.save(address);
        return Result.success("添加成功,不为默认地址");
    }


}
