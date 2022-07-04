package com.javayuhm.fastlms.calendar.controller;

import com.javayuhm.fastlms.calendar.dto.DateData;
import com.javayuhm.fastlms.calendar.dto.ScheduleDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
public class CalendarController {

    @RequestMapping("/member/calendar")
    public String calendar(Model model, HttpServletRequest httpServletRequest, DateData dateData){

        Calendar cal = Calendar.getInstance();
        DateData calendarDate;

        if(dateData.getDate().equals("") && dateData.getMonth().equals("")){
            dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)), String.valueOf(Calendar.MONTH), String.valueOf(Calendar.DATE)
            , null, null);

        }

        Map<String, Integer> today_info = dateData.todayInfo(dateData);
        List<DateData> dateDataList = new ArrayList<DateData>();

        ScheduleDto[][] scheduleList = new ScheduleDto[32][4];


        return "calendar/calendar";
    }
}
