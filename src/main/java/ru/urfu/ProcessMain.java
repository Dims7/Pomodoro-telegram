package ru.urfu;

public class ProcessMain {
    private Pomodoro pomodoro;
    private InputProcessor inputProcessor;
    private Thread inputThread;

    public ProcessMain() {
        pomodoro = new Pomodoro();
        inputProcessor = new InputProcessor();
        inputThread = new Thread(inputProcessor);
        inputThread.start();
    }

    private void processRequests() {
        while (inputProcessor.peekRequest() != null) {
            switch (inputProcessor.poolRequest()) {
                case Config.COMMAND_START:
                    System.out.println(Strings.MESSAGE_WHEN_POMODORO_STARTED);
                    pomodoro.start();
                    break;
                case Config.COMMAND_STOP:
                    System.out.println(Strings.MESSAGE_WHEN_POMODORO_STOPPED);
                    pomodoro.stop();
                    break;
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(Config.MILLS_IN_TICK - System.currentTimeMillis() % Config.MILLS_IN_TICK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processPomodoro() {
        Pomodoro.Status tmp = pomodoro.popStatus();
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

    public void run() {
        while (true) {
            processRequests();
            processPomodoro();
            sleep();
        }
    }
}
