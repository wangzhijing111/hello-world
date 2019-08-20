package com.cyh.sfxt.entirty;


import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户信息实体类
 */
@Table(name = "users")
public class Users implements Serializable {
    @Id
    @KeySql(useGeneratedKeys=true)
    @Column(name = "id")
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "sex")
    private String sex;
    @Column(name = "phone")
    private String phone;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "state")
    private String state;
    @Column(name = "creat_time")
    private String creatTime;
    @Column(name = "other1")
    private String other1;
    @Column(name = "other2")
    private String other2;
    @Column(name = "other3")
    private String other3;

    public Users() {

    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public String getOther3() {
        return other3;
    }

    public void setOther3(String other3) {
        this.other3 = other3;
    }

    public Users(String id, String username, String password, String sex, String nickName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
