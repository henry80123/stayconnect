package com.studentproject.stayconnect;


        import android.app.ListActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;

        import java.util.List;


public class TestDatabaseActivity extends ListActivity {


    private DataConnection dataConnection;
    EditText Input;
    String tag = "db";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acronym_testing);
        dataConnection = new DataConnection(this);
        try{
            dataConnection.open();
        }
        catch (Exception e)
        { }



        List<Acronym> values = dataConnection.getAllAcronym();

        Input = (EditText) findViewById(R.id.input);
        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Acronym> adapter = new ArrayAdapter<Acronym>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(),SingleAcronymActivity.class);

                String num = ((TextView) view).getText().toString();


                i.putExtra("acronym", num);
                startActivity(i);



            }
        });


/*
        // Binding Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<Acronym>(this, android.R.layout.simple_list_item_1, R.id.label, values));
        // refer the ArrayAdapter Document in developer.android.com
        ListView lv = getListView();

        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                // selected item
                String num = ((TextView) view).getText().toString();

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), SingleAcronymActivity.class);

                // sending data to new activity
                i.putExtra("acronym", num);
                startActivity(i);

            }
        });*/

    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Acronym> adapter = (ArrayAdapter<Acronym>) getListAdapter();
        String ff = "fullform";
        String depts = "dept";
        Acronym acronym = null;
        switch (view.getId())
        {
            case R.id.add:
                String aacronym = Input.getText().toString() ;
                if(aacronym.length() > 0 )
                {
                    // save the new comment to the database
                    acronym = dataConnection.registerAcronym(aacronym, ff,  depts);
                    adapter.add(acronym);
                    Input.setText("");
                }
                break;

        }
        adapter.notifyDataSetChanged();


    }

    @Override
    protected void onResume() {
        try
        {
            dataConnection.open();
        }catch (Exception e)
        {

        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataConnection.close();
        super.onPause();
    }

}