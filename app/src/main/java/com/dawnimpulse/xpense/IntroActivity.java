package com.dawnimpulse.xpense;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import java.io.File;

public class IntroActivity extends AppCompatActivity {

    int introActivity=1;
    int introActivityDefault=0;         // Value 0 means App is opened first time
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check to see if Database present or not
        if (doesDatabaseExist(this) == false)
        {
            getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
            getSupportActionBar().hide();
            setContentView(R.layout.activity_intro);
        }
        else
        {
            //Calling Overview Activity
            Intent intent = new Intent(this,OverviewActivity.class);
            startActivity(intent);
        }

    }
    public void overviewActivityMethod(View view)
    {
        //Open Database
        SQLiteDatabase myDatabase = openOrCreateDatabase("Xpense",MODE_PRIVATE,null);

        DatabaseInitialize databaseInitialize = new DatabaseInitialize(); //New Java Class
        String[] array=getResources().getStringArray(R.array.category);

        //Database Creation
        databaseInitialize.databaseCreation(myDatabase);

        //Multiple Database Initialize
        databaseInitialize.expenseCategoryInitialize(myDatabase,array);
        //Call OverView Activity
        Intent intent = new Intent(this,OverviewActivity.class);
        startActivity(intent);
    }

    private static boolean doesDatabaseExist(Context context)
    {
        File dbFile = context.getDatabasePath("Xpense");
        return dbFile.exists();
    }
}
