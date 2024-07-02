package com.test.service;

import com.test.common.Result;
import com.test.pojo.entity.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 添加分类
     *
     * @param category
     * @return
     */
    Result save(Category category);


    /**
     * 查询分类
     * @return
     */
    List<Category> query();

    /**
     * 修改分类
     * @param category
     * @return
     */
    Result update(Category category);

    /**
     * 删除分类
     * @param categoryId
     * @return
     */
    Result delete(Long categoryId);

    /**
     * 按照分类id查询分类信息
     * @param categoryId
     * @return
     */
    Result queryById(Long categoryId);
}
