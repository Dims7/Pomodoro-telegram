package ru.urfu;

public class Main {
    public static void main(String[] args) {
        try {
            ProcessMain processMain = new ProcessMain();
            processMain.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

