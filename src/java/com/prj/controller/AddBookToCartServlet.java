/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.controller;

import com.prj.cart.CartList;
import com.prj.tblAccount.TblAccountDTO;
import com.prj.tblbook.TblBookDAO;
import com.prj.tblbook.TblBookDTO;
import java.io.IOException;
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
 * @author ASUS
 */
@WebServlet(name = "AddBookToCartServlet", urlPatterns = {"/AddBookToCartServlet"})
public class AddBookToCartServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String USER_SEARCH_BOOK_CONTROLLER = "UserSearchBookServlet";

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

        String bookID = request.getParameter("bookID");
        HttpSession session = request.getSession(false);
        String url = LOGIN_PAGE;
        try {
            if (session != null) {
                TblAccountDTO accountDTO = (TblAccountDTO) session.getAttribute("USER_ROLE");
                if (accountDTO != null) {
                    TblBookDAO bookDAO = new TblBookDAO();
                    //get book add
                    TblBookDTO bookDTO = bookDAO.GetBookByBookID(bookID);

                    //add book to cart                    
                    CartList cartList = (CartList) session.getAttribute("CART");
                    if (cartList == null) {
                        cartList = new CartList();
                    }
                    cartList.addBookToCart(bookDTO);
                    session.setAttribute("CART", cartList);
                    url = USER_SEARCH_BOOK_CONTROLLER;
                    String msg = "Add book to cart successfull";
                    request.setAttribute("MSG", msg);
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                } else {
                    response.sendRedirect(url);
                }
            } else {
                response.sendRedirect(url);
            }
        } catch (NamingException ex) {
            log("NamingException at AddBookToCartServlet " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException at AddBookToCartServlet " + ex.getMessage());
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
