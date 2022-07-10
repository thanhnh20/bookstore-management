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
public class TblBookDAO implements Serializable {

    public List<TblBookDTO> searchBookByName(String searchBookName)
            throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblBookDTO> listBook = new ArrayList<>();
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = " Select bookID, bookName, imagePath, quantity, price, status "
                        + " From tblBook "
                        + " Where bookName like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchBookName + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (rs.getBoolean("status") == true) {
                        String bookID = rs.getString("bookID");
                        String bookName = rs.getString("bookName");
                        String imagePath = rs.getString("imagePath");
                        int quantity = rs.getInt("quantity");
                        double price = rs.getDouble("price");
                        boolean status = rs.getBoolean("status");
                        listBook.add(new TblBookDTO(bookID, bookName, imagePath, quantity, price, status));
                    }
                }
            }

        } catch (Exception e) {
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
        return listBook;
    }

    public boolean updateStatusBook(String bookID)
            throws SQLException, NamingException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = "Update tblBook "
                        + " Set status = 0 "
                        + " Where bookID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, bookID);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public boolean updateBook(TblBookDTO dtoBook) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = "Update tblBook "
                        + " Set bookName = ?, imagePath = ?, quantity = ?, price = ?"
                        + " Where bookID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dtoBook.getBookName());
                stm.setString(2, dtoBook.getImagePath());
                stm.setInt(3, dtoBook.getQuantity());
                stm.setDouble(4, dtoBook.getPrice());
                stm.setString(5, dtoBook.getBookID());
                check = stm.executeUpdate() > 0;
            }

        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public boolean checkDuplicate(String bookID)
            throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = "Select bookID "
                        + " From tblBook "
                        + " Where bookID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, bookID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return check;
    }

    public boolean insertBook(TblBookDTO book) throws SQLException {
        if (book == null) {
            return false;
        }

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = "Insert Into tblBook(bookID, bookName, imagePath, quantity, price, status)"
                        + " Values (?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, book.getBookID());
                stm.setString(2, book.getBookName());
                stm.setString(3, book.getImagePath());
                stm.setInt(4, book.getQuantity());
                stm.setDouble(5, book.getPrice());
                stm.setBoolean(6, book.isStatus());
                int rowEffect = stm.executeUpdate();
                if (rowEffect > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                stm.close();
            }
        }
        return false;
    }

    public List<TblBookDTO> showAllBookByName()
            throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblBookDTO> listBook = new ArrayList<>();
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = " Select bookID, bookName, imagePath, quantity, price, status From tblBook ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (rs.getBoolean("status") == true) {
                        String bookID = rs.getString("bookID");
                        String bookName = rs.getString("bookName");
                        String imagePath = rs.getString("imagePath");
                        int quantity = rs.getInt("quantity");
                        double price = rs.getDouble("price");
                        boolean status = rs.getBoolean("status");
                        listBook.add(new TblBookDTO(bookID, bookName, imagePath, quantity, price, status));
                    }
                }
            }
        } catch (Exception e) {
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
        return listBook;
    }

}
