package com.example.covidtracer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private  List<StateData> newdata;
    private RequestQueue mQueue;
    private CustomAdapter adapter;


    public void activityShifter(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);




        Intent intent = getIntent();
        recyclerView = findViewById(R.id.recycleView);

        mQueue = new Volley().newRequestQueue(this);

        newdata = new ArrayList<>();

        jsonParse();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void jsonParse(){
        String url = "https://api.covid19india.org/data.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("statewise");

                    for (int i = 0;i < jsonArray.length();i++){
                        JSONObject statewise = jsonArray.getJSONObject(i);
                        StateData data = new StateData();
                        data.setStateName(statewise.getString("state").toString());
                        data.setConfirmedCases(statewise.getString("confirmed").toString());
                        data.setActiveCases(statewise.getString("active").toString());
                        data.setRecoveredCases(statewise.getString("recovered").toString());
                        data.setDeathCases(statewise.getString("deaths").toString());
                        newdata.add(data);
                        Log.i("info", String.valueOf(i));


                    }
                    adapter = new CustomAdapter(MainActivity2.this,newdata);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.i("info123","sww");

            }
        });
        mQueue.add(request);
    }


}