package com.FormDesigner.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface UserService {
    String createUser(JsonNode data);

    String deleteUser(JsonNode data);

    String getUserInfo(JsonNode data);
    String getUserDetailsByEmail(JsonNode data);
}
