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
@WebServlet(name = "UpdateBookServlet", urlPatterns = {"/UpdateBookServlet"})
public class UpdateBookServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String ERROR = "SearchBookServlet";
    private final String SUCCESS = "SearchBookServlet";

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
                    boolean status = Boolean.parseBoolean(request.getParameter("status"));
                    TblBookDAO daoBook = new TblBookDAO();
                    TblBookDTO bookCheck = daoBook.GetBookByBookID(bookID);
                    if (bookCheck.getQuantity() == 0 && quantity == 0 && status) {
                        String msgError = "The quantity need greater than 0 ";
                        request.setAttribute("MSG_ERROR", msgError);
                    } else {
                        TblBookDTO dtoBook = new TblBookDTO();
                        dtoBook.setBookID(bookID);
                        dtoBook.setBookName(bookName);
                        dtoBook.setImagePath(imagePath);
                        dtoBook.setQuantity(quantity);
                        dtoBook.setPrice(price);
                        dtoBook.setStatus(status);
                        boolean check = daoBook.updateBook(dtoBook);
                        if (check) {
                            String msg = "You have successfully updated";
                            request.setAttribute("MSG", msg);
                        }
                    }
                    url = SUCCESS;
                    request.getRequestDispatcher(url).forward(request, response);
                }
            } else {
                response.sendRedirect(url);
            }
        } catch (Exception ex) {
            log("Error at Update Book Servlet !" + ex.toString());
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
