package ru.innopolis.stc13.repository.pojo;

import java.util.UUID;

public class AirCompany {

    private String id;
    private String name;

    public AirCompany() {
    }

    public AirCompany(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public AirCompany(String id, String name) {
        this.id = id;
        this.name = name;
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

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }
}
