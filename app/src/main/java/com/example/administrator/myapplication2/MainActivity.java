package com.example.administrator.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.myapplication2.utils.ConfigApp;
import com.example.administrator.myapplication2.utils.HttpClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.findViewById(R.id.startConnect).setOnClickListener(this);
    }
   // AsyncHttpClient client = new AsyncHttpClient();
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startConnect:
                Log.i("startConnect","startConnect");
                Log.i("startConnect",HttpClient.isConnected(this)+"");
             /*  HttpClient.get("https://www.baidu.conm", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.i("onSuccess","onSuccess");
                        Log.i("responseBody",responseBody.toString()+":headers"+headers.toString()+":statusCode"+statusCode);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.i("onFailure","onFailure");
                    }
                });*/


                HttpClient.get(ConfigApp.JSON_TEXT,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.i("onSuccess","onSuccess"+response.toString());
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                    }
                });
                break;
            default:
                break;
        }
    }
}
