/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.tblorderdetail;

/**
 *
 * @author ASUS
 */
public class TblOrderDetailError {
    private String outOfQuantityError;
    private String invalidFormatQuantity;
    private String quantityOutOfStockError;

    public TblOrderDetailError() {
    }   
    
    public TblOrderDetailError(String outOfQuantityError, String invalidFormatQuantity, String quantityOutOfStockError) {
        this.outOfQuantityError = outOfQuantityError;
        this.invalidFormatQuantity = invalidFormatQuantity;
        this.quantityOutOfStockError = quantityOutOfStockError;
    }

    public String getOutOfQuantityError() {
        return outOfQuantityError;
    }

    public void setOutOfQuantityError(String outOfQuantityError) {
        this.outOfQuantityError = outOfQuantityError;
    }

    public String getInvalidFormatQuantity() {
        return invalidFormatQuantity;
    }

    public void setInvalidFormatQuantity(String invalidFormatQuantity) {
        this.invalidFormatQuantity = invalidFormatQuantity;
    }

    public String getQuantityOutOfStockError() {
        return quantityOutOfStockError;
    }

    public void setQuantityOutOfStockError(String quantityOutOfStockError) {
        this.quantityOutOfStockError = quantityOutOfStockError;
    }
    
    
}
