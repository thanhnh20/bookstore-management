/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.tblbook;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class TblBookDTO implements Serializable{
    private String bookID;
    private String boookName;
    private String imagePath;
    private int quantity; 
    private double price;
    private boolean status;

    public TblBookDTO() {
    }

    public TblBookDTO(String bookID, String boookName, String imagePath, int quantity, double price, boolean status) {
        this.bookID = bookID;
        this.boookName = boookName;
        this.imagePath = imagePath;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBoookName() {
        return boookName;
    }

    public void setBoookName(String boookName) {
        this.boookName = boookName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
