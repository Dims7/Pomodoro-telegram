package ru.urfu;

import lombok.Getter;

public class Pomodoro {
    private Timer timer;
    private Status currentStatus;
    private int countOfTasks = 0;

    public void start() {
        startWork();
    }

    private void startWork() {
        countOfTasks++;
        timer = new Timer(Config.WORK_TIME_IN_MINUTES);
        currentStatus = Status.WORK;
    }

    private void startShortRest() {
        timer = new Timer(Config.SHORT_REST_TIME_IN_MINUTES);
        currentStatus = Status.SHORT_REST;
    }

    private void startLongRest() {
        timer = new Timer(Config.LONG_REST_TIME_IN_MINUTES);
        currentStatus = Status.LONG_REST;
    }

    public void stop() {
        timer = null;
    }

    public Status popStatus() {
        if (timer != null && timer.checkTime()) {
            switch (currentStatus) {
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
            return currentStatus;
        }
        return null;
    }


    enum Status {
        WORK,
        SHORT_REST,
        LONG_REST
    }
}
