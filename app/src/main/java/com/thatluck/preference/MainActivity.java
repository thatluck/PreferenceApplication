package com.thatluck.preference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {
    TextView textView=null;
//    String filepath="src/main/res/raw/res.properties";
    String filepath="/mnt/sdcard/my_config.properties";
    EditText name;
    EditText value;
    Properties properties;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.tv_content);
        name=( EditText)findViewById(R.id.name);
        value=( EditText)findViewById(R.id.value);
        initConfig();
    }

    private void initConfig() {
        readFile(null);
    }

    public void check(View v) {
        SharedPreferences ps=PreferenceManager.getDefaultSharedPreferences(this);
        if( ps.contains("value")){
            Log.i("lingling","default共享了:"+ps.getString("value","value"));
        }else{
            Log.i("lingling","default没共享:");
        }
        SharedPreferences ps2=this.getSharedPreferences("private",MODE_PRIVATE);//.getDefaultSharedPreferences(this);
        if( ps2.contains("private")){
            Log.i("lingling","private共享了:"+ps2.getString("private","private"));
        }else{
            Log.i("lingling","private没共享:");
        }
        NormalClass normalClass=new NormalClass(this);
        normalClass.checkValue();

    }
    public void clear(View v) {
        SharedPreferences ps=PreferenceManager.getDefaultSharedPreferences(this);
        ps.edit().clear().commit();

        SharedPreferences ps2=this.getSharedPreferences("private",MODE_PRIVATE);//.getDefaultSharedPreferences(this);
        ps2.edit().clear().commit();

        SharedPreferences ps3=this.getSharedPreferences("normal",MODE_PRIVATE);
        ps3.edit().clear().commit();


    }
    public void openSetting(View v) {
      Intent intent=new Intent(this,MySetting.class);
        this.startActivity(intent);

    }
    public void saveFile(View v) {
         ConfigTool.saveMyConfig(filepath,properties);
    }
    public void deleteFile(View v) {
        ConfigTool.deleteConfig(filepath);
    }
    public void readFile(View v) {
        textView.setText("");
        Log.i("lingling","loadfile");
        properties= ConfigTool.loadConfig(filepath);
        Log.i("lingling",properties.toString());
        Iterator it=properties.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry=(Map.Entry)it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            textView.append(key +":"+value+"\n");
        }

    }
    public void addprop(View v) {
        properties= ConfigTool.loadConfig(filepath);
        properties.put(name.getText().toString(),value.getText().toString());
        saveFile(v);
        readFile(v);

    }
    public void deleteprop(View v) {
        properties= ConfigTool.loadConfig(filepath);
        properties.remove(name.getText().toString());
        saveFile(v);
        readFile(v);

    }


}
