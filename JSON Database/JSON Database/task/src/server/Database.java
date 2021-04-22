package server;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.locks.*;

class Database {
    File file;
    ReentrantReadWriteLock RWLock;
    HashMap<String, JsonElement> db;

    /**
     * Database - класс базы данных
     *
     * @param filePath путь к JSON файлу с базой данных
     */
    public Database(String filePath) throws IOException {
        RWLock = new ReentrantReadWriteLock();

        // Создание блокировки для записи
        Lock lock = RWLock.writeLock();
        lock.lock();

        // Открытие файла
        file = new File(filePath);
        if (!file.exists())
            // Создание файла, если его нет
            file.createNewFile();

        Gson json = new Gson();
        FileInputStream fileIn = new FileInputStream(file);

        // Чёрная магия с чтением файла в хэшмапу базы даных
        Type empMapType = new TypeToken<HashMap<String, JsonElement>>() {
        }.getType();
        db = json.fromJson(new String(fileIn.readAllBytes()), empMapType);
        if (db == null) {
            // Создание хэшмапы в случае отсутствия записей
            db = new HashMap<>();
        }

        // Закрытие файла, снятие блокировки
        fileIn.close();
        lock.unlock();
    }

    public void write() throws IOException {
        // Создание блокировки для записи
        Lock lock = RWLock.writeLock();
        lock.lock();

        FileOutputStream fileOut = new FileOutputStream(file, false);
        Gson json = new Gson();

        // Преобразование хэшмапы базы данных в JSON строку с последующей записью в файл
        fileOut.write(json.toJson(db).getBytes());
        fileOut.close();

        // Снятие блокировки
        lock.unlock();
    }

    public boolean containsKey(String key) {
        // Создание блокировки для чтения
        Lock lock = RWLock.readLock();
        lock.lock();

        // Получение сведений о наличии записи в хэшмапе
        boolean result = db.containsKey(key);

        // Снятие блокировки
        lock.unlock();
        return result;
    }

    public JsonElement get(String key) {
        // Создание блокировки для чтения
        Lock lock = RWLock.readLock();
        lock.lock();

        // Получение записи из хэшмапы
        JsonElement result = db.get(key);

        // Снятие блокировки
        lock.unlock();
        return result;
    }

    public void remove(String key) {
        // Создание блокировки для записи
        Lock lock = RWLock.writeLock();
        lock.lock();

        // Удаление записи из хэшмапы
        db.remove(key);

        // Запись файла базы данных
        try {
            this.write();
        } catch (Exception ignored) {
        }

        // Снятие блокировки
        lock.unlock();
    }

    public void put(String key, JsonElement value) {
        // Создание блокировки для записи
        Lock lock = RWLock.writeLock();
        lock.lock();

        // Запись в хэшмапу записи
        db.put(key, value);

        // Запись файла базы данных
        try {
            this.write();
        } catch (Exception ignored) {
        }

        // Снятие блокировки
        lock.unlock();
    }
}