package com.example.univealth.Pois;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PedestrianRouteData {
    @SerializedName("type")
    String type;
    @SerializedName("features")
    ArrayList<Features> features;

    public String getType() {
        return type;
    }

    public ArrayList<Features> getFeatures(){return features;}

    public class Features{
        @SerializedName("type")
        String type;
        @SerializedName("geometry")
        Geometry geometry;
        @SerializedName("properties")
        FeaturesProp properties;

        public String getType() {
            return type;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public FeaturesProp getProperties() {
            return properties;
        }

        public class Geometry{
            @SerializedName("type")
            String type;

            @SerializedName("coordinates")
            ArrayList<Object> coordinates;

            public String getType() {
                return type;
            }

            public ArrayList<Object> getCoordinates() {
                return coordinates;
            }
        }

        public class FeaturesProp {
            @SerializedName("totalDistance")
            String totalDistance;
            @SerializedName("totalTime")
            double totaltime;
            @SerializedName("index")
            int index;
            @SerializedName("pointIndex")
            int pointIndex;
            @SerializedName("name")
            String name;
            @SerializedName("description")
            String description;
            @SerializedName("direction")
            String direction;
            @SerializedName("nearPoiName")
            String nearPoiName;
            @SerializedName("nearPoiX")
            String nearPoiX;
            @SerializedName("nearPoiY")
            String nearPoiY;
            @SerializedName("intersectionName")
            String intersectionName;
            @SerializedName("facilityType")
            String facilityType;
            @SerializedName("facilityName")
            String facilityName;
            @SerializedName("turnType")
            int turnType;
            @SerializedName("pointType")
            String pointType;

            public String getTotalDistance() {
                return totalDistance;
            }

            public double getTotaltime() {
                return totaltime;
            }

            public int getIndex() {
                return index;
            }

            public int getPointIndex() {
                return pointIndex;
            }

            public String getName() {
                return name;
            }

            public String getDescription() {
                return description;
            }

            public String getDirection() {
                return direction;
            }

            public String getNearPoiName() {
                return nearPoiName;
            }

            public String getNearPoiX() {
                return nearPoiX;
            }

            public String getNearPoiY() {
                return nearPoiY;
            }

            public String getIntersectionName() {
                return intersectionName;
            }

            public String getFacilityType() {
                return facilityType;
            }

            public String getFacilityName() {
                return facilityName;
            }

            public int getTurnType() {
                return turnType;
            }

            public String getPointType() {
                return pointType;
            }
        }


    }
}
