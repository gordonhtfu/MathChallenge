package com.gofu.mathchallenge;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

//import com.swarmconnect.Swarm;
//import com.swarmconnect.SwarmActivity;
//import com.swarmconnect.SwarmLeaderboard;

public class GameActivity extends Activity implements View.OnClickListener{

    private DBHelper mydb;
    TextView name;
    TextView score;
    TextView time;

    // Putting the operations into constants
    final int ADD = 0;
    final int SUBTRACT = 1;
    final int MULTIPLY = 2;
    final int DIVIDE = 3;
    final int NUM_QUESTION = 10;

    // Number of question asked and correct answers so far
    int total = 0;
    int correct = 0;

    private TextView timerValue;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    Game game1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Swarm.setActive(this);

        score = (TextView) findViewById(R.id.textViewScore);
        time = (TextView) findViewById(R.id.textViewTimer);
        mydb = new DBHelper(this);

        setContentView(R.layout.game_activity);

        game1 = (Game) getIntent().getSerializableExtra("game");

        setQuestion();
        startTimer();

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button button0 = (Button) findViewById(R.id.button0);
        Button buttonClear = (Button) findViewById(R.id.buttonClear);
        Button buttonCheckAnswer = (Button) findViewById(R.id.buttonCheckAnswer);
        Button buttonSubmitScore = (Button) findViewById(R.id.buttonSubmitScore);

        try {
            button1.setOnClickListener(this);
            button2.setOnClickListener(this);
            button3.setOnClickListener(this);
            button4.setOnClickListener(this);
            button5.setOnClickListener(this);
            button6.setOnClickListener(this);
            button7.setOnClickListener(this);
            button8.setOnClickListener(this);
            button9.setOnClickListener(this);
            button0.setOnClickListener(this);
            buttonClear.setOnClickListener(this);
            buttonCheckAnswer.setOnClickListener(this);
            buttonSubmitScore.setOnClickListener(this);
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

    public void startTimer() {
        // Setting timer to default;
        TextView textViewScore = (TextView) findViewById(R.id.textViewScore);
        textViewScore.setText(" / ");

        timerValue = (TextView) findViewById(R.id.textViewTimer);
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
    }

    public void stopTimer() {
        timeSwapBuff += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);
    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText(mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }
    };

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

