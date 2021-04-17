package com.himanshu.taskassistant;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Data {
    String title;
    String description;
    String dateTime;
    LocalDateTime fDateTime;
    boolean alarm;
    @RequiresApi(api = Build.VERSION_CODES.O)
    Data(String title, String Desc, String dateTime, boolean alarm)
    {
        this.title = title;
        this.description = Desc;
        this.dateTime = dateTime;
        this.alarm = alarm;
        //this.fDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
