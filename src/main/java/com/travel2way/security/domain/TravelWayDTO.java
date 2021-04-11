package com.travel2way.security.domain;

public class TravelWayDTO {
    private int id;
    private String place;
    private String fullAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public TravelWayDTO(int id, String place, String fullAddress) {
        this.id = id;
        this.place = place;
        this.fullAddress = fullAddress;
    }
}
