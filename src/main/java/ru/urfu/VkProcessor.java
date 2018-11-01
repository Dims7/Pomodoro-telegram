package ru.urfu;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;

public class VkProcessor {
    private ArrayDeque<Request> requestsQueue = new ArrayDeque<>();
    private Group group = new Group(Config.GROUP_ID, readToken());

    public Request poolRequest() {
        return requestsQueue.poll();
    }

    public Request peekRequest() {
        return requestsQueue.peek();
    }

    public void run() {
        group.onSimpleTextMessage(message ->
                requestsQueue.add(new Request(message.authorId(), message.getText()))
        );
    }

    public void sendMessage(int id, String message) {
        new Message()
                .from(group)
                .to(id)
                .text(message)
                .send();
    }

    private String readToken() {
        try {
            try (java.io.FileReader fileReader = new java.io.FileReader(Config.TOKEN_FILE_NAME)) {
                try(BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                    return bufferedReader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
