package com.swati.dell.organizations123;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    List<String> arraylist;
    TextView name1,location1,publicresp,publicgists1,responseUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=(Spinner)findViewById(R.id.spinner);
        name1=(TextView)findViewById(R.id.Value);
        location1=(TextView)findViewById(R.id.Value1);
        publicresp=(TextView)findViewById(R.id.Value2);
        publicgists1=(TextView)findViewById(R.id.Value3);
        responseUrl=(TextView)findViewById(R.id.Value4);

        arraylist= new ArrayList<String>();
        arraylist.add("ogc");
        arraylist.add("sevenwire");
        arraylist.add("entryway");
        arraylist.add("merb");
        arraylist.add("moneyspyder");
        arraylist.add("sproutit");
        arraylist.add("wrenchlabs");
        arraylist.add("ipvideomarketinfo");
        arraylist.add("revelation");
        arraylist.add("railslove");
        arraylist.add("railsdog");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraylist);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);
        }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String org= parent.getItemAtPosition(position).toString();


        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + org, Toast.LENGTH_SHORT).show();
        getorg(org);

    }

    private void getorg(String org) {

        String url = "https://api.github.com/orgs/"+org;
        final JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                     String organization_name = response.getString("name");
                     String Location = response.getString("location");
                     String public_repos = response.getString("public_repos");
                     String public_gists = response.getString("public_gists");
                     final String repos_url=response.getString("repos_url");
                     name1.setText(organization_name);
                     location1.setText(Location);
                     publicresp.setText(public_repos);
                     publicgists1.setText(public_gists);
                     responseUrl.setText(repos_url);
                      responseUrl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            ResponseUrl1 fragment=new ResponseUrl1 (repos_url);
                            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame1, fragment);
                            fragmentTransaction.commit();
                        }
                    });


                    //define formatter for yout date time





                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication()," Option is invalid ",Toast.LENGTH_SHORT).show();



            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
