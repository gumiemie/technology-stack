package com.guyang.spring.boot.model;


import com.guyang.spring.boot.core.validator.PhoneNumber;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-11-13 15:23
 */
public class User implements Serializable {

    private Long id;

    @NotNull(message = "loginName不能为空")
    private String loginName;

    @NotNull()
    private String password;

    private String name;

    @Pattern(regexp = "1?[1-9]{0,2}",message = "年龄必须是0-200之间的正整数！")
    private Integer age;

    @PhoneNumber(message = "电话号码不对！")
    private String phoneNumber;

    private Integer sex;
    private Date birthDay;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(final String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(final Integer sex) {
        this.sex = sex;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(final Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", birthDay=" + birthDay +
                '}';
    }

    public User() {
    }

    public User(final String loginName, final String password) {
        this.loginName = loginName;
        this.password = password;
    }
}
