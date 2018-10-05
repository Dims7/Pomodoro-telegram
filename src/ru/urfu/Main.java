package ru.urfu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    private ArrayList<Pomodoro> pomodores = new ArrayList<>();
    private static String st;

    public static void initInput()
    {
        Thread inputThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true) {
                    Scanner sc = new Scanner(System.in);
                    st = sc.nextLine();
                }
            }
        });
        inputThread.start();
    }

    public static void main(String[] args)
    {
        initInput();
        while (true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(st);
        }

    }
}
