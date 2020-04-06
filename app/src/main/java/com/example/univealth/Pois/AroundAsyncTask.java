package com.example.univealth.Pois;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import com.example.univealth.R;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AroundAsyncTask extends AsyncTask<String,Pois, ArrayList> {
    ProgressDialog progressDialog;
    TMapView tMapView;
    Context context;
    double lat;
    double lon;

    public AroundAsyncTask(Context context, TMapView tMapView, double lat, double lon){
        this.progressDialog = new ProgressDialog(context);
        this.tMapView = tMapView;
        this.context = context;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    protected void onPreExecute() {
        if(progressDialog != null){
            progressDialog.dismiss();
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("주변 검색중입니다. 잠시만 기다려주세요.");

            progressDialog.show();
        }else {
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("주변 검색중입니다. 잠시만 기다려주세요.");

            progressDialog.show();
        }
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList o) {
        progressDialog.dismiss();
        super.onPostExecute(o);
    }

    @Override
    protected void onProgressUpdate(Pois[] values) {
        super.onProgressUpdate(values);
    }

    @SuppressLint("WrongThread")
    @Override
    protected ArrayList doInBackground(String[] strings) {
        Pois pois = new Pois();
        ArrayList<PoisItem> list = null;
        try {
            pois.callAroundPois(this.lat,this.lon);
            list = pois.getPoisList();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return list;
        }
    }
}
