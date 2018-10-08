package ru.innopolis.stc13.to;

import ru.innopolis.stc13.repository.pojo.*;

import java.time.LocalDateTime;
import java.util.List;

public class FlightWithRestrictions {

    private String id;
    private FlightType flightType;
    private String number;
    private LocalDateTime dateTime;
    private AirCompany airCompany;
    private String aircraft;
    private User pilot;
    private String gateNumber;
    private String parkingSpot;
    private FlightStatus status;

    private Role responsibleRole;
    private List<FlightStatus> availableStatuses;
    private boolean gateEditable;
    private boolean parkingSpotEditable;

    public FlightWithRestrictions(Flight flight, Restriction restriction) {
        this.id = flight.getId();
        this.flightType = flight.getFlightType();
        this.number = flight.getNumber();
        this.dateTime = flight.getDateTime();
        this.airCompany = flight.getAirCompany();
        this.aircraft = flight.getAircraft();
        this.pilot = flight.getPilot();
        this.gateNumber = flight.getGateNumber();
        this.parkingSpot = flight.getParkingSpot();
        this.status = flight.getStatus();
        this.responsibleRole = restriction.getResponsibleRole();
        this.availableStatuses = restriction.getAvailableStatuses();
        this.gateEditable = restriction.isGateEditable();
        this.parkingSpotEditable = restriction.isParkingSpotEditable();
    }

    public Role getResponsibleRole() {
        return responsibleRole;
    }

    public void setResponsibleRole(Role responsibleRole) {
        this.responsibleRole = responsibleRole;
    }

    public List<FlightStatus> getAvailableStatuses() {
        return availableStatuses;
    }

    public void setAvailableStatuses(List<FlightStatus> availableStatuses) {
        this.availableStatuses = availableStatuses;
    }

    public boolean isGateEditable() {
        return gateEditable;
    }

    public void setGateEditable(boolean gateEditable) {
        this.gateEditable = gateEditable;
    }

    public boolean isParkingSpotEditable() {
        return parkingSpotEditable;
    }

    public void setParkingSpotEditable(boolean parkingSpotEditable) {
        this.parkingSpotEditable = parkingSpotEditable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public AirCompany getAirCompany() {
        return airCompany;
    }

    public void setAirCompany(AirCompany airCompany) {
        this.airCompany = airCompany;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public User getPilot() {
        return pilot;
    }

    public void setPilot(User pilot) {
        this.pilot = pilot;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(String parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }
}
