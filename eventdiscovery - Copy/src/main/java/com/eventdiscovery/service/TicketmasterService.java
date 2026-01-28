package com.eventdiscovery.service;

import com.eventdiscovery.dto.EventResponse;
import com.eventdiscovery.dto.EventSearchRequest;
import com.eventdiscovery.models.Event;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketmasterService {

    @Value("${ticketmaster.api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://app.ticketmaster.com/discovery/v2/events.json";

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public TicketmasterService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = new ObjectMapper();
    }

    public EventResponse searchEvents(EventSearchRequest request) {
        try {
            String url = buildUrl(request);

            String response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return parseEventResponse(response, request.getPage());

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch events from Ticketmaster: " + e.getMessage(), e);
        }
    }

    private String buildUrl(EventSearchRequest request) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("apikey", apiKey)
                .queryParam("size", request.getSize())
                .queryParam("page", request.getPage());

        if (request.getCity() != null && !request.getCity().isBlank()) {
            builder.queryParam("city", request.getCity());
        }

        if (request.getCategory() != null && !request.getCategory().isBlank()) {
            builder.queryParam("classificationName", request.getCategory());
        }

        if (request.getStartDate() != null && !request.getStartDate().isBlank()) {
            builder.queryParam("startDateTime", request.getStartDate() + "T00:00:00Z");
        }

        if (request.getEndDate() != null && !request.getEndDate().isBlank()) {
            builder.queryParam("endDateTime", request.getEndDate() + "T23:59:59Z");
        }

        return builder.toUriString();
    }


    private EventResponse parseEventResponse(String jsonResponse, int currentPage) throws Exception {
        JsonNode root = objectMapper.readTree(jsonResponse);
        List<Event> events = new ArrayList<>();

        JsonNode embedded = root.path("_embedded");
        if (embedded.isMissingNode()) {
            return new EventResponse(events, 0, 0, currentPage);
        }

        JsonNode eventsNode = embedded.path("events");

        for (JsonNode eventNode : eventsNode) {
            Event event = new Event();

            event.setId(eventNode.path("id").asText());
            event.setName(eventNode.path("name").asText());

            JsonNode dates = eventNode.path("dates").path("start");
            event.setDate(dates.path("localDate").asText());
            event.setTime(dates.path("localTime").asText("TBA"));

            JsonNode venues = eventNode.path("_embedded").path("venues");
            if (venues.isArray() && !venues.isEmpty()) {
                JsonNode venue = venues.get(0);
                event.setVenueName(venue.path("name").asText());

                JsonNode city = venue.path("city").path("name");
                JsonNode state = venue.path("state").path("stateCode");

                event.setVenueLocation(city.asText("") + ", " + state.asText(""));
            }

            JsonNode classifications = eventNode.path("classifications");
            if (classifications.isArray() && !classifications.isEmpty()) {
                event.setCategory(
                        classifications.get(0).path("segment").path("name").asText()
                );
            }

            event.setDescription(eventNode.path("info").asText(""));
            event.setTicketUrl(eventNode.path("url").asText());

            JsonNode images = eventNode.path("images");
            if (images.isArray() && !images.isEmpty()) {
                event.setImageUrl(images.get(0).path("url").asText());
            }

            events.add(event);
        }

        JsonNode page = root.path("page");
        int totalPages = page.path("totalPages").asInt(1);
        long totalElements = page.path("totalElements").asLong(0);

        return new EventResponse(events, totalPages, totalElements, currentPage);
    }
}
