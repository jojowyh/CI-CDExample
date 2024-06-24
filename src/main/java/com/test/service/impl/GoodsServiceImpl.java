package com.test.service.impl;

import com.test.mapper.GoodsMapper;
import com.test.pojo.Goods;
import com.test.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public List<Goods> query() {
        List<Goods> goods=goodsMapper.query();
        return goods;
    }
}
