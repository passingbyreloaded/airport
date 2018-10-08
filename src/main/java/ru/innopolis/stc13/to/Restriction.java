package ru.innopolis.stc13.to;

import ru.innopolis.stc13.repository.pojo.FlightStatus;
import ru.innopolis.stc13.repository.pojo.Role;

import java.util.List;

public class Restriction {

    private Role responsibleRole;
    private List<FlightStatus> availableStatuses;
    private boolean gateEditable;
    private boolean parkingSpotEditable;

    public Restriction(Role responsibleRole, List<FlightStatus> availableStatuses, boolean gateEditable, boolean parkingSpotEditable) {
        this.responsibleRole = responsibleRole;
        this.availableStatuses = availableStatuses;
        this.gateEditable = gateEditable;
        this.parkingSpotEditable = parkingSpotEditable;
    }

    public Role getResponsibleRole() {
        return responsibleRole;
    }

    public List<FlightStatus> getAvailableStatuses() {
        return availableStatuses;
    }

    public boolean isGateEditable() {
        return gateEditable;
    }

    public boolean isParkingSpotEditable() {
        return parkingSpotEditable;
    }
}
