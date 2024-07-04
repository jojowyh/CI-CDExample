package com.test.controller;


import com.test.common.Result;

import com.test.pojo.DTO.GoodPageDTO;
import com.test.pojo.DTO.InsertGoodsDTO;
import com.test.pojo.VO.GoodsBaseVo;
import com.test.pojo.VO.GoodsDetailsVO;
import com.test.pojo.entity.Goods;
import com.test.pojo.result.PageResult;
import com.test.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
@Slf4j
@Api(tags = "商品管理")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询所有商品
     * @return
     */
    @PostMapping("/query")
    @ApiOperation("分页查询商品")
    public PageResult query(@RequestBody GoodPageDTO goodPageDTO){
        log.info("分页查询商品:{}",goodPageDTO);
        return goodsService.query(goodPageDTO);
    }

    /**
     * 根据id查询对应商品详情
     * @param goodsId
     * @return
     */
    @GetMapping ("/{id}")
    @ApiOperation("根据id查询对应商品详情")
    public Result<GoodsDetailsVO> queryById(@PathVariable(value = "id") Long goodsId){
        log.info("根据id查询对应商品详情,id:{}",goodsId);
        return goodsService.queryById(goodsId);
    }

    /**
     * 添加商品
     * @param insertGoodsDTO
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation("添加商品")
    public Result insertBatch(@RequestBody InsertGoodsDTO insertGoodsDTO){
        log.info("添加商品:{}",insertGoodsDTO);
        return goodsService.insertBatch(insertGoodsDTO);

    }

    /**
     * 修改商品
     * @param InsertGoodsDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改商品")
    public Result update(@RequestBody InsertGoodsDTO InsertGoodsDTO){
        log.info("修改商品:{}",InsertGoodsDTO);
        return goodsService.update(InsertGoodsDTO);
    }

    /**
     * 删除商品
     * @param goodsId
     * @return
     */
    @DeleteMapping("/{goodsId}")
    @ApiOperation("删除商品")
    public Result delete(@PathVariable Long goodsId){
        log.info("删除商品:{}",goodsId);
        return goodsService.delete(goodsId);
    }



}
