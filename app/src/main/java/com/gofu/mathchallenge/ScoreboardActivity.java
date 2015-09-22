package com.gofu.mathchallenge;

/**
 * Created by gofu on 2014-08-26.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ScoreboardActivity extends Activity {
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard_activity);

        dbHelper = new DBHelper(this);
        displayListView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayListView() {
        Cursor cursorEasyScoreboard = dbHelper.getEasyScoreboard();
        Cursor cursorIntermediateScoreboard = dbHelper.getIntermediateScoreboard();
        Cursor cursorHardScoreboard = dbHelper.getHardScoreboard();

        // The desired columns to be bound
        String[] from = new String[]{
                DBHelper.SCOREBOARD_COLUMN_NAME,
                DBHelper.SCOREBOARD_COLUMN_CORRECT,
                DBHelper.SCOREBOARD_COLUMN_TIME,
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[]{
                R.id.name,
                R.id.score,
                R.id.time,
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        SimpleCursorAdapter dataAdapterEasyScoreboard = new SimpleCursorAdapter(
                this, R.layout.list_layout,
                cursorEasyScoreboard,
                from,
                to,
                0);

        SimpleCursorAdapter dataAdapterIntermediateScoreboard = new SimpleCursorAdapter(
                this, R.layout.list_layout,
                cursorIntermediateScoreboard,
                from,
                to,
                0);

        SimpleCursorAdapter dataAdapterHardScoreboard = new SimpleCursorAdapter(
                this, R.layout.list_layout,
                cursorHardScoreboard,
                from,
                to,
                0);

        ListView listViewEasyLevelScoreboard = (ListView) findViewById(R.id.listViewEasyLevelScoreboard);
        ListView listViewIntermediateLevelScoreboard = (ListView) findViewById(R.id.listViewIntermediateLevelScoreboard);
        ListView listViewHardLevelScoreboard = (ListView) findViewById(R.id.listViewHardLevelScoreboard);

        // Assign adapter to ListView
        if(!dataAdapterEasyScoreboard.isEmpty()){
            listViewEasyLevelScoreboard.setAdapter(dataAdapterEasyScoreboard);
        }

        if(!dataAdapterIntermediateScoreboard.isEmpty()) {
            listViewIntermediateLevelScoreboard.setAdapter(dataAdapterIntermediateScoreboard);
        }

        if(!dataAdapterHardScoreboard.isEmpty()){
            listViewHardLevelScoreboard.setAdapter(dataAdapterHardScoreboard);
        }
    }

    public void onClickButtonMain(View view) {
        Intent intentMainActivity = new Intent(this, MainActivity.class);
        intentMainActivity.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intentMainActivity);
    }
}