package com.epam.aa.sportplace.servlet.action;

import com.epam.aa.sportplace.model.Customer;
import com.epam.aa.sportplace.service.CustomerService;
import com.epam.aa.sportplace.validation.Validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerRegisterPostAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
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
            return "/customer-register";
        }
        customer.setPassword(password);

        //TODO: how to catch validation exceptions and show them to user?
        Validators.validate(customer);

        CustomerService.create(customer);
        return "";
    }
}
