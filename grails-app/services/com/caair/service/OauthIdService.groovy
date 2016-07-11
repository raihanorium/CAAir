package com.caair.service

import com.caair.BaseDomainService
import com.caair.entity.AppUser
import com.caair.entity.OAuthID
import grails.transaction.Transactional
import org.apache.log4j.Logger

@Transactional
class OauthIdService extends BaseDomainService {

    private Logger log = Logger.getLogger(getClass())

    AppUserService appUserService

    @Override
    public void init() {
        domainClass = OAuthID.class
    }

    /**
     * Pull appUserRole object
     * @return - list of appUserRole
     */
    @Override
    public List<OAuthID> list() {
        return OAuthID.list()
    }

    public OAuthID findByAccessToken(String token) {
        OAuthID oAuthID = OAuthID.findByAccessToken(token)
        return oAuthID
    }

    /**
     * create default data
     * this method is called from the BootstrapActionService class
     * @return - true if default data is loaded successfully
     */
    @Override
    boolean createDefaultData() {
        try {
            return true
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }


}
