package com.mihirvp.sqlitetest;

//import androidx.annotation.RecentlyNonNull;

//import androidx.annotation.RecentlyNonNull;

import java.lang.String;

public class StoreModel {


    public int usid;
    public String url,password1;
    public boolean isActive;



    public StoreModel(int usid ,String url, String password1, boolean isActive ) {

        this.usid = usid;
        this.url = url;
        this.password1 = password1;
        this.isActive = isActive;

    }

    public  StoreModel(){

    }


    //getters and setters


    public int getUsid() {
        return usid;
    }

    public void setUsid(int usid) {
        this.usid = usid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    //toString Method


    @Override
    public String toString() {
        return "StoreModel{" +
                "usid=" + usid +
                ", url='" + url + '\'' +
                ", password1='" + password1 + '\'' +
                ", isActive=" + isActive +
                '}';
    }


}
