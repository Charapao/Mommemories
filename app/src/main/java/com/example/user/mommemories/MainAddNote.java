package com.example.user.mommemories;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.sql.Date;

import static com.example.user.mommemories.Constants.*;

public class MainAddNote extends AppCompatActivity {

    private NotesHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_note);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        helper = new NotesHelper(this);

        try{
            Cursor cursor = getSomeNotes();
            showNotes(cursor);

        }finally {
            helper.close();
        }
        final EditText txt = (EditText) findViewById(R.id.detailbutton);
        Button btnSave = (Button) findViewById(R.id.savebutton);

        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    addNote(txt.getText().toString());
                    Cursor cursor = getSomeNotes();
                    showNotes(cursor);

                }
                finally {
                    helper.close();
                }
            }
        });




    }
    private void addNote(String str){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUME_TIME_NOTE, System.currentTimeMillis());
        values.put(COLUME_DATE_NOTE, "null");
        values.put(COLUME_DETAIL_NOTE,"null");
        values.put(COLUME_LOCATION_NOTE,"null");
        values.put(COLUME_SUBJECT,"null");
        db.insertOrThrow(TABLE_NAME, null, values);
    }
    private static String[] COLUMES = { _ID, COLUME_TIME_NOTE, COLUME_DATE_NOTE, COLUME_DETAIL_NOTE, COLUME_LOCATION_NOTE ,COLUME_SUBJECT };
    private static String ORDER_BY = COLUME_TIME_NOTE + " DESC";

    private Cursor getSomeNotes(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,COLUMES,null,null,null,null,ORDER_BY);
        return  cursor;


    }
    private void showNotes(Cursor cursor){
        StringBuilder builder = new StringBuilder("fdgdfgdf\n\n");

        while (cursor.moveToNext()){
            long id = cursor.getLong(0);
            long timee = cursor.getLong(1);
            String datee =cursor.getString(2);
            String detaill = cursor.getString(3);
            String locationn = cursor.getString(4);
            String subjectt = cursor.getString(5);
            builder.append("ffdssfd").append(id).append(": ");
            String strDate = (String) DateFormat.format("yyyy-MM-dd hh:mm:ss",new Date(timee));
            builder.append(strDate).append("\n");



        }
        TextView tv = (TextView) findViewById(R.id.show);
        tv.setText(builder);


    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
