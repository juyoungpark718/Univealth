package com.example.univealth.Pois;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PoisRepo {
    @SerializedName("searchPoiInfo")
    poiInfo poiInfo;

    public poiInfo getPoiInfo(){
        return poiInfo;
    }

    public class poiInfo{
        @SerializedName("totalCount")
        String totalCount;
        @SerializedName("count")
        String count;
        @SerializedName("page")
        String page;
        @SerializedName("pois")
        pois pois;

        public String getTotalCount(){
            return totalCount;
        }

        public String getCount(){
            return count;
        }

        public String getPage(){
            return page;
        }

        public PoisRepo.poiInfo.pois getPois() {
            return pois;
        }

        public class pois{
            @SerializedName("poi")
            public List<Poi> Poi = new ArrayList<>();
            public List<Poi> getPoi(){return Poi;}

            public class Poi{
                @SerializedName("id")
                String id;
                @SerializedName("name")
                String name;
                @SerializedName("upperAddrName")
                String upperAddrName;
                @SerializedName("middleAddrName")
                String middleAddrName;
                @SerializedName("lowerAddrName")
                String lowerAddrName;
                @SerializedName("detailAddrName")
                String detailAddrName;
                @SerializedName("frontLat")
                String frontLat;
                @SerializedName("frontLon")
                String frontLon;
                @SerializedName("noorLat")
                String noorLat;
                @SerializedName("noorLon")
                String noorLon;

                public String getId() {
                    return id;
                }

                public String getName() {
                    return name;
                }

                public String getFrontLat() {
                    return frontLat;
                }

                public String getFrontLon() {
                    return frontLon;
                }

                public String getNoorLat() {
                    return noorLat;
                }

                public String getNoorLon() {
                    return noorLon;
                }

                public String getUpperAddrName() {
                    return upperAddrName;
                }

                public String getMiddleAddrName() {
                    return middleAddrName;
                }

                public String getLowerAddrName() {
                    return lowerAddrName;
                }
                public String getDetailAddrName() {
                    return detailAddrName;
                }
            }
        }

    }
}


