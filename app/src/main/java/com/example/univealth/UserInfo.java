package com.example.univealth;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class UserInfo {

    Calendar calendar = new GregorianCalendar(Locale.KOREA);
    int year = calendar.get(Calendar.YEAR);
    private int userHeight;
    private int userBirthyear;
    private int userWeight;
    private int userStride;
    private String userGender;
    private Context context;

    public UserInfo(Context context) {
        this.context = context;
        try{
            DBHelper dbHelper = new DBHelper(context,"TimeTable.db",null,1);
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
                    userGender = cursor.getString(1);
                    userBirthyear = cursor.getInt(2);
                    userWeight = cursor.getInt(3);
                    userHeight = cursor.getInt(4);
                    userStride = cursor.getInt(5);
                }
            }else{
                Toast.makeText(context,"Please Check your information in myinfo Tab",Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }catch (SQLException e){
            Toast.makeText(context,"Please Check your information in myinfo Tab",Toast.LENGTH_SHORT).show();
        }
    }

    public int getUserHeight() {
        return userHeight;
    }

    public int getUserBirthyear() {
        return userBirthyear;
    }

    public int getUserWeight() {
        return userWeight;
    }

    public int getUserStride() {
        return userStride;
    }

    public String getUserGender() {
        return userGender;
    }

    public int getYear() {
        return year;
    }

    public boolean getInfo(){
        DBHelper dbHelper = new DBHelper(context,"TimeTable.db",null,1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM USERINFOTABLE";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
