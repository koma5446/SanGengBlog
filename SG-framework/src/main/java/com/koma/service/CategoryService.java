package com.koma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.koma.pojo.entity.Category;
import com.koma.pojo.vo.CategoryVo;

import java.util.List;

public interface CategoryService extends IService<Category> {

    List<CategoryVo> categoryList();

}
