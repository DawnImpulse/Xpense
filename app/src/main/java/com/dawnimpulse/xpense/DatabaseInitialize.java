package com.dawnimpulse.xpense;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Dawn on 5/27/2016.
 */
public class DatabaseInitialize extends AppCompatActivity {

    private static String INSERT = "INSERT INTO ";
    private static String CREATE = "CREATE TABLE IF NOT EXISTS ";

    public void databaseCreation(SQLiteDatabase myDatabase) {

        myDatabase.execSQL(CREATE + "Categories(CategoryName VARCHAR(50) NOT NULL);");
        myDatabase.execSQL(CREATE + "Transact( _id INTEGER PRIMARY KEY AUTOINCREMENT,SUM INTEGER NOT NULL,CategoryName VARCHAR(50),Note VARCHAR(50),Type VARCHAR(20));");
    }

    public void expenseCategoryInitialize(SQLiteDatabase myDatabase, String[] category)
    {
        for (String item : category)
        {
            myDatabase.execSQL(INSERT + "Categories VALUES ('"+item+"');");
        }
    }

}
