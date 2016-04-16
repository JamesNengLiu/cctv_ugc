package com.cctv.ugc.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangyang on 15-10-15.
 */
public class DateUtils {

    private static final SimpleDateFormat sSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final long MINUTE_IN_MILLS = 60 * 1000;
    private static final long HOUR_IN_MILLS = 60 * MINUTE_IN_MILLS;
    private static final long DAY_IN_MILLS = 24 * HOUR_IN_MILLS;

    public static String parseDate(long timeInSeconds){
        long timeInMills = timeInSeconds * 1000;
        Date date = new Date(timeInMills);
        return sSimpleDateFormat.format(date);
    }

}
