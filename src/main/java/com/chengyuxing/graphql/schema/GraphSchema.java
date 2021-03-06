package com.chengyuxing.graphql.schema;

import com.chengyuxing.graphql.domain.UserDO;
import graphql.GraphQL;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLSchema;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created with IntelliJ IDEA.
 * Author: chengyuxing
 * Date: 2016/12/24
 * Time: 下午6:42
 * Description:
 */
@Service("GraphSchema")
public class GraphSchema {
    private GraphQLOutputType userType;

    private void initOutputType() {
        /**
         * 会员对象结构
         */
        userType = newObject()
                .name("UserDO")
                .field(newFieldDefinition().name("id").type(GraphQLInt).build())
                .field(newFieldDefinition().name("age").type(GraphQLInt).build())
                .field(newFieldDefinition().name("sex").type(GraphQLInt).build())
                .field(newFieldDefinition().name("name").type(GraphQLString).build())
                .field(newFieldDefinition().name("pic").type(GraphQLString).build())
                .build();
    }

    /**
     * 查询单个用户信息
     * @return
     */

    private GraphQLFieldDefinition createUserField() {
        return newFieldDefinition()
                .name("user")
                .argument(newArgument().name("id").type(GraphQLInt).build())
                .type(userType)
                .dataFetcher(environment -> {
                    // 获取查询参数
                    int id = environment.getArgument("id");

                    // 执行查询, 这里随便用一些测试数据来说明问题
                    UserDO userDO = new UserDO();
                    userDO.setId(id);
                    userDO.setAge(id + 15);
                    userDO.setSex(id % 2);
                    userDO.setName("Name_" + id);
                    userDO.setPic("pic_" + id + ".jpg");
                    return userDO;
                })
                .build();
    }

    /**
     * 查询多个会员信息
     * @return
     */
    private GraphQLFieldDefinition createUsersField() {
        return newFieldDefinition()
                .name("users")
                .argument(newArgument().name("page").type(GraphQLInt).build())
                .argument(newArgument().name("size").type(GraphQLInt).build())
                .argument(newArgument().name("name").type(GraphQLString).build())
                .type(new GraphQLList(userType))
                .dataFetcher(environment -> {
                    // 获取查询参数
                    int page = environment.getArgument("page");
                    int size = environment.getArgument("size");
                    String name = environment.getArgument("name");

                    // 执行查询, 这里随便用一些测试数据来说明问题
                    List<UserDO> list = new ArrayList<>(size);
                    for (int i = 0; i < size; i++) {
                        UserDO userDO = new UserDO();
                        userDO.setId(i);
                        userDO.setAge(i + 15);
                        userDO.setSex(i % 2);
                        userDO.setName(name + "_" + page + "_" + i);
                        userDO.setPic("pic_" + i + ".jpg");
                        list.add(userDO);
                    }
                    return list;
                })
                .build();
    }

    private GraphQLSchema schema;

    public GraphSchema() {
        initOutputType();
        schema = GraphQLSchema.newSchema().query(newObject()
                .name("GraphQuery")
                .field(createUsersField())
                .field(createUserField())
                .build()).build();
    }

    public GraphQLSchema getSchema(){
        return schema;
    }

    public Object doQuery(String query){
        GraphQLSchema schema = new GraphSchema().getSchema();

        Map<String, Object> result = null;

        try {
            result = (Map<String, Object>) new GraphQL(schema).execute(query).getData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        GraphQLSchema schema = new GraphSchema().getSchema();

        String query1 = "{users(page:2,size:5,name:\"john\") {id,sex,name,pic}}";
        String query2 = "{user(id:6) {id,sex,name,pic}}";
        String query3 = "{user(id:6) {id,sex,name,pic},users(page:2,size:5,name:\"john\") {id,sex,name,pic}}";

        Map<String, Object> result1 = (Map<String, Object>) new GraphQL(schema).execute(query1).getData();
        Map<String, Object> result2 = (Map<String, Object>) new GraphQL(schema).execute(query2).getData();
        Map<String, Object> result3 = (Map<String, Object>) new GraphQL(schema).execute(query3).getData();

        // 查询用户列表
        System.out.println(result1);
        // 查询单个用户
        System.out.println(result2);
        // 单个用户、跟用户列表一起查
        System.out.println(result3);

    }

}
