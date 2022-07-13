/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.controller;

import com.prj.tblAccount.TblAccountDTO;
import com.prj.tblbook.TblBookDAO;
import com.prj.tblbook.TblBookDTO;
import com.prj.tblbook.TblBookError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SignUpBookServlet", urlPatterns = {"/SignUpBookServlet"})
public class SignUpBookServlet extends HttpServlet {
    
    private final String LOGIN_PAGE = "login.jsp";
    private final String ERROR = "addNewBook.jsp";
    private final String SUCCESS = "ShowAllBookServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                TblAccountDTO accountDTO = (TblAccountDTO) session.getAttribute("ADMIN_ROLE");
                if (accountDTO != null) {
                    String bookID = request.getParameter("txtBookID");
                    String bookName = request.getParameter("txtBookName");
                    String imagePath = request.getParameter("txtImagePath");
                    int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                    double price = Double.parseDouble(request.getParameter("txtPrice"));

                    boolean check = true;
                    TblBookError bookError = new TblBookError();

                    if (bookID.trim().length() < 1 || bookID.trim().length() > 10) {
                        check = false;
                        bookError.setBookIDError("Book ID length must be [4..10] characters !");
                    }
                    if (bookName.trim().length() < 1 || bookName.trim().length() > 50) {
                        check = false;
                        bookError.setBookNameError("Book Name length must be [1..50] characters !");
                    }
                    if (imagePath.trim().length() < 1 || imagePath.trim().length() > 300) {
                        check = false;
                        bookError.setImagePathError("ImagePath length must be [1..300] characters !");
                    }
                    if (quantity < 0) {
                        check = false;
                        bookError.setQuantityError("Quantity must be greater than 0 !");
                    }
                    if (price <= 0) {
                        check = false;
                        bookError.setPriceError("Price must be greater than 0 !");
                    }
                    if (check) {
                        TblBookDAO dao = new TblBookDAO();
                        TblBookDTO dto = new TblBookDTO(bookID, bookName, imagePath, quantity, price, true);
                        boolean checkDuplicate = dao.checkDuplicate(bookID);
                        if (checkDuplicate) {
                            url = ERROR;
                            bookError.setBookIDError("Duplicate Book ID : " + bookID);
                            request.setAttribute("BOOK_ERROR", bookError);
                            request.getRequestDispatcher(url).forward(request, response);
                        } else {
                            boolean checkInsert = dao.insertBook(dto);
                            if (checkInsert) {
                                url = SUCCESS;
                                request.getRequestDispatcher(url).forward(request, response);
                            } else {
                                url = ERROR;
                                bookError.setMessageError("Can not Create New Book !");
                                request.getRequestDispatcher(url).forward(request, response);
                            }
                        }
                    } else {
                        url = ERROR;
                        bookError.setMessageError("Can not Create New Book !");
                        request.setAttribute("BOOK_ERROR", bookError);
                        request.getRequestDispatcher(url).forward(request, response);
                    }
                }else{
                    response.sendRedirect(url);
                }
            }else{
                response.sendRedirect(url);
            }
        } catch (NamingException ex) {
            log("NamingException at SignUpBookServlet " + ex.getMessage());
        }catch (SQLException ex){
            log("SQLException at SignUpBookServlet " + ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
