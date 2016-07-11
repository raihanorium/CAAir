package com.caair.service

import com.caair.BaseDomainService
import com.caair.entity.AppUser
import grails.plugin.springsecurity.SpringSecurityService
import org.apache.log4j.Logger

class AppUserService extends BaseDomainService {

    private Logger log = Logger.getLogger(getClass())

    SpringSecurityService springSecurityService

    @Override
    public void init() {
        domainClass = AppUser.class
    }

    /**
     * Pull airPlane object
     * @return - list of airPlane
     */
    @Override
    public List<AppUser> list() {
        return AppUser.list()
    }

    public AppUser findByName(String name) {
        AppUser appUser = AppUser.findByUsername(name)
        return appUser
    }

    /**
     * create default data
     * this method is called from the BootstrapActionService class
     * @return - true if default data is loaded successfully
     */
    @Override
    public boolean createDefaultData() {
        try {
            new AppUser(username: 'airstaff@caair.com', password: springSecurityService.encodePassword('airstaff'), enabled: true, accountExpired: false, accountLocked: false, passwordExpired: false, gender: "male", imagePath: "http://a.dryicons.com/images/icon_sets/shine_icon_set/png/256x256/user.png").save()
            new AppUser(username: 'admin@caair.com', password: springSecurityService.encodePassword('admin'), enabled: true, accountExpired: false, accountLocked: false, passwordExpired: false, gender: "male", imagePath: "http://a.dryicons.com/images/icon_sets/shine_icon_set/png/256x256/user.png").save()
            return true
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

}
