package com.test.controller;


import com.test.pojo.Goods;
import com.test.pojo.common.Result;
import com.test.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/hello")
public class Hello {
    @Autowired
    private GoodsService goodsService;

    @GetMapping
    public Result<List<Goods>> hello(){
        List<Goods> goods=goodsService.query();
        System.out.println("goods = " + goods);
        return Result.success(goods);
    }
}
