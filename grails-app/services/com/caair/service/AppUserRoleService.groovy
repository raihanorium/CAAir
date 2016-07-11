package com.caair.service

import com.caair.BaseDomainService
import com.caair.entity.AppUser
import com.caair.entity.AppUserRole
import com.caair.entity.Role
import grails.transaction.Transactional
import org.apache.log4j.Logger

@Transactional
class AppUserRoleService extends BaseDomainService {

    private Logger log = Logger.getLogger(getClass())

    AppUserService appUserService
    RoleService roleService

    @Override
    public void init() {
        domainClass = AppUserRole.class
    }

    /**
     * Pull appUserRole object
     * @return - list of appUserRole
     */
    @Override
    public List<AppUserRole> list() {
        return AppUserRole.list()
    }

    public AppUserRole findByAppUser(long userId) {
        AppUserRole appUserRole = AppUserRole.findByUserId(userId, [readOnly: true])
        return appUserRole
    }

    /**
     * get assigned roleList of a specific AppUser
     * @param userId -AppUser.id
     * @return -list of role
     */
    public List<Role> getRolesByUser(long userId) {
        List<Long> lstRoleIds = AppUserRole.findAllByUserId(userId, [readOnly: true]).collect { it.roleId }
        List<Role> lstRole = roleService.findAllByIdInList(lstRoleIds)
        return lstRole
    }

    /**
     * create default data
     * this method is called from the BootstrapActionService class
     * @return - true if default data is loaded successfully
     */
    @Override
    boolean createDefaultData() {
        try {
            AppUser userOne = appUserService.findByName('admin@caair.com')
            AppUser userTwo = appUserService.findByName('airstaff@caair.com')
            Role roleAdmin = roleService.findByAuthority('ROLE_ADMIN')
            Role roleStuff = roleService.findByAuthority('ROLE_AIRLINE_STUFF')

            new AppUserRole(userId: userOne.id, roleId: roleAdmin.id).save()
            new AppUserRole(userId: userTwo.id, roleId: roleStuff.id).save()
            return true
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }


}
