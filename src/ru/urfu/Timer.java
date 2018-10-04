package ru.urfu;

import java.util.Date;

public class Timer
{
    private long startTime;

    public Timer(int mins)
    {
        int millsInMin = 60000;
        timer = mins * millsInMin;
        startTime = System.currentTimeMillis();
    }

    private long timer;

    public boolean checkTime()
    {
        return System.currentTimeMillis() - startTime >= timer;
    }
}
