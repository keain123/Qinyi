package com.ch.chframe.utils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
    private static ThreadLocal<Map<String,SimpleDateFormat>> dateFormatHolder = new ThreadLocal<Map<String,SimpleDateFormat>>(){
        @Override
        protected Map<String, SimpleDateFormat> initialValue() {
            Map<String, SimpleDateFormat> map = new HashMap<String, SimpleDateFormat>();
            map.put("yyyy-MM-dd", new SimpleDateFormat("yyyy-MM-dd"));
            map.put("yyyy-MM-dd HH:mm:ss", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            return map;
        }
    };
    public static SimpleDateFormat getDateFormat(String pattern){
        Map<String, SimpleDateFormat> map = dateFormatHolder.get();
        SimpleDateFormat sdf = map.get(pattern);
        if(sdf == null){
            sdf = new SimpleDateFormat(pattern);
            map.put(pattern, sdf);
        }
        return sdf;
    }
    public static SimpleDateFormat getDateFormat10(){
        return getDateFormat("yyyy-MM-dd");
    }
    public static SimpleDateFormat getDateFormat19(){
        return getDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
