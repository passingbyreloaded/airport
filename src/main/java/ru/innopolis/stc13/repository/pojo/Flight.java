package ru.innopolis.stc13.repository.pojo;

import java.time.LocalDateTime;
import java.util.UUID;

public class Flight {

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

    public Flight() {
    }

    public Flight(FlightType flightType, String number, LocalDateTime dateTime, AirCompany airCompany, String aircraft, User pilot, String gateNumber, String parkingSpot, FlightStatus status) {
        this(UUID.randomUUID().toString(), flightType, number, dateTime, airCompany, aircraft, pilot, gateNumber, parkingSpot, status);
    }

    public Flight(String id, FlightType flightType, String number, LocalDateTime dateTime, AirCompany airCompany, String aircraft, User pilot, String gateNumber, String parkingSpot, FlightStatus status) {
        this.id = id;
        this.flightType = flightType;
        this.number = number;
        this.dateTime = dateTime;
        this.airCompany = airCompany;
        this.aircraft = aircraft;
        this.pilot = pilot;
        this.gateNumber = gateNumber;
        this.parkingSpot = parkingSpot;
        this.status = status;
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

    @Override
    public String toString() {
        return "Flight{" +
                "number='" + number + '\'' +
                ", dateTime=" + dateTime +
                ", airCompany=" + airCompany +
                '}';
    }
}
