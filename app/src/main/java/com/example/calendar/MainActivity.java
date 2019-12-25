package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    adapcalendar adapter;
    List<String> spmonth,spyear;
    GridView gvMdate;
    TextView tvEtext;
    Spinner spMmonth,spMyear;
    int curmonth,curyear,year,month;
    adapstruct days;
    float x1,x2;
    Button btMdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            gvMdate = findViewById(R.id.gvMdate);
            spMmonth=findViewById(R.id.spMmonth);
            spMyear=findViewById(R.id.spMyear);
            try {
                btMdate = findViewById(R.id.btMdate);
                btMdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        curyear = year;
                        curmonth = month;
                        spMmonth.setSelection(curmonth - 1);
                        spMyear.setSelection(curyear - 2010);
                        setdate(curmonth, curyear);
                        gvMdate.setAdapter(adapter);

                    }
                });
            }
            catch (Exception e){

            }
            spmonth=new ArrayList<>();
            spyear=new ArrayList<>();
            Calendar c= Calendar.getInstance();
             year=c.get(Calendar.YEAR);
             month=c.get(Calendar.MONTH)+1;
            days = new adapstruct();
            curmonth=month;
            curyear=year;
            days.thisday=c.get(Calendar.DATE);
            days.thismonth=month;
            days.thisyear=year;
            setdate(curmonth,curyear);
            setMonth();
            setYear();

            adapter = new adapcalendar(days, this);
            ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spmonth);
            ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spyear);
            gvMdate.setAdapter(adapter);
            monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spMmonth.setAdapter(monthAdapter);
            spMyear.setAdapter(yearAdapter);
            spMmonth.setSelection(month-1);
            spMyear.setSelection(year-2010);
            spMyear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    curyear=2010+(int)id;
                    setdate(curmonth,curyear);
                    gvMdate.setAdapter(adapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spMmonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    curmonth=1+(int)id;
                    setdate(curmonth,curyear);
                    gvMdate.setAdapter(adapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        catch (Exception e){
            setContentView(R.layout.excetionlayout);
            tvEtext=findViewById(R.id.tvEtext);
            tvEtext.setText("exception oCCUREd \n"+e.getMessage());
        }
    }
    void setdate(int mm,int yy){
        int[] month={1,4,4,0,2,5,0,3,6,1,4,6};
        int[] cen={6,4,2,0};
        int dd=1,y1,y2,yr,tt,r;
        y2=yy%100;
        yr=y2/4;
        tt=yr+dd+month[mm-1]+y2;
        if(mm==1||mm==2){
            if(yy%4==0&&yy%100!=0||yy%400==0){
               tt=tt-1;
            }
        }
        y1=yy%400;
        if(y1<100){
            tt=tt+cen[0];
        }
        else if(y1<200){
            tt=tt+cen[1];
        }
        else if(y1<300){
            tt=tt+cen[2];
        }
        else {
            tt=tt+cen[3];
        }
        r=tt%7;
        if(r==0){
            r=7; //sat day is 7 instead of 0.
        }
        int[] MONTH={31,28,31,30,31,30,31,31,30,31,30,31};
        if(yy%4==0&&yy%100!=0||yy%400==0){
            MONTH[1]=29;
        }
        else{
            MONTH[1]=28;
        }
        int premm;
        if(mm==1){
            premm=12;
        }
        else {
            premm=mm-1;
        }
        int fday,fbreak,sbreak,fl=0;
        if(r<=3) {
            fday = MONTH[premm - 1] - 7 - r + 2;
        }
        else {
            fday=MONTH[premm-1]-r+2;
        }
        fbreak=MONTH[premm-1];
        sbreak=MONTH[mm-1];
        days.dispmonth=mm;
        days.dispyear=yy;
        for (int i = 0; i < 42; i++) {
            days.num[i] = fday;
            fday++;
            if ((fday ==fbreak+1)&&(fl==0)) {
                fday = 1;
                days.fid=i+1;
                fl=1;
            }
            if((fday==sbreak+1)&&(fl==1)){
                fday=1;
                days.sid=i;
                fl=0;
            }
        }


    }
    void setMonth(){
        spmonth.add("JANUARY");
        spmonth.add("FEBRUARY");
        spmonth.add("MARCH");
        spmonth.add("APRIL");
        spmonth.add("MAY");
        spmonth.add("JUNE");
        spmonth.add("JULY");
        spmonth.add("AUGUST");
        spmonth.add("SEPTEMBER");
        spmonth.add("OCTOBER");
        spmonth.add("NOVEMBER");
        spmonth.add("DECEMBER");
    }

    void setYear(){
        spyear.add("2010");
        spyear.add("2011");
        spyear.add("2012");
        spyear.add("2013");
        spyear.add("2014");
        spyear.add("2015");
        spyear.add("2016");
        spyear.add("2017");
        spyear.add("2018");
        spyear.add("2019");
        spyear.add("2020");
        spyear.add("2021");
        spyear.add("2022");
        spyear.add("2023");
        spyear.add("2024");
        spyear.add("2025");
        spyear.add("2026");
        spyear.add("2027");
        spyear.add("2028");
        spyear.add("2029");
        spyear.add("2030");

    }


    @Override
    public  boolean dispatchTouchEvent(MotionEvent event){
        float MIN_DISTANCE=70;
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1=event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        curmonth=curmonth-1;
                        if(curmonth==0){
                            curmonth=12;
                            curyear=curyear-1;
                            if(curyear<=2009){
                                curyear=2010;
                                curmonth=1;
                            }
                        }
                        spMmonth.setSelection(curmonth-1);
                        spMyear.setSelection(curyear-2010);
                        setdate(curmonth,curyear);
                        gvMdate.setAdapter(adapter);

                    }

                    // Right to left swipe action
                    else
                    {
                        curmonth=curmonth+1;
                        if(curmonth==13){
                            curmonth=1;
                            curyear=curyear+1;
                            if(curyear>=2031){
                                curyear=2030;
                                curmonth=12;
                            }
                        }
                        spMmonth.setSelection(curmonth-1);
                        spMyear.setSelection(curyear-2010);
                        setdate(curmonth,curyear);
                        gvMdate.setAdapter(adapter);

                    }

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.dispatchTouchEvent(event);

    }
}
