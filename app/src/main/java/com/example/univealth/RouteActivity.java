package com.example.univealth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.univealth.Search.SearchAroundActivity;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.ParserConfigurationException;

public class RouteActivity extends AppCompatActivity {
    private GpsTracker gpsTracker;
    private MapPoint dest;
    ArrayList<MapPoint> passList;
    ArrayList<TMapPoint> passListForTmap = new ArrayList<>();
    String passList_str = "";
    String startName;
    String endName;
    TMapPolyLine routeLine;
    TMapView tMapView;
//    UserInfo userInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        UserInfo userInfo = new UserInfo(this);
        setContentView(R.layout.activity_route);
        gpsTracker = new GpsTracker(getApplicationContext());
        ChangeBitmap changeBitmap = new ChangeBitmap(getApplicationContext());
        Bitmap bitmap = changeBitmap.getBitmapFromVectorDrawable(R.drawable.ic_pin);
        Bitmap destIcon = changeBitmap.getBitmapFromVectorDrawable(R.drawable.ic_destination_24dp);

        //Tmap을 띄울 FrameLayout
        FrameLayout frameLayout = findViewById(R.id.routeTmap);
        //Tmap 세팅
        tMapView = new TMapView(getApplicationContext());
        tMapView.setSKTMapApiKey("TMAP API KEY");

        //intent에서 목적지와 경유지를 받음.
        Intent intent = getIntent();
        dest = (MapPoint)intent.getSerializableExtra("dest");
        passList = (ArrayList<MapPoint>)intent.getSerializableExtra("passList");

        //TmapMarker를 이용해 tMapView에 목적지 띄움
        TMapMarkerItem destItem = new TMapMarkerItem();
        destItem.setTMapPoint(new TMapPoint(dest.getLat(),dest.getLon()));
        destItem.setIcon(destIcon);
        destItem.setName(dest.getName());
        destItem.setCanShowCallout(true);
        destItem.setCalloutTitle(dest.getName());
        tMapView.addMarkerItem("dest",destItem);

        //경유지들을 Tmap에 띄움.
        for(MapPoint p:passList){
            passListForTmap.add(new TMapPoint(p.getLat(),p.getLon()));
            TMapMarkerItem pass = new TMapMarkerItem();
            pass.setTMapPoint(new TMapPoint(p.getLat(),p.getLon()));
            pass.setIcon(bitmap);
            pass.setName(p.getName());
            pass.setCanShowCallout(true);
            pass.setCalloutTitle(p.getName());
            tMapView.addMarkerItem(p.getName(),pass);
            passList_str += p.getLon()+","+p.getLat()+"_";
        }
        //API에 보낼 passList형식.
        passList_str = passList_str.substring(0,passList_str.length()-1);

        //목적지 도착지 UTF-8 처리
        try {
            startName = URLEncoder.encode("현재 위치", "UTF-8");
            endName = URLEncoder.encode(dest.getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        // 현재 위치를 받아옴
//        final double lat = gpsTracker.getLatitude();
//        final double lon = gpsTracker.getLongitude();

        // 에뮬 확인을 위한 고정값
        final double lon = 127.344569;
        final double lat = 36.360884;
        tMapView.setLocationPoint(lon, lat);
        tMapView.setIconVisibility(true);
        tMapView.setTrackingMode(true);

        @SuppressLint("StaticFieldLeak") AsyncTask asyncTask = new AsyncTask() {
            @SuppressLint("WrongThread")
            @Override
            protected Object doInBackground(Object[] objects) {
                TMapData tMapData = new TMapData();
                TMapPolyLine tMapPolyLine = null;
                try {
                    tMapPolyLine = tMapData.findPathDataWithType(TMapData.TMapPathType.PEDESTRIAN_PATH,new TMapPoint(lat,lon),new TMapPoint(dest.getLat(),dest.getLon()),passListForTmap,0);
                    tMapPolyLine.setLineColor(Color.BLUE);
                    tMapPolyLine.setLineWidth(2);
                    tMapView.addTMapPolyLine("line1",tMapPolyLine);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                return tMapPolyLine;
            }
        };

        try {
            //시간 가져오기
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("a hh:mm");
            TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");
            simpleDateFormat.setTimeZone(timeZone);
            Calendar cal = Calendar.getInstance(timeZone);

            routeLine = (TMapPolyLine) asyncTask.execute().get();
            double dist = Math.round(routeLine.getDistance()/1000.000);
            cal.add(Calendar.MINUTE,(int)Math.round(dist/0.07)); // 현재 시간에 걸리는 시간 더함.
            TextView distance = findViewById(R.id.distance);
            TextView addTime = findViewById(R.id.addTime);
            TextView takeTime = findViewById(R.id.takeTime);
            TextView kcalView = findViewById(R.id.kcalView);
            distance.setText(dist +"Km");
            if(simpleDateFormat.format(cal.getTime()).split(" ")[0].equals("PM")){
                addTime.setText("오후 " + simpleDateFormat.format(cal.getTime()).split(" ")[1]);
            }else{
                addTime.setText("오전 " + simpleDateFormat.format(cal.getTime()).split(" ")[1]);
            }

            takeTime.setText((int) Math.round(dist / 0.07) +"분"); // 시속 4km/h => 0.07km/m  거리 / 0.07 걸리는 시간표시
            kcalView.setText(50 +"Kcal");

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //        X => ex)127.2030, Y => ex)36.29230
//        PedestrianAsyncTask pedestrianAsyncTask = new PedestrianAsyncTask(RouteActivity.this,lon,lat,dest.getLon(),dest.getLat(),startName,endName,passList_str);
//        ArrayList<PedestrianRouteData.Features> list = null;
//        try {
//            list = pedestrianAsyncTask.execute().get();
//            if(list != null){
//                for(int i=0 ; i<list.size();i++){
//                    if(list.get(i).getGeometry().getType().equals("Point")){
//                        if(i == list.size()-1){
//                            //TMapPolyLine tMapPolyLine = tmapdata.findPathDataWithType(TMapPathType.CAR_PATH, point1, point2);
//                            TMapData tMapData = new TMapData();
//                            String endName = URLDecoder.decode(list.get(i).getProperties().getName(),"UTF-8");
//                            System.out.println(endName);
//                        }else{
//                            System.out.println(list.get(i).getProperties().getName());
//                        }
//                    }
//                }
//            }
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }


        frameLayout.addView(tMapView,0);
        Intent intent2 = new Intent(getApplicationContext(),SearchAroundActivity.class);
        setResult(RESULT_OK,intent2);
    }
}
