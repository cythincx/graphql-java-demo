# graphql-java-demo
graphql-java测试

##/graphql/getUser
测试接口
> method:post

```json
request-body:
{
	user(id:6) 
	{
		id,
		sex,
		name,
		pic
	}
}

response-body
{
  "user": {
    "id": 6,
    "sex": 0,
    "name": "Name_6",
    "pic": "pic_6.jpg"
  }
}
```
