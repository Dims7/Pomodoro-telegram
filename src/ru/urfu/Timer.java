package ru.urfu;

import java.util.Date;

public class Timer
{
    private final int millsInMin = 60000;
    private long startTime;

    public Timer(int mins)
    {
        timer = mins * millsInMin;
        startTime = System.currentTimeMillis();
    }

    private long timer;

    public boolean checkTime()
    {
        return System.currentTimeMillis() - startTime >= timer;
    }
}
