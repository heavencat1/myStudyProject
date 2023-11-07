package com.atguigu.wtt.firstmodel.vo;


import lombok.Data;

@Data
public class HeadLineVO {
    /**
     * 新闻id
     */
    private String hid;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 新闻正文
     */
    private String article;

    /**
     * 新闻所属类别编号
     */
    private String type;

    /**
     * 新闻所属类别
     */
    private String typeName;

    /**
     * 新闻浏览量
     */
    private String pageViews;

    /**
     * 发布时间已过小时数
     */
    private String pastHours;

    /**
     * 发布作者id
     */
    private String publisher;

    /**
     * 新闻作者
     */
    private String author;
}
