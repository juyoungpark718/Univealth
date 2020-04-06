package com.example.univealth.Search;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.univealth.ChangeBitmap;
import com.example.univealth.GpsTracker;
import com.example.univealth.MapPoint;
import com.example.univealth.Pois.AroundAsyncTask;
import com.example.univealth.Pois.PoisItem;
import com.example.univealth.R;
import com.example.univealth.RouteActivity;
import com.google.android.material.button.MaterialButton;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SearchAroundActivity extends AppCompatActivity {
    private GpsTracker gpsTracker;
    private ListView listView;
    private SelectedAdapter adapter;
    private ArrayList<TMapMarkerItem> selectedPoi;
    private ArrayList<MapPoint> selectedlist;
    private ConstraintLayout listViewLayout;
    private MapPoint dest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searcharound);
        MaterialButton button = findViewById(R.id.routebtn);
        ChangeBitmap changeBitmap = new ChangeBitmap(getApplicationContext());
        final TMapView tMapView = new TMapView(getApplicationContext());
        tMapView.setSKTMapApiKey("API KEY");
        Bitmap bitmap = changeBitmap.getBitmapFromVectorDrawable(R.drawable.ic_pin);
        Bitmap destIcon = changeBitmap.getBitmapFromVectorDrawable(R.drawable.ic_destination_24dp);
        gpsTracker = new GpsTracker(this);
//        double lat = gpsTracker.getLatitude();
//        double lon = gpsTracker.getLongitude();
        // 에뮬레이터에서는 현재 위치를 못잡아서 임의로 설정.
        double lat = 36.360884;
        double lon = 127.344569;

        // 현재 위치 표시
        tMapView.setLocationPoint(lon,lat);
        tMapView.setTrackingMode(true);
        tMapView.setIconVisibility(true);

        //목적지 표시
        Intent intent = getIntent();
        final MapPoint dest = (MapPoint)intent.getSerializableExtra("mapPoint");
        TMapPoint destPoint = new TMapPoint(dest.getLat(),dest.getLon());
        TMapMarkerItem destItem = new TMapMarkerItem();
        destItem.setIcon(destIcon);
        destItem.setTMapPoint(destPoint);
        destItem.setName(dest.getName());
        destItem.setCanShowCallout(true);
        destItem.setCalloutTitle(dest.getName());
        tMapView.addMarkerItem("dest",destItem);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), RouteActivity.class);
                intent1.putExtra("dest",dest);
                intent1.putExtra("passList",selectedlist);
                startActivityForResult(intent1,200);
            }
        });

        //Tmap에 listView 띄우기(경유지 클릭 시).
        FrameLayout last =  findViewById(R.id.aroundLayoutTmap);
        listViewLayout = findViewById(R.id.listViewLayout);
        listView = findViewById(R.id.selectedList);
        selectedPoi = new ArrayList<>();
        selectedlist = new ArrayList<>();
        adapter = new SelectedAdapter(selectedlist,this);
        listView.setAdapter(adapter);

        // 주변 정보 POI 가져오는 Thread
        AroundAsyncTask aroundAsyncTask = new AroundAsyncTask(SearchAroundActivity.this,tMapView,lat,lon);
        ArrayList<PoisItem> list = null;
        try {
            list = aroundAsyncTask.execute().get();
            // 가져온 주변 정보로 tmap에 pin들 추가.
            for(int i = 0 ; i < list.size() ; i++) {
                PoisItem poi = list.get(i);
                TMapPoint tpoint = new TMapPoint(poi.getLat(), poi.getLon());
                if(!(poi.getName().equals(dest.getName()))){
                    TMapMarkerItem tItem = new TMapMarkerItem();
                    tItem.setTMapPoint(tpoint);
                    tItem.setName(poi.getName());
                    tItem.setCanShowCallout(true);
                    tItem.setCalloutTitle(poi.getName());
                    tItem.setEnableClustering(true); // 클러스터링
                    tItem.setIcon(bitmap);
                    // 핀모양으로 된 마커를 사용할 경우 마커 중심을 하단 핀 끝으로 설정.
                    tItem.setPosition(0.5f, 1.0f);         // 마커의 중심점을 하단, 중앙으로 설정
                    tMapView.addMarkerItem(Integer.toString(i),tItem);
                }

            }

            // 주변 정보 PIN 클릭 시 list view에 item 추가.
            tMapView.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {
                @Override
                public boolean onPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                    for(int i = 0 ; i < arrayList.size() ; i++){
                        TMapMarkerItem item = arrayList.get(i);
//                        MapPoint pointItem = new MapPoint(item.getName(),item.getTMapPoint().getLatitude(),item.getTMapPoint().getLongitude());

                        if(!selectedPoi.contains(item)){
                            if(selectedPoi.size() < 3){
                                selectedPoi.add(item);
                            }
                        }else{
                            selectedPoi.remove(item);
                        }
                    }
                    if(selectedPoi.size() >= 1 ){
                        listViewLayout.setVisibility(View.VISIBLE);
                        System.out.println("@@@@@@@@@@@@@ adapter" + selectedPoi.size());
                        selected(selectedPoi);
                    }else{
                        listViewLayout.setVisibility(View.GONE);
                    }
                    return false;
                }

                @Override
                public boolean onPressUpEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                    return false;
                }
            });
            last.addView(tMapView,0);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void selected(ArrayList<TMapMarkerItem> list) {
        this.selectedlist.clear();
        for(int i=0 ; i<list.size() ; i++){
            this.selectedlist.add(new MapPoint(list.get(i).getName(),list.get(i).getTMapPoint().getLatitude(),list.get(i).getTMapPoint().getLongitude()));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200){
            if(resultCode == RESULT_OK){
                findViewById(R.id.listViewLayout).setVisibility(View.GONE);
                this.selectedlist.clear();
                this.selectedPoi.clear();
                adapter.notifyDataSetChanged();
            }
        }
    }
}
