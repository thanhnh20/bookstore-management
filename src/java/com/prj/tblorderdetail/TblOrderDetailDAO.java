/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.tblorderdetail;

import com.prj.tblbook.TblBookDTO;
import com.prj.utils.DBHepler;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ASUS
 */
public class TblOrderDetailDAO implements Serializable {

    public boolean createOrderDetail(String orderID, Date purchaseDate, String username,
            List<TblBookDTO> listBook) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                con.setAutoCommit(false);
                String sql = "INSERT INTO tblOrder(orderID, purchaseDate, username) "
                        + "VALUES(?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setDate(2, purchaseDate);
                stm.setString(3, username);              
                int effectRow1 = stm.executeUpdate();
                
                int effectRow2 = 0;
                for (TblBookDTO book : listBook) {
                    sql = "INSERT INTO tblOrder_Details(quantity, totalPrice, orderID, bookID) "
                            + "VALUES(?, ?, ?, ?) ";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, book.getQuantity());
                    stm.setDouble(2, book.getPrice());
                    stm.setString(3, orderID);
                    stm.setString(4, book.getBookID());
                    effectRow2 = stm.executeUpdate();
                }
                
                con.commit();
                if(effectRow1 > 0 && effectRow2 > 0){
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
