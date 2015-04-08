package com.epam.aa.sportplace.servlet;

import com.epam.aa.sportplace.servlet.action.Action;
import com.epam.aa.sportplace.servlet.action.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontController", urlPatterns = "/")
public class FrontController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Action action = ActionFactory.getAction(request);
            System.out.println(request.getMethod() + request.getPathInfo() + request.getRequestURI());
            if (action == null) return;
            String view = action.execute(request, response);
            if (view.equals(request.getRequestURI())) {
                request.getRequestDispatcher(view + ".jsp").forward(request, response);
            } else {
                response.sendRedirect(view);
            }
        } catch (Exception e) {
            throw new ServletException("Executing action failed.", e);
        }
    }
}
