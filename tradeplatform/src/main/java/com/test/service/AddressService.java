package com.test.service;

import com.test.common.Result;
import com.test.pojo.DTO.AddressDTO;
import com.test.pojo.entity.Address;

public interface AddressService {
    Result queryAll(Long userId);

    Result save(AddressDTO addressDTO);
}
