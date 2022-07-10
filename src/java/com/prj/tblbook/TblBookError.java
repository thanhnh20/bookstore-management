/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.tblbook;

/**
 *
 * @author Admin
 */
public class TblBookError {
    private String bookIDError;
    private String bookNameError;
    private String imagePathError;
    private String quantityError;
    private String priceError;
    private String statusError;
    private String messageError;

    public TblBookError() {
    }

    public TblBookError(String bookIDError, String bookNameError, String imagePathError, String quantityError, String priceError, String statusError, String messageError) {
        this.bookIDError = bookIDError;
        this.bookNameError = bookNameError;
        this.imagePathError = imagePathError;
        this.quantityError = quantityError;
        this.priceError = priceError;
        this.statusError = statusError;
        this.messageError = messageError;
    }

    public String getBookIDError() {
        return bookIDError;
    }

    public void setBookIDError(String bookIDError) {
        this.bookIDError = bookIDError;
    }

    public String getBookNameError() {
        return bookNameError;
    }

    public void setBookNameError(String bookNameError) {
        this.bookNameError = bookNameError;
    }

    public String getImagePathError() {
        return imagePathError;
    }

    public void setImagePathError(String imagePathError) {
        this.imagePathError = imagePathError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

}
