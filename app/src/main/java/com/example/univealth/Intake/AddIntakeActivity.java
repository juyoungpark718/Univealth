package com.example.univealth.Intake;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.univealth.R;

public class AddIntakeActivity extends AppCompatActivity {

    TextView foodNameView;
    TextView foodKcalView;

    //private final String key = "";
    //private final String endPoint = "https://bablabs.com/openapi/v1";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addintake);

        foodNameView = findViewById(R.id.food_name_text);
        foodKcalView = findViewById(R.id.food_kcal_text);


    }

    public void onClickAddIntakeCompleteBtn(View v){
        String foodName = foodNameView.getText().toString();
        String foodKcal = foodKcalView.getText().toString();
        //칼로리 입력이 안될경우 0, 되있을 경우 미입력 오류코드 2를 보냄
        if (foodName.equals("")||foodKcal.equals("")) {
            setResult(2);
            finish();
        }
        else {
            //올바르게 입력된경우 성공코드1과 데이터를 보냄
            Intent data = new Intent();
            data.putExtra("음식명", foodName);
            data.putExtra("칼로리", Integer.parseInt(foodKcal));

            setResult(1, data);
            finish();
        }
    }
}
