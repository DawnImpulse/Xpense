package com.dawnimpulse.xpense;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class AddTransactionActivity extends AppCompatActivity implements OnItemSelectedListener {

    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private TextView textView;
    private static final String SELECT_SQL = "SELECT * FROM persons";
    private boolean value = false;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        //Get the text

//
        SQLiteDatabase myDatabase = openOrCreateDatabase("Xpense", MODE_PRIVATE, null);

        // Spinner TypeDrop down elements

        String[] type = {"Saving", "Expense"};

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);

        // Drop down layout style - Spinner
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.Type);
        spinner.setOnItemSelectedListener(this);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        //Get Cursor
        Cursor resultSet = myDatabase.rawQuery("SELECT * FROM Categories", null);

        //Create String to Get Cursor Elements
        String[] array = new String[resultSet.getCount()];
        int i = 0;

        //Move from Cursor to String
        resultSet.moveToFirst();
        array[i] = resultSet.getString(resultSet.getColumnIndex("CategoryName"));

        while (resultSet.moveToNext()) {
            i++;
            String categoryName = resultSet.getString(resultSet.getColumnIndex("CategoryName"));
            array[i] = categoryName;
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array);

        // Drop down layout style - Spinner
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner1 = (Spinner) findViewById(R.id.Spinner);
        spinner1.setOnItemSelectedListener(this);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter1);



        //Get Amount


        //FAB Code
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDatabaseItems();
                Intent intent = new Intent(AddTransactionActivity.this,OverviewActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.Type) {
            if (position == 0) {
                value = true;
            }
            if(position ==1)
            {
                value = false;
            }
        }
        if (spinner.getId() == R.id.Spinner)
        {
            category = parent.getItemAtPosition(position).toString();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setDatabaseItems()
    {
        EditText text = (EditText) findViewById(R.id.addTransactionName);
        String note = text.getText().toString();

        EditText textAmount = (EditText) findViewById(R.id.editAmount);
        String sum = textAmount.getText().toString();

        String type = returnString(value);

        SQLiteDatabase myDatabase = openOrCreateDatabase("Xpense", MODE_PRIVATE, null);
        myDatabase.execSQL("INSERT INTO Transact(Sum,CategoryName,Note,Type) VALUES('"+sum+"','"+category+"','"+note+"','"+type+"');");
    }

    private String returnString(boolean value)
    {
        String expense = "Expense";
        String saving  = "Saving";
        if(value==true)
        {
            return saving;
        }
        else return expense;
    }
}
