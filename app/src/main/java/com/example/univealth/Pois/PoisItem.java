package com.example.univealth.Pois;

import java.io.Serializable;

public class PoisItem implements Serializable {
    String name;
    double lat;
    double lon;

    public void setName(String name) {
        this.name = name;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public PoisItem(String name, double lat, double lon){
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

}
