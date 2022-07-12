/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.tblorder;

import com.prj.utils.DBHepler;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
