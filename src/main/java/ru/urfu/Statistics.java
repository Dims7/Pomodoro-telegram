package ru.urfu;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Statistics {
    HashMap<Integer, Integer> statistic = new HashMap<Integer, Integer>();

    public Statistics() {
        try (java.io.FileReader fileReader = new java.io.FileReader(Config.STATISTIC_FILE_NAME);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            statistic = jsonToMap(bufferedReader.readLine());
        } catch (IOException e) {
            statistic = new HashMap<>();
        }
    }

    public void addPointToUser(int userId) {
        if (statistic.containsKey(userId))
            statistic.put(userId, statistic.get(userId) + 1);
        else
            statistic.put(userId, 1);
        saveDataToFile();
    }

    public Integer getUserStatistic(int userId) {
        if (statistic.containsKey(userId))
            return statistic.get(userId);
        return 0;
    }

    private void saveDataToFile() {
        String data = mapToJson(statistic);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Config.STATISTIC_FILE_NAME));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String mapToJson(HashMap<Integer, Integer> map) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(map);
        return json;
    }

    private HashMap<Integer, Integer> jsonToMap(String json) {
        Gson gson = new GsonBuilder().create();
        Type typeOfHashMap = new TypeToken<HashMap<Integer, Integer>>() {
        }.getType();
        return gson.fromJson(json, typeOfHashMap);
    }
}
