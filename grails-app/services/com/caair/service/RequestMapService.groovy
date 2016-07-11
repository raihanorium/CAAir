package com.caair.service

import com.caair.BaseDomainService
import com.caair.entity.RequestMap
import grails.transaction.Transactional
import org.apache.log4j.Logger

@Transactional
class RequestMapService extends BaseDomainService {

    private Logger log = Logger.getLogger(getClass())

    @Override
    void init() {
        domainClass = RequestMap.class
    }

    /**
     * create default data
     * this method is called from the BootstrapActionService class
     * @return - true if default data is loaded successfully
     */
    @Override
    boolean createDefaultData() {
        try {
            new RequestMap(url: "/", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY").save()
            new RequestMap(url: "/js/**", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY").save()
            new RequestMap(url: "/css/**", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY").save()
            new RequestMap(url: "/images/**", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY").save()
            new RequestMap(url: "/login/**", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY").save()
            new RequestMap(url: "/logout/**", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY").save()
            new RequestMap(url: "/plugins/**", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY").save()
            new RequestMap(url: "/assets/**", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY").save()
            new RequestMap(url: "/oauth/**", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY").save()
            new RequestMap(url: "/oauthCallBack/**", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY").save()
            new RequestMap(url: "/j_spring_security_check", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY").save()
            new RequestMap(url: "/google/**", configAttribute: "ROLE_ADMIN,ROLE_CUSTOMER,ROLE_AIRLINE_STUFF").save()
            new RequestMap(url: "/appUser/**", configAttribute: "ROLE_ADMIN,ROLE_CUSTOMER,ROLE_AIRLINE_STUFF").save()
            new RequestMap(url: "/dashboard/**", configAttribute: "ROLE_ADMIN").save()
            new RequestMap(url: "/customer/**", configAttribute: "ROLE_ADMIN").save()
            new RequestMap(url: "/flight/**", configAttribute: "ROLE_ADMIN,ROLE_AIRLINE_STUFF,ROLE_CUSTOMER").save()
            new RequestMap(url: "/paymentInfo/**", configAttribute: "ROLE_ADMIN").save()
            new RequestMap(url: "/ticketInfo/**", configAttribute: "ROLE_ADMIN").save()
            new RequestMap(url: "/airPlane/**", configAttribute: "ROLE_ADMIN").save()
            new RequestMap(url: "/dashboard/**", configAttribute: "ROLE_ADMIN").save()
            new RequestMap(url: "/flightBooking/**", configAttribute: "ROLE_ADMIN").save()
            new RequestMap(url: "/login/**", configAttribute: "IS_AUTHENTICATED_ANONYMOUSLY").save()
            return true
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }
}
