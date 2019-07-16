package com.example.openshift_deneme.json;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JsonSchemaValidationException extends JsonSchemaException {

    private JSONObject messageAsJson;

    public JsonSchemaValidationException(String msg, JSONObject messageAsJson) {
        super(msg);
        this.messageAsJson = messageAsJson;
    }

    public JSONObject getMessageAsJson() {
        return messageAsJson;
    }
}
