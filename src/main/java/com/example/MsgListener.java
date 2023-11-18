package com.example;

public class MsgListener extends Thread {
    Connection connection;

    MsgListener(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        String[] response;
        while (true) {
            System.out.println();
            response = connection.receveKeyValue();
            if (response[0] == "msgForAll") {
                System.out.println(response[1]);
            }
        }
    }
}