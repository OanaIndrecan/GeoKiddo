package com.example.geokiddo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private GridView catGrid;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

       // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Casele Geoparcului");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        catGrid = findViewById(R.id.catGridView);

        List<String> catList = new ArrayList<>();

        catList.add("Casa Geoparcului");
        catList.add("Casa Vulcanilor");
        catList.add("Casa Dinozaurilor Pitici");
        catList.add("Casa Traditiilor");
        //catList.add("Casa Pietrelor");
        //catList.add("Casa pentru Stiinta si Arta");

        CatGridAdapter adapter = new CatGridAdapter(catList);
        catGrid.setAdapter(adapter);

//        catGrid.getChildAt(0).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(parent.getContext().CasaGeoparcului)
//            }
//        });
        catGrid.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                if(position==0) {
                    Intent intent = new Intent(view.getContext(), CasaGeoparculuiInfo.class);
                    startActivity(intent);
                }
                else if(position==1)
                {
                    Intent intent = new Intent(view.getContext(), CasaVulcanilor.class);
                    startActivity(intent);

                }
                else if(position==2)
                {
                    Intent intent = new Intent(view.getContext(), CasaDinoPitici.class);
                    startActivity(intent);

                }
                else if(position==3)
                {
                    Intent intent = new Intent(view.getContext(), CasaTraditiilor.class);
                    startActivity(intent);

                }

            }
        });




    }

    //@Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if(item.getItemId()== android.R.id.home){
//            CategoryActivity.this.finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    private void setSupportActionBar(Toolbar toolbar) {
//    }
}
