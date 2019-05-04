package com.zhouzhou.pagehelper.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhouzhou.pagehelper.common.Response;
import com.zhouzhou.pagehelper.dao.entity.User;
import com.zhouzhou.pagehelper.dao.entity.UserExample;
import com.zhouzhou.pagehelper.dao.mapper.UserMapper;
import com.zhouzhou.pagehelper.model.UserSearchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * Description: 测试专用
 * User: zhouzhou
 * Date: 2019-05-03
 * Time: 10:00 AM
 */
@Api(description = "测试用")
@RestController
public class TestController {


    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUser")
    @ApiOperation(value = "查询用户用模型", notes = "测试查询")
    public Response<PageInfo> getUser(UserSearchModel searchModel) {
        PageHelper.startPage(searchModel.getStartPage(),searchModel.getPageSize());
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameLike(searchModel.getName().trim());
        List<User> users = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        Response<PageInfo> response = new Response<>();
        response.setData(pageInfo);
        response.setCode(Response.HttpResponseStatus.OK);
        return response;
    }

    @PostMapping("/user")
    @ApiOperation(value = "新增用户用模型", notes = "测试新增")
    public Response<String> insertUser(Integer number) {
        Random random = new Random();
        Integer success = 0;
        for (int i = 0; i < number; i++) {
            User user = new User();
            user.setName("周舟" + random.nextInt(100));
            user.setAge(random.nextInt(100) + 20 + "");
            int insert = userMapper.insert(user);
            if (insert > 0){
                success ++;
            }

        }
        Response<String> response = new Response<>();
        response.setCode(Response.HttpResponseStatus.OK);
        response.setData(String.format("一共插入{%s}", success));
        return response;
    }


}
