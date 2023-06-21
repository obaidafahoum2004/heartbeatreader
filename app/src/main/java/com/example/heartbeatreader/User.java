package com.example.heartbeatreader;

public class User {

    private String name, nickname, phone, iv;

    public User() {
    }
    public User(String name, String nickname, String phone, String iv) {
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.iv = iv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserimg() {
        return iv;
    }

    public void setUserimg(String iv) {
        this.iv= iv;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", iv='" + iv + '\'' +
                '}';
    }
}
