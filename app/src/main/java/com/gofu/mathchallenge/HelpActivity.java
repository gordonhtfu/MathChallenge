package com.gofu.mathchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HelpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.help_activity);
    }

    public void onClickButtonMain(View view) {
        Intent intentMainActivity = new Intent(this, MainActivity.class);
        intentMainActivity.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intentMainActivity);
    }
}
