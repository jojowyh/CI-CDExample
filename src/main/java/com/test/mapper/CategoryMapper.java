package com.test.mapper;

import com.test.pojo.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author 86187
* @description 针对表【category(类别表)】的数据库操作Mapper
* @createDate 2024-06-25 14:03:52
* @Entity com.test.pojo.entity.Category
*/
@Mapper
public interface CategoryMapper {

    /**
     * 添加分类
     * @param category
     * @return
     */

    void save(Category category);
    /**
     * 查询分类
     * @return
     */
    @Select("select * from category")
    List<Category> query();

    /**
     * 根据条件查询分类
     * @return
     */

    Category queryByName(String categoryName);

    /**
     * 修改分类
     * @param category
     */
    @Update("update category set category_name = #{categoryName} , update_time = #{updateTime} " +
            "where category_id =#{categoryId}")
    void update(Category category);

    /**
     * 删除分类
     * @param categoryId
     */
    @Delete("delete from category where category_id = #{categoryId}")
    void delete(Long categoryId);

    /**
     *根据id查询分类
     * @param categoryId
     * @return
     */
    @Select("select * from trade_platform.category where category_id = #{categoryId}")
    Category queryById(Long categoryId);
}




