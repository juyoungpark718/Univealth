package com.example.univealth.Intake;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.univealth.R;
import com.google.android.material.button.MaterialButton;

public class IntakeItemView extends LinearLayout {
    TextView intakenametextview;
    TextView intakekcaltextview;
    MaterialButton intake_delete_button;

    public IntakeItemView(Context context) {
        super(context);

        init(context);
    }

    public IntakeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public IntakeItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.intake_item,this,true);

        intakenametextview = (TextView) findViewById(R.id.intake_food_name);
        intakekcaltextview = (TextView) findViewById(R.id.intake_food_kcal);
        intake_delete_button = (MaterialButton) findViewById(R.id.intake_delete_button);

    }
    public void setName(String name) {intakenametextview.setText(name);}
    public void setKcal(int kcal) {intakekcaltextview.setText(String.valueOf(kcal)+"kcal");}


}
