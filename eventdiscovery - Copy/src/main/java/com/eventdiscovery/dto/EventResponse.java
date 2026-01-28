package com.eventdiscovery.dto;

import com.eventdiscovery.models.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EventResponse {
    private List<Event> events;
    private int totalPages;
    private long totalElements;
    private int currentPage;
}
