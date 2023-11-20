package com.example;

public class BroadcastListener extends Thread {
    Connection connection;

    public BroadcastListener(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        String[] keyValue;
        while (true) {
            keyValue = connection.receveKeyValue();
            if (keyValue[0] == "msgRecivedBroadcast") {
                System.out.println("-----------------");
                System.out.print("Canale Broadcast: " + keyValue[1]);
                System.out.println("-----------------");
            }
        }
    }
}
