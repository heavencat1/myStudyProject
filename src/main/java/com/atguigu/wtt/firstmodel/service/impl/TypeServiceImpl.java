package com.atguigu.wtt.firstmodel.service.impl;

import com.atguigu.wtt.firstmodel.dto.NewsPageDTO;
import com.atguigu.wtt.firstmodel.pojo.Headline;
import com.atguigu.wtt.firstmodel.utils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.wtt.firstmodel.pojo.Type;
import com.atguigu.wtt.firstmodel.service.TypeService;
import com.atguigu.wtt.firstmodel.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author jujian
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2023-10-09 21:12:19
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService{

    @Autowired
    private TypeMapper typeMapper;


    @Override
    public Result findAllTypes() {
        return null;
    }


}




