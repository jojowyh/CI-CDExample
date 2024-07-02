package com.test.service.impl;

import com.test.common.DateUtils;
import com.test.common.Result;
import com.test.mapper.CategoryMapper;
import com.test.mapper.GoodsMapper;
import com.test.mapper.SizeMapper;
import com.test.pojo.DTO.InsertCategoryDTO;
import com.test.pojo.VO.CategoryVO;
import com.test.pojo.entity.Category;
import com.test.pojo.entity.Goods;
import com.test.pojo.entity.Size;
import com.test.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    public  static  final  String ALREADY_EXISTS="类别已存在";


    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private SizeMapper sizeMapper;

    /**
     * 添加分类
     *
     * @param insertCategoryDTO
     * @return
     */
    @Override
    @Transactional
    public Result save(InsertCategoryDTO insertCategoryDTO) {

        Category queryByName = categoryMapper.queryByName(insertCategoryDTO.getCategoryName());
        if (queryByName != null ){
            return Result.error("分类名重复");
        }
        //对分类表进行操作
        Category category = new Category();
        category.setCategoryName(insertCategoryDTO.getCategoryName());
        category.setCategoryGrade(insertCategoryDTO.getCategoryGrade());
        category.setCreateTime(DateUtils.now());
        category.setUpdateTime(DateUtils.now());
        categoryMapper.save(category);
        //主键回显
        Long categoryId = category.getCategoryId();


        List<Size> sizeList=new ArrayList<>();
        List<String> sizeNameList = insertCategoryDTO.getSizeNameList();
        if (sizeNameList.isEmpty()){
            return Result.success("添加分类成功(无规格)");
        }
        sizeNameList.forEach(sizeName->{
            Size size = new Size();
            size.setSizeName(sizeName);
            size.setCategoryId(categoryId);
            sizeList.add(size);
        });

        //对规格表进行操作
        sizeMapper.insert(sizeList);


        return Result.success("添加分类成功(带规格)");
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
        return Result.success("更新成功");
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
        return Result.success("删除成功");
    }

    /**
     * 按照分类id查询分类信息
     * @param categoryId
     * @return
     */
    @Override
    public Result<CategoryVO> queryById(Long categoryId) {
        //分类相关
        Category category = categoryMapper.queryById(categoryId);
        if (category == null){
            return Result.error("输入的值不是分类id");
        }
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(category,categoryVO);

        //规格相关
        List<Size> sizeList = sizeMapper.queryBycategoryId(categoryId);
         categoryVO.setSizeList(sizeList);

        return Result.success(categoryVO);
    }


}
