package com.gofu.mathchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView timerTextView;
        setContentView(R.layout.main_activity);

//        Swarm.setActive(this);
//        Swarm.init(this, 12855, "685bdc00f7b180d380dfe08814e3bacc");
        Button buttonChooseEasyLevel = (Button) findViewById(R.id.buttonChooseEasyLevel);
        Button buttonChooseIntermediateLevel = (Button) findViewById(R.id.buttonChooseIntermediateLevel);
        Button buttonChooseHardLevel = (Button) findViewById(R.id.buttonChooseHardLevel);

        try {
            buttonChooseEasyLevel.setOnClickListener(this);
            buttonChooseIntermediateLevel.setOnClickListener(this);
            buttonChooseHardLevel.setOnClickListener(this);
        } catch (Exception e) {
        }
    }

    public void onResume() {
        super.onResume();
//        Swarm.setActive(this);
    }

    public void onPause() {
        super.onPause();
//        Swarm.setInactive(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
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

    public void onClick(View arg0) {
        Intent intentGameActivity = new Intent(this, GameActivity.class);
        intentGameActivity.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        switch (arg0.getId()) {
            case R.id.buttonChooseEasyLevel:
                Game game1 = new Game("easy");
                intentGameActivity.putExtra("game", game1);
                intentGameActivity.putExtra("easy", "level");
                break;
            case R.id.buttonChooseIntermediateLevel:
                game1 = new Game("intermediate");
                intentGameActivity.putExtra("game", game1);
                intentGameActivity.putExtra("intermediate", "level");
                break;
            case R.id.buttonChooseHardLevel:
                game1 = new Game("hard");
                intentGameActivity.putExtra("game", game1);
                break;
        }
        startActivity(intentGameActivity);
    }

    public void onClickButtonScoreboard(View view) {
        Intent intentScoreboardActivity = new Intent(this, ScoreboardActivity.class);
        intentScoreboardActivity.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intentScoreboardActivity);
    }

    public void onClickButtonInstruction(View view) {
        Intent intentInstructionActivity = new Intent(this, HelpActivity.class);
        intentInstructionActivity.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intentInstructionActivity);
    }
}