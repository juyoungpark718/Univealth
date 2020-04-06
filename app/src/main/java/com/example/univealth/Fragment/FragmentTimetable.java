package com.example.univealth.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.univealth.DBHelper;
import com.example.univealth.TimeTable.InsertActivity;
import com.example.univealth.R;
import com.example.univealth.TimeTable.ViewActivity;

import static android.app.Activity.RESULT_OK;

public class FragmentTimetable extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timetable,container, false);
        final DBHelper dbHelper = new DBHelper(getContext(), "TimeTable.db", null, 1);

        GridLayout grid = (GridLayout) v.findViewById(R.id.grid);

        // 월요일 1교시~15교시 TextView
        TextView t101 = (TextView) v.findViewById(R.id.table101);
        TextView t102 = (TextView) v.findViewById(R.id.table102);
        TextView t103 = (TextView) v.findViewById(R.id.table103);
        TextView t104 = (TextView) v.findViewById(R.id.table104);
        TextView t105 = (TextView) v.findViewById(R.id.table105);
        TextView t106 = (TextView) v.findViewById(R.id.table106);
        TextView t107 = (TextView) v.findViewById(R.id.table107);
        TextView t108 = (TextView) v.findViewById(R.id.table108);
        TextView t109 = (TextView) v.findViewById(R.id.table109);
        TextView t110 = (TextView) v.findViewById(R.id.table110);
        TextView t111 = (TextView) v.findViewById(R.id.table111);
        TextView t112 = (TextView) v.findViewById(R.id.table112);
        TextView t113 = (TextView) v.findViewById(R.id.table113);
        TextView t114 = (TextView) v.findViewById(R.id.table114);
        TextView t115 = (TextView) v.findViewById(R.id.table115);

        // 화요일 1교시~15교시 TextView
        TextView t201 = (TextView) v.findViewById(R.id.table201);
        TextView t202 = (TextView) v.findViewById(R.id.table202);
        TextView t203 = (TextView) v.findViewById(R.id.table203);
        TextView t204 = (TextView) v.findViewById(R.id.table204);
        TextView t205 = (TextView) v.findViewById(R.id.table205);
        TextView t206 = (TextView) v.findViewById(R.id.table206);
        TextView t207 = (TextView) v.findViewById(R.id.table207);
        TextView t208 = (TextView) v.findViewById(R.id.table208);
        TextView t209 = (TextView) v.findViewById(R.id.table209);
        TextView t210 = (TextView) v.findViewById(R.id.table210);
        TextView t211 = (TextView) v.findViewById(R.id.table211);
        TextView t212 = (TextView) v.findViewById(R.id.table212);
        TextView t213 = (TextView) v.findViewById(R.id.table213);
        TextView t214 = (TextView) v.findViewById(R.id.table214);
        TextView t215 = (TextView) v.findViewById(R.id.table215);

        // 수요일 1교시~15교시 TextView
        TextView t301 = (TextView) v.findViewById(R.id.table301);
        TextView t302 = (TextView) v.findViewById(R.id.table302);
        TextView t303 = (TextView) v.findViewById(R.id.table303);
        TextView t304 = (TextView) v.findViewById(R.id.table304);
        TextView t305 = (TextView) v.findViewById(R.id.table305);
        TextView t306 = (TextView) v.findViewById(R.id.table306);
        TextView t307 = (TextView) v.findViewById(R.id.table307);
        TextView t308 = (TextView) v.findViewById(R.id.table308);
        TextView t309 = (TextView) v.findViewById(R.id.table309);
        TextView t310 = (TextView) v.findViewById(R.id.table310);
        TextView t311 = (TextView) v.findViewById(R.id.table311);
        TextView t312 = (TextView) v.findViewById(R.id.table312);
        TextView t313 = (TextView) v.findViewById(R.id.table313);
        TextView t314 = (TextView) v.findViewById(R.id.table314);
        TextView t315 = (TextView) v.findViewById(R.id.table315);

        // 목요일 1교시~15교시 TextView
        TextView t401 = (TextView) v.findViewById(R.id.table401);
        TextView t402 = (TextView) v.findViewById(R.id.table402);
        TextView t403 = (TextView) v.findViewById(R.id.table403);
        TextView t404 = (TextView) v.findViewById(R.id.table404);
        TextView t405 = (TextView) v.findViewById(R.id.table405);
        TextView t406 = (TextView) v.findViewById(R.id.table406);
        TextView t407 = (TextView) v.findViewById(R.id.table407);
        TextView t408 = (TextView) v.findViewById(R.id.table408);
        TextView t409 = (TextView) v.findViewById(R.id.table409);
        TextView t410 = (TextView) v.findViewById(R.id.table410);
        TextView t411 = (TextView) v.findViewById(R.id.table411);
        TextView t412 = (TextView) v.findViewById(R.id.table412);
        TextView t413 = (TextView) v.findViewById(R.id.table413);
        TextView t414 = (TextView) v.findViewById(R.id.table414);
        TextView t415 = (TextView) v.findViewById(R.id.table415);

        // 금요일 1교시~15교시 TextView
        TextView t501 = (TextView) v.findViewById(R.id.table501);
        TextView t502 = (TextView) v.findViewById(R.id.table502);
        TextView t503 = (TextView) v.findViewById(R.id.table503);
        TextView t504 = (TextView) v.findViewById(R.id.table504);
        TextView t505 = (TextView) v.findViewById(R.id.table505);
        TextView t506 = (TextView) v.findViewById(R.id.table506);
        TextView t507 = (TextView) v.findViewById(R.id.table507);
        TextView t508 = (TextView) v.findViewById(R.id.table508);
        TextView t509 = (TextView) v.findViewById(R.id.table509);
        TextView t510 = (TextView) v.findViewById(R.id.table510);
        TextView t511 = (TextView) v.findViewById(R.id.table511);
        TextView t512 = (TextView) v.findViewById(R.id.table512);
        TextView t513 = (TextView) v.findViewById(R.id.table513);
        TextView t514 = (TextView) v.findViewById(R.id.table514);
        TextView t515 = (TextView) v.findViewById(R.id.table515);

        // 시간표 보여주기
        t101.append(dbHelper.show(101));
        t102.append(dbHelper.show(102));
        t103.append(dbHelper.show(103));
        t104.append(dbHelper.show(104));
        t105.append(dbHelper.show(105));
        t106.append(dbHelper.show(106));
        t107.append(dbHelper.show(107));
        t108.append(dbHelper.show(108));
        t109.append(dbHelper.show(109));
        t110.append(dbHelper.show(110));
        t111.append(dbHelper.show(111));
        t112.append(dbHelper.show(112));
        t113.append(dbHelper.show(113));
        t114.append(dbHelper.show(114));
        t115.append(dbHelper.show(115));

        t201.append(dbHelper.show(201));
        t202.append(dbHelper.show(202));
        t203.append(dbHelper.show(203));
        t204.append(dbHelper.show(204));
        t205.append(dbHelper.show(205));
        t206.append(dbHelper.show(206));
        t207.append(dbHelper.show(207));
        t208.append(dbHelper.show(208));
        t209.append(dbHelper.show(209));
        t210.append(dbHelper.show(210));
        t211.append(dbHelper.show(211));
        t212.append(dbHelper.show(212));
        t213.append(dbHelper.show(213));
        t214.append(dbHelper.show(214));
        t215.append(dbHelper.show(215));

        t301.append(dbHelper.show(301));
        t302.append(dbHelper.show(302));
        t303.append(dbHelper.show(303));
        t304.append(dbHelper.show(304));
        t305.append(dbHelper.show(305));
        t306.append(dbHelper.show(306));
        t307.append(dbHelper.show(307));
        t308.append(dbHelper.show(308));
        t309.append(dbHelper.show(309));
        t310.append(dbHelper.show(310));
        t311.append(dbHelper.show(311));
        t312.append(dbHelper.show(312));
        t313.append(dbHelper.show(313));
        t314.append(dbHelper.show(314));
        t315.append(dbHelper.show(315));

        t401.append(dbHelper.show(401));
        t402.append(dbHelper.show(402));
        t403.append(dbHelper.show(403));
        t404.append(dbHelper.show(404));
        t405.append(dbHelper.show(405));
        t406.append(dbHelper.show(406));
        t407.append(dbHelper.show(407));
        t408.append(dbHelper.show(408));
        t409.append(dbHelper.show(409));
        t410.append(dbHelper.show(410));
        t411.append(dbHelper.show(411));
        t412.append(dbHelper.show(412));
        t413.append(dbHelper.show(413));
        t414.append(dbHelper.show(414));
        t415.append(dbHelper.show(415));

        t501.append(dbHelper.show(501));
        t502.append(dbHelper.show(502));
        t503.append(dbHelper.show(503));
        t504.append(dbHelper.show(504));
        t505.append(dbHelper.show(505));
        t506.append(dbHelper.show(506));
        t507.append(dbHelper.show(507));
        t508.append(dbHelper.show(508));
        t509.append(dbHelper.show(509));
        t510.append(dbHelper.show(510));
        t511.append(dbHelper.show(511));
        t512.append(dbHelper.show(512));
        t513.append(dbHelper.show(513));
        t514.append(dbHelper.show(514));
        t515.append(dbHelper.show(515));

        // dbHelper.findtime(101);

        // 월요일 1교시~15교시 클릭 이벤트
        t101.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 101;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 101);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 101);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t102.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 102;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 102);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 102);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t103.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 103;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 103);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 103);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t104.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 104;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 104);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 104);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t105.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 105;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 105);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 105);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t106.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 106;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 106);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 106);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t107.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 107;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 107);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 107);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t108.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 108;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 108);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 108);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t109.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 109;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 109);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 109);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t110.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 110;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 110);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 110);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 111;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 111);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 111);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t112.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 112;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 112);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 112);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t113.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 113;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 113);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 113);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t114.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 114;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 114);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 114);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t115.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 115;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 115);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 115);
                    startActivityForResult(intent, 200);
                }
            }
        });

        // 화요일 1교시~15교시 클릭 이벤트
        t201.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 201;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 201);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 201);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t202.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 202;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 202);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 202);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t203.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 203;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 203);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 203);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t204.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 204;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 204);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 204);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t205.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 205;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 205);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 205);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t206.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 206;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 206);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 206);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t207.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 207;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 207);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 207);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t208.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 208;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 208);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 208);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t209.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 209;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 209);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 209);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t210.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 210;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 210);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 210);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t211.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 211;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 211);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 211);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t212.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 212;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 212);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 212);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t213.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 213;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 213);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 213);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t214.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 214;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 214);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 214);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t215.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 215;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 215);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 215);
                    startActivityForResult(intent, 200);
                }
            }
        });

        // 수요일 1교시~15교시 클릭 이벤트
        t301.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 301;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 301);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 301);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t302.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 302;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 302);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 302);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t303.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 303;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 303);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 303);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t304.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 304;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 304);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 304);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t305.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 305;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 305);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 305);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t306.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 306;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 306);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 306);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t307.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 307;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 307);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 307);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t308.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 308;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 308);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 308);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t309.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 309;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 309);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 309);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t310.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 310;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 310);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 310);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t311.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 311;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 311);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 311);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t312.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 312;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 312);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 312);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t313.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 313;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 313);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 313);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t314.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 314;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 314);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 314);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t315.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 315;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 315);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 315);
                    startActivityForResult(intent, 200);
                }
            }
        });

        // 목요일 1교시~15교시 클릭 이벤트
        t401.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 401;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 401);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 401);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t402.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 402;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 402);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 402);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t403.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 403;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 403);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 403);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t404.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 404;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 404);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 404);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t405.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 405;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 405);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 405);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t406.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 406;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 406);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 406);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t407.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 407;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 407);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 407);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t408.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 408;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 408);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 408);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t409.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 409;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 409);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 409);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t410.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 410;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 410);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 410);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t411.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 411;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 411);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 411);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t412.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 412;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 412);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 412);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t413.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 413;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 413);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 413);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t414.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 414;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 414);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 414);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t415.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 415;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 415);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 415);
                    startActivityForResult(intent, 200);
                }
            }
        });

        // 금요일 1교시~15교시 클릭 이벤트
        t501.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 501;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 501);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 501);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t502.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 502;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 502);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 502);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t503.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 503;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 503);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 503);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t504.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 504;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 504);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 504);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t505.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 505;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 505);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 505);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t506.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 506;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 506);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 506);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t507.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 507;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 507);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 507);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t508.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 508;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 508);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 508);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t509.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 509;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 509);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 509);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t510.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 510;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 510);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 510);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t511.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 511;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 511);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 511);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t512.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 512;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 512);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 512);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t513.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 513;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 513);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 513);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t514.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 514;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 514);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 514);
                    startActivityForResult(intent, 200);
                }
            }
        });
        t515.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablenumber = 515;
                if(dbHelper.find_subject(tablenumber)!=null) {
                    Intent intent = new Intent(getContext(), ViewActivity.class);
                    intent.putExtra("tablenumber", 515);
                    startActivityForResult(intent, 200);
                }
                else {
                    Intent intent = new Intent(getContext(), InsertActivity.class);
                    intent.putExtra("tablenumber", 515);
                    startActivityForResult(intent, 200);
                }
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200){
            if(resultCode == RESULT_OK){
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(this).attach(this).commit();
            }
        }
    }
}
