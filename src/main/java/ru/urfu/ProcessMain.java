package ru.urfu;

public class ProcessMain {
    private Pomodoro pomodoro;
    private VkProcessor vkProcessor;

    public ProcessMain() {
        pomodoro = new Pomodoro();
        vkProcessor = new VkProcessor();
        vkProcessor.run();
    }

    private void processRequests() {
        while (vkProcessor.peekRequest() != null) {
            switch (vkProcessor.poolRequest()) {
                case Config.COMMAND_START:
                    vkProcessor.sendMessage(Strings.MESSAGE_WHEN_POMODORO_STARTED);
                    pomodoro.start();
                    break;
                case Config.COMMAND_STOP:
                    vkProcessor.sendMessage(Strings.MESSAGE_WHEN_POMODORO_STOPPED);
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
                    vkProcessor.sendMessage(Strings.MESSAGE_WHEN_WORK_STARTED);
                    break;
                case LONG_REST:
                    vkProcessor.sendMessage(Strings.MESSAGE_WHEN_LONG_REST_STARTED);
                    break;
                case SHORT_REST:
                    vkProcessor.sendMessage(Strings.MESSAGE_WHEN_SHORT_REST_STARTED);
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
