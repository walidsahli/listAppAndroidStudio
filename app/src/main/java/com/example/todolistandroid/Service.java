package com.example.todolistandroid;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Service {
    private static Service instance = null;
    private String TAG = "SERVICE";
    public static ArrayList<DataModel> data = new ArrayList<DataModel>();

    private Service(){}

    public static Service getInstance(){
        if(instance == null) {
            instance = new Service();
            return instance;
        }else {
            return instance;
        }
    }

    protected void getData (Context context){
        String url = "https://www.neutrinoapi.com/apis.json";
        RequestTask req = new RequestTask(context);
        req.execute(url);
    }

    class RequestTask extends AsyncTask<String, String, ArrayList<DataModel>> {

        private AsyncListenerInterface listener;

        public RequestTask(Context context){
            listener = (AsyncListenerInterface) context;
        }

        @Override
        protected ArrayList<DataModel> doInBackground(String... uri) {
            try {
                URL url = new URL(uri[0]);
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                if(conn.getResponseCode() == HttpsURLConnection.HTTP_OK){
                    // Do normal input or output stream reading
                    BufferedInputStream response  = new BufferedInputStream(conn.getInputStream());
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response));
                    String line;
                    StringBuilder sb =  new StringBuilder();
                    while ((line = rd.readLine()) != null) {
                        sb.append(line);
                    }
                    rd.close();
                    String contentOfMyInputStream = sb.toString();
                    JSONObject content = new JSONObject(contentOfMyInputStream);
                    JSONArray jsonArray = content.getJSONArray("apis");
                    int counter = 0;
                    ArrayList<DataModel> result = new ArrayList<>();
                    while( counter < jsonArray.length()){
                        String id = jsonArray.getJSONObject(counter).getString("name");
                        String image = jsonArray.getJSONObject(counter).getString("image");
                        String title = jsonArray.getJSONObject(counter).getString("description");
                        DataModel item = new DataModel(id,title,image );
                        result.add(item);
                        counter++;
                    }
                    return result;
                }
                else {
                    return null; // See documentation for more info on response handling
                }
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<DataModel> result) {
            super.onPostExecute(result);
            //Do anything with response..
            data = result;
            listener.onTaskFinished();
        }
    }
}
