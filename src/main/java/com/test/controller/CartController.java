package com.test.controller;

import com.test.common.Result;
import com.test.pojo.DTO.SaveCartDTO;
import com.test.pojo.DTO.UpdateCartDTO;
import com.test.pojo.entity.Cart;
import com.test.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags ="购物车管理")
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * 添加商品到购物车
     * @param saveCartDTO
     * @return
     */
    @PostMapping("save")
    @ApiOperation("添加商品到购物车")
    public Result save(@RequestBody SaveCartDTO saveCartDTO){
        log.info("添加商品到购物车，参数为:{}",saveCartDTO);
        return cartService.save(saveCartDTO);

    }

    /**
     * 购物车中商品数加一
     * @return
     */
    @PutMapping ("/add")
    @ApiOperation("购物车中商品数加一")
    public Result addNumber(@RequestBody UpdateCartDTO updateCartDTO){
        log.info("购物车中商品数减一,{}",updateCartDTO);
        return cartService.add(updateCartDTO);
    }


    /**
     * 购物车中商品数减一
     * @return
     */
    @PutMapping("/sub")
    @ApiOperation("购物车中商品数减一")
    public Result subNumber(@RequestBody UpdateCartDTO updateCartDTO){
        log.info("购物车中商品数减一,{}",updateCartDTO);
        return cartService.sub(updateCartDTO);
    }

    /**
     * 根据用户id查询用户的购物车
     * @param userId
     * @return
     */
    @GetMapping("query/{userId}")
    @ApiOperation("根据用户id查询用户的购物车")
    public Result query(@PathVariable Long userId){
        log.info("根据用户id查询用户的购物车,用户id{}",userId);
        return cartService.query(userId);
    }

    @DeleteMapping("/deleteAll/{userId}")
    @ApiOperation("清空购物车")
    public Result deleteAll(){
        log.info("根据用户id清空购物车");
        return null;
    }

}
