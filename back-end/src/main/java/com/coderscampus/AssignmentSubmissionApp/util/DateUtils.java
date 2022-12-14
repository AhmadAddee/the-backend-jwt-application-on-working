package com.coderscampus.AssignmentSubmissionApp.util;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.Date;

/**
 * Calculate the exact time ago and returns a string value telling how long time ago from now something happened.
 * */
public class DateUtils {

    public static String calculateTimeAgo(Date pastTime) {
        Period period = new Period(new DateTime(pastTime.getTime()), new DateTime(System.currentTimeMillis()));

        return period.getYears() != 0? onlyYears(period) :
                period.getMonths() > 0? onlyMonths(period) :
                        period.toStandardDays().getDays() > 0? onlyDays(period) :
                                period.toStandardHours().getHours() > 0? onlyHours(period) :
                                        period.toStandardMinutes().getMinutes() > 0? onlyMinutes(period) : onlySeconds(period);
    }

    private static String onlyYears(Period period) {
        return new PeriodFormatterBuilder().appendYears()
                .appendSuffix(" year ago", " years ago").toFormatter().print(period);
    }

    private static String onlyMonths(Period period) {
        return new PeriodFormatterBuilder().appendMonths()
                .appendSuffix(" month ago", " months ago").toFormatter().print(period);
    }

    private static String onlyDays(Period period) {
        return period.getWeeks() != 0?
                period.getWeeks() * 7 + period.getDays() + " days ago" :
                new PeriodFormatterBuilder().appendDays()
                .appendSuffix(" day ago", " days ago").toFormatter().print(period);
    }

    private static String onlyHours(Period period) {
        return new PeriodFormatterBuilder().appendHours()
                .appendSuffix(" hour ago", " hours ago").toFormatter().print(period);
    }

    private static String onlyMinutes(Period period) {
        return new PeriodFormatterBuilder().appendMinutes()
                .appendSuffix(" minute ago", " minutes ago").toFormatter().print(period);
    }

    private static String onlySeconds(Period period) {
        return new PeriodFormatterBuilder().appendSeconds()
                .appendSuffix(" second ago", " seconds ago").toFormatter().print(period);
    }
}