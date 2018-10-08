package ru.innopolis.stc13.repository.pojo;

import java.util.UUID;

public class User {

    private String id;
    private String name;
    private String password;
    private Role role;
    private AirCompany airCompany;

    public User() {
    }

    public User(String name, String password, Role role, AirCompany airCompany) {
        this(UUID.randomUUID().toString(), name, password, role, airCompany);
    }

    public User(String id, String name, String password, Role role, AirCompany airCompany) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.airCompany = airCompany;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AirCompany getAirCompany() {
        return airCompany;
    }

    public void setAirCompany(AirCompany airCompany) {
        this.airCompany = airCompany;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
