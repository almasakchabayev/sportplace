package com.epam.aa.sportplace.servlet;

import com.epam.aa.sportplace.dao.CustomerDao1;
import com.epam.aa.sportplace.dao.DaoFactory1;
import com.epam.aa.sportplace.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone");

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.addPhoneNumber(phoneNumber);

        DaoFactory1 daoFactory1 = DaoFactory1.getDAOFactory();
        DaoManager daoManager = new

                CustomerDao1 customerDAO = daoFactory1.getCustomerDAO();
        customerDAO.insert(customer);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
