package com.koma.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.koma.ResponseResult;
import com.koma.constants.SystemConstants;
import com.koma.pojo.entity.Article;
import com.koma.mapper.ArticleMapper;
import com.koma.service.ArticleService;
import com.koma.service.CategoryService;
import com.koma.utils.BeanCopyUtils;
import com.koma.pojo.vo.PageVo;
import com.koma.pojo.vo.ArticleVo;
import com.koma.pojo.vo.HotArticleVo;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    @Lazy
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<Article>().eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL).orderByDesc(Article::getViewCount);
        Page<Article> page = new Page(1, 10);
        page(page, queryWrapper);
        List<Article> articleList = page.getRecords();
        List<HotArticleVo> hotArticleList = BeanCopyUtils.copyBeanList(articleList, HotArticleVo.class);
        return ResponseResult.okResult(hotArticleList);
    }

    @Override
    public PageVo<ArticleVo> articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId).eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL).orderByDesc(Article::getIsTop);
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<Article> articleList = page.getRecords();
//        for (Article article : articleList) {
//            article.setCategoryName(categoryService.getById(article.getCategoryId()).getName());
//        }

        List<ArticleVo> articleVoList = BeanCopyUtils.copyBeanList(articleList.stream().map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName())).collect(Collectors.toList()), ArticleVo.class);
        return new PageVo<ArticleVo>(articleVoList, page.getTotal());
    }

    @Override
    public ResponseResult articleDetail(Long id) {
        Article article = getById(id);
        ArticleVo articleVo = BeanCopyUtils.copyBean(article, ArticleVo.class);
        articleVo.setCategoryName(categoryService.getById(articleVo.getCategoryId()).getName());
        return ResponseResult.okResult(articleVo);
    }
}
