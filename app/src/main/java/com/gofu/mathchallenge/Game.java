package com.gofu.mathchallenge;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by gofu on 2014-08-23.
 */
public class Game implements Serializable{
    // Total number of questions to be asked for various levels
    final int EASY = 5;
    final int INTERMEDIATE = 20;
    final int HARD = 50;
    String strLevel;
    int max_question;

    Game(String level){
        this.strLevel = level;
        Log.d("AUTOMATION", this.strLevel);
    }

    public int getNumQuestion(){
        return this.max_question;
    }

}
