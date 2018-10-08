package ru.innopolis.stc13.repository.dao.daoint;

import ru.innopolis.stc13.repository.pojo.Flight;
import ru.innopolis.stc13.repository.pojo.FlightStatus;
import ru.innopolis.stc13.to.Restriction;

import java.util.List;
import java.util.Map;

public interface FlightDao {

    boolean add(Flight flight);

    Flight get(String id);

    boolean update(Flight flight);

    boolean update(String id, String gateNumber, String parkingSpot, String status);

    boolean delete(String id);

    List<Flight> getByPilot(String id);

    List<Flight> getAll();

    Map<FlightStatus, Restriction> getRestrictions();
}
