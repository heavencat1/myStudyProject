package com.atguigu.wtt.firstmodel.service;

import com.atguigu.wtt.firstmodel.dto.HidDTO;
import com.atguigu.wtt.firstmodel.dto.InsertNewsDTO;
import com.atguigu.wtt.firstmodel.dto.NewsPageDTO;
import com.atguigu.wtt.firstmodel.pojo.Headline;
import com.atguigu.wtt.firstmodel.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author jujian
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2023-10-09 21:12:19
*/
public interface HeadlineService extends IService<Headline> {

    /**
     * 分页查询首页头条信息
     * @param newsPageDTO
     * @return
     */
    Result findNewsPage(NewsPageDTO newsPageDTO);


    /**
     * 根据id查看详情
     * @param hid
     * @return
     */
    Result showHeadlineDetail(Integer hid);


    /**
     * 新增文章
     * @param insertNewsDTO
     * @return
     */
    Result publish(InsertNewsDTO insertNewsDTO,Long userId);


    /**
     * 修改新闻
     * @param insertNewsDTO
     * @return
     */
    Result updateNew(InsertNewsDTO insertNewsDTO);


    /**
     * 删除新闻
     * @param hid
     * @return
     */
    Result removeByHid(Integer hid);
}
