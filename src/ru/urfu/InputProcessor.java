package ru.urfu;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class InputProcessor
{
    private static ArrayDeque<String> requestsQueue = new ArrayDeque<>();

    public String poolRequest()
    {
        return requestsQueue.poll();
    }

    public String peekRequest()
    {
        return requestsQueue.peek();
    }


    public InputProcessor()
    {
        initInput();
    }

    private static void initInput()
    {
        Thread inputThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true) {
                    Scanner sc = new Scanner(System.in);
                    requestsQueue.add(sc.nextLine());
                }
            }
        });
        inputThread.start();
    }
}
