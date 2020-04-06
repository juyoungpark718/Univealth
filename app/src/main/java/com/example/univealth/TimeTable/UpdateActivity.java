package com.example.univealth.TimeTable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.univealth.DBHelper;
import com.example.univealth.MainActivity;
import com.example.univealth.R;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "TimeTable.db", null, 1);

        // 테이블에 있는 모든 데이터 출력
        final TextView result = (TextView) findViewById(R.id.result);

        // EditText 모두 불러오기
        final EditText etDay = (EditText) findViewById(R.id.edit_day);
        final EditText etStartTime = (EditText) findViewById(R.id.edit_starttime);
        final EditText etEndTime = (EditText) findViewById(R.id.edit_endtime);
        final EditText etSubject = (EditText) findViewById(R.id.edit_subject);
        final EditText etClassroom = (EditText) findViewById(R.id.edit_classroom);
        final EditText etProfessor = (EditText) findViewById(R.id.edit_professor);

        // Intent로 전달 받은 값
        final Intent receive_intent = getIntent();

        final int org_tablenumber = receive_intent.getExtras().getInt("org_tablenumber");
        final int org_day = receive_intent.getExtras().getInt("org_day");
        final int org_starttime = receive_intent.getExtras().getInt("org_starttime");
        final String org_subject = receive_intent.getExtras().getString("org_subject");

        final Intent table_intent = new Intent(getApplicationContext(), MainActivity.class);

        // 자동입력
        etDay.append(Integer.toString(dbHelper.find_day(org_tablenumber)));
        etStartTime.append(Integer.toString(dbHelper.find_starttime(org_tablenumber)));
        etEndTime.append(Integer.toString(dbHelper.find_endtime(org_tablenumber)));
        etSubject.append(dbHelper.find_subject(org_tablenumber));
        etClassroom.append(dbHelper.find_classroom(org_tablenumber));
        etProfessor.append(dbHelper.find_professor(org_tablenumber));

        // DB 수정 -> 기존 DB 삭제, 새 DB 추가
        Button insert = (Button) findViewById(R.id.button_update);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 이전 기록 지우기
                dbHelper.delete(org_day, org_starttime, org_subject);

                // 새 DB 추가
                int day = Integer.parseInt(etDay.getText().toString());
                int starttime = Integer.parseInt(etStartTime.getText().toString());
                int endtime = Integer.parseInt(etEndTime.getText().toString());
                int tablenumber = day * 100 + starttime;
                String subject = etSubject.getText().toString();
                String classroom = etClassroom.getText().toString();
                String professor = etProfessor.getText().toString();

                // for문을 위한 변수
                int time = endtime - starttime + 1;

                for (int i=0; i<time; i++) {
                    dbHelper.insert(tablenumber+i, day, starttime, endtime, subject, classroom, professor);
                }

                setResult(RESULT_OK,table_intent);
                finish();
            }
        });

        // ViewActivity에서 전달된 값 확인
        result.setText(org_subject);

    }
}
