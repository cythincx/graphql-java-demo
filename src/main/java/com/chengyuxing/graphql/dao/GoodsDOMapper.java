package com.chengyuxing.graphql.dao;

import com.chengyuxing.graphql.domain.GoodsDO;

public interface GoodsDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsDO record);

    int insertSelective(GoodsDO record);

    GoodsDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsDO record);

    int updateByPrimaryKey(GoodsDO record);
}