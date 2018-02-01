package com.pef.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;


import java.io.DataOutput;
import java.io.DataOutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etPass;
    Button btnLog;
    String ip = "meddata.sytes.net";
    TextView txtCreateA;
    JSONArray jsonArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btnLog = (Button) findViewById(R.id.btnLog);
        txtCreateA = (TextView) findViewById(R.id.txtCreateA);

        txtCreateA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CrearCuenta.class);
                startActivity(i);
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolleyPetition("http://"+ ip +"/hcd/consultarUsuario.php?email=" + etEmail.getText().toString()
                        + "&pass=" +etPass.getText().toString());
            }
        });
    }


    private void VolleyPetition(String URL) {
        Log.i("url", "" + URL);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    jsonArray = new JSONArray(response);
                    String password = jsonArray.getString(2);
                    String email = jsonArray.getString(1);
                    txtCreateA.setText(email);
                    if (etEmail.getText().toString().equals(email) & etPass.getText().toString().equals(password)) {

                        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Main.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect user or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "User doesn't exist", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }
    }





