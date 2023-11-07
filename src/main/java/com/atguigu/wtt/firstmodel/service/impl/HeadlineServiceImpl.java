package com.atguigu.wtt.firstmodel.service.impl;

import com.atguigu.wtt.firstmodel.dto.HidDTO;
import com.atguigu.wtt.firstmodel.dto.InsertNewsDTO;
import com.atguigu.wtt.firstmodel.dto.NewsPageDTO;
import com.atguigu.wtt.firstmodel.enums.ResultCodeEnum;
import com.atguigu.wtt.firstmodel.utils.Result;
import com.atguigu.wtt.firstmodel.vo.HeadLineVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.wtt.firstmodel.pojo.Headline;
import com.atguigu.wtt.firstmodel.service.HeadlineService;
import com.atguigu.wtt.firstmodel.mapper.HeadlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

/**
* @author jujian
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2023-10-09 21:12:19
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline> implements HeadlineService{
    @Autowired
    private HeadlineMapper headlineMapper;

    @Override
    public Result findNewsPage(NewsPageDTO newsPageDTO) {
        IPage<Map> page = new Page<>(newsPageDTO.getPageNum(),newsPageDTO.getPageSize());

        IPage<Map> headLinePage = headlineMapper.selectMyPage(page,newsPageDTO);
        List<Map> headLinePageRecords = headLinePage.getRecords();

        HashMap<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageData", headLinePageRecords);
        pageInfo.put("pageNum",headLinePage.getCurrent());
        pageInfo.put("pageSize",headLinePage.getSize());
        pageInfo.put("totalPage",headLinePage.getPages());
        pageInfo.put("totalSize",headLinePage.getTotal());

        HashMap<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo",pageInfo);


//        ArrayList<Page<Headline>> headlines = new ArrayList<>();
//        headlines.add(headline);
//
//        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("pageInfo",objectObjectHashMap);

        return Result.build(pageInfoMap, ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result showHeadlineDetail(Integer hid) {

        HeadLineVO headlineVO= headlineMapper.getHeadlineByHid(hid);


        if(headlineVO == null){
            return Result.build(null,ResultCodeEnum.NONEWS);
        }

//        //将此新闻的浏览量加1
        Integer pageViews = 0;
        String pageViewStr = headlineVO.getPageViews();
        if(pageViewStr != null || pageViewStr != ""){
             pageViews = Integer.valueOf(pageViewStr);
        }


        LambdaQueryWrapper<Headline> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Headline::getHid, headlineVO.getHid());
        Headline headline = headlineMapper.selectOne(wrapper);

        LambdaUpdateWrapper<Headline> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Headline::getPageViews,pageViews+1);
        updateWrapper.set(Headline::getVersion,headline.getVersion()+1);
        updateWrapper.eq(Headline::getHid,headlineVO.getHid());
        headlineMapper.update(null,updateWrapper);


        //组装返回参数
        HashMap<String, HeadLineVO> map = new HashMap<>();
        map.put("headline",headlineVO);


        return Result.build(map,ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result publish(InsertNewsDTO insertNewsDTO,Long userId) {
        Headline headline = new Headline();
        headline.setTitle(insertNewsDTO.getTitle());
        headline.setArticle(insertNewsDTO.getArticle());
        headline.setType(Integer.valueOf(insertNewsDTO.getType()));
        headline.setPublisher(userId.intValue());
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());


        int insert = headlineMapper.insert(headline);
        if(insert>0){
            return Result.build(null,ResultCodeEnum.SUCCESS);
        }

        return Result.build(null,ResultCodeEnum.INSERTFAIL);
    }

    @Override
    public Result updateNew(InsertNewsDTO insertNewsDTO) {
        Headline headline = headlineMapper.selectById(insertNewsDTO.getHid());

        if(headline == null){
            return Result.build(null,ResultCodeEnum.NONEWS);
        }

        headline.setTitle(insertNewsDTO.getTitle());
        headline.setArticle(insertNewsDTO.getArticle());
        headline.setType(Integer.valueOf(insertNewsDTO.getType()));
        headline.setVersion(headline.getVersion());

        headlineMapper.updateById(headline);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result removeByHid(Integer hid) {
        Headline headline = headlineMapper.selectById(hid);
        if(headline == null){
            return Result.build(null,ResultCodeEnum.NONEWS);
        }

        headline.setIsDeleted(1);
        headline.setVersion(headline.getVersion());

        headlineMapper.deleteById(headline);

        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}




