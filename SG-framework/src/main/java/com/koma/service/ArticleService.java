package com.koma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.koma.ResponseResult;
import com.koma.pojo.entity.Article;
import com.koma.pojo.vo.PageVo;

import java.util.List;

public interface ArticleService extends IService<Article> {

    ResponseResult<List<Article>> hotArticleList();

    PageVo articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult articleDetail(Long id);
}
