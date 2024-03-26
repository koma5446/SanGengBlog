package com.koma.controller;

import com.koma.ResponseResult;
import com.koma.service.CategoryService;
import com.koma.pojo.vo.CategoryVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public ResponseResult categoryList() {
        List<CategoryVo> categoryList = categoryService.categoryList();
        return ResponseResult.okResult(categoryList);
    }


}
