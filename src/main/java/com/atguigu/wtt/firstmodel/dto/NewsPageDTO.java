package com.atguigu.wtt.firstmodel.dto;


import lombok.Data;

@Data
public class NewsPageDTO {

    /**
     * 搜索标题关键字
     */
    private String keyWords;

    /**
     * 新闻类型
     */
    private Integer type;

    /**
     * 页码数
     */
    private Integer pageNum;

    /**
     * 页大小
     */
    private Integer pageSize;
}
