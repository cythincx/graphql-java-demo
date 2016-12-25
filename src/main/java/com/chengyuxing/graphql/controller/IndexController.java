package com.chengyuxing.graphql.controller;

import com.chengyuxing.graphql.schema.UserSchema;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Author: chengyuxing
 * Date: 2016/12/25
 * Time: 下午6:01
 * Description:
 */
@Controller
@RequestMapping("/graphql")
public class IndexController {

    @Resource
    private UserSchema userSchema;

    @ResponseBody
    @RequestMapping(value="/getUser",method= RequestMethod.POST)
    public Object getUser(@RequestBody String query){

        Object result = userSchema.doQuery(query);

        return result;
    }
}
