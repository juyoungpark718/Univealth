package com.example.univealth.Search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.univealth.MapPoint;
import com.example.univealth.Pois.PoisItem;
import com.example.univealth.R;
import com.example.univealth.TimeTable.InsertActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-08-07.
 */

public class SearchAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PoisItem> list;
    private LayoutInflater inflate;
    private ViewHolder viewHolder;
    private String startActivity;

    public SearchAdapter(ArrayList<PoisItem> list, Context context,String startActivity){
        this.list = list;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
        this.startActivity = startActivity;
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflate.inflate(R.layout.row_listview,null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    if(startActivity.equals("FragmentMap")){
                        MapPoint mapPoint = new MapPoint(list.get(position).getName(),list.get(position).getLat(),list.get(position).getLon());
                        intent = new Intent(context,SearchAroundActivity.class);
                        intent.putExtra("mapPoint",mapPoint);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    }else{
                        intent = new Intent(context, InsertActivity.class);
                        intent.putExtra("classroom",list.get(position).getName());
                        AppCompatActivity activity = (AppCompatActivity) context;
                        activity.setResult(Activity.RESULT_OK,intent);
                        activity.finish();

                    }
                }
            });

            viewHolder = new ViewHolder();
            viewHolder.label = (TextView) convertView.findViewById(R.id.label);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // 리스트에 있는 데이터를 리스트뷰 셀에 뿌린다.
        viewHolder.label.setText(list.get(position).getName());

        return convertView;
    }

    static class ViewHolder{
        public TextView label;
    }

}
