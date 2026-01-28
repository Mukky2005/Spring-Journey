package com.eventdiscovery.service;

import com.eventdiscovery.models.Event;
import com.eventdiscovery.models.SavedEvent;
import com.eventdiscovery.repository.SavedEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SavedEventService {

    private final SavedEventRepository repository;

    public SavedEventService(SavedEventRepository repository) {
        this.repository = repository;
    }

    public SavedEvent saveEvent(Event event) {
        if (repository.existsByEventId(event.getId())) {
            throw new RuntimeException("Event already saved");
        }

        SavedEvent savedEvent = new SavedEvent();
        savedEvent.setEventId(event.getId());
        savedEvent.setName(event.getName());
        savedEvent.setDate(event.getDate());
        savedEvent.setTime(event.getTime());
        savedEvent.setVenueName(event.getVenueName());
        savedEvent.setVenueLocation(event.getVenueLocation());
        savedEvent.setCategory(event.getCategory());
        savedEvent.setDescription(event.getDescription());
        savedEvent.setTicketUrl(event.getTicketUrl());
        savedEvent.setImageUrl(event.getImageUrl());

        return repository.save(savedEvent);
    }

    public List<SavedEvent> getAllSavedEvents() {
        return repository.findAll();
    }

    @Transactional
    public void removeSavedEvent(String eventId) {
        if (!repository.existsByEventId(eventId)) {
            throw new RuntimeException("Event not found in saved list");
        }
        repository.deleteByEventId(eventId);
    }

    public boolean isEventSaved(String eventId) {
        return repository.existsByEventId(eventId);
    }
}