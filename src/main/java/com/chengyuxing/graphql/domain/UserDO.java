package com.chengyuxing.graphql.domain;

/**
 * Created with IntelliJ IDEA.
 * Author: chengyuxing
 * Date: 2016/12/25
 * Time: 下午6:26
 * Description:
 */
public class UserDO {
    private Integer id;
    private Integer age;
    private Integer sex;
    private String name;
    private String pic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
