package ru.urfu;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ProcessMain {
    private Pomodoro pomodoro;
    private VkProcessor vkProcessor;
    private HashMap<Integer, Pomodoro> pomodoros = new HashMap<>();

    public ProcessMain() {
        vkProcessor = new VkProcessor();
        vkProcessor.run();
    }

    private void processRequests() {
        while (vkProcessor.peekRequest() != null) {
            int id = vkProcessor.peekRequest().getUserID();
            String message = vkProcessor.poolRequest().getMessage();
            switch (message.toLowerCase()) {
                case Config.COMMAND_START:
                    if (!pomodoros.containsKey(id)) {
                        pomodoros.put(id, new Pomodoro());
                        vkProcessor.sendMessage(id, Strings.MESSAGE_WHEN_POMODORO_STARTED);
                        pomodoros.get(id).start();
                    }
                    else
                        vkProcessor.sendMessage(id, Strings.MESSAGE_WHEN_POMODORO_RESTARTED);
                    break;
                case Config.COMMAND_STOP:
                    if (pomodoros.containsKey(id)) {
                        vkProcessor.sendMessage(id, Strings.MESSAGE_WHEN_POMODORO_STOPPED);
                        pomodoros.remove(id);
                    } else {
                        vkProcessor.sendMessage(id, Strings.MESSAGE_WHEN_POMODORO_NOT_FOUND);
                    }
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

    private void processPomodoros() {
        pomodoros.forEach((id, pomodoro) -> {
            Pomodoro.Status pomodoroStatus = pomodoro.popStatus();
            if (pomodoroStatus != null)
                switch (pomodoroStatus) {
                    case WORK:
                        vkProcessor.sendMessage(id, Strings.MESSAGE_WHEN_WORK_STARTED);
                        break;
                    case LONG_REST:
                        vkProcessor.sendMessage(id, Strings.MESSAGE_WHEN_LONG_REST_STARTED);
                        break;
                    case SHORT_REST:
                        vkProcessor.sendMessage(id, Strings.MESSAGE_WHEN_SHORT_REST_STARTED);
                        break;
                }
        });


    }

    public void run() {
        while (true) {
            processRequests();
            processPomodoros();
            sleep();
        }
    }
}
