package com.atguigu.wtt.firstmodel.service;

import com.atguigu.wtt.firstmodel.dto.NewsPageDTO;
import com.atguigu.wtt.firstmodel.pojo.Type;
import com.atguigu.wtt.firstmodel.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author jujian
* @description 针对表【news_type】的数据库操作Service
* @createDate 2023-10-09 21:12:19
*/
public interface TypeService extends IService<Type> {

    /**
     * 查询首页所有分类
     * @return
     */
    Result findAllTypes();


}
