package com.pef.login;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.pef.login.Fragments.profileFragment;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class menu_principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    TextView text_monday;
    TextView text_tuesday;
    TextView text_wednesday;
    TextView text_thursday;
    TextView text_friday;
    TextView text_saturday;
    TextView text_sunday;
    SimpleDateFormat dia;
    ImageView image_profile;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothClass bluetoothDevice;
    Button button;

    private static final int REQUEST_ENABLED = 0;
    private static final int REQUEST_ENABLED_BT = 1;
    private static final int REQUEST_DISCOVERABLE = 0;

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

        //------------Nombre Email Imagen Menu--------------------------
        GlobalVars g = (GlobalVars)getApplication();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
////
        View hView = navigationView.getHeaderView(0);
        ImageView image_profile = (ImageView)findViewById(R.id.image_profile);
        TextView txtNameM = (TextView)hView.findViewById(R.id.txtNameM);
        TextView txtEmailM =(TextView)hView.findViewById(R.id.txtEmailM);
        ImageView ivProfileM = (ImageView)hView.findViewById(R.id.ivProfileM);
        TextView txtmonday = (TextView)hView.findViewById(R.id.text_monday);
        txtEmailM.setText(g.getEmail());
        txtNameM.setText(g.getName());
        Picasso.with(this).load("http://meddata.sytes.net/phpfiles/pImg/" + g.getImage())
                .resize(150,150).centerCrop().into(ivProfileM);
        //--------------------------------------------------------------------
        //Main screen
        LottieAnimationView animationView_nivel = (LottieAnimationView)findViewById(R.id.animation_view_nivel);
        LottieAnimationView animationView_nivel_rojo = (LottieAnimationView)findViewById(R.id.animation_view_nivel_rojo);
        Picasso.with(this).load("http://meddata.sytes.net/phpfiles/pImg/" + g.getImage())
                .resize(150,150).centerCrop().into(image_profile);
        animationView_nivel.setSpeed(10f);
        animationView_nivel_rojo.setSpeed(100f);
        animationView_nivel.playAnimation(0,10);
        animationView_nivel_rojo.playAnimation(0,30);

        TextView textNameMain = (TextView)findViewById(R.id.textNameMain);
        textNameMain.setText(g.getName());
        //--------------------------------------------------------------------
        //Bluetoo
        //--------------------------------------------------------------------
        TextView text = (TextView)findViewById(R.id.textView3);
        /*mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null){

            Toast.makeText(this,"Bluetooth not supported",Toast.LENGTH_LONG).show();
        }
        String adress = mBluetoothAdapter.getAddress();
        //text.setText(adress);
        int type = bluetoothDevice.getDeviceClass();
        text.setText("" +type);
        */
        BluetoothSPP bt = new BluetoothSPP(this);
        if(!bt.isBluetoothAvailable()) {
            // any command for bluetooth is not available
            Toast.makeText(getApplicationContext(), "NO ", Toast.LENGTH_SHORT).show();
        }else { Toast.makeText(getApplicationContext(), "SI ", Toast.LENGTH_SHORT).show();
        }
        if(!bt.isBluetoothEnabled()) {
            // Do somthing if bluetooth is disable
            Toast.makeText(getApplicationContext(), "ACTIVATE  ", Toast.LENGTH_SHORT).show();
        } else {
            // Do something if bluetooth is already enable
            Toast.makeText(getApplicationContext(), "GOOD ", Toast.LENGTH_SHORT).show();
        }
        bt.startService(BluetoothState.DEVICE_ANDROID);







        // Botones principales main
        ImageButton imageButtonSW = (ImageButton)findViewById(R.id.imageButtonSW);
        ImageButton imageButtonSH = (ImageButton)findViewById(R.id.imageButtonSH);
        ImageButton imageButtonSS = (ImageButton)findViewById(R.id.imageButtonSS);



        imageButtonSW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "BOTON", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(menu_principal.this, DeviceList.class);
                startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
            }
        });
        imageButtonSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageButtonSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //
        //Variables
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
            fragmentManager.beginTransaction().replace(R.id.contenedor,new profileFragment()).commit();
            //Intent intent = new Intent(menu_principal.this, CrearCuenta.class);
            //startActivity(intent);
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
