/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.utils;

/**
 *
 * @author ASUS
 */
public class MyServices {
    public static String createIdOrder(String lastID) {
        if (lastID == null) {
            lastID = "ID0001";
            return lastID;
        }else{
            // tách chuỗi ID và số riêng
            String prefix = lastID.substring(0, 2);
            String subnumber = lastID.substring(lastID.indexOf("D") + 1);
            // convert chuỗi gồm 4 số về số 
            int number = Integer.parseInt(subnumber);
            // tăng 1 đơn vị rồi convert lại thành chuỗi  
            String nextNumber = "" + (number + 1);
            // nối với chuỗi prefix ban đầu
            int length = subnumber.length() - nextNumber.length();
            String newId = prefix;
            for (int i = 0; i < length; i++) {
                newId = newId + "0";
            }
            newId = newId + nextNumber;
            return newId;
        }
    }
}
