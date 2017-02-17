package com.example.administrator.myapplication2.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class HttpClient {
    /**
     * 判断是否有网络
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        // Log.e(TAG, "isConnnected");
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivityManager) {
            NetworkInfo networkInfo[] = connectivityManager.getAllNetworkInfo();

            if (null != networkInfo) {
                for (NetworkInfo info : networkInfo) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /** 拼Json */
    public static String getString(Map<?, ?> map) {
        Gson gson = new Gson();
        // Log.i(TAG, "--json---" + gson.toJson(map));
        return gson.toJson(map);
    }

    private static AsyncHttpClient client = new AsyncHttpClient(); // 实例话对象
    static {
        client.setTimeout(7000); // 设置链接超时，如果不设置，默认为10s
    }


    public static void get(String urlString, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象
    {
        Log.i("cacec", "get URL:" + urlString);
        client.get(urlString, res);
    }

    public static void get(String urlString, RequestParams params,
                           AsyncHttpResponseHandler res) // url里面带参数
    {
        Log.i("cacec",
                "get URL:" + urlString + "\n		params:" + params.toString());
        client.get(urlString, params, res);
    }

    /**
     * 不带参数，获取json对象或者数组
     * @param context
     * @param url
     * @param headers
     * @param res
     */
    public static void get(Context context, String url, Header[] headers,
                           JsonHttpResponseHandler res)
    {
        Log.i("cacec", "get URL:" + url + "  headers:" + headers.toString());
        client.get(context, url, headers, null, res);
    }

    /**
     * 不带参数，获取json对象或者数组
     * @param urlString
     * @param res
     */
    public static void get(String urlString, JsonHttpResponseHandler res)
    {
        Log.i("cacec", "get URL:" + urlString);
        client.get(urlString, res);
    }

    /**
     * 带参数，获取json对象或者数组
     * @param urlString
     * @param params
     * @param res
     */
    public static void get(String urlString, RequestParams params,
                           JsonHttpResponseHandler res)
    {
        Log.i("cacec",
                "get URL:" + urlString + "\n		params:" + params.toString());
        client.get(urlString, params, res);
    }

    /**
     *  下载数据使用，会返回byte数据
     * @param uString
     * @param bHandler
     */
    public static void get(String uString, BinaryHttpResponseHandler bHandler)
    {
        client.get(uString, bHandler);
    }

    public static void post(Context context, String url,
                            Map<String, Object> map, JsonHttpResponseHandler res) {
        HttpEntity entity = null;
        try {
            entity = new StringEntity(HttpClient.getString(map), "UTF-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Log.i("cacec",
                "post URL:" + url + "\n		map:" + HttpClient.getString(map));

        client.post(context, url, entity, "application/json", res);
    }


    public static void post(Context context, String url, RequestParams params, JsonHttpResponseHandler responseHandler){
        Log.i("cacec",
                "post URL:" + url + "\n		params:" + params.toString());
        client.post(context, url, params, responseHandler);
    }
}
