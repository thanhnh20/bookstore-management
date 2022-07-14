/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.tblorder;

import com.prj.tblAccount.TblAccountDTO;
import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author ASUS
 */
public class TblOrderDTO implements Serializable{
    private String orderID;
    private Date purchaseDate;
    private TblAccountDTO account;

    public TblOrderDTO() {
    }

    public TblOrderDTO(String orderID, Date purchaseDate, TblAccountDTO account) {
        this.orderID = orderID;
        this.purchaseDate = purchaseDate;
        this.account = account;
    }   

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getPurchaseDate(){        
        return purchaseDate;
    }
    
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public TblAccountDTO getAccount() {
        return account;
    }

    public void setAccount(TblAccountDTO account) {
        this.account = account;
    }
}
