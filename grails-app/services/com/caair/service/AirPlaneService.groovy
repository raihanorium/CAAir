package com.caair.service

import com.caair.BaseDomainService
import com.caair.entity.AirPlane
import org.apache.log4j.Logger


class AirPlaneService extends BaseDomainService {

    private Logger log = Logger.getLogger(getClass())

    @Override
    public void init() {
        domainClass = AirPlane.class
    }

    /**
     * Pull airPlane object
     * @return - list of airPlane
     */
    @Override
    public List<AirPlane> list() {
        return AirPlane.list()
//              return AirPlane.findAllByCompanyId(companyId, [sort: AirPlane.DEFAULT_SORT_FIELD, order: ASCENDING_SORT_ORDER, readOnly: true]);
    }


    public AirPlane findByName(String name) {
        AirPlane airPlane = AirPlane.findByName(name)
        return airPlane
    }

    /**
     * create default data
     * this method is called from the BootstrapActionService class
     * @return - true if default data is loaded successfully
     */
    @Override
    boolean createDefaultData() {
        try {
            new AirPlane(name: 'Airplane 1', totalSeat: 500).save()
            new AirPlane(name: 'Airplane 2', totalSeat: 500).save()
            new AirPlane(name: 'Airplane 3', totalSeat: 250).save()
            new AirPlane(name: 'Airplane 4', totalSeat: 250).save()
            new AirPlane(name: 'Airplane 5', totalSeat: 300).save()
            new AirPlane(name: 'Airplane 6', totalSeat: 300).save()
            new AirPlane(name: 'Airplane 7', totalSeat: 300).save()
            new AirPlane(name: 'Airplane 8', totalSeat: 300).save()
            new AirPlane(name: 'Airplane 9', totalSeat: 250).save()
            new AirPlane(name: 'Airplane 10', totalSeat: 300).save()
            new AirPlane(name: 'Airplane 11', totalSeat: 250).save()
            new AirPlane(name: 'Airplane 12', totalSeat: 250).save()
            return true
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }


}
