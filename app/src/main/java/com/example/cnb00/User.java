package com.example.cnb00;

public class User {
    public String state; // field2
    public String company_name; // field4
    public String company_info;// field5
    public String type;// field6
    public String field;// field7
    public String owner; // field8
    public String phone_number; // field9
    public String address;// field11
    public String webcite;// field12

    public String getState(){
        return state;
    }

    public String getCompanyName(){
        return company_name;
    }

    public String getCompanyInfo(){
        return company_info;
    }

    public String getType(){
        return type;
    }

    public String getField(){
        return field;
    }

    public String getOwner(){
        return owner;
    }

    public String getPhoneNumber(){
        return phone_number;
    }

    public String getAddress(){
        return address;
    }

    public String getWebCite(){
        return webcite;
    }

    public void setState(String state){
        this.state = state;
    }

    public void setCompanyName(String company_name){
        this.company_name = company_name;
    }

    public void setCompanyInfo(String company_info){
        this.company_info = company_info;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setField(String field){
        this.field = field;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }

    public void setPhoneNumber(String phone_number){
        this.phone_number = phone_number;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setWebCite(String webcite){
        this.webcite = webcite;
    }

}
