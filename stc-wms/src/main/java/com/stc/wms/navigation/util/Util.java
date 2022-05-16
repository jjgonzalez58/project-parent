package com.stc.wms.navigation.util;

import java.text.DateFormatSymbols;
import java.util.Random;

/**
 * User: job
 * Date: 27/04/22
 * Time: 18:59
 *
 * @author job
 */
public class Util {
    public static Random random = new Random();
    public static String[] MONTHS = new DateFormatSymbols().getMonths();

    public static int nextInt(int low, int high){
        return random.nextInt(high-low) + low;
    }

//    public static void setupColor(Charts chart){
//        int n = chart.getSeriesSize();
//        BsColor[] colors = BsColor.values();
//        for (int i = 0 ; i < n ; i++){
//            chart.getSeries(i).setColor(colors[i % colors.length].getHexCode());
//        }
//    }
}
