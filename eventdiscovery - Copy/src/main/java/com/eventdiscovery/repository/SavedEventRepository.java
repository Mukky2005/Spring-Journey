package com.eventdiscovery.repository;

import com.eventdiscovery.models.SavedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavedEventRepository extends JpaRepository<SavedEvent, Long> {
    Optional<SavedEvent> findByEventId(String eventId);
    boolean existsByEventId(String eventId);
    void deleteByEventId(String eventId);
}