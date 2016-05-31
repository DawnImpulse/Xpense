package com.dawnimpulse.xpense;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Dawn on 5/31/2016.
 */
public class DatabaseFetchActivity extends AppCompatActivity
{
    private int saving , expense;

    public int setSaving(SQLiteDatabase sqLiteDatabase)
    {
        Cursor cursor = sqLiteDatabase.rawQuery("select SUM(sum) as total from Transact where type ='Saving';",null);
        if(cursor==null)
            return 0;
        cursor.moveToFirst();
        saving = cursor.getInt(cursor.getColumnIndex("total"));
        return saving;
    }
    public int setExpense(SQLiteDatabase sqLiteDatabase)
    {
        Cursor cursor = sqLiteDatabase.rawQuery("select SUM(sum)as total from Transact where type ='Expense';",null);
        if(cursor==null)
            return 0;
        cursor.moveToFirst();
        expense = cursor.getInt(cursor.getColumnIndex("total"));
        return expense;

    }
    public int returnBalance(SQLiteDatabase sqLiteDatabase)
    {
        int balance = saving - expense;
        return balance;

    }
}
