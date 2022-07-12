/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.cart;

import com.prj.tblbook.TblBookDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class CartList {
    public List<TblBookDTO> cartList;
    
    public void addBookToCart(TblBookDTO book){
        if(cartList == null){
            cartList = new ArrayList<>();
        }        
        TblBookDTO bookCheck = findBookInCart(book.getBookID());
        if(bookCheck == null){
            book.setQuantity(1);
            cartList.add(book);
        }else{
            bookCheck.setQuantity(1 + bookCheck.getQuantity());
        }
    }
    
    public TblBookDTO findBookInCart(String bookID){
        for (TblBookDTO tblBookDTO : cartList) {
            if(tblBookDTO.getBookID().equals(bookID)){
                return tblBookDTO;
            }
        }
        return null;
    }
    
    public void removeFromCart(String[] bookID){
        for (String id : bookID) {
            cartList.remove(findBookInCart(id));
        }    
    }
    public List<TblBookDTO> getCartList() {
        return cartList;
    }
    
}
