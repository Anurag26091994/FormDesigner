package com.FormDesigner.service.serviceimpl;

import com.FormDesigner.constants.EventType;
import com.FormDesigner.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final UserService userService;

    public EventService(UserService userService) {
        this.userService = userService;
    }

    public String processEvent(String eventName, EventType eventType,
                               JsonNode data, Boolean flag) {
        String response = "";
        System.out.println("Flag value: " + flag);
        if(flag) {
            System.out.println("Event Name: " + eventName + " :: Event Type: " + eventType);
            if (eventType.equals(EventType.USER)) {
                System.out.println("Processing USER event");
                switch (eventName.toUpperCase()) {
                    case "CREATEUSER":
                        response = userService.createUser(data);
                        break;
                    case "GETUSERINFO":
                        response= userService.getUserInfo(data);
                        break;
                    case "GETUSERDETAILSBYEMAIL":
                        response= userService.getUserDetailsByEmail(data);
                        break;
                    case "DELETEUSER":
                        response = userService.deleteUser(data);
                        break;
                    default:
                        return "Unsupported USER event name: " + eventName;
                }
                return response;
            }

            if (eventType.toString().isEmpty()) {
                return "Event type is required.";
            }
        }
        return "Flag is false, event not processed.";
    }
}
