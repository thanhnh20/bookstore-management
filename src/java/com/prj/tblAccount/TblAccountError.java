/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.tblAccount;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class TblAccountError implements Serializable{
    private String usernameEmpty;
    private String passwordEmpty;
    private String wrongAccount;
    private String wrongPassword;
    private String fullName;
    private String usernameDuplicate;

    public TblAccountError() {
    }

    public TblAccountError(String usernameEmpty, String passwordEmpty, String wrongAccount, String wrongPassword, String fullName, String usernameDuplicate) {
        this.usernameEmpty = usernameEmpty;
        this.passwordEmpty = passwordEmpty;
        this.wrongAccount = wrongAccount;
        this.wrongPassword = wrongPassword;
        this.fullName = fullName;
        this.usernameDuplicate = usernameDuplicate;
    }

    public String getUsernameEmpty() {
        return usernameEmpty;
    }

    public void setUsernameEmpty(String usernameEmpty) {
        this.usernameEmpty = usernameEmpty;
    }

    public String getPasswordEmpty() {
        return passwordEmpty;
    }

    public void setPasswordEmpty(String passwordEmpty) {
        this.passwordEmpty = passwordEmpty;
    }

    public String getWrongAccount() {
        return wrongAccount;
    }

    public void setWrongAccount(String wrongAccount) {
        this.wrongAccount = wrongAccount;
    }

    public String getWrongPassword() {
        return wrongPassword;
    }

    public void setWrongPassword(String wrongPassword) {
        this.wrongPassword = wrongPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsernameDuplicate() {
        return usernameDuplicate;
    }

    public void setUsernameDuplicate(String usernameDuplicate) {
        this.usernameDuplicate = usernameDuplicate;
    }
    
    
}
