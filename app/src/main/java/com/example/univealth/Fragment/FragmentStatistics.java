package com.example.univealth.Fragment;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.univealth.DBHelper;
import com.example.univealth.MyMarkerView;
import com.example.univealth.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class FragmentStatistics extends Fragment {
    private DBHelper dbHelper;
    private LineChart lineChart;
    ArrayList<Entry> entries = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_statistics,container, false);

        dbHelper = new DBHelper(getContext(), "TimeTable.db", null, 1);



        lineChart = (LineChart) v.findViewById(R.id.statisticschart);
        //테스트용 버튼들
        v.findViewById(R.id.statisticsstepcountBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAmountofActivity(view, entries);
//                getAmountofActivity(view);
                showstatisticschart();
            }
        });



        return v;
    }


    //활동량 DB에서 가져옴, 걸음수만 가져와서 나머지는 계산
    public void getAmountofActivity(View v, ArrayList<Entry> entries){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar time = new GregorianCalendar(Locale.KOREA);
        int dayNum = time.get(Calendar.DAY_OF_WEEK)-1;
        int[] temp = new int[7];
        System.out.println(dayNum);
        for(int i=0;i<7;i++){//테스트 지난 6일 불러오기

            String ID = simpleDateFormat.format(time.getTime());
            System.out.println("ID:"+ID);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String sql = "SELECT * FROM ACTIVITYTABLE WHERE ID = '" + ID + "'";
            System.out.println(sql);
            Cursor cursor = db.rawQuery(sql, null);
            try{
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
//                토스트메세지로 테스트함
//                Toast.makeText(getContext(),
//                        "ID = "+ cursor.getString(0)+
//                        " YEAR = "+ cursor.getInt(1) +
//                        " MONTH = " + cursor.getInt(2) +
//                        " DATE = " + cursor.getInt(3) +
//                        " STEP = " + cursor.getInt(4) +
//                        " ACTKCAL = " + cursor.getDouble(5) +
//                        " LEFTKCAL = " + cursor.getDouble(6) +
//                        " DISTANCE = " + cursor.getDouble(7), Toast.LENGTH_SHORT).show();
                        temp[dayNum] = cursor.getInt(4);
                        System.out.println("temp["+dayNum+"]: "+temp[dayNum]);
                        dayNum -=1;
                        if(dayNum<0){ dayNum=6; }
                    }
                }else{
                    temp[dayNum] = 0;
                    System.out.println("temp["+dayNum+"]: "+temp[dayNum]);
                    dayNum -=1;
                    if(dayNum<0){ dayNum=6; }
                }
                cursor.close();
            }catch (SQLException e){

            }
            time.add(Calendar.DATE,-1);
        }

//        for(int i=0;i<7;i++){
//            entries.add(new Entry(i, temp[i]));
//        }
    }

    public void showstatisticschart(){
//        ArrayList<Entry> entries = new ArrayList<>();
//        entries.add(new Entry(0, 0));
//        entries.add(new Entry(1, 0));
//        entries.add(new Entry(2, 0));
//        entries.add(new Entry(3, 0));
//        entries.add(new Entry(4, 0));
//        entries.add(new Entry(5, 125));
//        entries.add(new Entry(6, 0));

        LineDataSet dataset = new LineDataSet(entries, "걸음수");
        dataset.setLineWidth(2);
        dataset.setCircleRadius(6);
        dataset.setCircleColor(Color.parseColor("#FFA1B4DC"));
        dataset.setCircleHoleColor(Color.BLUE);
        dataset.setDrawCircleHole(true);
        dataset.setDrawCircles(true);
        dataset.setDrawHorizontalHighlightIndicator(false);
        dataset.setDrawHighlightIndicators(false);
        dataset.setDrawValues(false);


        LineData data = new LineData(dataset);

        /*dataset.setDrawCubic(true); //선 둥글게 만들기
        dataset.setDrawFilled(true); //그래프 밑부분 색칠*/

        lineChart.setData(data);
//        lineChart.animateY(5000);



        final String[] weekdays = {"일","월", "화", "수", "목", "금", "토"};
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new MyValueFormatter(weekdays));

        xAxis.setDrawGridLines(false);
//        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.BLACK);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        //y축 오른쪽방향은 비활성화
        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.animateY(2000, Easing.EaseInCubic);
        lineChart.invalidate();

        MyMarkerView marker = new MyMarkerView(getContext(),R.layout.markerviewtext);
        marker.setChartView(lineChart);
        lineChart.setMarker(marker);
    }
}
