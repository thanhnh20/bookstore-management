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
import java.util.ArrayList;
import java.util.List;
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
                if (rs.next()) {
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
            if (rs != null) {
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

    public static List<TblUserDTO> getAllUser() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblUserDTO> list = null;
        //Connecting to a database
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = "select * from tblUser ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                //load data into list
                //if userId and password are correct
                while (rs.next()) {
                    TblUserDTO user = new TblUserDTO();
                    TblAccountDAO d = new TblAccountDAO();
                    user.setAccount(d.getAccount(rs.getString("username")));
                    user.setUserID(rs.getInt("userID"));
                    user.setAddress(rs.getString("address"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setPhone(rs.getString("phoneNumber"));
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(user);
                }
            }
            //Creating and executing sql statements            
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        //Closing the connection

        return list;
    }

    public static List<TblUserDTO> search(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblUserDTO> list = null;
        try {
            //Connecting to a database
            con = DBHepler.makeConnection();
            //Creating and executing sql statements            
            String sql = "select * from tblUser where username like ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + username + "%");
            rs = stm.executeQuery();
            //load data into list
            //if userId and password are correct
            while (rs.next()) {
                TblUserDTO user = new TblUserDTO();
                TblAccountDAO d = new TblAccountDAO();
                user.setAccount(d.getAccount(rs.getString("username")));
                user.setUserID(rs.getInt("userID"));
                user.setAddress(rs.getString("address"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phoneNumber"));
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(user);
            }
        } finally {
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
        //Closing the connection
        return list;
    }
}
