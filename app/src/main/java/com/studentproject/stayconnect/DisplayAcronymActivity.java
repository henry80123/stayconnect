package com.studentproject.stayconnect;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by HenryChiang on 15-01-29.
 */
public class DisplayAcronymActivity extends ListActivity {

    private final String tag = "DisplayAcronymActivity";
    private DataConnection dataConnection;
    private ArrayAdapter<Acronym> adapter;
    private String jsonResult;
    private String url = "http://10.215.84.101//select_all.php";
    final String tag2= "connection";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_acronym_layout);
        accessWebService(url);

        /* ---------------------
            dataConnection = new DataConnection(this);
            try{
                dataConnection.open();
            }
            catch (Exception e)
            { }

            List<Acronym> values = dataConnection.getAllAcronym();

            // use the SimpleCursorAdapter to show the
            // elements in a ListView
            adapter = new ArrayAdapter<Acronym>(this, android.R.layout.simple_list_item_1, values);
            setListAdapter(adapter);
        ----------------------- */

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //click on acronym to see its details
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), SingleAcronymActivity.class);
                String acronymClicked = ((TextView) view).getText().toString();
               try {
                   i.putExtra("acronym", acronymClicked);
                   startActivity(i);
               }catch(Exception e){

                    Log.d(tag2,e.toString());
                }
            }
        });

        //Search the acronym
        EditText acronymSearch = (EditText) findViewById(R.id.acronym_list_search);
        acronymSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                try {
                    DisplayAcronymActivity.this.adapter.getFilter().filter(cs);
                }catch(Exception e){
                    Toast.makeText(DisplayAcronymActivity.this, "The Acronym is empty: \n" + e.toString() , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });



    }

    private class JsonReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(params[0]);
            try {
                Log.d(tag2, "inside doInBackground.. trying to connect");
                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(
                        response.getEntity().getContent()).toString();
            }

            catch (ClientProtocolException e) {
                e.printStackTrace();
                Log.d(tag2,"fail to connect" +e.getMessage());

            } catch (IOException e) {
                Log.d(tag2,"fail to connect" + e.getMessage());
                e.printStackTrace();
            }
            Log.d(tag2,"connected!!!!");
            return null;
        }

        private StringBuilder inputStreamToString(InputStream is) {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            try {
                while ((rLine = rd.readLine()) != null) {
                    answer.append(rLine);
                }
            }

            catch (IOException e) {
                // e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Error..." + e.toString(), Toast.LENGTH_LONG).show();
            }
            return answer;
        }

        @Override
        protected void onPostExecute(String result) {
            ListDrawer();
        }


    }

    public void accessWebService(String url) {
        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        Log.d(tag2, "inside accessWebService");
        task.execute(url);
    }

    public void ListDrawer() {
        List<Acronym> values = new ArrayList<>();
        dataConnection = new DataConnection(this);
        try{
            dataConnection.open();
        }
        catch (Exception e)
        {
        Log.d("tag2",e.toString());
        }
        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("acronym");

            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                Acronym acro = new Acronym();
                acro.setAcronym(jsonChildNode.optString("acronym"));
                acro.setFull_form(jsonChildNode.optString("full_form"));
                acro.setDept(jsonChildNode.optString("department"));
                //String name = jsonChildNode.optString("acronym");
                dataConnection.registerAcronym(jsonChildNode.optString("acronym"), jsonChildNode.optString("full_form"), jsonChildNode.optString("department"));
                values.add(acro);

            }

            Collections.sort(values, new Comparator<Acronym>() {
                public int compare(Acronym a1, Acronym a2) {
                    return a1.getAcronym().compareTo(a2.getAcronym());
                }
            });

        } catch (Exception e) {
            Log.d(tag2,"Server cannot connect, Error" + e.toString());

        }
        adapter = new ArrayAdapter<Acronym>(this, android.R.layout.simple_list_item_1,values);
        setListAdapter(adapter);


    }

}