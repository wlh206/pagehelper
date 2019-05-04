package com.zhouzhou.pagehelper.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserSearchModel implements Serializable {

    int startPage = 0;

    int pageSize = 10;

    private String name;

    private String age;


}