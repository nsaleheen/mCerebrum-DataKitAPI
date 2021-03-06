package org.md2k.datakitapi.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*
 * Copyright (c) 2015, The University of Memphis, MD2K Center
 * - Syed Monowar Hossain <monowar.hossain@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
public class DateTime {
    public static int MILLISECOND = 1;
    public static int NANOSECOND = 2;
    public final static long SECOND_IN_MILLIS = 1000L;
    public final static long MINUTE_IN_MILLIS = 60L * 1000L;
    public final static long HOUR_IN_MILLIS = 60L * 60 * 1000L;
    public final static long DAY_IN_MILLIS = 24 * 60 * 60 * 1000L;

    private static long getDateTimeMillis() {
        return System.currentTimeMillis();
    }

    private static long getDateTimeNanos() {
        return System.nanoTime();
    }

    public static long getDateTime() {
        return getDateTimeMillis();
    }
    public static long getTimeZoneOffset(){
        return TimeZone.getDefault().getRawOffset();
    }
    public static long getDayLightSavingOffset(){
        TimeZone timeZone=TimeZone.getDefault();
        return timeZone.getDSTSavings();
    }
    public static boolean isDayLightSavingNow(){
        TimeZone timeZone=TimeZone.getDefault();
        return timeZone.inDaylightTime(new Date());
    }
    public static String convertTimestampToTimeStr(long timestamp){
        timestamp=timestamp/1000;
        long hour=timestamp/(60*60);
        timestamp=timestamp-(hour*60*60);
        long minute=timestamp/(60);
        long second=timestamp-(minute*60);
        String timeStr="";
        timeStr=timeStr+String.format(Locale.getDefault(), "%02d:%02d:%02d",hour,minute,second);
        return timeStr;
    }
    public static String convertTimeStampToDateTime(long timestamp){
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS", Locale.getDefault());
            Date currenTimeZone = calendar.getTime();
            return sdf.format(currenTimeZone);
        } catch (Exception e) {
            return "";
        }
    }
    public static String convertTimeStampToDateTime(long timestamp, String format){
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
            Date currenTimeZone = calendar.getTime();
            return sdf.format(currenTimeZone);
        } catch (Exception e) {
            return "";
        }
    }

    public static long getTodayAtInMilliSecond(String time){
        String[] s = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(s[1]));
        calendar.set(Calendar.SECOND, Integer.parseInt(s[2]));
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
    public static long getTimeInMillis(String time){
        String timeTemp=time;
        long timeValue;
        if(time.startsWith("-") || time.startsWith("+"))
            timeTemp=time.substring(1);
        String[] s = timeTemp.split(":");
        timeValue = Long.parseLong(s[0])*HOUR_IN_MILLIS+Long.parseLong(s[1])*MINUTE_IN_MILLIS+Long.parseLong(s[2])*SECOND_IN_MILLIS;
        if(time.startsWith("-"))
            timeValue=-timeValue;
        return timeValue;
    }

    public static int getDayOfWeek(long timestamp) {
        Calendar calender=Calendar.getInstance();
        calender.setTimeInMillis(timestamp);
        return calender.get(Calendar.DAY_OF_WEEK);
    }
}
