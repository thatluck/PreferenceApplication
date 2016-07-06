package com.thatluck.preference;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Administrator on 2016/7/5.
 */
public class ConfigTool {
    public static final  String configPath="/mnt/sdcard/my_config.properties";
    public static Properties loadConfig(String file) {
        Log.i("lingling", "loadConfig");
        Properties properties = new Properties();
        try {
            File config=new File(file);
            if(config==null||!config.exists()){
                saveMyConfig(file,properties);
                loadConfig(file);
            }
            FileInputStream s = new FileInputStream(file);
            properties.load(s);
            Log.i("lingling", "properties.load(s)");
            Log.i("lingling",properties.toString());
        }catch (IOException e) {
            Log.i("lingling", e.getMessage());
            e.printStackTrace();
        }

        return properties;
    }

    public static void deleteConfig(String file) {

        Properties properties = new Properties();

            File config=new File(file);
            if(config!=null){
                config.delete();
            }
    }

    public static void saveMyConfig( String file, Properties properties) {
        Log.i("lingling", "saveMyConfig");
        try {
            FileOutputStream s = new FileOutputStream(file, false);
            properties.store(s, "save");
            Log.i("lingling", "properties.store");
        } catch (IOException  e){
            Log.i("lingling", e.getMessage());
            e.printStackTrace();
        }
    }

    public  static void saveConfig(String key, Object value) {
        Properties properties=ConfigTool.loadConfig(ConfigTool.configPath);
        properties.put(key,value);
        ConfigTool.saveMyConfig(ConfigTool.configPath,properties);
    }
}
