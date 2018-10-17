package ru.urfu;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

import java.util.ArrayDeque;

public class VkProcessor {
    private ArrayDeque<String> requestsQueue = new ArrayDeque<>();
    private Group group = new Group(Config.GROUP_ID, Config.TOKEN);

    public String poolRequest() {
        return requestsQueue.poll();
    }

    public String peekRequest() {
        return requestsQueue.peek();
    }

    public void run() {
        group.onSimpleTextMessage(message ->
                requestsQueue.add(message.getText())
        );
    }

    public void sendMessage(String message) {
        new Message()
                .from(group)
                .to(Config.USER_FOR_DEBAG_ID)
                .text(message)
                .send();
    }
}
