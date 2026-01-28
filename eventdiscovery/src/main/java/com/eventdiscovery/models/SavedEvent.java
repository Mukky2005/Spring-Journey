package com.eventdiscovery.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "saved_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavedEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String eventId;

    @Column(length = 500)
    private String name;
    private String date;
    private String time;

    @Column(length = 500)
    private String venueName;

    @Column(length = 500)
    private String venueLocation;
    private String category;

    @Column(length = 2000)
    private String description;

    @Column(length = 1000)
    private String ticketUrl;

    @Column(length = 1000)
    private String imageUrl;
}