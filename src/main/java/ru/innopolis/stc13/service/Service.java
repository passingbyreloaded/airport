package ru.innopolis.stc13.service;

import org.apache.log4j.Logger;
import ru.innopolis.stc13.repository.dao.daoint.FlightDao;
import ru.innopolis.stc13.repository.dao.daoint.UserDao;
import ru.innopolis.stc13.repository.pojo.Flight;
import ru.innopolis.stc13.repository.pojo.FlightStatus;
import ru.innopolis.stc13.repository.pojo.FlightType;
import ru.innopolis.stc13.repository.pojo.User;
import ru.innopolis.stc13.to.Restriction;
import ru.innopolis.stc13.utils.MD5Helper;

import java.time.LocalDateTime;
import java.util.*;

public class Service {

    static final Logger LOGGER = Logger.getLogger(Service.class);
    private final UserDao userDao;
    private final FlightDao flightDao;

    public Service(UserDao userDao, FlightDao flightDao) {
        this.userDao = userDao;
        this.flightDao = flightDao;
    }

    public boolean checkAuth(String login, String password) {
        if (login != null) {
            User user = userDao.getUserByLogin(login);
            LOGGER.info(String.format("add user by login %s", login));
            return user != null && user.getPassword().equals(MD5Helper.getMD5(password));
        }
        return false;
    }

    public boolean checkAuth(User user, String password) {
        return user != null && user.getPassword().equals(MD5Helper.getMD5(password));
    }

    public User getUserByLogin(String login) {
        if (login != null) {
            LOGGER.info(String.format("add user by login %s", login));
            return userDao.getUserByLogin(login);
        }
        return null;
    }

    public boolean add(String type, String number, String dateTime, String airCompany,
                       String airCraft, String pilotName, String gateNumber, String parkingSpot, String status) {
        if (type != null && number != null && dateTime != null && airCompany != null && airCraft != null && pilotName != null && status != null) {
            User user = getUserByLogin(pilotName);
            Flight flight = new Flight(FlightType.valueOf(type),
                    number,
                    LocalDateTime.parse(dateTime),
                    user.getAirCompany(),
                    airCraft,
                    user,
                    gateNumber,
                    parkingSpot,
                    FlightStatus.valueOf(status));
            LOGGER.info(String.format("add flight %s for pilot %s", flight.getId(), pilotName));
            return flightDao.add(flight);
        }
        return false;
    }

    public void update(String id, String gateNumber, String parkingSpot, String status) {
        if (id != null && status != null) {
            LOGGER.info(String.format("update flight %s for status %s", id, status));
            flightDao.update(id, gateNumber, parkingSpot, status);
        }
    }

    public List<Flight> getByPilot(String id) {
        if (id == null || id.isEmpty()) {
            return Collections.emptyList();
        }
        LOGGER.info(String.format("get flights for pilot %s", id));
        return flightDao.getByPilot(id);
    }

    public List<Flight> getAll() {
        LOGGER.info("get all flights");
        return flightDao.getAll();
    }

    public List<FlightType> getTypes() {
        return Arrays.asList(FlightType.values());
    }

    public List<FlightStatus> getAvailableStatuses() {
        List<FlightStatus> list = new ArrayList<>();
        list.add(FlightStatus.LANDING_REQUEST);
        list.add(FlightStatus.READY_FOR_BOARDING);
        list.add(FlightStatus.GATE_REQUEST);
        return list;
    }

    public List<FlightStatus> getStatuses() {
        return Arrays.asList(FlightStatus.values());
    }

    public Map<FlightStatus, Restriction> getRestrictions() {
        LOGGER.info("get restrictions");
        return flightDao.getRestrictions();
    }
}
