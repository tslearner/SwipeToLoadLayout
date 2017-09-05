package com.example.tianshuai.swipetoloadlayout;

/**
 * Created by tianshuai on 2017/9/5.
 */
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;



import java.util.HashMap;
import java.util.Set;
public class SharePreferenceUtils {
    private static final String DEFAULT_FILE_NAME="sp_yuliao";

    /**
     * 保存数据到sp的方法，根据value类型调用不同的保存方法,默认用的sp的文件名字为DEFAULT_FILE_NAME
     * @param context
     * @param key
     * @param value
     */
    public static boolean setParam(Context context , String key, Object value){
        HashMap<String ,Object> map = new HashMap<>();
        map.put(key ,value);
        return setParam(DEFAULT_FILE_NAME,context,map);
    }

    public  static boolean setParam( Context context ,HashMap<String ,Object> values){
        return setParam(DEFAULT_FILE_NAME,context,values);
    }
    public  static boolean setParam( String fileName,Context context ,String key, Object value){
        HashMap<String ,Object> map = new HashMap<>();
        map.put(key ,value);
        return setParam(fileName,context,map);
    }

    public static boolean setParam(String fileName, Context context , HashMap<String ,Object> values){
        if(context == null || fileName == null){
            return false;
        }
        SharedPreferences sp = context.getSharedPreferences(fileName ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Set<String> keys = values.keySet();
        for(String tempKey :keys){
            Object value = values.get(tempKey);
            String type = value.getClass().getSimpleName();

            if("String".equals(type)){
                editor.putString(tempKey, (String)value);
            }
            else if("Integer".equals(type)){
                editor.putInt(tempKey, (Integer)value);
            }
            else if("Boolean".equals(type)){
                editor.putBoolean(tempKey, (Boolean)value);
            }
            else if("Float".equals(type)){
                editor.putFloat(tempKey, (Float)value);
            }
            else if("Long".equals(type)){
                editor.putLong(tempKey, (Long)value);
            }
        }
        return editor.commit();
    }

    /**
     * 根据defaultValue的类型返回相应的类型值,默认用的sp的文件名字为DEFAULT_FILE_NAME
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object getParam(Context context , String key, Object defaultValue){
        return getParam(DEFAULT_FILE_NAME,context,key,defaultValue);
    }

    public static Object getParam(String fileName,Context context , String key, Object defaultValue){
        String type = defaultValue.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);

        if("String".equals(type)){
            return sp.getString(key, (String)defaultValue);
        }
        else if("Integer".equals(type)){
            return sp.getInt(key, (Integer)defaultValue);
        }
        else if("Boolean".equals(type)){
            return sp.getBoolean(key, (Boolean)defaultValue);
        }
        else if("Float".equals(type)){
            return sp.getFloat(key, (Float)defaultValue);
        }
        else if("Long".equals(type)){
            return sp.getLong(key, (Long)defaultValue);
        }

        return null;
    }




}
