package ru.urfu;

import lombok.Getter;

public class Request {
    @Getter
    private String userID;

    @Getter
    private String message;

    public Request(String userID, String message) {
        this.userID = userID;
        this.message = message;
    }
}
