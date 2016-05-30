package com.dawnimpulse.xpense;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.database.sqlite.SQLiteDatabase;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //Finding The Navigation Drawer ListView
        ListView list1 = (ListView) findViewById(R.id.drawerList);

        //Getting Strings to set In Navigation Drawer List
        String[] array1=getResources().getStringArray(R.array.drawerList);

        //Set the Navigation List
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, array1);
        list1.setAdapter(adapter1);

        //If an Item is clicked inside the NavigationListView
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Position Related Data
                if(position==0)
                {
                    Intent intent = new Intent(CategoryActivity.this,OverviewActivity.class);
                    startActivity(intent);
                }

            }
        });

        //Initialize Database
        SQLiteDatabase myDatabase = openOrCreateDatabase("Xpense",MODE_PRIVATE,null);

        //Get Cursor
        Cursor resultSet = myDatabase.rawQuery("SELECT * FROM Categories", null);

        //Create String to Get Cursor Elements
        String[] array = new String[resultSet.getCount()];
        int i = 0;

        //Move from Cursor to String
        resultSet.moveToFirst();
        array[i]=resultSet.getString(resultSet.getColumnIndex("CategoryName"));

        while(resultSet.moveToNext())
        {
            i++;
            String categoryName = resultSet.getString(resultSet.getColumnIndex("CategoryName"));
            array[i] = categoryName;
        }

        //Find List VIew
        ListView list = (ListView) findViewById(R.id.expenseList) ;

        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, array);

        //Set Adapter
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        //FAB Code
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this,AddCategoryActivity.class);
                startActivity(intent);
            }
        });
    }




}
