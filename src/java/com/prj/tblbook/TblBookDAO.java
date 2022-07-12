/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.tblbook;

import com.prj.utils.DBHepler;
import java.io.Serializable;
import java.sql.Connection;
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
public class TblBookDAO implements Serializable{
    public List<TblBookDTO> getListBook() throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblBookDTO> listBook = null;
        try{
            con = DBHepler.makeConnection();
            if(con != null){
                String sql = "SELECT bookID, bookName, imagePath, quantity, price, status "
                        + "FROM tblBook";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    String bookID = rs.getString("bookID");
                    String bookName = rs.getString("bookName");
                    String imagePath = rs.getString("imagePath");
                    int quantity = rs.getInt("quantity");                   
                    double price = rs.getDouble("price");
                    boolean status = rs.getBoolean("status");
                    if(listBook == null){
                        listBook = new ArrayList<>();
                    }
                    TblBookDTO dto = new TblBookDTO(bookID, bookName, imagePath, quantity, price, status);
                    listBook.add(dto);
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();;
            }
            if(con != null){
                con.close();
            }
        }
        return listBook;
    }
    
    public TblBookDTO GetBookByBookID(String bookID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;       
        try{
            con = DBHepler.makeConnection();
            if(con != null){
                String sql = "SELECT bookID, bookName, imagePath, quantity, price, status "
                        + "FROM tblBook "
                        + "WHERE bookID = ? ";               
                stm = con.prepareStatement(sql);
                stm.setString(1, bookID);
                rs = stm.executeQuery();
                if(rs.next()){
                    String bookName = rs.getString("bookName");
                    String imagePath = rs.getString("imagePath");
                    int quantity = rs.getInt("quantity");                   
                    double price = rs.getDouble("price");
                    boolean status = rs.getBoolean("status");
                    TblBookDTO bookDTO = new TblBookDTO(bookID, bookName, imagePath, quantity, price, status);
                    return bookDTO;
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();;
            }
            if(con != null){
                con.close();
            }
        }
        return null;
    }
}
