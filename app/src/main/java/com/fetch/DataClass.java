package com.fetch;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class DataClass implements Runnable {

    private static final String DATA_URL = " https://fetch-hiring.s3.amazonaws.com/hiring.json";
    int id,listID,nums;
    String name,num;
    private MainActivity mainActivity;

    public DataClass(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @Override
    public void run() {

        Uri dataUri = Uri.parse(DATA_URL);
        String urlToUse = dataUri.toString();

        Log.d(TAG, "run: " + urlToUse);

        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlToUse);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.d(TAG, "run: HTTP ResponseCode NOT OK: " + conn.getResponseCode());
                return;
            }

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader((new InputStreamReader(is)));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }

            Log.d(TAG, "run: " + sb.toString());

        } catch (Exception e) {
            Log.e(TAG, "run: ", e);
            return;
        }

        handleResults(sb.toString());
        Log.d(TAG, "run: ");

    }

    private void handleResults(String s) {

        final ArrayList<DataIntilization> rankList = parserJSON(s);

       mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivity.addInfo(rankList);
            }
        });
    }

    private ArrayList<DataIntilization> parserJSON(String s) {

        ArrayList<DataIntilization> rankList = new ArrayList<>();


        try {
            JSONArray Offices = new JSONArray(s);

            for (int i = 0; i < Offices.length(); i++) {

                name="";

                JSONObject cObj = Offices.getJSONObject(i);
                id=cObj.getInt("id");
                listID=cObj.getInt("listId");
                if(!cObj.isNull("name")) {
                    name = cObj.getString("name");
                }
                else {
                    name = "null";
                }


                rankList.add(new DataIntilization(id,listID,name));
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return rankList;
    }
}
