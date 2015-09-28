package com.example.user.mommemories;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import static com.example.user.mommemories.Constants.*;


public class MainNote extends ListActivity {
   // public class MainNote2 extends ListActivity{
    private int[] VIEWS = { R.id.rowid,R.id.time_note,R.id.date_note,R.id.detail_note,R.id.location_note,R.id.subject_note};
    private NotesHelper helper;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_note);

//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);


            helper = new NotesHelper(this);
            try{
                Cursor cursorr = getSomeNotes();
                showNotes(cursorr);

            }finally {
                helper.close();
            }
    }
        private String ORDER_BY = COLUME_TIME_NOTE + " DESC";
//
        private Cursor getSomeNotes(){
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.query(TABLE_NAME, COLUMES, null, null, null, null, ORDER_BY);
            return  cursor;
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_note, menu);
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

    public void onClink_tonewnote(View view) {
        Intent intent = new Intent(this, MainAddNote.class);
        startActivity(intent);
    }
        private String[] COLUMES = { _ID, COLUME_TIME_NOTE, COLUME_DATE_NOTE, COLUME_DETAIL_NOTE, COLUME_LOCATION_NOTE ,COLUME_SUBJECT };
//
        private void showNotes(Cursor cursor){
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.item,cursor,COLUMES,VIEWS);
        setListAdapter(adapter);

    }
//}
}
