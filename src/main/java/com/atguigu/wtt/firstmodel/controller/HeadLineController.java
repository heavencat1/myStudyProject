package com.atguigu.wtt.firstmodel.controller;


import com.atguigu.wtt.firstmodel.dto.InsertNewsDTO;
import com.atguigu.wtt.firstmodel.service.HeadlineService;
import com.atguigu.wtt.firstmodel.utils.JwtHelper;
import com.atguigu.wtt.firstmodel.utils.Result;
import io.jsonwebtoken.JwtBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/headline")
@CrossOrigin
public class HeadLineController {

    @Autowired
    private HeadlineService headlineService;

    @Autowired
    private JwtHelper jwtHelper;


    @ResponseBody
    @PostMapping("/publish")
    public Result publish(@RequestBody InsertNewsDTO insertNewsDTO, @RequestHeader String token){
        Long userId = jwtHelper.getUserId(token);

        return headlineService.publish(insertNewsDTO,userId);
    }

    @ResponseBody
    @PostMapping("/findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid){
        return headlineService.showHeadlineDetail(hid);
    }

    @ResponseBody
    @PostMapping("/update")
    public Result update(@RequestBody InsertNewsDTO insertNewsDTO){
        return headlineService.updateNew(insertNewsDTO);
    }


    @ResponseBody
    @PostMapping("/removeByHid")
    public Result removeByHid(Integer hid){
        return headlineService.removeByHid(hid);
    }

}
