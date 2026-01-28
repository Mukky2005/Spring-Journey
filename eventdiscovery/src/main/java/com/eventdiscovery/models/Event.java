package com.eventdiscovery.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private String id;
    private String name;
    private String date;
    private String time;
    private String venueName;
    private String venueLocation;
    private String category;
    private String description;
    private String ticketUrl;
    private String imageUrl;
}