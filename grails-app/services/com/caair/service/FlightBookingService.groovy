package com.caair.service

import com.caair.BaseDomainService
import com.caair.entity.AirPlane
import grails.transaction.Transactional
import groovy.sql.GroovyRowResult
import groovy.sql.Sql

import javax.sql.DataSource


class FlightBookingService extends BaseDomainService {

    Sql sql
    DataSource dataSource;


    @Override
    public void init() {
    }

    @Override
    boolean createDefaultData() {
        return false
    }
/*
 * Search with provied input and result actual search result from database
 *
 **/

    @Transactional(readOnly = true)
    public List<GroovyRowResult> getSearchResult(Map params) {
        println params;
        String condition = "";
        if (params?.optradio == '2') {
            condition = " OR (departure_air_port  ='${params?.arrivalAirport}' " +
                    "AND arrival_air_port='${params?.departureAirport}' AND departure_time='${params?.arrivalTime}' )";
        }
        String strSQL = """
                   SELECT air_plane.name AS airplane,flight.* FROM flight
                    INNER JOIN air_plane ON air_plane.id=flight.air_plane_id
                    WHERE (departure_air_port  ='${params?.departureAirport}'
                            AND arrival_air_port='${params?.arrivalAirport}'
                            AND departure_time='${params?.departureTime}' )
                    ${condition}
                    """;
        sql = new Sql(dataSource);
        List list = sql.rows(strSQL);
        return list;
    }

}
