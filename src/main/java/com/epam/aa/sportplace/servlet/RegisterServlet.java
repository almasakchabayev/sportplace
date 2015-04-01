package com.epam.aa.sportplace.servlet;

import com.epam.aa.sportplace.dao.CustomerDao;
import com.epam.aa.sportplace.dao.DaoCommand;
import com.epam.aa.sportplace.dao.DaoFactory;
import com.epam.aa.sportplace.dao.GenericDao;
import com.epam.aa.sportplace.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone");

        final Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.addPhoneNumber(phoneNumber);

        DaoFactory daoFactory = DaoFactory.getInstance();

        Integer o = (Integer) daoFactory.executeTx(new DaoCommand() {
            public Object execute(DaoFactory daoFactory) {
                GenericDao<Customer, Integer> customerDAO = daoFactory.getCustomerDao();
                return customerDAO.create(customer);
            }
        });

        PrintWriter writer = response.getWriter();
        writer.println(o);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
