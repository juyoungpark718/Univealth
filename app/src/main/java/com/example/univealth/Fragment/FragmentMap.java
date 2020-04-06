package com.example.univealth.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.univealth.GpsTracker;
import com.example.univealth.R;
import com.example.univealth.Search.SearchDestActivity;
import com.skt.Tmap.TMapView;

public class FragmentMap extends Fragment {
    private GpsTracker gpsTracker;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map,container, false);

        FrameLayout last =  v.findViewById(R.id.frameLayoutTmap);
        TextView textView = v.findViewById(R.id.search);

        final TMapView tMapView = new TMapView(getContext());
        tMapView.setSKTMapApiKey("TMap API KEY");

        gpsTracker = new GpsTracker(getContext());
        double lat = gpsTracker.getLatitude();
        double lon = gpsTracker.getLongitude();

//        tMapView.setLocationPoint(lon,lat);
        tMapView.setLocationPoint(127.344569, 36.360884);
        tMapView.setIconVisibility(true);
        tMapView.setTrackingMode(true);

        last.addView(tMapView,0);
        textView.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchDestActivity.class);
                intent.putExtra("activity","FragmentMap");
                startActivityForResult(intent,200);
            }
        });
        return v;
    }
}