    public void onClickButtonStartOver(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void setQuestion() {
        int random = new Random().nextInt(4);
        int random1=0;
        int random2=0;

        Log.d("AUTOMATION", game1.strLevel);

        TextView textViewToChange3 = (TextView) findViewById(R.id.textViewInstruction);
        if(game1.strLevel.equals("easy")) {
            random1 = new Random().nextInt(10) + 1;
            random2 = new Random().nextInt(10) + 1;
        }
        else if(game1.strLevel.equals("intermediate")){
            random1 = new Random().nextInt(100) + 1;
            random2 = new Random().nextInt(10) + 1;
        }

        else if(game1.strLevel.equals("hard")){
            random1 = new Random().nextInt(100) + 1;
            random2 = new Random().nextInt(100) + 1;
        }

        TextView textViewToChange1 = (TextView) findViewById(R.id.textViewFirstNumber);
        TextView textViewToChange2 = (TextView) findViewById(R.id.textViewSecondNumber);

        if (random == ADD) {
            textViewToChange3.setText(getString(R.string.add));
            textViewToChange1.setText(String.valueOf(random1));
            textViewToChange2.setText(String.valueOf(random2));
        } else if (random == SUBTRACT) {
            textViewToChange3.setText(getString(R.string.subtract));
            if(random1 < random2){
                int temp = random1;
                random1 = random2;
                random2 = temp;
            }
            textViewToChange1.setText(String.valueOf(random1));
            textViewToChange2.setText(String.valueOf(random2));
        } else if (random == MULTIPLY) {
            textViewToChange3.setText(getString(R.string.multiply));
            textViewToChange1.setText(String.valueOf(random1));
            textViewToChange2.setText(String.valueOf(random2));
        } else if (random == DIVIDE) {
            textViewToChange3.setText(getString(R.string.divide));
            textViewToChange1.setText(String.valueOf(random1 * random2));
            textViewToChange2.setText(String.valueOf(random2));
        } else {
            Log.d("test", "a bug");
        }

        TextView textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
        textViewAnswer.setText("");
    }

    //check whether the answer is valid .. if it is empty or not numeric, it returns false; otherwise returns true
    public boolean checkAnswers() {
        TextView textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
        String strResult = textViewAnswer.getText().toString();

        if (strResult.isEmpty())
            return false;
        else if (!isNumeric(strResult)) {
            return false;
        } else {
            TextView textViewFirstNumber = (TextView) findViewById(R.id.textViewFirstNumber);
            String strFirstNumber = textViewFirstNumber.getText().toString();
            int intFirstNumber = Integer.parseInt(strFirstNumber);

            TextView textViewSecondNumber = (TextView) findViewById(R.id.textViewSecondNumber);
            String strSecondNumber = textViewSecondNumber.getText().toString();
            int intSecondNumber = Integer.parseInt(strSecondNumber);

            TextView textViewToChange3 = (TextView) findViewById(R.id.textViewInstruction);
            String strInstruction = textViewToChange3.getText().toString();
            int answer = 0;
            String strOperation = "";
            if (strInstruction == getString(R.string.add)) {
                answer = intFirstNumber + intSecondNumber;
                strOperation = " + ";
            } else if (strInstruction == getString(R.string.subtract)) {
                answer = intFirstNumber - intSecondNumber;
                strOperation = " - ";
            } else if (strInstruction == getString(R.string.multiply)) {
                answer = intFirstNumber * intSecondNumber;
                strOperation = " x ";
            } else if (strInstruction == getString(R.string.divide)) {
                answer = intFirstNumber / intSecondNumber;
                strOperation = " / ";
            } else {
                Log.d("test", "A bug");
            }
            total = total + 1;

            TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
            if (answer == Integer.parseInt(strResult)) {
                textViewResult.setText(getString(R.string.correct));
                textViewResult.setTextColor(Color.GREEN);
                correct = correct + 1;
            } else {
                textViewResult.setText(getString(R.string.incorrect) + "! " + strFirstNumber + " " + strOperation + " " + strSecondNumber + " " + getString(R.string.should_be) + " " + answer);
                textViewResult.setTextColor(Color.RED);
            }
            TextView textViewCorrect = (TextView) findViewById(R.id.textViewCorrect);
            String strCorrect = Integer.toString(correct);
            textViewCorrect.setText(strCorrect);
            Log.d("AUTOMATION", strCorrect);

            TextView textViewTotal = (TextView) findViewById(R.id.textViewTotal);
            String strTotal = Integer.toString(total);
            textViewTotal.setText(strTotal);
            Log.d("AUTOMATION", strTotal);

            return true;
        }
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void onClick(View arg0) {
        TextView textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
        String strResult = textViewAnswer.getText().toString();

        switch (arg0.getId()) {
            case R.id.button1:
                strResult += "1";
                break;
            case R.id.button2:
                strResult += "2";
                break;
            case R.id.button3:
                strResult += "3";
                break;
            case R.id.button4:
                strResult += "4";
                break;
            case R.id.button5:
                strResult += "5";
                break;
            case R.id.button6:
                strResult += "6";
                break;
            case R.id.button7:
                strResult += "7";
                break;
            case R.id.button8:
                strResult += "8";
                break;
            case R.id.button9:
                strResult += "9";
                break;
            case R.id.button0:
                strResult += "0";
                break;
            case R.id.buttonClear:
                strResult = "";
                break;
            case R.id.buttonSubmitScore:
                EditText editTextName = (EditText) findViewById(R.id.editTextName);
                String strName = editTextName.getText().toString();

                if (strName != null && !strName.isEmpty()){
                    submitScore(strName);
                }
                break;
            case R.id.buttonCheckAnswer:
                strResult = "";
                if(checkAnswers()) {
                    if (total == NUM_QUESTION) {
                        stopTimer();
                        findViewById(R.id.buttonCheckAnswer).setVisibility(View.GONE);
                        findViewById(R.id.editTextName).setVisibility(View.VISIBLE);
                        findViewById(R.id.buttonSubmitScore).setVisibility(View.VISIBLE);
                    } else {
                        setQuestion();
                    }
                }
                break;
        }
        if(arg0.getId() != R.id.buttonSubmitScore){
            textViewAnswer.setText(strResult);
        }
    }

    public void submitScore(String strName)
    {
        TextView textViewTimer = (TextView) findViewById(R.id.textViewTimer);
        String strTime = textViewTimer.getText().toString();

        TextView textViewCorrect = (TextView) findViewById(R.id.textViewCorrect);
        String correct2 = textViewCorrect.getText().toString();
        int correct = Integer.parseInt(correct2);

        TextView textViewTotal = (TextView) findViewById(R.id.textViewTotal);
        int total = Integer.parseInt(textViewTotal.getText().toString());

        mydb.insertScoreboard(strName, correct, total, strTime, game1.strLevel);
    //        Toast.makeText(getApplicationContext(), "Done"+ strName + " " + correct + " / " + total + " " + strTime, Toast.LENGTH_SHORT).show();
    //        Toast.makeText(getApplicationContext(), "Cannot Save", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,ScoreboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickButtonMain(View view) {
        Intent intentMainActivity = new Intent(this, MainActivity.class);
        intentMainActivity.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intentMainActivity);
    }
}