package com.caair.service

import com.caair.BaseDomainService
import com.caair.entity.AirPlane
import com.caair.entity.Flight
import org.apache.log4j.Logger


class FlightService extends BaseDomainService {

    private Logger log = Logger.getLogger(getClass())

    AirPlaneService airPlaneService

    @Override
    public void init() {
        domainClass = Flight.class
    }

    /**
     * Pull flight object
     * @return - list of flight
     */
    @Override
    public List<Flight> list() {
        List<Flight> flightList = Flight.list();
    }

    public Flight findByArrivalAirPort(String arrivalAirPort) {
        return Flight.findByArrivalAirPort(arrivalAirPort, [readonly: true])
    }

    /**
     * create default data
     * this method is called from the BootstrapActionService class
     * @return - true if default data is loaded successfully
     */
    @Override
    boolean createDefaultData() {
        try {
            AirPlane airPlaneOne = airPlaneService.findByName('Airplane 1')
            AirPlane airPlaneTwo = airPlaneService.findByName('Airplane 2')
            AirPlane airPlane3 = airPlaneService.findByName('Airplane 3')
            AirPlane airPlane4 = airPlaneService.findByName('Airplane 4')
            AirPlane airPlane5 = airPlaneService.findByName('Airplane 5')
            AirPlane airPlane6 = airPlaneService.findByName('Airplane 6')
            AirPlane airPlane7 = airPlaneService.findByName('Airplane 7')
            AirPlane airPlane8 = airPlaneService.findByName('Airplane 8')
            AirPlane airPlane9 = airPlaneService.findByName('Airplane 9')
            AirPlane airPlane10 = airPlaneService.findByName('Airplane 10')
            AirPlane airPlane11 = airPlaneService.findByName('Airplane 11')
            AirPlane airPlane12 = airPlaneService.findByName('Airplane 12')
            new Flight(airPlaneId: airPlaneOne.id, departureAirPort: 'USA', arrivalAirPort: 'Dhaka', departureTime: new Date(), arrivalTime: new Date() + 1).save()
            new Flight(airPlaneId: airPlaneTwo.id, departureAirPort: 'USA', arrivalAirPort: 'London', departureTime: new Date() + 1, arrivalTime: new Date() + 2).save()
            new Flight(airPlaneId: airPlane3.id, departureAirPort: 'USA', arrivalAirPort: 'Canada', departureTime: new Date() + 2, arrivalTime: new Date() + 3).save()
            new Flight(airPlaneId: airPlane4.id, departureAirPort: 'USA', arrivalAirPort: 'France', departureTime: new Date() + 3, arrivalTime: new Date() + 4).save()
            new Flight(airPlaneId: airPlane5.id, departureAirPort: 'USA', arrivalAirPort: 'Malaysia', departureTime: new Date() + 4, arrivalTime: new Date() + 5).save()
            new Flight(airPlaneId: airPlane6.id, departureAirPort: 'USA', arrivalAirPort: 'Dubai', departureTime: new Date() + 5, arrivalTime: new Date() + 6).save()
            new Flight(airPlaneId: airPlane7.id, departureAirPort: 'USA', arrivalAirPort: 'India', departureTime: new Date() + 6, arrivalTime: new Date() + 7).save()
            new Flight(airPlaneId: airPlane8.id, departureAirPort: 'USA', arrivalAirPort: 'Mexico', departureTime: new Date() + 7, arrivalTime: new Date() + 8).save()
            new Flight(airPlaneId: airPlane9.id, departureAirPort: 'USA', arrivalAirPort: 'UK', departureTime: new Date() + 8, arrivalTime: new Date() + 9).save()
            new Flight(airPlaneId: airPlane10.id, departureAirPort: 'USA', arrivalAirPort: 'Spain', departureTime: new Date() + 9, arrivalTime: new Date() + 10).save()
            new Flight(airPlaneId: airPlane11.id, departureAirPort: 'USA', arrivalAirPort: 'Italy', departureTime: new Date() + 10, arrivalTime: new Date() + 11).save()
            new Flight(airPlaneId: airPlane12.id, departureAirPort: 'USA', arrivalAirPort: 'Brazil', departureTime: new Date() + 11, arrivalTime: new Date() + 12).save()
            return true
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }
}
