package com.thatluck.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Administrator on 2016/6/28.
 */
public class NormalClass {
    private Context context;

    public NormalClass(Context context) {
        this.context = context;
    }
    public void setValue(){
        SharedPreferences ps=context.getSharedPreferences("normal",context.MODE_PRIVATE);
        SharedPreferences.Editor editor2=ps.edit();
        editor2.putString("normal","over");
        editor2.commit();
    }

    public void clearValue(){

    }
    public void checkValue(){
        SharedPreferences ps2=context.getSharedPreferences("normal",context.MODE_PRIVATE);//.getDefaultSharedPreferences(this);
        if( ps2.contains("normal")){
            Log.i("lingling","normal共享了:"+ps2.getString("normal","normal"));
        }else{
            Log.i("lingling","normal没共享:");
        }
    }
}
