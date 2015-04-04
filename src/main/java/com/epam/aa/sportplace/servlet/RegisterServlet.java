package com.epam.aa.sportplace.servlet;

import com.epam.aa.sportplace.dao.DaoCommand;
import com.epam.aa.sportplace.dao.DaoFactory;
import com.epam.aa.sportplace.dao.GenericDao;
import com.epam.aa.sportplace.model.Customer;
import com.epam.aa.sportplace.service.CustomerService;

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

        PrintWriter writer = response.getWriter();
        Integer id = new CustomerService().create(customer);
        writer.println(id);
//        DaoFactory daoFactory = DaoFactory.getInstance();


//        Integer o = daoFactory.executeTx(new DaoCommand() {
//            public Object execute(DaoFactory daoFactory) {
//                GenericDao<Customer> customerDAO = daoFactory.getCustomerDao();
//                return customerDAO.create(customer);
//            }
//        });


//        String s = daoFactory.executeTx(new DaoCommand() {
//            public Object execute(DaoFactory daoFactory) {
//                GenericDao<Customer> customerDAO = daoFactory.getCustomerDao();
//                return customerDAO.create(customer);
//            }
//        }).getClass().toString();
//        writer.println(s);
//
//        DaoFactory daoFactory2 = DaoFactory.getInstance();
//        String s2 = daoFactory2.executeTx(new DaoCommand() {
//
//            public Object execute(DaoFactory daoFactory) {
//                GenericDao<Customer> customerDAO = daoFactory.getCustomerDao();
//                return customerDAO.read(1);
//            }
//        }).getClass().toString();
//        writer.println(s2);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
