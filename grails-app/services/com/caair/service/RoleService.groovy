package com.caair.service

import com.caair.BaseDomainService
import com.caair.entity.Role
import grails.transaction.Transactional

@Transactional
class RoleService extends BaseDomainService {

    @Override
    public void init() {
        domainClass = Role.class
    }

    /**
     * Pull appUserRole object
     * @return - list of appUserRole
     */
    @Override
    public List<Role> list() {
        return Role.list()
    }

    public Role findByAuthority(String authority) {
        Role role = Role.findByAuthority(authority)
        return role
    }

    public List<Role> findAllByIdInList(List<Long> lstRoleIds) {
        return Role.findAllByIdInList(lstRoleIds, [readOnly: true])
    }

    /**
     * create default data
     * this method is called from the BootstrapActionService class
     * @return - true if default data is loaded successfully
     */
    @Override
    boolean createDefaultData() {
        try {
            new Role(name: "Admin", authority: 'ROLE_ADMIN').save()
            new Role(name: "Customer", authority: 'ROLE_CUSTOMER').save()
            new Role(name: "Air staff", authority: 'ROLE_AIRLINE_STUFF').save()
            return true
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }


}
