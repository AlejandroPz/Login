package com.pef.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class app_bar_menu extends AppCompatActivity {
    TextView dia;
    TextView text_monday = (TextView)findViewById(R.id.text_monday);
    TextView text_tuesday = (TextView)findViewById(R.id.text_tuesday);
    TextView text_wednsday = (TextView)findViewById(R.id.text_wednesday);
    TextView text_thursday = (TextView)findViewById(R.id.text_thursday);
    TextView text_friday = (TextView)findViewById(R.id.text_friday);
    TextView text_saturday = (TextView)findViewById(R.id.text_saturday);
    TextView text_sunday = (TextView)findViewById(R.id.text_sunday);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_menu_principal);
        Date d = new Date();
        SimpleDateFormat dia = new SimpleDateFormat("EEEE");
        String currentDateTimeStrin = dia.format(dia);

        if(currentDateTimeStrin.equalsIgnoreCase("Monday")){
            text_monday.setBackgroundColor(Color.parseColor("#0000FF"));
        }else{
            text_monday.setBackgroundColor(Color.parseColor("#000000"));
        }
    }

}
