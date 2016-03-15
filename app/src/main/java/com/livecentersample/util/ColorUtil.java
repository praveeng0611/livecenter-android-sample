package com.livecentersample.util;

import android.graphics.Color;

/**
 * Created by King on 14/03/16.
 */
public class ColorUtil {

    public static int getContrastColor(String colorString) {
        int color = Color.parseColor(colorString);
        double y = (299 * Color.red(color) + 587 * Color.green(color) + 114 * Color.blue(color)) / 1000;
        if(y>=128){
            return Color.BLACK;
        }else {
            return  Color.WHITE;
        }
    }
}
