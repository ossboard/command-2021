package com.github.spring.util;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");

    public static List<Date> betweenDate(Date date1, Date date2) {
        Calendar from = Calendar.getInstance();
        from.setTime(date1);
        Calendar to = Calendar.getInstance();
        to.setTime(date2);
        List<Date> lists = new ArrayList<Date>();
        if (from.before(to)) {
            while (from.before(to) || from.equals(to)) {
                lists.add(from.getTime());
                from.add(Calendar.MONTH, 1);
            }
        } else if (from.equals(to)) {
            lists.add(from.getTime());
        }
        return lists;
    }

    public static List<Date> betweenDate(Date date1, Date date2, int field, int amount) {
        Calendar from = Calendar.getInstance();
        from.setTime(date1);
        Calendar to = Calendar.getInstance();
        to.setTime(date2);
        List<Date> lists = new ArrayList<Date>();
        if (from.before(to)) {
            while (from.before(to) || from.equals(to)) {
                lists.add(from.getTime());
                from.add(field, amount);
            }
            //lists.add(from.getTime());
        } else if (from.equals(to)) {
            lists.add(from.getTime());
        }
        return lists;
    }

    public static Date addDate(Date date, int calendar, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calendar, day);
        return cal.getTime();
    }

    public static int absoluteFiveMinute() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int minutes = cal.get(Calendar.MINUTE);
        minutes = (minutes / 5) * 5;
        return 0;
    }

    public static Date absoluteFiveDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int minutes = cal.get(Calendar.MINUTE);
        minutes = (minutes / 5) * 5;
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static String addDateYMD(Date date, int calendar, int day) {
        SimpleDateFormat sdfYmd = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (day != 0) {
            cal.add(calendar, day);
        }
        return sdfYmd.format(cal.getTime());
    }

    public static String getToday(int choiceDay) {
        DecimalFormat df = new DecimalFormat("00");
        Calendar currentCal = Calendar.getInstance();

        currentCal.add(currentCal.DATE, choiceDay);

        String year = Integer.toString(currentCal.get(Calendar.YEAR));
        String month = df.format(currentCal.get(Calendar.MONTH) + 1);
        String day = df.format(currentCal.get(Calendar.DAY_OF_MONTH));

        return year + "-" + month + "-" + day;
    }

    public static Date getDate(String ymd) {
        SimpleDateFormat sdfYmd = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdfYmd.parse(ymd);
        } catch (ParseException e) {
            logger.info(e.getMessage(), e);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
        }
        return null;
    }

    public static Date getDateTime(int year, int month, int day, int hour, int min, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hour, min, sec);

        Date date = cal.getTime();

        return date;
    }

    public static String getDateDay(String date, String dateType) throws Exception {
        String day = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat(dateType);
        Date nDate = dateFormat.parse(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(nDate);

        int dayNum = cal.get(Calendar.DAY_OF_WEEK);

        switch (dayNum) {
            case 1:
                day = "일";
                break;
            case 2:
                day = "월";
                break;
            case 3:
                day = "화";
                break;
            case 4:
                day = "수";
                break;
            case 5:
                day = "목";
                break;
            case 6:
                day = "금";
                break;
            case 7:
                day = "토";
                break;

        }

        return day;
    }

    public static List<String> getWeek(String date, String dateType) throws Exception {
        List<String> list = new ArrayList<String>();

        SimpleDateFormat dateFormat = new SimpleDateFormat(dateType);
        Date nDate = dateFormat.parse(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(nDate);
        for (int i = 2; i < 9; i++) {
            if (i == 8) {
                //일요일 -> 여기서는 일요일부터 시작이라 다음주일요일을 구해야해서
                cal.add(Calendar.DATE, 1);
            } else {
                cal.set(Calendar.DAY_OF_WEEK, i);
            }
            list.add(dateFormat.format(cal.getTime()));
        }

        return list;
    }

    public static long getTimeDiff(String firstTime, String secondTime, String dateType) throws Exception {
        //시간 설정
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateType);
        Date firstTimeDate = dateFormat.parse(firstTime);
        Date secondTimeDate = dateFormat.parse(secondTime);
        long startTime = firstTimeDate.getTime();
        long endTime = secondTimeDate.getTime();

        long mills = endTime - startTime;

        //분으로 변환
        return mills / 60000;
    }

    public static String getDateFormatChange(String orgDate, String orgDateType, String changeDateType) {
        SimpleDateFormat orgFormat = new SimpleDateFormat(orgDateType);
        SimpleDateFormat newFormat = new SimpleDateFormat(changeDateType);
        try {
            Date orgDt = orgFormat.parse(orgDate);
            String newDate = newFormat.format(orgDt);
            return newDate;
        } catch (ParseException e) {
            logger.info(e.getMessage(), e);
            return null;
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return null;
        }
    }

    public static String getDateFormat(String yyyyMMdd, String delimiter)
            throws Exception {

        if (yyyyMMdd.length() != 8) {
            throw new Exception("Length of date must be 8");
        }

        return yyyyMMdd.substring(0, 4) + delimiter + yyyyMMdd.substring(4, 6)
                + delimiter + yyyyMMdd.substring(6);
    }

    public static String getHourMinute(long second) {
        long myHour = second / 3600;
        long myMinute = second % 3600 / 60;

        StringBuffer time = new StringBuffer();
        if (myHour != 0) {
            time.append(StringUtils.leftPad(String.valueOf(myHour), 2) + ":");
        } else {
            time.append("00:");
        }

        if (myMinute != 0) {
            time.append(StringUtils.leftPad(String.valueOf(myMinute), 2));
        } else {
            time.append("00");
        }

        return time.toString();
    }

    public static void defaultDatePeriod(Map<String, Object> paramMap) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, -6);
        String createtime_from = MapUtils.getString(paramMap,"createtime_from",  ymd.format(calendar.getTime()) );
        String createtime_to = MapUtils.getString(paramMap,"createtime_to", ymd.format(Calendar.getInstance().getTime()));
        paramMap.put("createtime_from", createtime_from);
        paramMap.put("createtime_to", createtime_to);
    }

    public static void defaultDatePeriodByWeek(Map<String, String> paramMap, int week_from, int week_to) {
        Calendar calendar_from = Calendar.getInstance();
        calendar_from.add(Calendar.DATE , week_from);
        paramMap.put("startdate", ymd.format(calendar_from.getTime()));

        Calendar calendar_to = Calendar.getInstance();
        calendar_to.add(Calendar.DATE , week_to);
        paramMap.put("enddate", ymd.format(calendar_to.getTime()));
    }
}