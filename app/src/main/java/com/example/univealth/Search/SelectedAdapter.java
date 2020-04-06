package com.example.univealth.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.univealth.MapPoint;
import com.example.univealth.R;
import com.example.univealth.Search.SearchAdapter;
import com.skt.Tmap.TMapMarkerItem;

import java.util.ArrayList;

public class SelectedAdapter extends BaseAdapter {

    private  ArrayList<MapPoint> list;
    private  Context context;
    private LayoutInflater inflate;
    private SearchAdapter.ViewHolder viewHolder;

    public SelectedAdapter(ArrayList<MapPoint> list, Context context){
        this.list = list;
        this.context = context;
        this.inflate = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflate.inflate(R.layout.row_listview,null);
            viewHolder = new SearchAdapter.ViewHolder();
            viewHolder.label = (TextView)convertView.findViewById(R.id.label);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (SearchAdapter.ViewHolder)convertView.getTag();
        }

        viewHolder.label.setText(list.get(position).getName());

        return convertView;
    }
}
