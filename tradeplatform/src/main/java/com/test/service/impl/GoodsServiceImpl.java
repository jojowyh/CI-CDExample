package com.test.service.impl;

import com.test.common.DateUtils;
import com.test.common.Result;
import com.test.mapper.GoodsMapper;
import com.test.mapper.ImgMapper;
import com.test.mapper.SizeMapper;
import com.test.pojo.DTO.InsertGoodsDTO;
import com.test.pojo.entity.Size;
import com.test.pojo.VO.GoodsBaseVo;
import com.test.pojo.VO.GoodsDetailsVO;
import com.test.pojo.entity.Goods;
import com.test.pojo.entity.Img;
import com.test.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ImgMapper imgMapper;
    @Autowired
    private SizeMapper sizeMapper;



    /**
     * 查询所有商品
     * @return
     */
    @Override
    @Transactional
    public Result<List<GoodsBaseVo>> query() {

        List<Goods> goods=goodsMapper.query();

        List<GoodsBaseVo> goodsBaseVos = new ArrayList<>();



        goods.forEach(good -> {
            GoodsBaseVo goodsBaseVo = new GoodsBaseVo();
            BeanUtils.copyProperties(good,goodsBaseVo);
            Img img = imgMapper.queryByDefault(good.getGoodsId(), 1);
            BeanUtils.copyProperties(img,goodsBaseVo);
            goodsBaseVos.add(goodsBaseVo);
            }
        );


        return Result.success(goodsBaseVos);
    }
    /**
     * 根据id查询对应商品详情
     * @param goodsId
     * @return
     */
    @Override
    @Transactional
    public GoodsDetailsVO queryById(Long goodsId) {


        //查询对应的商品图片
        List <Img> imgList=imgMapper.queryById(goodsId);

        //查询对应的商品数据

        Goods goods=goodsMapper.queryById(goodsId);

        //查询对应商品规格
        List<Size> sizeList = sizeMapper.queryById(goodsId);

        GoodsDetailsVO goodsDetailsVO = new GoodsDetailsVO();

        BeanUtils.copyProperties(goods,goodsDetailsVO);
        goodsDetailsVO.setImgs(imgList);
        goodsDetailsVO.setSizes(sizeList);

        return goodsDetailsVO;
    }
    /**
     * 添加商品
     * @param insertGoodsDTO
     * @return
     */
    @Override
    @Transactional
    public void insertBatch(InsertGoodsDTO insertGoodsDTO) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(insertGoodsDTO,goods);
        goods.setCreateTime(DateUtils.now());
        goods.setUpdateTime(DateUtils.now());

        //向商品表插入一条数据
        goodsMapper.insert(goods);
        Long goodsId = goods.getGoodsId();

        //向图片表插入多条数据
        List<Img> imgList = insertGoodsDTO.getImgList();
        if (imgList !=null && imgList.size()>0){
            imgList.forEach(img -> {
                img.setGoodsId(goodsId);
            });
        }

        imgMapper.insertBatch(imgList);



        //向规格表插入多条数据
        List<Size> sizeList = insertGoodsDTO.getSizeList();
        if (sizeList !=null && sizeList.size()>0){
            sizeList.forEach(size -> {
                size.setGoodsId(goodsId);
            });
        }


        sizeMapper.insertBatch(sizeList);
    }


    @Override
    @Transactional
    public Result update(InsertGoodsDTO insertGoodsDTO) {
        //修改对应商品表
        Goods goods=new Goods();
        BeanUtils.copyProperties(insertGoodsDTO,goods);
        goods.setUpdateTime(DateUtils.now());

        goodsMapper.update(goods);


        //修改对应图片表
        Long goodsId = goods.getGoodsId();
        imgMapper.delete(goodsId);

        List<Img> imgList = insertGoodsDTO.getImgList();
        if (imgList !=null && imgList.size()>0){
            imgList.forEach(img -> {
                img.setGoodsId(goodsId);
            });
        }

        imgMapper.insertBatch(imgList);


        //修改对应的规格表
        sizeMapper.delete(goodsId);

        List<Size> sizeList = insertGoodsDTO.getSizeList();
        if (sizeList !=null && sizeList.size()>0){
            sizeList.forEach(size -> {
                size.setGoodsId(goodsId);
            });
        }

        sizeMapper.insertBatch(sizeList);


        return Result.success();

    }
    /**
     * 删除商品
     * @param goodsId
     * @return
     */
    @Override
    @Transactional
    public Result delete(Long goodsId) {

        //删除对应规格表
        sizeMapper.delete(goodsId);
        //删除对应图片表
        imgMapper.delete(goodsId);
        //删除对应商品表
        goodsMapper.delete(goodsId);

        return Result.success();
    }
}
