package com.caair.action.login

import com.caair.ActionServiceIntf
import com.caair.BaseService
import com.caair.entity.AppUser
import com.caair.entity.Role
import com.caair.service.AppUserRoleService
import com.caair.service.AppUserService
import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional
import org.apache.log4j.Logger

import javax.servlet.http.HttpSession

/**
 * Login success event
 * For details please go through use case named 'LoginSuccessActionService'
 */
class LoginSuccessActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    private static final String APP_USER = 'appUser'
    private static final String APP_USER_NOT_FOUND = 'User not found'
    private static final String POST_URL = 'postUrl'
    private static final String SESSION = "session"

    SpringSecurityService springSecurityService
    AppUserService appUserService
    AppUserRoleService appUserRoleService

    /**
     * 1. get user object
     * 2. check if user exists
     * This method is in transactional readOnly block and will roll back in case of any exception
     *
     * @param params - serialized parameters from UI
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Override
    @Transactional(readOnly = true)
    Map executePreCondition(Map parameters) {
        try {
            def userDetails = springSecurityService.principal
            AppUser appUser = (AppUser) appUserService.read(userDetails.id)
            if (!appUser) {
                return super.setError(parameters, APP_USER_NOT_FOUND)
            }
            parameters.put(APP_USER, appUser)
            return parameters
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException()
        }
    }

    /**
     * 1. receive appUser object from executePreCondition method
     * 2. get url depending on role
     * This method is in transactional readOnly block and will roll back in case of any exception
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Override
    @Transactional(readOnly = true)
    Map execute(Map previousResult) {
        try {
            HttpSession session = (HttpSession) previousResult.get(SESSION)
            AppUser appUser = (AppUser) previousResult.get(APP_USER)
            String postUrl = getUrl(previousResult)
            previousResult.put(POST_URL, postUrl)
            session.appUser = appUser
            return previousResult
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException()
        }
    }

    /**
     * There is no postCondition, so return the same map as received
     *
     * @param result - resulting map from execute
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Override
    Map executePostCondition(Map previousResult) {
        return previousResult
    }

    /**
     * 1. put success message if required
     * @param result -map from execute/executePost method
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Override
    Map buildSuccessResultForUI(Map executeResult) {
        return executeResult
    }

    /**
     * The input-parameter Map must have "isError:true" with corresponding message
     *
     * @param result -map returned from previous methods
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Override
    Map buildFailureResultForUI(Map executeResult) {
        return executeResult
    }

    /**
     * get url depending on role
     * @param params - map from caller method
     * @return
     */
    private String getUrl(Map params) {
        String url
        AppUser appUser = (AppUser) params.get(APP_USER)
        List<Role> lstRoles = appUserRoleService.getRolesByUser(appUser.id)
        if (hasRole("ROLE_ADMIN", lstRoles)) {
            url = "/airPlane/show"
        } else if (hasRole("ROLE_AIRLINE_STUFF", lstRoles)) {
            url = "/flight/show"
        } else {
            url = "/login/auth"
        }
        return url
    }

    /**
     * check for role
     * @param authority
     * @param lstRoles
     * @return
     */
    private boolean hasRole(String authority, List<Role> lstRoles) {
        if (!lstRoles) return false
        for (int i = 0; i < lstRoles.size(); i++) {
            Role role = (Role) lstRoles[i]
            if (role.authority.equals(authority)) {
                return true
            }
        }
        return false
    }
}
