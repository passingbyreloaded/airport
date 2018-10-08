package ru.innopolis.stc13.controller;

import ru.innopolis.stc13.utils.FlightUtil;
import ru.innopolis.stc13.repository.dao.FlightDaoImpl;
import ru.innopolis.stc13.repository.dao.UserDaoImpl;
import ru.innopolis.stc13.repository.pojo.FlightStatus;
import ru.innopolis.stc13.service.Service;
import ru.innopolis.stc13.to.Restriction;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class FlightServlet extends HttpServlet {

    private Service service;
    private Map<FlightStatus, Restriction> restrictions;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = new Service(new UserDaoImpl(), new FlightDaoImpl());
        restrictions = service.getRestrictions();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("add".equals(req.getParameter("action"))) {
            req.setAttribute("types", service.getTypes());
            req.setAttribute("statuses", service.getAvailableStatuses());
            req.getRequestDispatcher("/edit.jsp").forward(req, resp);
        } else {
            if ("PILOT".equals(req.getSession().getAttribute("role"))) {
                req.setAttribute("flights", FlightUtil.getWithRestrictions(service.getByPilot((String) req.getSession().getAttribute("id")), restrictions));
            } else {
                req.setAttribute("flights", FlightUtil.getWithRestrictions(service.getAll(), restrictions));
            }
            req.getRequestDispatcher("/flights.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gateNumber = req.getParameter("gateNumber");
        String parkingSpot = req.getParameter("parkingSpot");
        String status = req.getParameter("status");
        if ("update".equals(req.getParameter("action"))) {
            String id = req.getParameter("id");
            service.update(id, gateNumber, parkingSpot, status);
        } else {
            String type = req.getParameter("flightType");
            String number = req.getParameter("number");
            String dateTime = req.getParameter("dateTime");
            String airCompany = req.getParameter("airCompany");
            String airCraft = req.getParameter("aircraft");
            String pilotName = req.getParameter("pilot");
            service.add(type, number, dateTime, airCompany, airCraft, pilotName, gateNumber, parkingSpot, status);
        }
        resp.sendRedirect("flights");
    }
}
