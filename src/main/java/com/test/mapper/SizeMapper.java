package com.test.mapper;

import com.test.pojo.entity.Size;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 86187
* @description 针对表【size(规格表)】的数据库操作Mapper
* @createDate 2024-06-25 10:10:20
* @Entity com.test.pojo.Size
*/
@Mapper
public interface SizeMapper {

    /**
     * 根据规格id查询规格
     * @param sizeId
     * @return
     */
    @Select("select * from size where size_id = #{sizeId}")
    Size queryById(Long sizeId);

    /**
     * 根据规格名和分类id查询规格id
     * @param size
     * @return
     */
    @Select("select * from size where size_name = #{sizeName} and category_id = #{categoryId}")
    Size query(Size size);

    @Select("select * from size where category_id =#{categoryId}" )
    List<Size> queryBycategoryId(Long categoryId);
}




