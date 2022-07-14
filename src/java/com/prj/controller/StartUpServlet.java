/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.controller;

import com.prj.tblAccount.TblAccountDAO;
import com.prj.tblAccount.TblAccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class StartUpServlet extends HttpServlet {
    private final String SHOW_LIST_BOOK_TO_USER_CONTROLLER = "ShowListBookToUserServlet";
    private final String SHOW_ALL_BOOK_TO_ADMIN_CONTROLLER = "ShowAllBookServlet";
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
        String url = SHOW_LIST_BOOK_TO_USER_CONTROLLER;
        try{
            //get cookies
            Cookie[] listCookie = request.getCookies();
            if(listCookie != null){
                Cookie cookie = listCookie[listCookie.length - 1];
                String username = cookie.getName();
                String password = cookie.getValue();
                //check login with name and value of cookie
                TblAccountDAO accountDAO = new TblAccountDAO();
                boolean result = accountDAO.checkLogin(username, password);
                
                if(result){
                    TblAccountDTO accountDTO = accountDAO.getAccount(username); 
                    //check role
                    if (accountDTO.isRole()) {
                        HttpSession session = request.getSession();
                        session.setAttribute("ADMIN_ROLE", accountDTO);
                        url = SHOW_ALL_BOOK_TO_ADMIN_CONTROLLER;
                    } else {
                        HttpSession session = request.getSession();
                        session.setAttribute("USER_ROLE", accountDTO);
                    }
                }
            }
        }catch(NamingException ex){
            log("NamingException at StartUpServlet " + ex.getMessage());
        }catch(SQLException ex){
            log("SQLException at StartUpServlet " + ex.getMessage());
        }finally{
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
