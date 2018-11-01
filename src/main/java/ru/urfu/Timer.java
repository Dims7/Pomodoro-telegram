package ru.urfu;

public class Timer
{
    private long startTime;

    public Timer(int mins)
    {
        final int millsInMin = 60000;
        timer = mins * millsInMin;
        startTime = System.currentTimeMillis();
    }

    private long timer;

    public boolean checkTime()
    {
        return System.currentTimeMillis() - startTime >= timer;
    }
}
