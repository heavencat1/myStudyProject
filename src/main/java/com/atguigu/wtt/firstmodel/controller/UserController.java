package com.atguigu.wtt.firstmodel.controller;

import com.atguigu.wtt.firstmodel.enums.ResultCodeEnum;
import com.atguigu.wtt.firstmodel.pojo.User;
import com.atguigu.wtt.firstmodel.service.UserService;
import com.atguigu.wtt.firstmodel.utils.JwtHelper;
import com.atguigu.wtt.firstmodel.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.micrometer.common.util.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;


    /**
     * 登录接口
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    @CrossOrigin
    public Result login(@RequestBody User user){
        Result result = userService.login(user.getUsername(),user.getUserPwd());

        return result;
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    @ResponseBody
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        return userService.getUserInfo(token);
    }


    /**
     * 注册
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/regist")
    public Result regist(@RequestBody User user) {
        return userService.regist(user);
    }

    /**
     * 查询用户名是否占用
     * @param username
     * @return
     */
    @ResponseBody
    @PostMapping("/checkUserName")
    public Result checkUserName(String username){
        return userService.checkUserName(username);
    }

    @ResponseBody
    @GetMapping("/checkLogin")
    public Result checkLogin(@RequestHeader String token){
        if(StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)){
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


}
