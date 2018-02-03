package com.pef.login;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_menu_principal);
    }
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

}
