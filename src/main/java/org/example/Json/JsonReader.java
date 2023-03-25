package org.example.Json;

import org.example.Entities.Client;
import org.example.Entities.Cooker;
import org.example.Entities.Food;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    private String path;

    public JsonReader(String path) {
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Cooker[] parseCookersIntoArray() {
        Cooker[] cookers = new Cooker[3];
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray json = (JSONArray) jsonParser.parse(new FileReader(path));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Client[] parseClientsIntoArray() {
        Client[] clients = new Client[5];
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray json = (JSONArray) jsonParser.parse(new FileReader(path));
            int counter = 0;
            for (Object o : json) {
                JSONObject jsonObject = (JSONObject) o;
                String name = (String) jsonObject.get("name");
                int money = ((Long) jsonObject.get("money")).intValue();
                JSONArray orderJsonArray = (JSONArray) jsonObject.get("order");
                int[] order = new int[orderJsonArray.size()];
                for (int i = 0; i < orderJsonArray.size(); i++) {
                    order[i] = ((Long) orderJsonArray.get(i)).intValue();
                }
                Client client = new Client(name, money, order);
                clients[counter++] = client;
            }
        } catch (ParseException | IOException e) {
            System.out.println("Что-то пошло не так при попытке распарсить json - clients.json");
        }
        return clients;
    }

    public void parseFoodIntoArray(Food[] food, int[] ids) {
        JSONParser jsonParser = new JSONParser();
        try {
            int counter = 0;
            JSONArray json = (JSONArray) jsonParser.parse(new FileReader(path));
            for (Object o : json) {
                JSONObject jsonObject = (JSONObject) o;
                int id = ((Long) jsonObject.get("dish_id")).intValue();
                for (int i : ids) {
                    if (i == id) {
                        food[counter++] = new Food(id, (String) jsonObject.get("dish_name"), ((Long) jsonObject.get("dish_cost")).intValue(), ((Long) jsonObject.get("prep_time")).intValue());
                    }
                }
            }
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}