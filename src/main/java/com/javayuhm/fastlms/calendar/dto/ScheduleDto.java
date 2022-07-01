package com.javayuhm.fastlms.calendar.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ScheduleDto {
    int scheduleIdx;
    int scheduleNum;
    String scheduleSubject;
    String scheduleDesc;
    String scheduleDate;

    String scheduleShare;
    String scheduleMycolor;

    @Override
    public String toString(){
        return "ScheduleDto [ scheduleIdx : "+scheduleIdx+" , scheduleNum: "+scheduleNum+"";
    }
}
