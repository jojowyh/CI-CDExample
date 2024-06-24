package com.test.controller;


import com.test.common.Result;
import com.test.pojo.Goods;
import com.test.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询所有商品
     * @return
     */
    @GetMapping
    public Result<List<Goods>> query(){
        List<com.test.pojo.Goods> goods=goodsService.query();
        System.out.println("goods = " + goods);
        return Result.success(goods);
    }
}
