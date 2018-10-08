package ru.innopolis.stc13.repository.pojo;

public enum FlightStatus {

    LANDING_REQUEST("Landing request"),
    LANDING("Landing"),

    GATE_REQUEST("Gate request"),
    GATE_PARKING("Gate parking"),

//    DISEMBARKING("Disembarking"),
    DISEMBARKED("Disembarked"),
    PARKING("Parking"),
    FLIGHT_DONE("Flight done"),

    READY_FOR_BOARDING("Ready for boarding"),
    BOARDING("Boarding"),
    TAKEOFF_REQUEST("Take-off request"),
    TAKEOFF("Take-off"),
    AWAY("Away");

    private String title;

    FlightStatus(String title) {
        this.title = title;
    }

//    public String getTitle() {
//        return title;
//    }

    @Override
    public String toString() {
        return title;
    }
}
