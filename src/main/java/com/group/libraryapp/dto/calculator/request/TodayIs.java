package com.group.libraryapp.dto.calculator.request;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class TodayIs {
    private DayOfWeek dayOfTheWeek;

    public TodayIs(LocalDate date){
        this.dayOfTheWeek = date.getDayOfWeek();
    }

    public DayOfWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }
}
