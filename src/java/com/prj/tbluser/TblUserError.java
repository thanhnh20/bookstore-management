/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.tbluser;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class TblUserError implements Serializable{
    private String errorAddressLength;
    private String errorPhoneNumberLength;
    private String errorDateInvalid;
    private String errorDateEmpty;
    private String errorPhoneNumberFormat;

    public TblUserError() {
    }

    public TblUserError(String errorAddressLength, String errorPhoneNumberLength, String errorDateInvalid, String errorDateEmpty, String errorPhoneNumberFormat) {
        this.errorAddressLength = errorAddressLength;
        this.errorPhoneNumberLength = errorPhoneNumberLength;
        this.errorDateInvalid = errorDateInvalid;
        this.errorDateEmpty = errorDateEmpty;
        this.errorPhoneNumberFormat = errorPhoneNumberFormat;
    }

    public String getErrorAddressLength() {
        return errorAddressLength;
    }

    public void setErrorAddressLength(String errorAddressLength) {
        this.errorAddressLength = errorAddressLength;
    }

    public String getErrorPhoneNumberLength() {
        return errorPhoneNumberLength;
    }

    public void setErrorPhoneNumberLength(String errorPhoneNumberLength) {
        this.errorPhoneNumberLength = errorPhoneNumberLength;
    }

    public String getErrorDateInvalid() {
        return errorDateInvalid;
    }

    public void setErrorDateInvalid(String errorDateInvalid) {
        this.errorDateInvalid = errorDateInvalid;
    }

    public String getErrorDateEmpty() {
        return errorDateEmpty;
    }

    public void setErrorDateEmpty(String errorDateEmpty) {
        this.errorDateEmpty = errorDateEmpty;
    }

    public String getErrorPhoneNumberFormat() {
        return errorPhoneNumberFormat;
    }

    public void setErrorPhoneNumberFormat(String errorPhoneNumberFormat) {
        this.errorPhoneNumberFormat = errorPhoneNumberFormat;
    }
    
    
}
