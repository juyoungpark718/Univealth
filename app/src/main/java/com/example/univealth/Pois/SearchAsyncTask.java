package com.example.univealth.Pois;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

public class SearchAsyncTask extends AsyncTask<String,Pois, ArrayList<PoisItem>> {

//    ProgressDialog progressDialog;
//    TMapView tMapView;
    String searchword;
    Context context;
    public SearchAsyncTask(Context context, String searchword){
//        this.progressDialog = new ProgressDialog(context);
        this.context = context;
        this.searchword = searchword;
    }

    @Override
    protected void onPreExecute() {
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.setMessage("검색중입니다. 잠시만 기다려주세요.");
//
//        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList o) {
//        progressDialog.dismiss();
        super.onPostExecute(o);
    }

    @Override
    protected void onProgressUpdate(Pois[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<PoisItem> doInBackground(String[] strings) {
        Pois pois = new Pois();
        ArrayList<PoisItem> list = null;
        try {
            pois.callPoisList(searchword);
            list = pois.getPoisList();

//            for(int i = 0 ; i < list.size() ; i++) {
//                Pois.PoisItem poi = list.get(i);
//                TMapPoint tpoint = new TMapPoint(poi.getLat(), poi.getLon());
//                TMapMarkerItem tItem = new TMapMarkerItem();
//                tItem.setTMapPoint(tpoint);
//                tItem.setName(poi.getName());
//                tItem.setVisible(TMapMarkerItem.VISIBLE);
//                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.current_pin);
//                tItem.setIcon(bitmap);
//
//                // 핀모양으로 된 마커를 사용할 경우 마커 중심을 하단 핀 끝으로 설정.
//                tItem.setPosition(0.5f, 1.0f);         // 마커의 중심점을 하단, 중앙으로 설정
//                tMapView.addMarkerItem(Integer.toString(i),tItem);
//            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return list;
        }
    }
}
