package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.validator;

import java.util.HashMap;
import java.util.Map;

public class Errors {
    private final Map<String, String> messages = new HashMap<>();

    public void addMessage(String attribute, String message) {
        messages.put(attribute, message);
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public boolean hasError() {
        return !messages.isEmpty();
    }
}