package com.atguigu.wtt.firstmodel.service.impl;

import com.atguigu.wtt.firstmodel.enums.ResultCodeEnum;
import com.atguigu.wtt.firstmodel.utils.JwtHelper;
import com.atguigu.wtt.firstmodel.utils.MD5Util;
import com.atguigu.wtt.firstmodel.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.wtt.firstmodel.pojo.User;
import com.atguigu.wtt.firstmodel.service.UserService;
import com.atguigu.wtt.firstmodel.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
* @author jujian
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2023-10-09 21:12:19
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;


    @Override
    public Result login(String userName, String passWord) {
        //根据账户查询出用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,userName);
        queryWrapper.eq(User::getUserPwd, MD5Util.encrypt(passWord));
        User user = userMapper.selectOne(queryWrapper);

        //登录成功返回
        if (user != null){
            String token = jwtHelper.createToken(Long.valueOf(user.getUid()));
            HashMap<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", token);
            return  Result.build(tokenMap,ResultCodeEnum.SUCCESS);
        }

        //校验数据库中是否存在此用户
        if(isExist(userName)){
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }

        return Result.build(null,ResultCodeEnum.USERNAME_ERROR);

    }

    @Override
    public Boolean isExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,userName);
        User user = userMapper.selectOne(queryWrapper);

        return user != null ? true:false;
    }

    @Override
    public Result getUserInfo(String token) {
        //校验token是否到期
        if(!jwtHelper.isExpiration(token)){
            Long userId = jwtHelper.getUserId(token);
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUid,userId);
            User user = userMapper.selectOne(queryWrapper);

            user.setUserPwd("");
            HashMap<String, User> userHashMap = new HashMap<>();
            userHashMap.put("loginUser",user);
            return Result.build(userHashMap,ResultCodeEnum.SUCCESS);
        }


        return Result.build(null,ResultCodeEnum.NOTLOGIN);
    }

    @Override
    public Result regist(User user) {
        if (isExist(user.getUsername())) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        int i = userMapper.insert(user);
        if (i > 0) {
            return Result.build(null, ResultCodeEnum.SUCCESS);
        } else {
            return Result.build(null,ResultCodeEnum.INSERTFAIL);
        }
    }

    @Override
    public Result checkUserName(String userName) {
        if(isExist(userName)){
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        return Result.build(null, ResultCodeEnum.SUCCESS);

    }


}




