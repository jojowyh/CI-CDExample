package com.test.controller;

import com.test.common.Result;
import com.test.mapper.CategoryMapper;
import com.test.mapper.GoodsMapper;
import com.test.mapper.SizeMapper;
import com.test.pojo.DTO.InsertCategoryDTO;
import com.test.pojo.VO.CategoryVO;
import com.test.pojo.entity.Category;
import com.test.pojo.entity.Size;
import com.test.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
@Api(tags = "分类管理")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SizeMapper sizeMapper;

    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 添加分类
     * @param insertCategoryDTO
     * @return
     */
    @ApiOperation("新增分类")
    @PostMapping ("/save")
    public Result save(@RequestBody InsertCategoryDTO insertCategoryDTO){
        log.info("新增分类:{}",insertCategoryDTO);

        return categoryService.save(insertCategoryDTO);
    }

    /**
     * 查询所有分类
     * @return
     */
    @GetMapping
    @ApiOperation("查询所有分类")
    public Result<List<Category>> query(){
        log.info("查询分类");

        List <Category> categoryList=categoryService.query();
        return Result.success(categoryList);
    }

    /**
     * 修改分类
     * @param category
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改分类")
    public Result update(@RequestBody Category category){
        log.info("修改分类");
         return categoryService.update(category);
    }

    /**
     * 删除分类
     * @param category_id
     * @return
     */
    @DeleteMapping("/{category_id}")
    @ApiOperation("删除分类")
    public Result delete(@PathVariable Long category_id){
        log.info("删除分类");
        return categoryService.delete(category_id);
    }


    /**
     * 按照分类id查询分类信息
     * @param category_id
     * @return
     */
    @PutMapping("/query/{category_id}")
    @ApiOperation("按照分类id查询分类信息")
    public Result<CategoryVO> queryById(@PathVariable Long category_id){
        log.info("按照分类id查询分类信息,category_id:{}",category_id);
        return categoryService.queryById(category_id);
    }

    @PostMapping("querySize")
    @ApiOperation("查询分类下的规格")
    public Result<List<Size>> querySize(@RequestParam String categoryName){
        log.info("查询分类下的规格,category_name:{}",categoryName);

        Category category = categoryMapper.queryByName(categoryName);
        if (category == null){
        return Result.error("无此规格");
        }
        List<Size>sizeList=sizeMapper.queryBycategoryId(category.getCategoryId());

        return Result.success(sizeList);
    }
}
