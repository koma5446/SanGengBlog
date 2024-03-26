package com.koma.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.koma.constants.SystemConstants;
import com.koma.pojo.entity.Article;
import com.koma.pojo.entity.Category;
import com.koma.mapper.CategoryMapper;
import com.koma.service.ArticleService;
import com.koma.service.CategoryService;
import com.koma.utils.BeanCopyUtils;
import com.koma.pojo.vo.CategoryVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private ArticleService articleService;

    @Override
    public List<CategoryVo> categoryList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(queryWrapper);
        Set<Long> categoryIdList = articleList.stream().map(Article::getCategoryId).collect(Collectors.toSet());
        List<Category> categoryList = listByIds(categoryIdList).stream().filter(category -> category.getStatus().equals(SystemConstants.CATEGORY_STATUS_NORMAL)).collect(Collectors.toList());
        return BeanCopyUtils.copyBeanList(categoryList, CategoryVo.class);
    }


}
