package com.example.univealth.TimeTable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.univealth.DBHelper;
import com.example.univealth.MainActivity;
import com.example.univealth.R;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "TimeTable.db", null, 1);

        // 테이블에 있는 모든 데이터 출력
        final TextView result = (TextView) findViewById(R.id.result);

        // EditText 모두 불러오기
        final TextView viewDay = (TextView) findViewById(R.id.view_day);
        final TextView viewStartTime = (TextView) findViewById(R.id.view_starttime);
        final TextView viewEndTime = (TextView) findViewById(R.id.view_endtime);
        final TextView viewSubject = (TextView) findViewById(R.id.view_subject);
        final TextView viewClassroom = (TextView) findViewById(R.id.view_classroom);
        final TextView viewProfessor = (TextView) findViewById(R.id.view_professor);

        // Intent로 전달 받은 값
        final Intent receive_intent = getIntent();
        final int tablenumber = receive_intent.getExtras().getInt("tablenumber");

        final Intent table_intent = new Intent(getApplicationContext(), MainActivity.class);
        final Intent update_intent = new Intent(getApplicationContext(), UpdateActivity.class);

        // 자동입력
        viewDay.append(Integer.toString(dbHelper.find_day(tablenumber)));
        viewStartTime.append(Integer.toString(dbHelper.find_starttime(tablenumber)));
        viewEndTime.append(Integer.toString(dbHelper.find_endtime(tablenumber)));
        viewSubject.append(dbHelper.find_subject(tablenumber));
        viewClassroom.append(dbHelper.find_classroom(tablenumber));
        viewProfessor.append(dbHelper.find_professor(tablenumber));

        // 수정 페이지로 이동
        Button update = (Button) findViewById(R.id.button_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update_intent.putExtra("org_tablenumber", tablenumber);
                update_intent.putExtra("org_day", Integer.parseInt(viewDay.getText().toString()));
                update_intent.putExtra("org_starttime", Integer.parseInt(viewStartTime.getText().toString()));
                update_intent.putExtra("org_subject", viewSubject.getText().toString());

                startActivityForResult(update_intent,200);
            }
        });

        // DB에 있는 데이터 삭제
        Button delete = (Button) findViewById(R.id.button_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day = Integer.parseInt(viewDay.getText().toString());
                int starttime = Integer.parseInt(viewStartTime.getText().toString());
                String subject = viewSubject.getText().toString();

                dbHelper.delete(day, starttime, subject);
                setResult(RESULT_OK,table_intent);
                finish();
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
                Intent table_intent = new Intent(getApplicationContext(), MainActivity.class);
                setResult(RESULT_OK,table_intent);
                finish();
            }
        }
    }
}

