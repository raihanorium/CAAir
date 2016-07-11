package com.caair.model

class ShowFlightActionServiceModel {

    public static final String SQL_LIST_FLIGHT = """
        DROP TABLE IF EXISTS show_flight_action_service_model;
        DROP VIEW IF EXISTS show_flight_action_service_model;
        CREATE OR REPLACE VIEW show_flight_action_service_model AS
        SELECT flight.id, plane.name, flight.departure_air_port, flight.arrival_air_port,
        flight.departure_time, flight.arrival_time
        FROM flight
        LEFT JOIN air_plane plane ON flight.air_plane_id = plane.id
    """

    long id
    String name
    String departureAirPort         // departure airport name
    String arrivalAirPort           // arrival airport name
    Date departureTime              // time of departure
    Date arrivalTime                // time of arrival

    static mapping = {
        cache usage: "read-only"
        version false
    }
}
