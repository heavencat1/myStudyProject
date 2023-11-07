package com.atguigu.wtt.firstmodel.mapper;

import com.atguigu.wtt.firstmodel.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
* @author jujian
* @description 针对表【news_user】的数据库操作Mapper
* @createDate 2023-10-09 21:12:19
* @Entity com.atguigu.wtt.firstmodel.pojo.User
*/
@Component
public interface UserMapper extends BaseMapper<User> {

}




