package com.test.controller;

import com.test.common.Result;
import com.test.mapper.GoodsMapper;
import com.test.pojo.entity.Category;
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


    /**
     * 添加分类
     * @param category
     * @return
     */
    @ApiOperation("新增分类")
    @PostMapping ("/save")
    public Result save(@RequestBody Category category){
        log.info("新增分类:{}",category);

        return categoryService.save(category);
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
}
