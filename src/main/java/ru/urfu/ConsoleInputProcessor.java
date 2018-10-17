package ru.urfu;

import java.util.ArrayDeque;
import java.util.Scanner;

class ConsoleInputProcessor
        implements Runnable {
    private ArrayDeque<String> requestsQueue = new ArrayDeque<>();

    public String poolRequest() {
        return requestsQueue.poll();
    }

    public String peekRequest() {
        return requestsQueue.peek();
    }


    public void run() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            requestsQueue.add(sc.nextLine());
        }
    }
}