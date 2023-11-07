package com.atguigu.wtt.firstmodel.controller;


import com.atguigu.wtt.firstmodel.dto.HidDTO;
import com.atguigu.wtt.firstmodel.dto.NewsPageDTO;
import com.atguigu.wtt.firstmodel.enums.ResultCodeEnum;
import com.atguigu.wtt.firstmodel.pojo.Type;
import com.atguigu.wtt.firstmodel.service.HeadlineService;
import com.atguigu.wtt.firstmodel.service.TypeService;
import com.atguigu.wtt.firstmodel.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/portal")
@CrossOrigin
public class PortalController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    @ResponseBody
    public Result findAllTypes(){
        List<Type> typeList = typeService.list();
        return Result.build(typeList, ResultCodeEnum.SUCCESS);
    }


    @PostMapping("/findNewsPage")
    @ResponseBody
    public Result findNewsPage(@RequestBody NewsPageDTO newsPageDTO){
       return headlineService.findNewsPage(newsPageDTO);

    }

    @PostMapping("/showHeadlineDetail")
    @ResponseBody
    public Result showHeadlineDet(Integer hid){
        return headlineService.showHeadlineDetail(hid);

    }

}
