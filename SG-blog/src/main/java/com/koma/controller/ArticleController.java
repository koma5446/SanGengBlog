package com.koma.controller;

import com.koma.ResponseResult;
import com.koma.pojo.entity.Article;
import com.koma.service.ArticleService;
import com.koma.pojo.vo.PageVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult<List<Article>> hotArticleList() {

        return articleService.hotArticleList();
    }

    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize, @RequestParam(required = false)  Long categoryId) {
        PageVo articlePageVo = articleService.articleList(pageNum, pageSize, categoryId);
        return ResponseResult.okResult(articlePageVo);
    }


    @GetMapping("/{id}")
    public ResponseResult articleDetail(@PathVariable Long id){
        return  articleService.articleDetail(id);
    }
}
