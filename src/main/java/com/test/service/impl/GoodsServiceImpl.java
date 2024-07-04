package com.test.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.test.common.DateUtils;
import com.test.common.Result;
import com.test.mapper.CategoryMapper;
import com.test.mapper.GoodsMapper;
import com.test.mapper.ImgMapper;
import com.test.mapper.SizeMapper;
import com.test.pojo.DTO.GoodPageDTO;
import com.test.pojo.DTO.InsertGoodsDTO;
import com.test.pojo.VO.GoodsPageVO;
import com.test.pojo.entity.Category;
import com.test.pojo.entity.Size;
import com.test.pojo.VO.GoodsBaseVo;
import com.test.pojo.VO.GoodsDetailsVO;
import com.test.pojo.entity.Goods;
import com.test.pojo.entity.Img;
import com.test.pojo.result.PageResult;
import com.test.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CategoryMapper categoryMapper;



    /**
     * 查询所有商品
     * @return
     */
    @Override
    @Transactional
    public PageResult query(GoodPageDTO goodPageDTO) {

        PageHelper.startPage(goodPageDTO.getPage(), goodPageDTO.getPageSize());


        Page<GoodsPageVO> page =goodsMapper.pageQuery(goodPageDTO);
        System.out.println(page.getResult());
        return new PageResult(page.getTotal(),page.getResult());
     /*   List<Goods> goods=goodsMapper.query();

        List<GoodsBaseVo> goodsBaseVos = new ArrayList<>();
        if (goods.isEmpty()){
            return Result.error("查询商品为空");
        }



        goods.forEach(good -> {
            GoodsBaseVo goodsBaseVo = new GoodsBaseVo();
            BeanUtils.copyProperties(good,goodsBaseVo);
            //根据类别id返回类别名
            Category category =categoryMapper.queryById(good.getCategoryId());
            goodsBaseVo.setCategoryName(category.getCategoryName());

            //根据规格id返回规格名
            Size size = sizeMapper.queryById(good.getSizeId());
            goodsBaseVo.setSizeName(size.getSizeName());

            //根据商品id返回路径
            Img img = imgMapper.queryByDefault(good.getGoodsId(), 1);
            BeanUtils.copyProperties(img,goodsBaseVo);

            goodsBaseVos.add(goodsBaseVo);
            }
        );*/



        //return Result.success(goodsBaseVos);
    }
    /**
     * 根据id查询对应商品详情
     *
     * @param goodsId
     * @return
     */
    @Override
    @Transactional
    public Result<GoodsDetailsVO> queryById(Long goodsId) {

        //查询对应的商品数据
        Goods goods=goodsMapper.queryById(goodsId);


        //查询对应的商品图片
        List <Img> imgList=imgMapper.queryById(goodsId);

        //根据规格id返回规格名
        Size size = sizeMapper.queryById(goods.getSizeId());


        GoodsDetailsVO goodsDetailsVO = new GoodsDetailsVO();
        if (goods == null){
            return Result.error("查无此商品");
        }

        //根据类别id返回类别名
        Category category =categoryMapper.queryById(goods.getCategoryId());


        BeanUtils.copyProperties(goods,goodsDetailsVO);

        goodsDetailsVO.setCategoryName(category.getCategoryName());
        goodsDetailsVO.setImgs(imgList);
        goodsDetailsVO.setSizeName(size.getSizeName());

        return Result.success(goodsDetailsVO);
    }
    /**
     * 添加商品
     *
     * @param insertGoodsDTO
     * @return
     */
    @Override
    @Transactional
    public Result insertBatch(InsertGoodsDTO insertGoodsDTO) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(insertGoodsDTO,goods);
        //根据分类名查询分类id
        Category category = categoryMapper.queryByName(insertGoodsDTO.getCategoryName());
        if (insertGoodsDTO.getCategoryName() != null && category != null){
            goods.setCategoryId(category.getCategoryId());
        }

        //根据商品规格名和类别id查询商品规格id
        if (insertGoodsDTO.getCategoryName() !=null && category !=null){
            Size size = new Size();
            size.setCategoryId(category.getCategoryId());
            size.setSizeName(insertGoodsDTO.getSizeName());
            size=sizeMapper.query(size);
            goods.setSizeId(size.getSizeId());
        }

        goods.setCreateTime(DateUtils.now());
        goods.setUpdateTime(DateUtils.now());

        //向商品表插入一条数据
        goodsMapper.insert(goods);
        Long goodsId = goods.getGoodsId();

        //向图片表插入多条数据
        List<Img> imgList = insertGoodsDTO.getImgList();

        if (!imgList.isEmpty()){
            imgList.forEach(img -> {
                img.setIsDefault(0);
                img.setGoodsId(goodsId);
            });
        }
        imgList.get(0).setIsDefault(1);
        imgMapper.insertBatch(imgList);


        return Result.success("插入成功");
    }


    @Override
    @Transactional
    public Result update(InsertGoodsDTO insertGoodsDTO) {
        //修改对应商品表
        Goods goods=new Goods();
        BeanUtils.copyProperties(insertGoodsDTO,goods);
        Category category = categoryMapper.queryByName(insertGoodsDTO.getCategoryName());

        //根据类别名字查询类别id
        if (insertGoodsDTO.getCategoryName() != null && category != null){
            goods.setCategoryId(category.getCategoryId());
        }

        //根据商品规格名和类别id查询商品规格id
        if (insertGoodsDTO.getCategoryName() !=null && category !=null){
            Size size = new Size();
            size.setCategoryId(category.getCategoryId());
            size.setSizeName(insertGoodsDTO.getSizeName());
            size=sizeMapper.query(size);
            goods.setSizeId(size.getSizeId());
        }

        goods.setUpdateTime(DateUtils.now());

        goodsMapper.update(goods);

        //修改对应图片表
        Long goodsId = goods.getGoodsId();
        imgMapper.delete(goodsId);

        List<Img> imgList = insertGoodsDTO.getImgList();
        if (imgList !=null && imgList.size()>0){
            imgList.forEach(img -> {
                img.setGoodsId(goodsId);
                img.setIsDefault(0);
            });
        }
        imgList.get(0).setIsDefault(1);
        imgMapper.insertBatch(imgList);

        return Result.success("修改成功");
    }
    /**
     * 删除商品
     * @param goodsId
     * @return
     */
    @Override
    @Transactional
    public Result delete(Long goodsId) {


        //删除对应图片表
        imgMapper.delete(goodsId);
        //删除对应商品表
        goodsMapper.delete(goodsId);

        return Result.success();
    }
}
