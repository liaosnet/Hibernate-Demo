package com.gbasedbt.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {

    /**
     * 建表语句，可通过@Column控制自动创建生成
     * create table student (id serial not null, username varchar(60), usertext text, userphoto byte, primary key(id));
     */
    // 序号, int/serial
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 用户名, length指定长度: 不指定使用255, 大于255使用lvarchar
    @Column(name = "username", length = 60)
    private String username;

    // 简历信息, columnDefinition 会覆盖掉length信息, 使用text/clob
    @Column(columnDefinition = "clob")
    private String usertext;

    // 照片， byte/blob
    @Column(name = "userphoto", columnDefinition = "blob")
    private byte[] userphoto;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertext() {
        return usertext;
    }
    public void setUsertext(String usertext) {
        this.usertext = usertext;
    }

    public byte[] getUserphoto() {
        return userphoto;
    }
    public void setUserphoto(byte[] userphoto) {
        this.userphoto = userphoto;
    }

    @Override
    public String toString() {
        return "学生  [序号 = " + id + "\t姓名 = " + username + "\t简介= " + usertext + "]";
    }

}
