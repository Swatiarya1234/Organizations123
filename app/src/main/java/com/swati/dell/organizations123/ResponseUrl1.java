package com.swati.dell.organizations123;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ResponseUrl1 extends Fragment {
    String Url;
    WebView webView;


    public ResponseUrl1(String Url) {
        // Required empty public constructor
        this.Url=Url;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v1 = inflater.inflate(R.layout.fragment_response_url1, container, false);
        webView=(WebView)v1.findViewById(R.id.repos_url);
        getUrl();
        // Inflate the layout for this fragment
        return v1;
    }

    private void getUrl() {

        final JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String githuburl = response.getString("html_url");
                    Log.d("url",githuburl);
                    webView.loadUrl(githuburl);
                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    webView.getSettings().setPluginState(WebSettings.PluginState.ON);
                    webView.setWebViewClient(new WebViewClient());





                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(jor);
    }
    }


