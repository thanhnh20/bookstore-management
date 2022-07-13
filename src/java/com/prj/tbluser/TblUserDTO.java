/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.tbluser;

import com.prj.tblAccount.TblAccountDTO;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class TblUserDTO implements Serializable{
    private int userID;
    private String address;
    private Date birthday;
    private String phone;
    private boolean gender;
    private TblAccountDTO account;

    public TblUserDTO() {
    }
    
    public TblUserDTO(int userID, String address, Date birthday, String phone, boolean gender, TblAccountDTO account) {
        this.userID = userID;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;
        this.gender = gender;
        this.account = account;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public TblAccountDTO getAccount() {
        return account;
    }

    public void setAccount(TblAccountDTO account) {
        this.account = account;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
    
    
}
