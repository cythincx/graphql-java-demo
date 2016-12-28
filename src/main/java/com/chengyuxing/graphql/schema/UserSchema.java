package com.chengyuxing.graphql.schema;

import com.chengyuxing.graphql.dao.IUserDAO;
import com.chengyuxing.graphql.domain.UserDO;
import graphql.GraphQL;
import graphql.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
 * Date: 2016/12/26
 * Time: 上午12:09
 * Description:
 */
@Service("UserSchema")
public class UserSchema {

    @Autowired(required = true)
    private IUserDAO userDAO;

    private GraphQLOutputType userType;

    private GraphQLSchema schema;

    public GraphQLSchema getSchema(){
        return schema;
    }

    private GetUserDataFetcher getUserDataFetcher;

    @PostConstruct
    private void init(){
        initOutputType();
        getUserDataFetcher = new GetUserDataFetcher();
        getUserDataFetcher.innerUserDAO =  this.userDAO;
        schema = GraphQLSchema.newSchema().query(newObject()
                .name("GraphQuery")
                .field(getUser())
                .build())
                .build();
    }

    public class GetUserDataFetcher implements DataFetcher{
        public IUserDAO innerUserDAO;

        @Override
        public Object get(DataFetchingEnvironment dataFetchingEnvironment) {
            int id = dataFetchingEnvironment.getArgument("id");
            System.out.println("id=" + id);
            List<UserDO> userDO = userDAO.getUserById(id);
            return userDO;
        }
    }

    private void initOutputType() {
        userType = newObject()
                .name("UserDO")
                .field(newFieldDefinition().name("id").type(GraphQLInt).build())
                .field(newFieldDefinition().name("age").type(GraphQLInt).build())
                .field(newFieldDefinition().name("sex").type(GraphQLInt).build())
                .field(newFieldDefinition().name("name").type(GraphQLString).build())
                .field(newFieldDefinition().name("pic").type(GraphQLString).build())
                .build();
    }

    private GraphQLFieldDefinition getUser(){
        return newFieldDefinition()
                .name("user")
                .argument(newArgument().name("id").type(GraphQLInt).build())
                .type(new GraphQLList(userType))
                .dataFetcher(getUserDataFetcher)
                .build();
    }

    public Object doQuery(String query){
        GraphQLSchema schema = this.getSchema();
        Map<String, Object> result = null;

        try {
            result = (Map<String, Object>) new GraphQL(schema).execute(query).getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
