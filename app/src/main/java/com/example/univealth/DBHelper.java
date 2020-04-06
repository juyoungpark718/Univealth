package com.example.univealth;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        // tablenumber:시간표 칸 좌표, day:요일, starttime:수업 시작 교시, endtime:수업 끝나는 교시, subject:과목명, classroom:교실, professor:교수님
        db.execSQL("CREATE TABLE TIMETABLE (id INTEGER PRIMARY KEY AUTOINCREMENT, tablenumber INTEGER, day INTEGER, starttime INTEGER, endtime INTEGER, subject TEXT, classroom TEXT, professor TEXT);");
        // 유저 개인정보 테이블
        db.execSQL("CREATE TABLE USERINFOTABLE (id TEXT PRIMARY KEY , gender TEXT, year INTEGER, weight INTEGER, height INTEGER, stride INTEGER);");
        // 유저 활동량 테이블
        db.execSQL("CREATE TABLE ACTIVITYTABLE (id TEXT PRIMARY KEY ,year INTEGER, month INTEGER, day INTEGER, stepcount INTEGER, actkcal REAL, leftkcal REAL, distance REAL);");
        // 유저 섭취음식 테이블
        db.execSQL("CREATE TABLE INTAKETABLE (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, year INTEGER, month INTEGER, day INTEGER, name TEXT, kcal INTEGER);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert(int tablenumber, int day, int starttime, int endtime, String subject, String classroom, String professor) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO TIMETABLE VALUES(null, " + tablenumber + ", " + day + ", " + starttime + ", " + endtime + ", '" + subject + "', '" + classroom + "', '" + professor + "');");
        db.close();
    }

//    // 테이블 넘버를 통한 업데이트
//    public void update(int tablenumber, int day, int starttime, int endtime, String subject, String classroom, String professor) {
//        SQLiteDatabase db = getWritableDatabase();
//        // 입력한 항목과 일치하는 행의 가격 정보 수정
//        db.execSQL("UPDATE TIMETABLE SET day=" + day + " WHERE tablenumber=" + tablenumber + ";");
//        db.execSQL("UPDATE TIMETABLE SET starttime=" + starttime + " WHERE tablenumber=" + tablenumber + ";");
//        db.execSQL("UPDATE TIMETABLE SET endtime=" + endtime + " WHERE tablenumber=" + tablenumber + ";");
//        db.execSQL("UPDATE TIMETABLE SET subject='" + subject + "' WHERE tablenumber=" + tablenumber + ";");
//        db.execSQL("UPDATE TIMETABLE SET classroom='" + classroom + "' WHERE tablenumber=" + tablenumber + ";");
//        db.execSQL("UPDATE TIMETABLE SET professor='" + professor + "' WHERE tablenumber=" + tablenumber + ";");
//        db.close();
//    }

//    // 테이블 넘버를 통한 삭제
//    public void delete(int tablenumber) {
//        SQLiteDatabase db = getWritableDatabase();
//        // 입력한 항목과 일치하는 행 삭제
//        db.execSQL("DELETE FROM TIMETABLE WHERE tablenumber=" + tablenumber + ";");
//        db.close();
//    }

    // 요일, 과목, 시작교시를 통한 삭제
    public void delete(int day, int starttime, String subject) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM TIMETABLE WHERE day=" + day + " AND starttime='" + starttime + "' AND subject='" + subject + "';");
        db.close();
    }

    // DB 테스트용
    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM TIMETABLE", null);
        while (cursor.moveToNext()) {
            result += cursor.getInt(1)
                    + "/"
                    + cursor.getString(2)
                    + "/"
                    + cursor.getString(3)
                    + "/"
                    + cursor.getString(4)
                    + "/"
                    + cursor.getString(5)
                    + "/"
                    + cursor.getString(6)
                    + "/"
                    + cursor.getString(7)
                    + "\n";
        }
        return result;
    }

    // Table에 표시
    public String show(int tablenumber) {
        // 특정 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM TIMETABLE", null);
        while (cursor.moveToNext()) {
            if (cursor.getInt(1) == tablenumber) {
                result += cursor.getString(5)
                        + "\n"
                        + cursor.getString(6)
                        + "\n"
                        + cursor.getString(7);
            }
        }
        return result;
    }

    // tablenumber로 day 불러오기
    public int find_day(int tablenumber) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int day = 0;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM TIMETABLE WHERE tablenumber=" + tablenumber, null);
        while (cursor.moveToNext()) {
            day = cursor.getInt(2);
        }
        return day;
    }

    // tablenumber로 starttime 불러오기
    public int find_starttime(int tablenumber) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int starttime = 0;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM TIMETABLE WHERE tablenumber=" + tablenumber, null);
        while (cursor.moveToNext()) {
            starttime = cursor.getInt(3);
        }
        return starttime;
    }

    // tablenumber로 endtime 불러오기
    public int find_endtime(int tablenumber) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int endtime = 0;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM TIMETABLE WHERE tablenumber=" + tablenumber, null);
        while (cursor.moveToNext()) {
            endtime = cursor.getInt(4);
        }
        return endtime;
    }

    // tablenumber로 subject 불러오기
    public String find_subject(int tablenumber) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String subject = null;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM TIMETABLE WHERE tablenumber=" + tablenumber, null);
        while (cursor.moveToNext()) {
            subject = cursor.getString(5);
        }
        return subject;
    }

    // tablenumber로 classroom 불러오기
    public String find_classroom(int tablenumber) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String classroom = null;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM TIMETABLE WHERE tablenumber=" + tablenumber, null);
        while (cursor.moveToNext()) {
            classroom = cursor.getString(6);
        }
        return classroom;
    }

    // tablenumber로 professor 불러오기
    public String find_professor(int tablenumber) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String professor = null;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM TIMETABLE WHERE tablenumber=" + tablenumber, null);
        while (cursor.moveToNext()) {
            professor = cursor.getString(7);
        }
        return professor;
    }

}
