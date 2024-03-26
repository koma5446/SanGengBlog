package com.koma.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.koma.ResponseResult;
import com.koma.constants.SystemConstants;
import com.koma.mapper.LinkMapper;
import com.koma.pojo.entity.Link;
import com.koma.pojo.vo.LinkVo;
import com.koma.service.LinkService;
import com.koma.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult allLink() {
        List<Link> linkList = list(new LambdaQueryWrapper<Link>().eq(Link::getStatus, SystemConstants.LINK_STATUS_APPROVED));
        List<LinkVo> linkVoList = BeanCopyUtils.copyBeanList(linkList, LinkVo.class);
        return ResponseResult.okResult(linkVoList);
    }
}
