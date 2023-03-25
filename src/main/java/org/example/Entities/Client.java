package org.example.Entities;

import java.util.Arrays;

public class Client implements Runnable {

    private String name;
    private int money;
    private int[] order;

    public Client(String name, int money, int[] order) {
        this.name = name;
        this.money = money;
        this.order = order;
    }

    @Override
    public String toString() {
        return String.format("Имя - %s, деньги - %d, заказ - %s", name, money, Arrays.toString(order));
    }

    public int[] getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public void run() {
        Supervisor.addClient(this); // вызов функции управляющей сущности
        Supervisor.process(this);
    }
}