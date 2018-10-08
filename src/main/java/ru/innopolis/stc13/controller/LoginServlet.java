package ru.innopolis.stc13.controller;

import ru.innopolis.stc13.repository.dao.FlightDaoImpl;
import ru.innopolis.stc13.repository.dao.UserDaoImpl;
import ru.innopolis.stc13.repository.pojo.User;
import ru.innopolis.stc13.service.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private Service service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = new Service(new UserDaoImpl(), new FlightDaoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = service.getUserByLogin(login);
        if (service.checkAuth(user, password)) {
            req.getSession().setAttribute("id", user.getId());
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("company", user.getAirCompany()==null? "null" : user.getAirCompany().getName());
            req.getSession().setAttribute("role", user.getRole().name());
            resp.sendRedirect("/flights");
        } else {
            resp.sendRedirect("/login?errorCode=wrong");
        }
    }
}
