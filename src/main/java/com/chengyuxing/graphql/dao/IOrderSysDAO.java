package com.chengyuxing.graphql.dao;

import com.chengyuxing.graphql.domain.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: chengyuxing
 * Date: 2016/12/29
 * Time: 下午11:55
 * Description:
 */
public interface IOrderSysDAO {
    List<UserDO> getUserById(@Param("id") Integer id);
}
