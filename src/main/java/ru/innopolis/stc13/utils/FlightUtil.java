package ru.innopolis.stc13.utils;

import ru.innopolis.stc13.repository.pojo.Flight;
import ru.innopolis.stc13.repository.pojo.FlightStatus;
import ru.innopolis.stc13.to.FlightWithRestrictions;
import ru.innopolis.stc13.to.Restriction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlightUtil {

    private FlightUtil() {
    }

    public static List<FlightWithRestrictions> getWithRestrictions(List<Flight> flights, Map<FlightStatus, Restriction> restrictions) {
        return flights.stream()
                .map(flight -> new FlightWithRestrictions(flight, restrictions.get(flight.getStatus())))
                .collect(Collectors.toList());
    }
}
