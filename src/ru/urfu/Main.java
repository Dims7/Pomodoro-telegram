package ru.urfu;

import java.util.ArrayList;

public class Main
{
    private static Pomodoro pomodoro = new Pomodoro();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static void processRequests()
    {
        while (inputProcessor.peekRequest() != null) {
            switch (inputProcessor.poolRequest()) {
                case "start":
                    System.out.println(Strings.MESSAGE_WHEN_POMODORO_STARTED);
                    pomodoro.start();
                    break;
                case "stop":
                    System.out.println(Strings.MESSAGE_WHEN_POMODORO_STOPPED);
                    pomodoro.stop();
                    break;
            }
        }
    }

    private static void sleep()
    {
        try {
            Thread.sleep(1000 - System.currentTimeMillis() % 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void processPomodoro()
    {
        Pomodoro.Status tmp = pomodoro.check();
        if (tmp != null)
            switch (tmp) {
                case WORK:
                    System.out.println(Strings.MESSAGE_WHEN_WORK_STARTED);
                    break;
                case LONG_REST:
                    System.out.println(Strings.MESSAGE_WHEN_LONG_REST_STARTED);
                    break;
                case SHORT_REST:
                    System.out.println(Strings.MESSAGE_WHEN_SHORT_REST_STARTED);
                    break;
            }
    }

    public static void main(String[] args)
    {
        while (true) {
            processRequests();
            processPomodoro();
            sleep();
        }
    }
}

