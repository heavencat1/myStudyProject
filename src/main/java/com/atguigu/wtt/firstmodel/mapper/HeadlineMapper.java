package com.atguigu.wtt.firstmodel.mapper;

import com.atguigu.wtt.firstmodel.dto.NewsPageDTO;
import com.atguigu.wtt.firstmodel.pojo.Headline;
import com.atguigu.wtt.firstmodel.vo.HeadLineVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
* @author jujian
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2023-10-09 21:12:19
* @Entity com.atguigu.wtt.firstmodel.pojo.Headline
*/
@Component
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectMyPage(IPage page, NewsPageDTO newsPageDTO);


    HeadLineVO getHeadlineByHid(@Param("hid") Integer hid);




}




