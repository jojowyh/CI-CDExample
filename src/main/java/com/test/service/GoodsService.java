package com.test.service;

import com.test.common.Result;
import com.test.pojo.DTO.GoodPageDTO;
import com.test.pojo.DTO.InsertGoodsDTO;
import com.test.pojo.VO.GoodsBaseVo;
import com.test.pojo.VO.GoodsDetailsVO;
import com.test.pojo.result.PageResult;

import java.util.List;

public interface GoodsService {
    /**
     * 查询所有商品
     * @return
     */
    PageResult query(GoodPageDTO goodPageDTO);

    /**
     * 根据id查询对应商品详情
     *
     * @param goodsId
     * @return
     */
    Result<GoodsDetailsVO> queryById(Long goodsId);

    /**
     * 添加商品
     *
     * @param insertGoodsDTO
     * @return
     */
    Result insertBatch(InsertGoodsDTO insertGoodsDTO);

    /**
     * 修改商品
     * @param insertGoodsDTO
     * @return
     */
    Result update(InsertGoodsDTO insertGoodsDTO);
    /**
     * 删除商品
     * @param goodsId
     * @return
     */
    Result delete(Long goodsId);
}
