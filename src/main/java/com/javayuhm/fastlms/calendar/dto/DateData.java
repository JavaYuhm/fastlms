package com.javayuhm.fastlms.calendar.dto;

import lombok.Data;

@Data
public class DateData {
    String year;
    String month;
    String date;
    String value;
    String db_startDate;
    String db_endDate;

    ScheduleDto[] scheduleDtos = new ScheduleDto[4];

}
