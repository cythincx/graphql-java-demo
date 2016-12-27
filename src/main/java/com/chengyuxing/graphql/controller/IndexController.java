package com.chengyuxing.graphql.controller;

import com.chengyuxing.graphql.dao.IUserDAO;
import com.chengyuxing.graphql.domain.UserDO;
import com.chengyuxing.graphql.schema.GraphSchema;
import com.chengyuxing.graphql.schema.UserSchema;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private GraphSchema graphSchema;

//    @Resource
//    private IUserDAO userDAO;

    @ResponseBody
    @RequestMapping(value="/getUser",method= RequestMethod.POST)
    public Object getUser(@RequestBody String query){

        Object result = userSchema.doQuery(query);

        return result;
    }

//    @ResponseBody
//    @RequestMapping(value="/test",method=RequestMethod.GET)
//    public Object test(){
//        List<UserDO> list = userDAO.getUserById(1);
//
//        return list;
//    }
}
