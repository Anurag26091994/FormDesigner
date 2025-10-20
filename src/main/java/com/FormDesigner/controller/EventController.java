package com.FormDesigner.controller;

import com.FormDesigner.DTOs.EventRequest;
import com.FormDesigner.service.serviceimpl.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@CrossOrigin("*")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/trigger")
    public ResponseEntity<String> triggerEvent(@RequestBody EventRequest data) {
        String response = eventService.processEvent(data.getEventName(), data.getEventType(),
                data.getData(), data.getFlag());
        return ResponseEntity.ok(response);
    }
}
