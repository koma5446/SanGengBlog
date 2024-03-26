package com.koma.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Data
@Validated
public class ArticleVo {

    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;

    private String categoryName;
    //缩略图
    private String thumbnail;
    //访问量
    private Long viewCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //是否允许评论 1是，0否
    private String isComment;

    private Long categoryId;

    private String content;
}
