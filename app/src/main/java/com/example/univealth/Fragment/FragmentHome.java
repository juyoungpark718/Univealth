package com.example.univealth.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.univealth.DBHelper;
import com.example.univealth.Intake.AddIntakeActivity;
import com.example.univealth.Intake.IntakeItem;
import com.example.univealth.Intake.IntakeItemView;
import com.example.univealth.R;
import com.example.univealth.UserInfo;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class FragmentHome extends Fragment implements SensorEventListener{

    private double userBMR; //Basal Metablolic Rate 기초대사량

    //걸음 센서 변수들
    private SensorManager sensorManager;
    private Sensor stepDetectorSensor;
    TextView stepCountView; //걸음수뷰
    TextView stepCountCalcView; //걸음수에 따른 소모 칼로리뷰
    int stepDetectorCount; //걸음수
    double kcalPerStep; //한걸음당 소모칼로리변수


    //섭취칼로리 부분
    private int intakeKcalSum = 0;
    TextView intakeKcalSumView; //섭취칼로리 총합이 표시될 텍스트뷰
    private Button addIntakeBtn; //섭취칼로리 추가버튼
    private ListView addIntakeListView; //섭취칼로리 리스트 뷰
    private IntakeAdapter addIntakeAdapter; //섭취칼로리 어댑터
    UserInfo userInfo;

    //남은 칼로리부분
    TextView leftKcalView; //섭취칼로리 총합이 표시될 텍스트뷰

    private PieChart pieChart;

    //오늘운동량 저장디비
    private DBHelper dbHelper; // 유저정보를 저장할 DB
    private View v;



    //----------------------------------- JinGyu --------------------------------
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container, false);
        dbHelper = new DBHelper(getContext(), "TimeTable.db", null, 1);
        userInfo = new UserInfo(getContext());
        if(userInfo.getInfo()){
            userBMR = BMRCalc(userInfo,v);
            stepCountCalc(userInfo);
        }else{
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, new FragmentMyinfo()).commitAllowingStateLoss();
            BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigationView);
            bottomNavigationView.setSelectedItemId(R.id.myinfoItem);
        }

        v.findViewById(R.id.add_intake_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAddIntakeBtn(view);
            }
        });
        stepCountView = v.findViewById(R.id.stepcount);     //걸음수 측정관련 뷰설정, 센서설정
        stepCountCalcView = v.findViewById(R.id.stepcount_calc);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if(stepDetectorSensor == null) {
//            Toast.makeText(getContext(), "No Step Detect Sensor", Toast.LENGTH_SHORT).show();
        }
        //섭취칼로리 총합
        intakeKcalSumView = v.findViewById(R.id.intake_kcal);
        //섭취한음식 추가부분
        addIntakeBtn = v.findViewById(R.id.add_intake_button);
        //어댑터 선언부분
        addIntakeAdapter = new IntakeAdapter();
        addIntakeListView = v.findViewById(R.id.intake_listview);
        //addIntakeListView.setAdapter(addIntakeAdapter);

        //남은 칼로리 텍스트뷰 지정
        leftKcalView = v.findViewById(R.id.left_kcal);

        //그래프 표시
        pieChart = (PieChart)v.findViewById(R.id.kcal_chart);

        //오늘 운동량 불러오기
        getAmountofActivity(v);

        //초기 유저정보 불러왓을때 값들 계산해서 텍스트뷰로 띄워줌
        stepCountView.setText(stepDetectorCount + " 걸음");
        stepCountCalcView.setText(String.format("%.3f", stepDetectorCount* kcalPerStep) + " kcal " + String.format("%.2f", (double)stepDetectorCount*(double)userInfo.getUserStride()/100000) +" km");
        leftKcalCalc();

        //오늘 섭취칼로리 불러오기
        getIntakeKcalDB(v);

        //차트 보여주기
        showChart();

        return v;
    }

    //활동량 DB저장 191122
    public void setAmountofActivity(View v){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar time = new GregorianCalendar(Locale.KOREA);

        String ID = simpleDateFormat.format(time.getTime());
        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH) + 1;
        int day = time.get(Calendar.DAY_OF_MONTH);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = String.format("INSERT OR REPLACE INTO ACTIVITYTABLE VALUES('"+ ID + "', " + year + ", " + month + ", " + day + ", " + stepDetectorCount + ", " + Double.parseDouble(String.format("%.3f", stepDetectorCount* kcalPerStep)) + ", " + leftKcalCalc() + ", " + Double.parseDouble(String.format("%.2f", (double)stepDetectorCount*(double)userInfo.getUserStride()/100000))+ ");");
        db.execSQL(sql);
    }

    //활동량 DB에서 가져옴, 걸음수만 가져와서 나머지는 계산
    public void getAmountofActivity(View v){
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar time = new GregorianCalendar(Locale.KOREA);
            String ID = simpleDateFormat.format(time.getTime());

            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String sql = "SELECT * FROM ACTIVITYTABLE WHERE ID = '" + ID + "'";
            System.out.println(sql);
            Cursor cursor = db.rawQuery(sql, null);
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
                    stepDetectorCount = cursor.getInt(4);
                }
            }
            cursor.close();
        }catch (SQLException e){
            setAmountofActivity(v);
        }
    }


    //섭취음식 DB에 저장
    public void setIntakeKcalDB(View v, String foodname, int foodkcal){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar time = new GregorianCalendar(Locale.KOREA);

        String date = simpleDateFormat.format(time.getTime());
        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH) + 1;
        int day = time.get(Calendar.DAY_OF_MONTH);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = String.format("INSERT INTO INTAKETABLE VALUES(null, '"+ date + "', " + year + ", " + month + ", " + day + ", '" + foodname + "', " + foodkcal + ");");
        db.execSQL(sql);
    }

    //섭취음식 DB에 저장시 가장 최근에 입력된 ID값 호출
    public int getLastInsertID(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "SELECT LAST_INSERT_ROWID();";
        int LastfoodID = 0;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                LastfoodID = cursor.getInt(0);
            }
        }
        cursor.close();

        return LastfoodID;
    }

    //섭취음식 DB에서 제거
    public void deleteIntakeKcalDB(View v, int foodID){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar time = new GregorianCalendar(Locale.KOREA);

        String date = simpleDateFormat.format(time.getTime());
        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH) + 1;
        int day = time.get(Calendar.DAY_OF_MONTH);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = String.format("DELETE FROM INTAKETABLE WHERE DATE = '"+ date + "' AND ID = '"+foodID+"'");
        db.execSQL(sql);
    }

    //섭취음식 DB에서 불러오기
    public void getIntakeKcalDB(View v){
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar time = new GregorianCalendar(Locale.KOREA);
            String date = simpleDateFormat.format(time.getTime());

            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String sql = "SELECT * FROM INTAKETABLE WHERE DATE = '" + date + "'";
            System.out.println(sql);
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    int foodID = cursor.getInt(0);
                    String foodName = cursor.getString(5);
                    int foodKcal = cursor.getInt(6);
                    addIntakeAdapter.addItem(new IntakeItem(foodID, foodName, foodKcal)); //어댑터에 추가
                }
            }
            cursor.close();
            addIntakeListView.setAdapter(addIntakeAdapter); //어댑터를 리스트에 띄우고
            addIntakeAdapter.getKcalSum();//섭취 총합 칼로리 계산
            intakeKcalSumView.setText(intakeKcalSum+" kcal"); //칼로리 총합 최신화
            leftKcalCalc();//남은 칼로리 최신화
            setListViewHeightBasedOnChildren(addIntakeListView);//리스트뷰 높이 최신화
        }catch (SQLException e){
            Toast.makeText(getContext(),"섭취한 음식이 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public double userBMRChangedByTime(double userBMR){
        SimpleDateFormat format = new SimpleDateFormat("HH");
        Calendar time = Calendar.getInstance();
        String format_time = format.format(time.getTime());
        double changeduserBMR = userBMR*Double.parseDouble(format_time)/24;//현재시간을 받아와 24로 나눈값을 곱함
        changeduserBMR = Double.parseDouble(String.format("%.3f", changeduserBMR));//소수점3자리까지 자름
        //Toast.makeText(getContext(),"결과 "+ changeduserBMR, Toast.LENGTH_SHORT).show();
        return changeduserBMR;
    }

    @Override
    public void onResume() {
        sensorManager.registerListener(this, stepDetectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_STEP_DETECTOR){
            if (sensorEvent.values[0] == 1.0f){
                stepDetectorCount += sensorEvent.values[0];
                stepCountView.setText(stepDetectorCount + " 걸음");
                stepCountCalcView.setText(String.format("%.3f", stepDetectorCount* kcalPerStep) + " kcal " + String.format("%.2f", (double)stepDetectorCount*(double)userInfo.getUserStride()/100000) +" km"); // 걸음수 * 한걸음당 칼로리, 걸음수 * 보폭(cm)/100000 km 단위환산

                leftKcalCalc();
                setAmountofActivity(v);
                showChart(); //그래프 최신화
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    //음식 추가 후 결과를 받는 메소드
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //코드1, 음식과 칼로리가 제대로 입력이 된경우
        if(resultCode == 1){
            String foodName = data.getExtras().getString("음식명"); //음식명과 칼로리를 받아
            int foodKcal = data.getExtras().getInt("칼로리");

            setIntakeKcalDB(v, foodName, foodKcal);//섭취DB에 저장

            addIntakeAdapter.addItem(new IntakeItem(getLastInsertID(), foodName, foodKcal)); //어댑터에 추가
            addIntakeListView.setAdapter(addIntakeAdapter); //어댑터를 리스트에 띄우고
            addIntakeAdapter.getKcalSum();//섭취 총합 칼로리 계산
            intakeKcalSumView.setText(intakeKcalSum+" kcal"); //칼로리 총합 최신화
            leftKcalCalc();//남은 칼로리 최신화
            showChart(); //그래프 최신화

            //이 아래부분은 스크롤뷰안에 들어있는 리스트뷰의 높이를 섭취한음식이 추가될때마다 바뀌는 과정
            setListViewHeightBasedOnChildren(addIntakeListView);

            //Toast.makeText(getApplicationContext(), "칼로리합:" + addIntakeAdapter.getKcalSum(), Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), "ResultCode : " + resultCode + "음식명" + foodName, Toast.LENGTH_LONG).show();

        } else if(resultCode == 2){//코드2, 음식 또는 칼로리가 입력이 안된경우
            Toast.makeText(getContext(), "오류코드 "+resultCode +": 음식명 또는 칼로리 미입력", Toast.LENGTH_LONG).show();
        }

    }

    //섭취음식 추가 어댑터
    public class IntakeAdapter extends BaseAdapter {
        private ArrayList<IntakeItem> items = new ArrayList<IntakeItem>();


        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(IntakeItem item) { items.add(item);}

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertview, ViewGroup viewGroup) {
            IntakeItemView view = new IntakeItemView(getContext());
            final IntakeItem item = items.get(position);
            view.setName(item.getFoodName());
            view.setKcal(item.getFoodKcal());

            final MaterialButton intake_deletebtn = view.findViewById(R.id.intake_delete_button);//섭취한 음식 삭제버튼
            intake_deletebtn.setOnClickListener(new View.OnClickListener() {//삭제 버튼 누를때
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());//경고 다이얼로그
                    builder.setTitle("안내");
                    builder.setMessage("삭제하시겠습니까?");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);

                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteIntakeKcalDB(v,items.get(position).getFoodID());//디비에서 삭제
                            items.remove(position);//배열리스트에서 아이템삭제
                            addIntakeAdapter.notifyDataSetChanged();//리스트뷰 갱신
                            addIntakeAdapter.getKcalSum();//섭취칼로리 총합 최신화
                            intakeKcalSumView.setText(intakeKcalSum+" kcal");
//                          leftKcalView.setText(String.format("%.3f",leftKcalCalc())+" kcal"); //leftKcalCalc에서 하도록 변경

                            //리스트뷰 높이 최신화
                            setListViewHeightBasedOnChildren(addIntakeListView);

                            leftKcalCalc();
                            showChart();
                        }
                    });

                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

            return view;
        }
        public ArrayList<IntakeItem> getItems(){
            return items;
        }

        public void getKcalSum(){//섭취칼로리 총합 메소드
            intakeKcalSum = 0;
            for(int i = 0; i < items.size(); i++){
                intakeKcalSum += items.get(i).getFoodKcal();
            }
        }
    }

    //남은 칼로리 계산 메소드
    public double leftKcalCalc(){
        double leftKcal = intakeKcalSum - (stepDetectorCount* kcalPerStep) - userBMRChangedByTime(userBMR);
//        //음수일경우 0으로표기
//        if(leftKcal <0)
//            leftKcal = 0;
        if(leftKcal<0){
            leftKcalView.setText("0 kcal");
        }else {
            leftKcalView.setText(String.format("%.3f",leftKcal)+" kcal");
        }
        return leftKcal;
    }

    public void showChart(){
        ArrayList<PieEntry> KcalyValues = new ArrayList<PieEntry>();

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        KcalyValues.add(0, new PieEntry((float)userBMRChangedByTime(userBMR),"현재 기초대사량"));
        KcalyValues.add(1, new PieEntry((float)(stepDetectorCount* kcalPerStep),"운동칼로리"));
        if(leftKcalCalc() <0){
            KcalyValues.add(2, new PieEntry(0,"남은칼로리"));
        }
        else{
            KcalyValues.add(2, new PieEntry((float)leftKcalCalc(),"남은칼로리"));
        }


        Description description = new Description();
        description.setText("칼로리그래프"); //라벨
        description.setTextSize(15);
        pieChart.setDescription(description);

        //pieChart.animateY(1000, Easing.EaseInCubic); //애니메이션

        PieDataSet dataSet = new PieDataSet(KcalyValues,"Kcal");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
    }
    //음식 추가버튼 누를시
    public void onClickAddIntakeBtn(View v){
        Intent data = new Intent(getContext(), AddIntakeActivity.class);
        startActivityForResult(data, 0);
    }

    //기초대사량을 계산하는 메소드
    public double BMRCalc(UserInfo userInfo, View v){
        double userBMR;
        int age = userInfo.getYear()-userInfo.getUserBirthyear()+1;
        if (userInfo.getUserGender().equals("남자")){
            userBMR = 66.47+(13.75*userInfo.getUserWeight())+(5*userInfo.getUserHeight())-(6.76*age);
        } else {
            userBMR = 655.1+(9.56*userInfo.getUserWeight())+(1.85*userInfo.getUserHeight())-(4.68*age);
        }
        TextView t = v.findViewById(R.id.BMR);
        //t.setText(userGender);
        //기초대사량 출력표기 소수점3자리로 수정 191118
        t.setText(String.format("%.3f", userBMR)+" kcal");
        return userBMR;
    }

    //한걸음당 칼로리수를 계산하는 메소드
    public void stepCountCalc(UserInfo userInfo){ //키와 몸무게별 한걸음 당 칼로리
        int userHeight = userInfo.getUserHeight();
        int userWeight = userInfo.getUserWeight();
        if (userHeight <= 174){
            if(userWeight <= 45) {
                kcalPerStep = 0.0275;
            } else if (userWeight <= 55) {
                kcalPerStep = 0.033;
            } else if (userWeight <= 64) {
                kcalPerStep = 0.038;
            } else if (userWeight <= 73) {
                kcalPerStep = 0.0435;
            } else if (userWeight <= 82) {
                kcalPerStep = 0.049;
            } else if (userWeight <= 91) {
                kcalPerStep = 0.0545;
            } else if (userWeight <= 100) {
                kcalPerStep = 0.06;
            } else if (userWeight <= 114) {
                kcalPerStep = 0.0685;
            } else if (userWeight <= 125) {
                kcalPerStep = 0.075;
            } else {
                kcalPerStep = 0.082;
            }
        } else if (userHeight <= 181) {
            if(userWeight <= 45) {
                kcalPerStep = 0.025;
            } else if (userWeight <= 55) {
                kcalPerStep = 0.03;
            } else if (userWeight <= 64) {
                kcalPerStep = 0.03455;
            } else if (userWeight <= 73) {
                kcalPerStep = 0.03955;
            } else if (userWeight <= 82) {
                kcalPerStep = 0.04455;
            } else if (userWeight <= 91) {
                kcalPerStep = 0.04955;
            } else if (userWeight <= 100) {
                kcalPerStep = 0.05455;
            } else if (userWeight <= 114) {
                kcalPerStep = 0.06225;
            } else if (userWeight <= 125) {
                kcalPerStep = 0.0682;
            } else {
                kcalPerStep = 0.07455;
            }
        } else {
            if(userWeight <= 45) {
                kcalPerStep = 0.0229;
            } else if (userWeight <= 55) {
                kcalPerStep = 0.0275;
            } else if (userWeight <= 64) {
                kcalPerStep = 0.03165;
            } else if (userWeight <= 73) {
                kcalPerStep = 0.03625;
            } else if (userWeight <= 82) {
                kcalPerStep = 0.04085;
            } else if (userWeight <= 91) {
                kcalPerStep = 0.0454;
            } else if (userWeight <= 100) {
                kcalPerStep = 0.05;
            } else if (userWeight <= 114) {
                kcalPerStep = 0.0571;
            } else if (userWeight <= 125) {
                kcalPerStep = 0.0625;
            } else {
                kcalPerStep = 0.06835;
            }
        }
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0,0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}

