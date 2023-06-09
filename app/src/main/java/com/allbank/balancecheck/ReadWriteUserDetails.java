package com.allbank.balancecheck;

public class ReadWriteUserDetails {
    public String name, doB,gender,mobile, state, city;
//    public ReadWriteUserDetails(){};

    public ReadWriteUserDetails (String textFullName,String textDoB,String textGender,String textMobile,String state, String city){

        this.name=textFullName;
        this.doB=textDoB;
        this.gender=textGender;
        this.mobile=textMobile;
        this.state=state;
        this.city=city;
    }
}
