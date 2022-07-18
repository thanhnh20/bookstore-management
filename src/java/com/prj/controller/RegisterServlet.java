/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.controller;

import com.prj.tblAccount.TblAccountDAO;
import com.prj.tblAccount.TblAccountDTO;
import com.prj.tblAccount.TblAccountError;
import com.prj.tbluser.TblUserDAO;
import com.prj.tbluser.TblUserDTO;
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

/**
 *
 * @author ASUS
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private final String REGISTER_PAGE = "register.jsp";
    private final String LOGIN_PAGE = "login.jsp";

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
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullname = request.getParameter("fullname");
        String url = REGISTER_PAGE;
        try {
            TblAccountError error = new TblAccountError();
            boolean foundError = false;
            TblAccountDAO accountdao = new TblAccountDAO();
            boolean check = accountdao.checkAccount(username);
            // check username is not empty
            if (username.trim().length() == 0) {
                error.setUsernameEmpty("Please enter username");
                foundError = true;
            }

            if (check == true) {
                error.setUsernameDuplicate("Username duplicated");
                foundError = true;
            }
            //check password is not empty
            if (password.trim().length() == 0) {
                error.setPasswordEmpty("Please enter password");
                foundError = true;
            }
            if (!confirmPassword.equals(password)) {
                error.setWrongPassword("Not the same password");
                foundError = true;
            }
            if (fullname.trim().length() == 0) {
                error.setFullName("Please enter your fullname");
                foundError = true;
            }
            //set error if found
            if (foundError) {
                request.setAttribute("ERROR", error);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                TblAccountDTO accountdto = new TblAccountDTO();
                accountdto.setUsername(username);
                accountdto.setPassword(password);
                accountdto.setFullName(fullname);
                accountdto.setRole(false);
                //TblAccountDAO accountdao = new TblAccountDAO();
                boolean result = accountdao.createAcount(accountdto);
                if (result) {
                    String msg = "Successful account registration, login now";
                    request.setAttribute("MSG", msg);
                    url = LOGIN_PAGE;
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }
            }
        } catch (NamingException ex) {
            log("RegisterController_NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log("RegisterController_SQLException " + ex.getMessage());
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
