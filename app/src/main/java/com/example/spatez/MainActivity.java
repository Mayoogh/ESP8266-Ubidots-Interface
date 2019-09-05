package com.example.spatez;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName() ;
    Button LightOn, LightOff, Low, Med, High;
    TextView textView1, textView2;

    private  RequestQueue mRequestQueue;
    private  StringRequest stringRequest;

    private static String deviceLabel = "spatez";
    private static String variable1 = "device1";
    private static String variable2 = "device2";
    private static String token = "BBFF-6oK8NKYnkipV5A5VyGzqP9uPjVfZut";
    private static String endpoint = "https://industrial.api.ubidots.com/api/v1.6/devices/";
    private static String userAgent = "Java/0.1";
    private static String ON = "1";
    private static String OFF = "0";
    private static String LOW = "341";
    private static String MED = "682";
    private static String HIGH = "1023";

    private String LighOnUrl = endpoint+deviceLabel+"/?token="+token+"&_method=post&"+variable1+"="+ON;
    private String LighOffUrl = endpoint+deviceLabel+"/?token="+token+"&_method=post&"+variable1+"="+OFF;
    private String LowUrl = endpoint+deviceLabel+"/?token="+token+"&_method=post&"+variable2+"="+LOW;
    private String MedUrl = endpoint+deviceLabel+"/?token="+token+"&_method=post&"+variable2+"="+MED;
    private String HighUrl = endpoint+deviceLabel+"/?token="+token+"&_method=post&"+variable2+"="+HIGH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LightOn = findViewById(R.id.buttonIdLightOn);
        LightOff = findViewById(R.id.buttonIdLightOff);
        Low = findViewById(R.id.buttonIdLow);
        Med = findViewById(R.id.buttonIdMed);
        High = findViewById(R.id.buttonIdHigh);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        LightOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LightOnRequest();
            }
        });
        LightOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LightOffRequest();
            }
        });
        Low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LowRequest();
            }
        });
        Med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MedRequest();
            }
        });
        High.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HighRequest();
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    private void LightOnRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.GET, LighOnUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"Response : " + response.toString());
                textView1.setText("Bulb Status : ON");
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error" + error.toString());
            }
        });
        mRequestQueue.add(stringRequest);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    private void LightOffRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.GET, LighOffUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"Response : " + response.toString());
                textView1.setText("Bulb Status : OFF");
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error" + error.toString());
            }
        });
        mRequestQueue.add(stringRequest);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    private void LowRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.GET, LowUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"Response : " + response.toString());
                textView2.setText("Bulb Status : Low");
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error" + error.toString());
            }
        });
        mRequestQueue.add(stringRequest);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    private void MedRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.GET, MedUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"Response : " + response.toString());
                textView2.setText("Bulb Status : Medium");
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error" + error.toString());
            }
        });
        mRequestQueue.add(stringRequest);
}
////////////////////////////////////////////////////////////////////////////////////////////////////
    private void HighRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.GET, HighUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"Response : " + response.toString());
                textView2.setText("Bulb Status : High");
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error" + error.toString());
            }
        });
        mRequestQueue.add(stringRequest);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
}

