package server;

import api.*;
import com.google.gson.*;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


class Worker extends Thread {
    AtomicBoolean shutdown;
    Socket socket;
    Database db;

    /**
     * Worker - поток для обработки отдельного запроса
     *
     * @param socket   сокет, по которому был получен запрос и должен быть возвращён ответ
     * @param db       база данных, с которой мы работаем
     * @param shutdown атомарный boolean для выключения в случае команды exit
     */
    public Worker(Socket socket, Database db, AtomicBoolean shutdown) {
        this.socket = socket;
        this.db = db;
        this.shutdown = shutdown;
    }

    /**
     * getObj - функция для поиска вложенных элементов в JSON
     *
     * @param key список ключей, по которым ищется вложенный элемент
     * @param obj объект, в котором ищем элемент
     * @return найденный элемент
     */
    static JsonElement getObj(JsonElement key, JsonElement obj) {
        if (key.isJsonPrimitive()) {
            return obj.getAsJsonObject().get(key.getAsString());
        }
        JsonArray keyArr = key.getAsJsonArray();

        if (keyArr.size() == 1) {
            return getObj(keyArr.get(0).getAsJsonPrimitive(), obj);
        } else if (keyArr.size() == 0) {
            return obj;
        }

        String currentKey = keyArr.get(0).getAsString();

        keyArr.remove(0);

        return getObj(keyArr, obj.getAsJsonObject().get(currentKey).getAsJsonObject());
    }

    /**
     * get - функция для получения записи из базы данных
     *
     * @param db     база данных, с которой мы работаем
     * @param db_key ключ записи, с которой мы работаем, в базе данных, первый ключ из массива ключей в запросе
     * @param key    ключи вложенных элементов записи, все ключи из массива ключей в запросе, кроме первого
     */
    static Response get(Database db, String db_key, JsonArray key) {
        // Проверка на наличие записи в базе данных
        if (!db.containsKey(db_key))
            // Возврат ошибки, если ключ не найден
            return Response.Error("No such key");

        JsonElement res = getObj(key, db.get(db_key));

        return Response.Ok(res);
    }

    /**
     * delete - функция для удаления записи из базы данных
     *
     * @param db     база данных, с которой мы работаем
     * @param db_key ключ записи, с которой мы работаем, в базе данных, первый ключ из массива ключей в запросе
     * @param key    ключи вложенных элементов записи, все ключи из массива ключей в запросе, кроме первого
     */
    static Response delete(Database db, String db_key, JsonArray key) {
        // Проверка на наличие записи в базе данных
        if (!db.containsKey(db_key))
            // Возврат ошибки, если ключ не найден
            return Response.Error("No such key");
        // Проверка на наличие вложенных ключей
        else if (key.size() == 0) {
            // Удаление записи из бд в случае отсутствия вложенных ключей
            db.remove(db_key);
            return Response.Ok();
        }

        // Получение записи из бд
        JsonElement root = db.get(db_key);

        // Получение последнего вложенного ключа - того, что нужно удалить
        String toDelete = key.get(key.size() - 1).getAsString();
        key.remove(key.size() - 1);

        // Получение записи, в которой содержится запись для удаления, и её удаление по ключу
        getObj(key, root).getAsJsonObject().remove(toDelete);

        // Запись измененной записи в базу данных
        db.put(db_key, root);

        return Response.Ok();
    }

    /**
     * set - функция для добавления записи в базу данных
     *
     * @param db     база данных, с которой мы работаем
     * @param db_key ключ записи, с которой мы работаем, в базе данных, первый ключ из массива ключей в запросе
     * @param key    ключи вложенных элементов записи, все ключи из массива ключей в запросе, кроме первого
     * @param val    элемент, для добавления в базу данных
     */
    static Response set(Database db, String db_key, JsonArray key, JsonElement val) {
        // Проверка на наличие вложенных ключей
        if (key.size() == 0) {
            // Добавление записи в базу данных, если нет вложенных ключей
            db.put(db_key, val);
            return Response.Ok();
        }

        // Получение записи из бд
        JsonElement root = db.get(db_key);

        // Получение последнего вложенного ключа - того, который надо добавить
        String toSet = key.get(key.size() - 1).getAsString();
        key.remove(key.size() - 1);

        // Получение записи, в которой должна быть добавлена запись, и её добавление
        getObj(key, root).getAsJsonObject().add(toSet, val);

        // Запись измененной записи в базу данных
        db.put(db_key, root);

        return Response.Ok();
    }

    /**
     * processRequest - функция для обработки запроса
     *
     * @param db  база данных, с которой мы работаем
     * @param req запрос, который будет обрабатываться
     * @return результат обработки запроса
     */
    public static Response processRequest(Database db, Request req) {
        Gson json = new Gson();

        // Получение JsonElement из строки key в запросе
        JsonElement JsonKey = json.fromJson(req.getKey(), JsonElement.class);
        JsonArray key;
        String db_key;
        // JsonKey: 1               -> db_key = "1"; key = []
        // JsonKey: ["1", "2", "3"] -> db_key = "1"; key = ["2", "3"]
        if (JsonKey.isJsonPrimitive()) {
            db_key = JsonKey.getAsString();
            key = new JsonArray();
        } else {
            key = JsonKey.getAsJsonArray();
            db_key = key.get(0).getAsString();
            key.remove(0);
        }

        // Получение значения для обработки
        JsonElement val = req.getValue();

        // Вызов соответствующей функции для обработки
        switch (req.getType()) {
            case "get":
                return get(db, db_key, key);
            case "delete":
                return delete(db, db_key, key);
            case "set":
                return set(db, db_key, key, val);
        }

        // Возврат ошибки, если команда не найдена
        return Response.Error();
    }

    /**
     * run - функция, вызываемая на старте потока
     */
    public void run() {
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            // Чтение и вывод входящего запроса
            String line = input.readUTF();
            System.out.println("Received: " + line);

            // Получение объекта класса Request из строки запроса
            Request req = Request.FromString(line);

            Response answer;
            // Проверка на соответствие команде exit
            if (req.getType().equals("exit")) {
                // Если exit - то установка shutdown в true
                shutdown.set(true);
                answer = Response.Ok();
            } else
                // Обработка остальных команд функцией processRequest
                answer = processRequest(this.db, req);

            // Получение JSON строки из объекта класса Response
            String resp = new Gson().toJson(answer);

            // Отправка ответа на запрос
            output.writeUTF(resp);
            System.out.println("Sent: " + resp);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Открытие базы данных
        Database db = new Database("./src/server/data/db.json");
        // Создание атомарного boolean для выключения сервера
        AtomicBoolean shutdown = new AtomicBoolean(false);

        String address = "127.0.0.1";
        int port = 23457;
        ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));
        System.out.println("Server started!");

        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Обработка входящих запросов, создание своего Worker для каждого запроса
        executor.submit(() -> {
            while (!shutdown.get()) {
                try {
                    new Worker(server.accept(), db, shutdown).start();
                } catch (Exception ignored) {
                }
            }
        });

        // Выключение сервера в случае установки shutdown в true
        while (!shutdown.get()) {
            Thread.sleep(50L);
        }

        executor.shutdown();
        server.close();
    }
}