package com.pef.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CrearCuenta extends AppCompatActivity {

    EditText etFname, etLname, etEmail, etRemail, etPass, etRpass, etPhone, etWeight, etHeight;
    Spinner spYear, spMonth, spDay, spCountry, spDaysEx, spHours, spExInt;
    RadioButton rbFemale, rbMale;
    TextView txtNext;
    CheckBox cbSmart, cbHead, cbBlood;

    String ip = "meddata.sytes.net";

    String CompleteName, Email, Password, Phone, Weight, Height, Birthday, Country, Gender, DeviceS,
    DeviceH, DeviceB, ExDays, ExHours, ExInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);


        txtNext = (TextView)findViewById(R.id.txtNext);
        etFname = (EditText)findViewById(R.id.etFname);
        etLname = (EditText)findViewById(R.id.etLname);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etRemail = (EditText)findViewById(R.id.etRemail);
        etPass = (EditText)findViewById(R.id.etPass);
        etRpass = (EditText)findViewById(R.id.etRpass);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etWeight = (EditText)findViewById(R.id.etWeight);
        etHeight = (EditText)findViewById(R.id.etHeight);
        spYear = (Spinner)findViewById(R.id.spYear);
        spMonth = (Spinner)findViewById(R.id.spMonth);
        spDay = (Spinner)findViewById(R.id.spDay);
        spCountry = (Spinner)findViewById(R.id.spCountry);
        rbFemale = (RadioButton)findViewById(R.id.rbFemale);
        rbMale = (RadioButton)findViewById(R.id.rbMale);
        spDaysEx=(Spinner)findViewById(R.id.spDaysEx);
        spHours=(Spinner)findViewById(R.id.spHours);
        spExInt=(Spinner)findViewById(R.id.spExInt);
        cbSmart=(CheckBox)findViewById(R.id.cbSmart);
        cbHead=(CheckBox)findViewById(R.id.cbHead);
        cbBlood=(CheckBox)findViewById(R.id.cbBlood);




        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.day, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.month, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.year, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.country, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.daysExe, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,
                R.array.hoursExe, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,
                R.array.exeInt, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spDay.setAdapter(adapter);
        spMonth.setAdapter(adapter2);
        spYear.setAdapter(adapter3);
        spCountry.setAdapter(adapter4);
        spDaysEx.setAdapter(adapter5);
        spHours.setAdapter(adapter6);
        spExInt.setAdapter(adapter7);

        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                CompleteName = etFname.getText().toString() + " " + etLname.getText().toString();

                if(etEmail.getText().toString().equals(etRemail.getText().toString())){
                    Email=etEmail.getText().toString();
                }
                if (etPass.getText().toString().equals(etRpass.getText().toString())){
                    Password = etPass.getText().toString();
                }
                if(rbFemale.isChecked()){
                    Gender = "0";
                }
                if (rbMale.isChecked()){
                    Gender="1";
                }

                Weight = etWeight.getText().toString();
                Height = etHeight.getText().toString();
                Phone = etPhone.getText().toString();
                Country = spCountry.getSelectedItem().toString();
                Birthday = spYear.getSelectedItem().toString() + spMonth.getSelectedItem().toString() + spDay.getSelectedItem().toString();

                if(cbBlood.isChecked()){
                    DeviceB="1";
                }else{
                    DeviceB="0";
                }
                if(cbHead.isChecked()){
                    DeviceH="1";
                }else{
                    DeviceH="0";
                }
                if(cbSmart.isChecked()){
                    DeviceS="1";
                }else{
                    DeviceS="0";
                }
                ExDays = spDaysEx.getSelectedItem().toString();
                ExHours = spHours.getSelectedItem().toString();
                ExInt = spExInt.getSelectedItem().toString();

                Thread tr = new Thread(){
                    @Override
                    public void run() {
                        final String res = sendPost(Email, Password, CompleteName,Phone,Gender,Weight,Height,Birthday,Country,DeviceS,
                                DeviceB,DeviceH,ExDays,ExHours,ExInt);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int r = objJASON(res);
                                if (r>0){
                                    Intent i = new Intent(getApplicationContext(),Main.class);
                                    startActivity(i);

                                }else{
                                    Toast.makeText(getApplicationContext(),"Invalid Email or Password ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                };
                tr.start();
            }
        });
    }
    public String sendPost(String email, String pass, String name, String phone, String gender,
                           String weight,String height, String birth, String country, String deviceS, String deviceB,
                           String deviceH, String days_ex, String hours_ex, String ex_int) {
        String parameters = "email=" + email + "&pass=" + pass + "&name=" + name + "&phone="
                + phone + "&gender="+ gender + "&weight=" + weight + "&height=" + height + "&birth=" + birth + "&country=" + country +
                "&deviceS=" + deviceS + "&deviceB=" + deviceB + "&deviceH=" + deviceH + "&days_ex=" + days_ex +
                "&hours_ex=" + hours_ex + "&ex_int=" + ex_int;
        HttpURLConnection conection = null;
        String response = "";

        try {
            URL url = new URL("http://"+ip+"/hcd/rg.php");

            conection = (HttpURLConnection)url.openConnection();
            conection.setRequestMethod("POST");
            conection.setRequestProperty("Content-Length", "" + Integer.toString(parameters.getBytes().length));

            conection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conection.getOutputStream());
            wr.writeBytes(parameters);
            wr.close();

            Scanner inStream = new Scanner(conection.getInputStream());

            while (inStream.hasNextLine())
                response += (inStream.nextLine());
        } catch (Exception e) {}
        return response.toString();
    }

    public int objJASON(String resp) {
        int res = 0;
        try {
            JSONArray json = new JSONArray(resp);
            if (json.length() > 0)
                res = 1;
        } catch (Exception e) {}
        return res;
    }


}
