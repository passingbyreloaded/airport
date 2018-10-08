package ru.innopolis.stc13.repository.dao;

import ru.innopolis.stc13.repository.connectionManager.ConnectionManager;
import ru.innopolis.stc13.repository.connectionManager.ConnectionManagerJdbcImpl;
import ru.innopolis.stc13.repository.dao.daoint.FlightDao;
import ru.innopolis.stc13.repository.pojo.*;
import ru.innopolis.stc13.to.Restriction;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class FlightDaoImpl implements FlightDao {

    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();

    @Override
    public boolean add(Flight flight) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO flights VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, flight.getId());
            preparedStatement.setString(2, flight.getFlightType().name());
            preparedStatement.setString(3, flight.getNumber());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(flight.getDateTime()));
            preparedStatement.setString(5, flight.getAirCompany().getId());
            preparedStatement.setString(6, flight.getAircraft());
            preparedStatement.setString(7, flight.getPilot().getId());
            preparedStatement.setString(8, flight.getGateNumber());
            preparedStatement.setString(9, flight.getParkingSpot());
            preparedStatement.setString(10, flight.getStatus().name());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Flight get(String id) {
        return null;
    }

    @Override
    public boolean update(Flight flight) {
        return false;
    }

    @Override
    public boolean update(String id, String gateNumber, String parkingSpot, String status) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement;
            if (gateNumber == null && parkingSpot == null) {
                preparedStatement = connection.prepareStatement("UPDATE flights SET status=? WHERE id=?");
                preparedStatement.setString(2, id);
            } else if (gateNumber != null) {
                preparedStatement = connection.prepareStatement("UPDATE flights SET " +
                        "status=?, gate_number=? WHERE id=?");
                preparedStatement.setString(2, gateNumber);
                preparedStatement.setString(3, id);
            } else {
                preparedStatement = connection.prepareStatement("UPDATE flights SET " +
                        "status=?, parking_spot=? WHERE id=?");
                preparedStatement.setString(2, parkingSpot);
                preparedStatement.setString(3, id);
            }
            preparedStatement.setString(1, status);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Flight> getByPilot(String id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select " +
                     "flights.*, " +
                     "companies.name as company_name, " +
                     "users.name as pilot_name " +
                     "from flights " +
                     "left join companies on flights.company = companies.id " +
                     "left join users on flights.pilot = users.id " +
                     "where flights.pilot = ? and status <> ? and status <> ?")) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, "FLIGHT_DONE");
            preparedStatement.setString(3, "AWAY");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Flight> list = new ArrayList<>();
            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getString("id"),
                        FlightType.valueOf(resultSet.getString("type")),
                        resultSet.getString("number"),
                        resultSet.getTimestamp("date_time").toLocalDateTime(),
                        new AirCompany(resultSet.getString("company"), resultSet.getString("company_name")),
                        resultSet.getString("aircraft"),
                        new User(id, resultSet.getString("pilot_name"), null, null, null),
                        resultSet.getString("gate_number"),
                        resultSet.getString("parking_spot"),
                        FlightStatus.valueOf(resultSet.getString("status")));
                list.add(flight);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Flight> getAll() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select " +
                     "flights.*, " +
                     "companies.name as company_name, " +
                     "users.name as pilot_name " +
                     "from flights " +
                     "left join companies on flights.company = companies.id " +
                     "left join users on flights.pilot = users.id " +
                     "where status <> ? and status <> ?")) {
            preparedStatement.setString(1, "FLIGHT_DONE");
            preparedStatement.setString(2, "AWAY");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Flight> list = new ArrayList<>();
            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getString("id"),
                        FlightType.valueOf(resultSet.getString("type")),
                        resultSet.getString("number"),
                        resultSet.getTimestamp("date_time").toLocalDateTime(),
                        new AirCompany(resultSet.getString("company"), resultSet.getString("company_name")),
                        resultSet.getString("aircraft"),
                        new User(resultSet.getString("pilot"), resultSet.getString("pilot_name"), null, null, null),
                        resultSet.getString("gate_number"),
                        resultSet.getString("parking_spot"),
                        FlightStatus.valueOf(resultSet.getString("status")));
                list.add(flight);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Map<FlightStatus, Restriction> getRestrictions() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * " +
                     "from statuses " +
                     "where status <> ? and status <> ?")) {
            preparedStatement.setString(1, "FLIGHT_DONE");
            preparedStatement.setString(2, "AWAY");
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<FlightStatus, Restriction> map = new ConcurrentHashMap<>();
            while (resultSet.next()) {
                map.put(FlightStatus.valueOf(resultSet.getString("status")),
                        new Restriction(Role.valueOf(resultSet.getString("role")),
                                Arrays.stream(resultSet.getString("available_statuses")
                                        .split(" "))
                                        .map(FlightStatus::valueOf)
                                        .collect(Collectors.toList()),
                                resultSet.getBoolean("gate_editable"),
                                resultSet.getBoolean("spot_editable")));
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
