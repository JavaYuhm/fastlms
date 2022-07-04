package com.javayuhm.fastlms.calendar.dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Data
public class DateData {
    String year;
    String month;
    String date;
    String value;
    String db_startDate;
    String db_endDate;

    ScheduleDto[] scheduleDtos = new ScheduleDto[4];

    public DateData(String year, String month, String date, String value, ScheduleDto[] scheduleDtos) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.value = value;
        this.scheduleDtos = scheduleDtos;
    }

    public Map<String, Integer> todayInfo(DateData dateData) {

        Map<String, Integer> todayData = new HashMap<>();

        Calendar cal = Calendar.getInstance();

        cal.set(Integer.parseInt(dateData.getYear()), Integer.parseInt(dateData.getMonth()), 1);

        int startDay = cal.getMinimum(Calendar.DATE);
        int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int start = cal.get(Calendar.DAY_OF_WEEK);

        Calendar todayCal = Calendar.getInstance();
        SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat msdf = new SimpleDateFormat("M");

        int todayYear = Integer.parseInt(ysdf.format(todayCal.getTime()));
        int todayMonth = Integer.parseInt(msdf.format(todayCal.getTime()));

        int searchYear = Integer.parseInt(dateData.getYear());
        int searchMonth = Integer.parseInt(dateData.getMonth())+1;

        int today  = -1;

        if(todayYear== searchYear && todayMonth == searchMonth){
            SimpleDateFormat dsdf = new SimpleDateFormat("dd");
            today = Integer.parseInt(dsdf.format(todayCal.getTime()));

        }
        searchMonth--;

        Map<String, Integer> beforAfterCalendar = beforAfterCalendar(searchYear, searchMonth);

        todayData.put("start", start);
        todayData.put("startDay", startDay);
        todayData.put("endDay", endDay);
        todayData.put("today", today);
        todayData.put("seacrhYear", searchYear);
        todayData.put("searchMonth", searchMonth);
        todayData.put("beforeYear", beforAfterCalendar.get("beforeYear"));
        todayData.put("beforeMonth", beforAfterCalendar.get("beforeMonth"));
        todayData.put("afterYear", beforAfterCalendar.get("afterYear"));
        todayData.put("afterMonth", beforAfterCalendar.get("afterMonth"));

        this.db_startDate = String.valueOf(searchYear)+"-"+String.valueOf(searchMonth+1)+"-"+String.valueOf(startDay);

        this.db_endDate = String.valueOf(searchYear)+"-"+String.valueOf(searchMonth+1)+"-"+String.valueOf(endDay);

        return todayData;
    }

    private Map<String, Integer> beforAfterCalendar(int searchYear, int searchMonth) {
        Map<String, Integer> beforAfterData = new HashMap<String, Integer>();
        int beforeYear = searchYear;
        int beforeMonth = searchMonth-1;
        int afterYear = searchYear;
        int afterMonth = searchMonth+1;

        if(beforeMonth<0) {
            beforeMonth=11;
            beforeYear = searchYear-1;
        }

        if(afterMonth>11){
            afterMonth = 0;
            afterYear = searchYear+1;
        }
        beforAfterData.put("beforeYear",beforeYear);
        beforAfterData.put("beforeMonth",beforeMonth);
        beforAfterData.put("afterYear",afterYear);
        beforAfterData.put("afterMonth",afterMonth);

        return beforAfterData;
    }
}
