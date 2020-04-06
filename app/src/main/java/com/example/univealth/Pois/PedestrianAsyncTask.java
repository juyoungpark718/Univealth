package com.example.univealth.Pois;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

public class PedestrianAsyncTask extends AsyncTask<Object, Object, ArrayList>{
//    ProgressDialog progressDialog;
    public final double startX;
    public final double startY;
    public final double endX;
    public final double endY;
    public final String startName;
    public final String endName;
    public final String passList;

    public PedestrianAsyncTask(Context context, double startX, double startY, double endX, double endY, String startName, String endName, String passList) {
//        progressDialog = new ProgressDialog(context);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.startName = startName;
        this.endName = endName;
        this.passList = passList;
    }

    @Override
    protected void onPreExecute() {
//        if(progressDialog != null){
//            progressDialog.dismiss();
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setMessage("경로 탐색중입니다. 잠시만 기다려주세요.");
//            progressDialog.show();
//        }else {
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setMessage("경로 탐색중입니다. 잠시만 기다려주세요.");
//
//            progressDialog.show();
//        }
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList o) {
//        progressDialog.dismiss();
        super.onPostExecute(o);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected ArrayList<PedestrianRouteData.Features> doInBackground(Object[] objects) {
        Pois pois = new Pois();
        ArrayList<PedestrianRouteData.Features> list = null;
        try {
            pois.callPedestrianRoute(startX,startY,endX,endY,startName,endName,passList);
            list = pois.getRouteList();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return list;
        }
    }
}
