/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.tblorderdetail;

import com.prj.tblbook.TblBookDTO;
import com.prj.tblorder.TblOrderDTO;
import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class TblOrderDetailDTO implements Serializable{
    private int orderDetailID;
    private int quantity;
    private double totalPrice;
    private TblOrderDTO order;
    private TblBookDTO book;

    public TblOrderDetailDTO() {
    }

    public TblOrderDetailDTO(int orderDetailID, int quantity, double totalPrice, TblOrderDTO order, TblBookDTO book) {
        this.orderDetailID = orderDetailID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.order = order;
        this.book = book;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public TblOrderDTO getOrder() {
        return order;
    }

    public void setOrder(TblOrderDTO order) {
        this.order = order;
    }

    public TblBookDTO getBook() {
        return book;
    }

    public void setBook(TblBookDTO book) {
        this.book = book;
    }
    
    
}
