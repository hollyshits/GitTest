package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static String getDate(int i){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        //System.out.println(sdf.format(date));
        long addTime = i;
        addTime *= 24;
        addTime *= 3600;
        addTime *= 1000;
        Date date1=new Date(date.getTime()+addTime);
        return sdf.format(date1);
    }
    //预约界面，获取几天后是星期几
    public static String getDays(int i){
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;
        return weekDays[(w+i)%7];

    }


    public static Date convertString2Date(String format, String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertDate2String(String format, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static List<String> getIntervalTimeList(String start, String end, int total) {
        Date startDate = DateUtils.convertString2Date("HH:mm", start);
        Date endDate = DateUtils.convertString2Date("HH:mm", end);
        long diff=endDate.getTime()-startDate.getTime();
        int interval=(int)diff/(total*60000);
        List<String> list = new ArrayList<>();
        int i=1;
        /*while (startDate.getTime() <= endDate.getTime())*/
        while (i <= total) {
            i++;
            //list.add(DateUtils.convertDate2String("HH:mm:ss", startDate));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.MINUTE, interval);
            list.add(DateUtils.convertDate2String("HH:mm", startDate)+" "+DateUtils.convertDate2String("HH:mm", calendar.getTime()));
            if (calendar.getTime().getTime() > endDate.getTime()) {
                if (!startDate.equals(endDate)) {
                    list.add(DateUtils.convertDate2String("HH:mm", endDate));
                }
                startDate = calendar.getTime();
            } else {
                startDate = calendar.getTime();
            }

        }
        return list;
    }

    public static void main(String[] args) {
        List<String> list = DateUtils.getIntervalTimeList("12:00", "13:00", 11);
        for (String s : list) {
            System.out.println(s);
        }
    }
}