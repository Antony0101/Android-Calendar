package com.example.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

class adapstruct{
    int[] num;
    int thisday;
    int thismonth;
    int thisyear;
    int dispmonth;
    int dispyear;
    int fid,sid;
    adapstruct(){
        num=new int[42];
    }
}

public class adapcalendar extends BaseAdapter {
     adapstruct days;
    Context context;
    LayoutInflater inflater;

    public adapcalendar(adapstruct num, Context context) {
        this.days = num;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return days.num.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=inflater.inflate(R.layout.gridview,null);
        TextView tvGlayout=v.findViewById(R.id.tvGlayout);
        tvGlayout.setText(Integer.toString(days.num[position]));
        tvGlayout.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.black,null));
        if(position<days.fid||position>days.sid){
            tvGlayout.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.fadeblack,null));
        }
        else if(days.dispmonth==days.thismonth&&days.thisyear==days.dispyear&&days.num[position]==days.thisday){
            tvGlayout.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.red,null));
        }
        return  v;
    }
}
