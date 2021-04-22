package api;

import com.google.gson.*;

public class Request {
    /* Тип запроса (get, delete, set...) */
    String type = null;
    /* Ключ (["Oleg", "Name"], "first", ...) */
    JsonElement key = null;
    /* Значение (для запросов put и set) */
    JsonElement value = null;

    /**
     * FromString - функция, для создания нового объекта класса Request из строки-запроса
     *
     * @param jsonString строка с JSON-запросом
     * @return созданный Request
     */
    public static Request FromString(String jsonString) {
        Gson json = new Gson();
        return json.fromJson(jsonString, Request.class);
    }

    /**
     * Request - конструктор Request-а из аргументов командной строки
     *
     * @param args аргументы командной строки
     */
    public Request(String[] args) {
        Gson json = new Gson();
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-t":
                    this.type = args[i + 1];
                    break;
                case "-k":
                    JsonElement JsonKey = json.toJsonTree(args[i + 1]);
                    if (JsonKey.isJsonPrimitive()) {
                        this.key = JsonKey.getAsJsonPrimitive();
                    } else
                        this.key = JsonKey.getAsJsonArray();
                    break;
                case "-v":
                    JsonElement JsonValue = json.toJsonTree(args[i + 1]);
                    if (JsonValue.isJsonPrimitive()) {
                        this.value = JsonValue.getAsJsonPrimitive();
                    } else
                        this.value = JsonValue;
            }
        }
    }

    public String getType() {
        return type;
    }

    public JsonElement getKey() {
        return key;
    }

    public JsonElement getValue() {
        return value;
    }
}