package com.example.univealth.TimeTable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.univealth.DBHelper;
import com.example.univealth.Fragment.FragmentTimetable;
import com.example.univealth.MainActivity;
import com.example.univealth.R;
import com.example.univealth.Search.SearchAroundActivity;
import com.example.univealth.Search.SearchDestActivity;
import com.google.android.material.button.MaterialButton;

public class InsertActivity extends AppCompatActivity {
    String subject = "";
    String classroom = "";
    String professor = "";
    MaterialButton btClassroom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "TimeTable.db", null, 1);

        // 테이블에 있는 모든 데이터 출력
        final TextView result = (TextView) findViewById(R.id.result);

        // EditText 모두 불러오기
        final EditText etDay = (EditText) findViewById(R.id.edit_day);
        final EditText etStartTime = (EditText) findViewById(R.id.edit_starttime);
        final EditText etEndTime = (EditText) findViewById(R.id.edit_endtime);
        final EditText etSubject = (EditText) findViewById(R.id.edit_subject);
        btClassroom = findViewById(R.id.edit_classroom);
        final EditText etProfessor = (EditText) findViewById(R.id.edit_professor);

        // Intent로 전달 받은 값
        final Intent receive_intent = getIntent();
        final int tmp_tablenumber = receive_intent.getExtras().getInt("tablenumber");

        final Intent table_intent = new Intent(getApplicationContext(), MainActivity.class);

        btClassroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchDestActivity.class);
                intent.putExtra("activity","InsertActivity");
                startActivityForResult(intent,200);
            }
        });

        // 수업 요일 자동입력
        char tmp_day = Integer.toString(tmp_tablenumber).charAt(0);

        if (tmp_day=='1') {
            etDay.append("월");
        } else if (tmp_day=='2') {
            etDay.append("화");
        } else if (tmp_day=='3') {
            etDay.append("수");
        } else if (tmp_day=='4') {
            etDay.append("목");
        } else {
            etDay.append("금");
        }

        // 시작 교시 자동입력
        String starttime = Integer.toString(tmp_tablenumber).substring(1);

        if (starttime.charAt(0)=='0') {
            etStartTime.append(starttime.substring(1));
        } else {
            etStartTime.append(starttime);
        }

        // DB에 데이터 추가
        Button insert = (Button) findViewById(R.id.button_insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day = 0;

                if (etDay.getText().toString().equals("월")) {
                   day = 1;
                } else if (etDay.getText().toString().equals("화")) {
                    day = 2;
                } else if (etDay.getText().toString().equals("수")) {
                    day = 3;
                } else if (etDay.getText().toString().equals("목")) {
                    day = 4;
                } else if (etDay.getText().toString().equals("금")) {
                    day = 5;
                }

                int starttime = Integer.parseInt(etStartTime.getText().toString());
                int endtime = Integer.parseInt(etEndTime.getText().toString());
                int tablenumber = day * 100 + starttime;

                subject = etSubject.getText().toString();
                professor = etProfessor.getText().toString();

                // for문을 위한 변수
                if(subject.equals("") && classroom.equals("") && professor.equals("")){
                    Toast.makeText(getApplicationContext(),"모든 값을 입력해주세요.",Toast.LENGTH_LONG).show();
                }else{
                    int time = endtime - starttime + 1;

                    for (int i=0; i<time; i++) {
                        dbHelper.insert(tablenumber+i, day, starttime, endtime, subject, classroom, professor);
                    }
                    setResult(RESULT_OK,table_intent);
                    finish();
                }
            }
        });

        // DB 조회
        result.setText(dbHelper.getResult());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200){
            if(resultCode == RESULT_OK){
                classroom = data.getStringExtra("classroom");
                btClassroom.setText(classroom);
            }
        }
    }
}
