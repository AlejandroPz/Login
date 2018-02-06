package com.pef.login;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class menu_principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView day;
    TextView text_monday;
    TextView text_tuesday;
    TextView text_wednesday;
    TextView text_thursday;
    TextView text_friday;
    TextView text_saturday;
    TextView text_sunday;
    SimpleDateFormat dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //Variables
        day = (TextView)findViewById(R.id.textView4);
        text_monday = (TextView)findViewById(R.id.text_monday);
        text_tuesday = (TextView)findViewById(R.id.text_tuesday);
        text_wednesday = (TextView)findViewById(R.id.text_wednesday);
        text_thursday = (TextView)findViewById(R.id.text_thursday);
        text_friday = (TextView)findViewById(R.id.text_friday);
        text_saturday = (TextView)findViewById(R.id.text_saturday);
        text_sunday = (TextView)findViewById(R.id.text_sunday);

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        String currentDateTimeStrin = formatter.format(today);

        if (currentDateTimeStrin.equalsIgnoreCase("Monday") || currentDateTimeStrin.equalsIgnoreCase("Lunes")){
            text_monday.setBackgroundColor(Color.parseColor("#b8ddcd"));
        }else{
            text_monday.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        if (currentDateTimeStrin.equalsIgnoreCase("Tuesday") || currentDateTimeStrin.equalsIgnoreCase("Martes")){
            text_tuesday.setBackgroundColor(Color.parseColor("#b8ddcd"));
        }else{
            text_tuesday.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        if (currentDateTimeStrin.equalsIgnoreCase("Wednesday") || currentDateTimeStrin.equalsIgnoreCase("Miércoles")){
            text_wednesday.setBackgroundColor(Color.parseColor("#b8ddcd"));
        }else {
            text_wednesday.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        if (currentDateTimeStrin.equalsIgnoreCase("Thursday") || currentDateTimeStrin.equalsIgnoreCase("Jueves")){
            text_thursday.setBackgroundColor(Color.parseColor("#b8ddcd"));
        }else {
            text_thursday.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        if (currentDateTimeStrin.equalsIgnoreCase("Friday") || currentDateTimeStrin.equalsIgnoreCase("Viernes")){
            text_friday.setBackgroundColor(Color.parseColor("#b8ddcd"));
        }else {
            text_friday.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        if (currentDateTimeStrin.equalsIgnoreCase("Saturday") || currentDateTimeStrin.equalsIgnoreCase("Sabado")){
            text_saturday.setBackgroundColor(Color.parseColor("#b8ddcd"));
        }else {
            text_saturday.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        if (currentDateTimeStrin.equalsIgnoreCase("Sunday") || currentDateTimeStrin.equalsIgnoreCase("Domingo")){
            text_sunday.setBackgroundColor(Color.parseColor("#b8ddcd"));
        }else {
            text_sunday.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu_principal_drawer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item){

        int id = item.getItemId();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_main){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Fragment()).commit();
        }else if(id == R.id.nav_profile){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Fragment()).commit();
        }else if(id == R.id.nav_stadistics){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Fragment()).commit();
        }else if( id == R.id.nav_healthcare){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Fragment()).commit();
        }else if(id == R.id.nav_records){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Fragment()).commit();
        }else if(id == R.id.nav_settings){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Fragment()).commit();
        }else if(id == R.id.nav_about){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Fragment()).commit();
        }
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        return (keyCode == KeyEvent.KEYCODE_BACK ? true : super.onKeyDown(keyCode, event));
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
