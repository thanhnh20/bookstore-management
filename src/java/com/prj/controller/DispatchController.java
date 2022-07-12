/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class DispatchController extends HttpServlet {
    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String USER_SEARCH_BOOK_CONTROLLER = "UserSearchBookServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String ADD_BOOK_TO_CART_CONTROLLER = "AddBookToCartServlet";
    private final String SHOW_CART_CONTROLLER = "ShowCartServlet";
    private final String SHOW_LIST_BOOK_CONTROLLER = "ShowListBookToUserServlet";
    private final String REMOVE_BOOK_FROM_CART_CONTROLLER = "RemoveBookFromCartServlet";
    private final String CHECK_OUT_CONTROLLER = "CheckOutServlet";
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
        
        String url = "";
        String action = request.getParameter("btnAction");
        try{
            if(action == null){
                url = START_UP_CONTROLLER;
            }else if("Login".equals(action)){
                url = LOGIN_CONTROLLER;
            }else if("Logout".equals(action)){
                url = LOGOUT_CONTROLLER;
            }else if("Show".equals(action)){
                url = SHOW_LIST_BOOK_CONTROLLER;
            }else if("Search".equals(action)){
                url = USER_SEARCH_BOOK_CONTROLLER;
            }else if("Add to cart".equals(action)){
                url = ADD_BOOK_TO_CART_CONTROLLER;
            }else if("ShowCart".equals(action)){
                url = SHOW_CART_CONTROLLER;
            }else if("Remove".equals(action)){
                url = REMOVE_BOOK_FROM_CART_CONTROLLER;
            }else if("Checkout".equals(action)){
                url = CHECK_OUT_CONTROLLER;
            }
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
