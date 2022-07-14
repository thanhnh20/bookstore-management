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
import com.prj.tblorder.TblOrderDAO;
import com.prj.tblorderdetail.TblOrderDetailDAO;
import com.prj.tblorderdetail.TblOrderDetailError;
import com.prj.utils.MyServices;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String SHOW_CART_CONTROLLER = "ShowCartServlet";
    private final String REMOVE_BOOK_FROM_CART_CONTROLLER = "RemoveBookFromCartServlet";

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
        TblOrderDetailError error = new TblOrderDetailError();
        boolean check = true;
        try {
            if (session != null) {
                TblAccountDTO accountDTO = (TblAccountDTO) session.getAttribute("USER_ROLE");
                if (accountDTO != null) {
                    String[] listID = request.getParameterValues("checkedItems");
                    if (listID != null) {
                        TblBookDAO bookDAO = new TblBookDAO();
                        int quantity;
                        for (String id : listID) {
                            quantity = Integer.parseInt(request.getParameter(id));
                            TblBookDTO bookDTO = bookDAO.GetBookByBookID(id);
                            
                            if(quantity <= 0){
                                error.setInvalidQuantity("Your input quantity is invalid");
                                check = false;
                            }
                            else if (bookDTO.getQuantity() == 0) {
                                error.setQuantityOutOfStockError("The "+"\""+  bookDTO.getBookName()+"\""+ " book is not ready now, "
                                        + "let remove from the cart");
                                check = false;
                            } else if (quantity > bookDTO.getQuantity()) {
                                error.setOutOfQuantityError("Your input quantity is greater than the quantity in stock\n");
                                check = false;
                            }
                            if (!check) {
                                break;
                            }
                        }
                        if (!check) {
                            url = SHOW_CART_CONTROLLER;
                            request.setAttribute("ERROR", error);
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);
                        } else {
                            //set field Order
                            TblOrderDAO orderDAO = new TblOrderDAO();
                            String orderID = MyServices.createIdOrder(orderDAO.getLastOrderID());
                            LocalDate today = LocalDate.now();
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            Date date = Date.valueOf(today.format(format));
                            //set field Order Detail
                            List<TblBookDTO> listBook = new ArrayList<TblBookDTO>();
                            for (String id : listID) {
                                TblBookDTO book = bookDAO.GetBookByBookID(id);
                                quantity = Integer.parseInt(request.getParameter(id));
                                book.setBookID(id);
                                book.setQuantity(quantity);
                                book.setPrice(book.getPrice() * (double) quantity);
                                listBook.add(book);
                            }

                            TblOrderDetailDAO orderDetailDAO = new TblOrderDetailDAO();
                            boolean result = orderDetailDAO.createOrderDetail(orderID, date, accountDTO.getUsername(), listBook);

                            if (result) {
                                url = REMOVE_BOOK_FROM_CART_CONTROLLER;
                                String msg = "Check out successfully";
                                request.setAttribute("MSG", msg);
                                RequestDispatcher rd = request.getRequestDispatcher(url);
                                rd.forward(request, response);
                            }
                        }
                    } else {
                        url = SHOW_CART_CONTROLLER;
                        String msg = "Please select items";
                        request.setAttribute("MSG", msg);
                        RequestDispatcher rd = request.getRequestDispatcher(url);
                        rd.forward(request, response);
                    }
                } else {
                    response.sendRedirect(url);
                }
            } else {
                response.sendRedirect(url);
            }
        } catch (NamingException ex) {
            log("NamingException at CheckOutServlet " + ex.getMessage());
        } catch (SQLException ex) {
            log("NamingException at CheckOutServlet " + ex.getMessage());
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
