package com.example.univealth.Pois;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TmapAPI {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json; charset=UTF-8",
            "appKey: "
    })
    @GET("/tmap/pois/search/around")
    Call<PoisRepo> getPoisList(@Query("version") int version, @Query("centerLon") double centerLon, @Query("centerLat") double centerLat, @Query("count") int count, @Query("radius") int radius, @Query("categories") String categories);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json; charset=UTF-8",
            "appKey: "
    })
    @GET("/tmap/pois")
    Call<PoisRepo> getSearchList(@Query("searchKeyword") String keyword, @Query("count") int count);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json; charset=UTF-8",
            "appKey: "
    })
    @POST("/tmap/routes/pedestrian")
    Call<PedestrianRouteData> getpedestrianRoute(@Body PedestrianRouteInput p);
}