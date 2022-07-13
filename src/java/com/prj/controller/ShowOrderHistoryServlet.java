/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.controller;

import com.prj.tblAccount.TblAccountDTO;
import com.prj.tblorder.TblOrderDAO;
import com.prj.tblorder.TblOrderDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "ShowOrderHistoryServlet", urlPatterns = {"/ShowOrderHistoryServlet"})
public class ShowOrderHistoryServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.jsp";
    private final String ORDER_HISTORY_PAGE = "orderHistory.jsp";
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
        
        HttpSession session = request.getSession(false);
        String url = LOGIN_PAGE;
        try{
            if(session != null){
                TblAccountDTO accountDTO = (TblAccountDTO)session.getAttribute("USER_ROLE");
                if(accountDTO != null){
                    TblOrderDAO orderDAO = new TblOrderDAO();
                    List<TblOrderDTO> listOrder = orderDAO.getListOrderByUsername(accountDTO.getUsername());
                    request.setAttribute("LIST_ORDER", listOrder);
                    url = ORDER_HISTORY_PAGE;
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }else{
                    response.sendRedirect(url);
                }
            }else{
                response.sendRedirect(url);
            }
        }catch(SQLException ex){
            log("SQLException at ShowOrderHistoryServlet " + ex.getMessage());
        }catch(NamingException ex){
            log("NamingException at ShowOrderHistoryServlet " + ex.getMessage());
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
