package com.chengyuxing.graphql.service;

import com.chengyuxing.graphql.dao.IUserDAO;
import com.chengyuxing.graphql.domain.UserDO;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mt-chengyuxing on 2016/12/26.
 */
public class UserService {

    @Resource
    private static IUserDAO iUserDAO;

    public static List<UserDO> getUserById(Integer id){
        return iUserDAO.getUserById(id);
    }
}
