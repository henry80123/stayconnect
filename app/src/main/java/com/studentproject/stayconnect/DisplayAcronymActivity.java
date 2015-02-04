package com.studentproject.stayconnect;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by HenryChiang on 15-01-29.
 */
public class DisplayAcronymActivity extends ListActivity {


        private final String tag = "DisplayAcronymActivity";
        private DataConnection dataConnection;
        EditText acronymSearch;
        private ArrayAdapter<Acronym> adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.display_acronym_layout);
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

            ListView lv = getListView();
            //lv.setAdapter(new ArrayAdapter<Acronym>(this, android.R.layout.simple_list_item_1, values));
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

             //click on acronym to see its details
                 @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(getApplicationContext(),SingleAcronymActivity.class);
                        String acronymClicked = ((TextView) view).getText().toString();
                        i.putExtra("acronym", acronymClicked);
                        startActivity(i);

                }
            });


            //Search the acronym
            acronymSearch = (EditText) findViewById(R.id.acronym_list_search);
            acronymSearch.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    DisplayAcronymActivity.this.adapter.getFilter().filter(cs);
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

}
