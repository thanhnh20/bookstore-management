/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.tbluser;

import com.prj.tblAccount.TblAccountDAO;
import com.prj.tblAccount.TblAccountDTO;
import com.prj.utils.DBHepler;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author ASUS
 */
public class TblUserDAO implements Serializable {

    public TblUserDTO getUserByUserName(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblUserDTO user = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = "SELECT userID, address, gender, birthday, phoneNumber "
                        + "FROM tblUser "
                        + "WHERE username = ? ";               
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                
                rs = stm.executeQuery();
                if(rs.next()){
                    int userID = rs.getInt("userID");
                    boolean gender = rs.getBoolean("gender");
                    String address = rs.getString("address");
                    Date birthday = rs.getDate("birthday");
                    String phone = rs.getString("phoneNumber");
                    TblAccountDAO accountDAO = new TblAccountDAO();
                    TblAccountDTO account = accountDAO.getAccount(username);
                    user = new TblUserDTO(userID, address, birthday, phone, gender, account);
                }
                return user;
            }
        } finally {
            if(rs != null){
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return user;
    }

    public boolean updateProfile(String address, Date birthday, String phoneNumber, boolean gender, 
             String userName) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblUser "
                        + "SET address = ? , birthday = ? , phoneNumber = ?, gender = ? "
                        + "WHERE username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, address);
                stm.setDate(2, birthday);
                stm.setString(3, phoneNumber);
                stm.setBoolean(4, gender);
                stm.setString(5, userName);

                int effectRow1 = stm.executeUpdate();
                if (effectRow1 > 0) {
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
