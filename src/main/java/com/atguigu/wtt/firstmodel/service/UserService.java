package com.atguigu.wtt.firstmodel.service;

import com.atguigu.wtt.firstmodel.pojo.User;
import com.atguigu.wtt.firstmodel.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.awt.print.Book;

/**
* @author jujian
* @description 针对表【news_user】的数据库操作Service
* @createDate 2023-10-09 21:12:19
*/
public interface UserService extends IService<User> {


    /**
     * 登录
     * @param userName
     * @param passWord
     * @return
     */
    Result login(String userName, String passWord);


    /**
     * 是否存在此用户
     * @param userName
     * @return
     */
    Boolean isExist(String userName);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    Result getUserInfo(String token);


    /**
     * 用户注册
     * @param user
     * @return
     */
    Result regist(User user);


    /**
     * 校验用户名是否被占用
     * @param userName
     * @return
     */
    Result checkUserName(String userName);


}
