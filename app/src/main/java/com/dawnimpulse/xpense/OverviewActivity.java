package com.dawnimpulse.xpense;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //Receiving Intent
        Intent intent = getIntent();
        setContentView(R.layout.activity_overview);


        //Finding The Navigation Drawer ListView
        ListView list = (ListView) findViewById(R.id.drawerList);

        //Getting Strings to set In Navigation Drawer List
        String[] array=getResources().getStringArray(R.array.drawerList);

        //Set the Navigation List
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, array);
        list.setAdapter(adapter);

        //If an Item is clicked inside the NavigationListView
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //Position Related Data
                if(position==1)
                {
                    Intent intent = new Intent(OverviewActivity.this,CategoryActivity.class);
                    startActivity(intent);
                }
            }
        });


        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("Xpense",MODE_PRIVATE,null);

        //Get Cursor
        Cursor resultSet = sqLiteDatabase.rawQuery("SELECT oid as _id , SUM,CategoryName,Type,Note FROM Transact", null);

        String colName1="SUM";
        String colName2="CategoryName";
        String colName3="Type";
        String colName4="Note";

        String[] from = new String[]{colName1,colName2,colName3,colName4};
        int[] to = new int[] {R.id.Sum1,R.id.Category1,R.id.Type1,R.id.Note1};

        SimpleCursorAdapter adapter1 = new SimpleCursorAdapter(this,R.layout.transactions,resultSet,from,to,0);
        ListView list1 = (ListView) findViewById(R.id.TransactionList);
        list1.setAdapter(adapter1);



        //FAB Code
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(OverviewActivity
                .this,AddTransactionActivity.class);
                startActivity(intent1);
            }
        });
    }

}
