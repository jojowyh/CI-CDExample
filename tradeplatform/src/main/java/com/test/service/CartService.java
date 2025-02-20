package com.test.service;

import com.test.common.Result;
import com.test.pojo.DTO.SaveCartDTO;
import com.test.pojo.DTO.UpdateCartDTO;

public interface CartService {






    Result query(Long userId);

    Result sub(UpdateCartDTO updateCartDTO);

    Result add(UpdateCartDTO updateCartDTO);

    Result save(SaveCartDTO saveCartDTO);
}
