package ru.urfu;

public class Pomodoro
{
    private Timer timer;
    private Status currentStatus;
    private int countOfTasks = 0;

    public void startWork()
    {
        countOfTasks++;
        timer = new Timer(Config.WORK_TIME_IN_MINUTES);
        currentStatus = Status.WORK;
    }

    public void startShortRest()
    {
        timer = new Timer(Config.SHORT_REST_TIME_IN_MINUTES);
        currentStatus = Status.SHORT_REST;
    }

    public void startLongRest()
    {
        timer = new Timer(Config.LONG_REST_TIME_IN_MINUTES);
        currentStatus = Status.LONG_REST;
    }

    public boolean check()
    {
        if (timer.checkTime())
        {
            switch (currentStatus)
            {
                case LONG_REST:
                    startWork();
                    break;
                case SHORT_REST:
                    startWork();
                    break;
                case WORK:
                    if (countOfTasks % 4 == 0)
                        startLongRest();
                    else
                        startShortRest();
                    break;
            }
            return true;
        }
        else
            return false;
    }


    enum Status
    {
        WORK,
        SHORT_REST,
        LONG_REST
    }
}
