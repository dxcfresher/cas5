package com.dxc.integral.identity.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Parse time string in Generalized Time format into Java Date object.
 * </p>
 * 
 * <p>
 * Generalized time is a form at may be used to represent time stamps, along
 * with time zone information. A generalized time value contains the following
 * components:
 * </p>
 * 
 * <ul>
 * <li>Four digits to signify the year.</li>
 * 
 * <li>Two digits to signify the month (01 for January, 02 for February, ..., 12
 * for December).</li>
 * 
 * <li>Two digits to signify the day of the month (01 through 28/29/30/31
 * depending on the month and whether it's a leap year).</li>
 * 
 * <li>Two digits to signify the hour of the day (00 for midnight through 23 for
 * 11 pm).</li>
 * 
 * <li>An optional two digits that specify the minute of the hour (between 00
 * and 59).</li>
 * 
 * <li>An optional two digits that specify the second of the minute (between 00
 * and 59, or 60 for leap seconds). This may only be included if the time stamp
 * value also contains the minute of the hour.</li>
 * 
 * <li>An optional period followed by one or more digits that specify the
 * fraction of a second. This may only be included if the time stamp value
 * contains minute and second information.</li>
 * 
 * <li>A time zone indicator. This may be either the capital letter "Z" to
 * indicate that the value is in the UTC time zone, or a plus or minus sign
 * followed by two or four digits that specify the offset from UTC time zone.</li>
 * 
 * <li>An example of a time stamp in a generalized time format is
 * "20070508200557Z", which specifies a time (in the UTC time zone) of 8:05:57
 * pm on May 28, 2007. An equivalent value in US central daylight savings time
 * (a five hour offset from UTC) would be 20070508150557-0500".</li>
 * </ul>
 * 
 * See {@link https://www.opends.org/wiki/page/DefinitionGeneralizedTime}
 */
public class GeneralizedTimeParser {

    private static final String TIME_REGEX = "([\\d]{4})([\\d]{2})([\\d]{2})([\\d]{2})([\\d]{2})([\\d]{2})Z";

    public static Date parse(String timeStr) {
        if (timeStr == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        Pattern p = Pattern.compile(TIME_REGEX);
        Matcher m = p.matcher(timeStr);

        if (m.matches()) {
            int year = Integer.parseInt(m.group(1));
            int month = Integer.parseInt(m.group(2));
            int day = Integer.parseInt(m.group(3));
            int hour = Integer.parseInt(m.group(4));
            int minute = Integer.parseInt(m.group(5));
            int second = Integer.parseInt(m.group(6));

            Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("UTC"));
            c.set(year, month - 1, day, hour, minute, second);

            return c.getTime();
        } else {
            throw new IllegalArgumentException(String.format(
                    "Invalid timestamp \"%s\", expected timestamp in format yyyyMMddHHmmssZ",
                    timeStr));
        }
    }

}
