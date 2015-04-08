package com.epam.aa.sportplace.servlet;

import com.epam.aa.sportplace.model.Customer;
import com.epam.aa.sportplace.service.CustomerService;
import com.epam.aa.sportplace.validation.Validators;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("form-register-first-name");
        String lastName = request.getParameter("form-register-last-name");
        String email = request.getParameter("form-register-email");
        String phoneNumber = request.getParameter("form-register-phone-number");
        String password = request.getParameter("form-register-password");
        String confirmPassword = request.getParameter("form-register-confirm-password");

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.addPhoneNumber(phoneNumber);
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "password and confirm-password are not the same");
            request.getRequestDispatcher("customer-register.jsp").forward(request, response);
        }
        customer.setPassword(password);

        //TODO: how to catch validation exceptions and show them to user?
        Validators.validate(customer);

        CustomerService.create(customer);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("customer-register.jsp").forward(request, response);
    }
}
