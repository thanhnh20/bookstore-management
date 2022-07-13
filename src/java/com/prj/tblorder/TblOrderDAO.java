/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.tblorder;

import com.prj.utils.DBHepler;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;


/**
 *
 * @author ASUS
 */
public class TblOrderDAO implements Serializable{
    public String getLastOrderID() throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBHepler.makeConnection();
            if(con != null){
                String sql = "SELECT orderID "
                        + "FROM tblOrder "
                        + "WHERE orderID >= ALL ("
                        + "SELECT orderID FROM tblOrder)";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if(rs.next()){
                    String orderID = rs.getString("orderID");
                    return orderID;
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return null;
    }
    
    public List<TblOrderDTO> getListOrderByUsername(String username) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblOrderDTO> listOrder = null;
        try{
            con = DBHepler.makeConnection();
            if(con != null){
                String sql = "SELECT orderID, purchaseDate "
                        + "FROM tblOrder "
                        + "WHERE username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                while(rs.next()){
                    String orderID = rs.getString("orderID");
                    Date purchaseDate = rs.getDate("purchaseDate");
                    TblOrderDTO order = new TblOrderDTO();
                    order.setOrderID(orderID);
                    order.setPurchaseDate(purchaseDate);
                    if(listOrder == null){
                        listOrder = new ArrayList<TblOrderDTO>();
                    }
                    listOrder.add(order);
                }
                return listOrder;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return null;
    }
    
    public TblOrderDTO getOrderByOrderID(String orderID) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblOrderDTO order = null;
        try{
            con = DBHepler.makeConnection();
            if(con != null){
                String sql = "SELECT orderID, purchaseDate "
                        + "FROM tblOrder "
                        + "WHERE orderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                rs = stm.executeQuery();
                if(rs.next()){
                    Date purchaseDate = rs.getDate("purchaseDate");
                    order = new TblOrderDTO();
                    order.setOrderID(orderID);
                    order.setPurchaseDate(purchaseDate);
                }
                return order;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return null;
    }
}
