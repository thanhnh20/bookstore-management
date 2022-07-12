/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.controller;

import com.prj.cart.CartList;
import com.prj.tblAccount.TblAccountDTO;
import com.prj.tblbook.TblBookDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "RemoveBookFromCartServlet", urlPatterns = {"/RemoveBookFromCartServlet"})
public class RemoveBookFromCartServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String CART_PAGE = "cart.jsp";

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
        String[] listBookID = request.getParameterValues("checkedItems");
        HttpSession session = request.getSession(false);
        if (session != null) {
            TblAccountDTO accountDTO = (TblAccountDTO) session.getAttribute("USER_ROLE");
            if (accountDTO != null) {
                CartList cartList = (CartList) session.getAttribute("CART");
                List<TblBookDTO> listCart = null;
                if (cartList != null) {
                    if (listBookID != null) {
                        cartList.removeFromCart(listBookID);
                    } else {
                        String msg = "Please select items";
                        request.setAttribute("MSG", msg);
                    }
                    listCart = cartList.getCartList();
                }
                url = CART_PAGE;
                request.setAttribute("LIST_BOOK_IN_CART", listCart);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                response.sendRedirect(url);
            }
        } else {
            response.sendRedirect(url);
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
