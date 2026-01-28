package com.eventdiscovery.controller;

import com.eventdiscovery.dto.EventResponse;
import com.eventdiscovery.dto.EventSearchRequest;
import com.eventdiscovery.models.Event;
import com.eventdiscovery.models.SavedEvent;
import com.eventdiscovery.service.SavedEventService;
import com.eventdiscovery.service.TicketmasterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("")
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final TicketmasterService ticketmasterService;
    private final SavedEventService savedEventService;

    public EventController(TicketmasterService ticketmasterService,
                           SavedEventService savedEventService) {
        this.ticketmasterService = ticketmasterService;
        this.savedEventService = savedEventService;
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchEvents(@RequestBody EventSearchRequest request) {
        try {
            EventResponse response = ticketmasterService.searchEvents(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveEvent(@RequestBody Event event) {
        try {
            SavedEvent saved = savedEventService.saveEvent(event);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/saved")
    public ResponseEntity<List<SavedEvent>> getSavedEvents() {
        return ResponseEntity.ok(savedEventService.getAllSavedEvents());
    }

    @DeleteMapping("/saved/{eventId}")
    public ResponseEntity<?> removeSavedEvent(@PathVariable String eventId) {
        try {
            savedEventService.removeSavedEvent(eventId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Event removed successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/saved/check/{eventId}")
    public ResponseEntity<Map<String, Boolean>> checkIfSaved(@PathVariable String eventId) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("saved", savedEventService.isEventSaved(eventId));
        return ResponseEntity.ok(response);
    }
}
