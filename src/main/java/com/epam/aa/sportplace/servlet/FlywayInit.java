package com.epam.aa.sportplace.servlet;

import org.flywaydb.core.Flyway;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "Servlet")
public class FlywayInit extends HttpServlet {
    public void init() throws ServletException {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:postgresql://localhost/sportplacedb", "almas", "sportplacedb");
        flyway.migrate();
    }
}
