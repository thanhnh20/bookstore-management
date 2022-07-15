/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prj.controller;

import com.prj.tblAccount.TblAccountDTO;
import com.prj.tblbook.TblBookDAO;
import com.prj.tblbook.TblBookDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "UserSearchBookServlet", urlPatterns = {"/UserSearchBookServlet"})
public class UserSearchBookServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String USER_PAGE = "user.jsp";

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

        String searchValue = request.getParameter("txtSearchValue");
        String srange = request.getParameter("range");
        int range = 0;
        if (!srange.isEmpty()) {
            range = Integer.parseInt(srange);
        }
        String url = LOGIN_PAGE;
        try {
            TblBookDAO bookDAO = new TblBookDAO();
            List<TblBookDTO> listBook = bookDAO.getListBook();
            List<TblBookDTO> listSearch = new ArrayList<TblBookDTO>();
            int min = 0;
            int max = 1000;
            if (range == 1) {
                max = 30;
            } else if (range == 2) {
                min = 30;
                max = 60;
            } else if (range == 3) {
                min = 60;
                max = 1000;
            }
            for (TblBookDTO dto : listBook) {
                boolean check = true;
                if (!searchValue.isEmpty() && !(dto.getBookName().trim().toLowerCase().contains(searchValue.trim().toLowerCase()))) {
                    check = false;
                }
                if (range != 0 && !(min <= dto.getPrice() && dto.getPrice() < max)) {
                    check = false;
                }
                if (check) {
                    listSearch.add(dto);
                }
            }
            
            int numberBook = listBook.size();
            int numberResult = listSearch.size();

            url = USER_PAGE;
            request.setAttribute("rangeSelected", range);
            request.setAttribute("NUMBER_BOOK", numberBook);
            request.setAttribute("NUMBER_RESULT", numberResult);
            request.setAttribute("LIST_BOOK", listSearch);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (NamingException ex) {
            log("NamingException at UserSearchBookServlet " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException at UserSearchBookServlet " + ex.getMessage());
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
