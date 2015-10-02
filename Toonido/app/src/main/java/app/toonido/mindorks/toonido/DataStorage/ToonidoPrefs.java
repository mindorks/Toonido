package app.toonido.mindorks.toonido.DataStorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;


/**
 * Created by janisharali on 02/10/15.
 */
public class ToonidoPrefs {
    private SharedPreferences sharedPreferences;
    private Editor editor;
    private final String TAG="ToonidoPrefs";
    public final String toonidoPREFERENCES = "ToonidoPrefs" ;

    public ToonidoPrefs(Context context){
        sharedPreferences = context.getSharedPreferences(toonidoPREFERENCES, Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();
    }

    public void put(String key,String value){
        editor.putString(key, value);
        editor.apply();
    }

    public void put(String key,int value){
        editor.putInt(key, value);
        editor.apply();
    }

    public void put(String key,float value){
        editor.putFloat(key, value);
        editor.apply();
    }
    public void put(String key,boolean value){
        editor.putBoolean(key, value);
        editor.apply();
    }

    public String get(String key,String defaultValue){
        try {
            return sharedPreferences.getString(key, defaultValue);
        }catch (ClassCastException e){
            Log.d(TAG,"ali ToonidoPrefs get exception "+e);
            return null;
        }
    }

    public Integer get(String key,int defaultValue){
        try {
            return sharedPreferences.getInt(key, defaultValue);
        }catch (ClassCastException e){
            Log.d(TAG,"ali ToonidoPrefs get exception "+e);
            return null;
        }
    }

    public Float get(String key,float defaultValue){
        try {
            return sharedPreferences.getFloat(key, defaultValue);
        }catch (ClassCastException e){
            Log.d(TAG,"ali ToonidoPrefs get exception "+e);
            return null;
        }
    }
    public Boolean get(String key,boolean defaultValue){
        try {
            return sharedPreferences.getBoolean(key, defaultValue);
        }catch (ClassCastException e){
            Log.d(TAG,"ali ToonidoPrefs get exception "+e);
            return null;
        }
    }

    public void deleteSavedData(String key){
        editor.remove(key);
        editor.apply();
    }

}
