package client;

import api.Request;
import com.google.gson.Gson;

import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String address = "127.0.0.1";
        int port = 23457;
        Socket socket = new Socket(InetAddress.getByName(address), port);
        System.out.println("Client started!");

        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        String query;
        if (args[0].equals("-in")) {
            // Чтение запроса из файла в случае наличия ключа -in
            FileInputStream f = new FileInputStream("./src/client/data/" + args[1]);
            query = new String(f.readAllBytes());
        } else {
            // Либо парсинг аргументов и создание JSON строки-запроса
            query = new Gson().toJson(new Request(args));
        }

        // Отправка запроса и его вывод
        output.writeUTF(query);
        System.out.println("Sent: " + query);

        // Приём запроса и его вывод
        DataInputStream input = new DataInputStream(socket.getInputStream());
        String answer = input.readUTF();
        System.out.println("Received: " + answer);

        socket.close();
    }
}