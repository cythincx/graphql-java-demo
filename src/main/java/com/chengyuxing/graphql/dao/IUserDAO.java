package com.chengyuxing.graphql.dao;

import com.chengyuxing.graphql.domain.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: chengyuxing
 * Date: 2016/12/26
 * Time: 上午12:00
 * Description:
 */
@Repository
public interface IUserDAO {
    List<UserDO> getUserById(@Param("id") Integer id);
}
