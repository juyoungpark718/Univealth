package com.example.univealth.Pois;

public class PedestrianRouteInput {
    public  final int version = 1;
    public final double startX;
    public final double startY;
    public final double endX;
    public final double endY;
    public final String startName;
    public final String endName;
    public final String sort = "index";
    public final String  reqCoordType = "WGS84GEO";
    public final String resCoordType = "WGS84GEO";
    public final String passList;

    public PedestrianRouteInput(double startX, double startY, double endX, double endY, String startName, String endName, String passList) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.startName = startName;
        this.endName = endName;
        this.passList = passList;
    }
}
