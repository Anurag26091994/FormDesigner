package com.FormDesigner.util;

import com.FormDesigner.constants.EventType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface EventHandler<T> {

    String handle(T form);
    EventType getEventType();
    String getEventName();
    Class<T> getFormClass();

    default String handleJson(JsonNode jsonNode, ObjectMapper mapper) {
        try {
            T form = mapper.treeToValue(jsonNode, getFormClass());
            return handle(form);
        } catch (Exception e) {
            throw new RuntimeException("Failed to handle JSON event or Parse data: ", e);
        }
    }
}
