package com.caair.model

class SearchFlightActionServiceModel {

    public static final String SQL_SEARCH_FLIGHT_MODEL = """
        DROP TABLE IF EXISTS search_flight_action_service_model;
        DROP VIEW IF EXISTS search_flight_action_service_model;
        CREATE OR REPLACE VIEW search_flight_action_service_model AS
        SELECT
            ap.id,
            ap.name,
            fl.departure_air_port,
            fl.arrival_air_port,
            fl.departure_time,
            fl.arrival_time,
            ti.is_booked,
            ti.is_paid
        FROM ticket_info ti
        LEFT JOIN flight fl ON fl.id = ti.flight_id
        LEFT JOIN air_plane ap ON ap.id = fl.air_plane_id
        WHERE ti.is_booked = false
    """

    long id
    String name                     // name of air plane
    String departureAirPort         // departure airport name
    String arrivalAirPort           // arrival airport name
    Date departureTime              // time of departure
    Date arrivalTime                // time of arrival
    boolean isBooked               // true/false, default value is false, if booked then true
    boolean isPaid                 // true/false default value is false, if paid then true

    static mapping = {
        cache usage: "read-only"
        version false
    }
}
