package com.epam.aa.sportplace.servlet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        Customer user = customerDAO.find(username, password);
//        if (user != null) {
//            request.getSession().setAttribute("user", user); // Login user.
//            return "home"; // Redirect to home page.
//        } else {
//            request.setAttribute("error", "Unknown username/password. Please retry."); // Store error message in request scope.
//            return "login"; // Go back to redisplay login form with error.
//        }
        return null;
    }
}
