/*
 * Copyright (c)2016 thatluck
 * 版权所有，违者必究
 */

package com.thatluck.preference;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

public class MySetting extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysetting);


    }

    public void openMain(View v) {
        Intent intent=new Intent(this,MainActivity.class);
        this.startActivity(intent);

    }

    public void setValue(View v) {
        SharedPreferences ps=PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=ps.edit();
        editor.putString("value","ok");
        editor.commit();


        SharedPreferences ps_private=this.getSharedPreferences("private",MODE_PRIVATE);
        SharedPreferences.Editor editor2=ps_private.edit();
        editor2.putString("private","me");
        editor2.commit();

        NormalClass normalClass=new NormalClass(this);
        normalClass.setValue();

    }
    public void check(View v) {
        SharedPreferences ps=PreferenceManager.getDefaultSharedPreferences(this);
        if( ps.contains("value")){
            Log.i("lingling","default没被干掉："+ps.getString("value","value"));
        }else{
            Log.i("lingling","default被干掉了！");
        }

        SharedPreferences ps2=this.getSharedPreferences("private",MODE_PRIVATE);

        if( ps2.contains("private")){
            Log.i("lingling","private没被干掉："+ps2.getString("private","private"));
        }else{
            Log.i("lingling","private被干掉了！");
        }

        SharedPreferences ps3=this.getSharedPreferences("normal",MODE_PRIVATE);

        if( ps3.contains("normal")){
            Log.i("lingling","normal没被干掉："+ps3.getString("normal","normal"));
        }else{
            Log.i("lingling","normal被干掉了！");
        }

    }

}
