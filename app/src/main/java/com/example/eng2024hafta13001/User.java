package com.example.eng2024hafta13001;

public class User {
    private String userid;
    private String name;
    private String email;

    public User(String userid, String name, String email) {
        this.userid = userid;
        this.name = name;
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public User(){}
@Override
    public String toString(){

        return this.name +" "+ this.email;
}


}
