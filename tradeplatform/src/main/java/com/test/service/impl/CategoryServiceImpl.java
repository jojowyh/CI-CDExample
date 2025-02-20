package com.test.service.impl;

import com.test.common.DateUtils;
import com.test.common.Result;
import com.test.mapper.CategoryMapper;
import com.test.mapper.GoodsMapper;
import com.test.pojo.entity.Category;
import com.test.pojo.entity.Goods;
import com.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    public  static  final  String ALREADY_EXISTS="类别已存在";


    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 添加分类
     *
     * @param category
     * @return
     */
    @Override
    @Transactional
    public Result save(Category category) {

        Category queryByName = categoryMapper.queryByName(category.getCategoryName());
        if (queryByName != null ){
            return Result.error("分类名重复");
        }

        category.setCreateTime(DateUtils.now());
        category.setUpdateTime(DateUtils.now());
        categoryMapper.save(category);
        return Result.success();
    }
    /**
     * 查询分类
     * @return
     */
    @Override
    public List<Category> query() {
        List<Category>categoryList=categoryMapper.query();
        return categoryList;
    }

    /**
     * 修改分类
     * @param category
     * @return
     */
    @Override
    public Result update(Category category) {
        category.setUpdateTime(DateUtils.now());
        categoryMapper.update(category);
        return Result.success();
    }
    /**
     * 删除分类
     * @param categoryId
     * @return
     */
    @Override
    public Result delete(Long categoryId) {
        Goods good = new Goods();
        good.setCategoryId(categoryId);
        List<Goods> goods = goodsMapper.queryByCategoryId(categoryId);
        if (!goods.isEmpty()){
            return Result.error("分类下存在商品");
        }
        categoryMapper.delete(categoryId);
        return Result.success();
    }


}
