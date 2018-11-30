package com.example.gbese.callmemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView coontentList;
    SQLiteDatabase db;
   
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coontentList = (ListView) findViewById(R.id.contentlist);


    }

    @Override
    protected void onStart() {
        super.onStart();
        SQLiteOpenHelper data = new UserData(this);
        try {
            db = data.getReadableDatabase();
            cursor = db.query("MMEMO",
                    new String[]{"_id", "CONTENT"},
                    null, null, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"CONTENT"},
                    new int[]{android.R.id.text1},
                    0);
            coontentList.setAdapter(listAdapter);

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.writetext,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.noteitem:
                Intent intent= new Intent(this,UserNote.class);
                startActivity(intent);
        }
        return true;
    }
}