package api;

import com.google.gson.JsonElement;

public class Response {
    /* Ответ ("OK"/"ERROR") */
    String response;
    /* Причина ошибки ("No such key") */
    String reason = null;
    /* Возвращаемое значение (для get) */
    JsonElement value = null;

    public Response(String response) {
        this.response = response;
    }

    Response(String response, String reason, JsonElement value) {
        this.response = response;
        this.reason = reason;
        this.value = value;
    }

    public static Response Error() {
        return new Response("ERROR");
    }

    public static Response Error(String reason) {
        return new Response("ERROR", reason, null);
    }

    public static Response Ok() {
        return new Response("OK");
    }

    public static Response Ok(JsonElement value) {
        return new Response("OK", null, value);
    }
}
