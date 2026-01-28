package com.eventdiscovery.dto;

import lombok.Data;

@Data
public class EventSearchRequest {
    private String city;
    private String category;
    private String startDate;
    private String endDate;
    private int page = 0;
    private int size = 20;
}


