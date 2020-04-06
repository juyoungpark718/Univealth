package com.example.univealth.Pois;

import com.skt.Tmap.TMapData;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pois {
    TMapData tmapData;
    private Retrofit mRetrofit;
    private TmapAPI mtmapAPI;
    private Call<PoisRepo> mCallPoisList;
    private Call<PoisRepo> mCallAroundPois;
    private Call<PedestrianRouteData> mCallPedestrianRoute;
    private ArrayList<PedestrianRouteData.Features> routeList;
    private ArrayList<PoisItem> poisList;

    public Pois (){
        setRetrofitInit();
    }

    private void setRetrofitInit() {
        mRetrofit = new Retrofit.Builder().baseUrl("https://apis.openapi.sk.com/").addConverterFactory(GsonConverterFactory.create()).build();
        mtmapAPI = mRetrofit.create(TmapAPI.class);
    }

    public void callPoisList(String keyword) throws IOException {
        ArrayList<PoisItem> poiList = new ArrayList<PoisItem>();
        mCallPoisList = mtmapAPI.getSearchList(keyword,3);
        Response<PoisRepo> response = mCallPoisList.execute();
        PoisRepo result = response.body();
        if(result == null){
            poiList = new ArrayList<PoisItem>();
        }else {
            for (int i = 0; i < result.getPoiInfo().getPois().getPoi().size(); i++) {
                PoisRepo.poiInfo.pois.Poi temp = result.getPoiInfo().getPois().getPoi().get(i);
                PoisItem item = new PoisItem(temp.getName(), Double.parseDouble(temp.getFrontLat()), Double.parseDouble(temp.getFrontLon()));
                poiList.add(item);
            }
        }
        setPoiList(poiList);
    }

    public void callAroundPois(double lat, double lon) throws IOException {
        ArrayList<PoisItem> poiList = new ArrayList<PoisItem>();
        mCallAroundPois = mtmapAPI.getPoisList(1,lon,lat,100,9,"주요시설물");
        Response<PoisRepo> response = mCallAroundPois.execute();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@" + response.code());
        PoisRepo result = response.body();
        if(result == null){
            poiList = new ArrayList<PoisItem>();
        }else{
            for(int i = 0 ; i < result.getPoiInfo().getPois().getPoi().size() ;i++){
                PoisRepo.poiInfo.pois.Poi temp = result.getPoiInfo().getPois().getPoi().get(i);
                PoisItem item = new PoisItem(temp.getName(), Double.parseDouble(temp.getFrontLat()), Double.parseDouble(temp.getFrontLon()));
                poiList.add(item);
            }
        }
        setPoiList(poiList);
    }

    public void callPedestrianRoute(double startX, double startY, double endX, double endY, String startName, String endName, String passList) throws IOException {
        // double startX,double startY,double endX,double endY, String startName, String endName
        PedestrianRouteInput p = new PedestrianRouteInput(startX,startY,endX,endY,
                startName,endName,passList);
        mCallPedestrianRoute = mtmapAPI.getpedestrianRoute(p);
        Response<PedestrianRouteData> response = mCallPedestrianRoute.execute();
        PedestrianRouteData result = response.body();
        setRouteList(result.getFeatures());
    }
    private  void setRouteList(ArrayList<PedestrianRouteData.Features> routeList){ this.routeList = routeList;}
    public  ArrayList<PedestrianRouteData.Features> getRouteList(){ return this.routeList; }

    private void setPoiList(ArrayList<PoisItem> poiList){
        this.poisList = poiList;
    }
    public ArrayList<PoisItem> getPoisList(){
        return this.poisList;
    }

//    private Callback<PoisRepo> mtmapCallback = new Callback<PoisRepo>() {
//        ArrayList<PoisItem> poiList = new ArrayList<PoisItem>();
//        @Override
//        public void onResponse(Call<PoisRepo> call, Response<PoisRepo> response) {
//            PoisRepo result = response.body();
//            for(int i = 0 ; i < result.getPoiInfo().getPois().getPoi().size() ;i++){
//                PoisRepo.poiInfo.pois.Poi temp = result.getPoiInfo().getPois().getPoi().get(i);
//                PoisItem item = new PoisItem(temp.getName(),Double.parseDouble(temp.getFrontLat()),Double.parseDouble(temp.getFrontLon()));
//                System.out.println("name : " + item.name);
//                poiList.add(item);
//            }
//            setPoiList(poiList);
//            response.errorBody().close();
//        }
//        @Override
//        public void onFailure(Call<PoisRepo> call, Throwable t) {
//            t.printStackTrace();
//        }
//    };

}
