package com.koma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.koma.ResponseResult;
import com.koma.pojo.entity.Link;

public interface LinkService extends IService<Link> {
    ResponseResult allLink();
}
