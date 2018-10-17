package ru.urfu;

import lombok.Getter;

public class Request {
    @Getter
    private int userID;

    @Getter
    private String message;

    public Request(int userID, String message) {
        this.userID = userID;
        this.message = message;
    }
}
