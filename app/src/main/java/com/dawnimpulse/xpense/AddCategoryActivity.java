package com.dawnimpulse.xpense;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCategory();
                Intent intent = new Intent(AddCategoryActivity.this,CategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addCategory()
    {
        EditText editText = (EditText) findViewById(R.id.NewCategoryName);
        String category = editText.getText().toString();

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("Xpense",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("INSERT INTO Categories Values ('"+category+"');");

    }
}
