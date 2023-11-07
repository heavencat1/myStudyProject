package com.atguigu.wtt.firstmodel.dto;


import lombok.Data;
import lombok.NonNull;

@Data
public class InsertNewsDTO {

    /**
     * 文章id，修改时传入
     */
    private String hid;


    /**
     * 文章标题
     */
    @NonNull
    private String title;

    /**
     * 文章内容
     */
    @NonNull
    private String article;

    /**
     * 文章类别
     */
    @NonNull
    private String type;

}
