package com.example.univealth.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.univealth.DBHelper;
import com.example.univealth.NumberPickerDialog;
import com.example.univealth.R;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class FragmentMyinfo extends Fragment implements NumberPicker.OnValueChangeListener{
    private String userID;
    private int userHeight;
    Calendar calendar = new GregorianCalendar(Locale.KOREA);
    int year = calendar.get(Calendar.YEAR);//생년을 받기위한 올해의 년도, 나이계산에도 사용
    private int userBirthyear;
    private int userWeight;
    private int userStride;
    private String userGender;
    private DBHelper dbHelper; // 유저정보를 저장할 DB
    private View v;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_myinfo,container, false);
        dbHelper = new DBHelper(getContext(), "TimeTable.db", null, 1);

        v.findViewById(R.id.user_gender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGenderPicker(v);
            }
        });
        v.findViewById(R.id.user_height).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNumberPicker(view);
            }
        });

        v.findViewById(R.id.user_birthyear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNumberPicker(view);
            }
        });
        v.findViewById(R.id.user_stride).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNumberPicker(view);
            }
        });
        v.findViewById(R.id.user_weight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNumberPicker(view);
            }
        });

//        setUserInfoDB(v);
        getUserInfoDB(v);
        return v;
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int vid, int i1) {
        Toast.makeText(getContext(),
                "selected number " + numberPicker.getValue(), Toast.LENGTH_SHORT).show();
        TextView t = v.findViewById(vid); //프로필 텍스트뷰를 받아와 값을 수정
        t.setText(Integer.toString(numberPicker.getValue()));
        if(vid == R.id.user_height){
            t = v.findViewById(R.id.user_stride);
            t.setText(Integer.toString((int)(numberPicker.getValue()*0.4)));//일반적으로 보폭은 키의 0.4배
        }
        setUserInfoDB(v); //프로필 텍스트뷰의 값들을 DB에 저장 191118
        getUserInfoDB(v); //유저 정보 DB에서 유저정보 값들을 받아옴 191118
    }
    //유저 정보를 DB에 저장 191118
    public void setUserInfoDB(View v){
        System.out.println(v);
        String gender;
        int year;
        int weight;
        int height;
        int stride;
        TextView t = v.findViewById(R.id.user_gender); //사용자의 기본 정보를 받아옴
        gender = (String) t.getText();
        t = v.findViewById(R.id.user_birthyear);
        year = Integer.parseInt(t.getText().toString());
        t = v. findViewById(R.id.user_weight);
        weight = Integer.parseInt(t.getText().toString());
        t= v.findViewById(R.id.user_height);
        height = Integer.parseInt(t.getText().toString());
        t = v.findViewById(R.id.user_stride);
        stride = Integer.parseInt(t.getText().toString());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = String.format("INSERT OR REPLACE INTO USERINFOTABLE VALUES('user1', '" + gender + "', " + year + ", " + weight + ", " + height + ", " + stride + ");");
        db.execSQL(sql);
    }

    //유저 정보를 DB에서 받아옴 191118
    public void getUserInfoDB(View v){
        try{
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String sql = "SELECT * FROM USERINFOTABLE";
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
////                토스트메세지로 테스트함
//                Toast.makeText(getContext(),
//                        "userGender = "+ cursor.getString(1)+
//                        "userBirthyear = "+ cursor.getInt(2) +
//                        "userWeight = " + cursor.getInt(3) +
//                        "userHeight = " + cursor.getInt(4) +
//                        "userStride = " + cursor.getInt(5), Toast.LENGTH_SHORT).show();
                    //유저정보를 디비에서 받아와 텍스트뷰로 띄움
                    TextView t = v.findViewById(R.id.user_gender);
                    userGender = cursor.getString(1);
                    t.setText(userGender);

                    t = v.findViewById(R.id.user_birthyear);
                    userBirthyear = cursor.getInt(2);
                    t.setText(String.valueOf(userBirthyear));

                    t = v. findViewById(R.id.user_weight);
                    userWeight = cursor.getInt(3);
                    t.setText(String.valueOf(userWeight));

                    t= v.findViewById(R.id.user_height);
                    userHeight = cursor.getInt(4);
                    t.setText(String.valueOf(userHeight));

                    t = v.findViewById(R.id.user_stride);
                    userStride = cursor.getInt(5);
                    t.setText(String.valueOf(userStride));
                }
            }
            cursor.close();
        }catch (SQLException e){
            setUserInfoDB(v);
        }

    }

    //프로필 넘버피커다이얼로그
    public void showNumberPicker(View v){
        NumberPickerDialog newFragment = new NumberPickerDialog();
        String title = "Choose Number";
        int minvalue = 1; //값 초기화, 최솟값, 최댓값, 시작값
        int maxvalue = 1;
        int defvalue = 1;
        TextView t;//임시 텍스트뷰

        if(v.getId() == R.id.user_birthyear){
            title = "생년선택";
            maxvalue = year;
            minvalue = year-100;
            t = v.findViewById(R.id.user_birthyear);
            defvalue = Integer.parseInt(t.getText().toString()); //시작값을 선택되어있는 값으로 하기위함
        } else if (v.getId() == R.id.user_height ){
            title = "키 선택";
            minvalue = 130;
            maxvalue = 230;
            t = v.findViewById(R.id.user_height);
            defvalue = Integer.parseInt(t.getText().toString());

        } else if (v.getId() == R.id.user_weight){
            title = "체중선택";
            minvalue = 30;
            maxvalue = 300;
            t = v.findViewById(R.id.user_weight);
            defvalue = Integer.parseInt(t.getText().toString());
        } else if (v.getId() == R.id.user_stride){
            title= "보폭선택";
            minvalue = 40;
            maxvalue = 100;
            t = v.findViewById(R.id.user_stride);
            defvalue = Integer.parseInt(t.getText().toString());
        }

        Bundle bundle = new Bundle(7); // 파라미터는 전달할 데이터 개수
        bundle.putString("title", title); // key , value
        bundle.putInt("maxvalue", maxvalue); // key , value
        bundle.putInt("minvalue", minvalue); // key , value
        bundle.putInt("defvalue", defvalue); // key , value
        bundle.putInt("vid", v.getId());

        newFragment.setArguments(bundle);
        newFragment.setValueChangeListener(this);
        newFragment.show(getFragmentManager(), "time picker");

    }

    //프로필 성별피커 다이얼로그
    public void showGenderPicker(final View v){
        final CharSequence[] items = {"남자", "여자"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("성별 선택");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), items[i] + "를 선택했습니다.", Toast.LENGTH_SHORT).show();
                TextView t = v.findViewById(R.id.user_gender);
                t.setText(items[i]);
                setUserInfoDB(v);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }
}
