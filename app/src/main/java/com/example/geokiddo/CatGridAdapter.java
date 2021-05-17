package com.example.geokiddo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.Random;

public class CatGridAdapter extends BaseAdapter {
    private List<String> catList;

    public CatGridAdapter(List<String> catList) {
        this.catList = catList;
    }


    @Override
    public int getCount() {
        return catList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView( final int position, View convertView,final ViewGroup parent) {

        View view;

        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item_layout,parent,false);

        }
        else{
            view = convertView;
        }



        ((TextView) view.findViewById(R.id.catName)).setText(catList.get(position));

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
        view.setBackgroundColor(color);


        return view;
    }
}
