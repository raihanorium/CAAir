package com.caair.action.appuser

import com.caair.ActionServiceIntf
import com.caair.BaseService
import com.caair.entity.AppUser
import com.caair.entity.AppUserRole
import com.caair.entity.OAuthID
import com.caair.oauth.SpringSecuritySigninService
import com.caair.service.AppUserRoleService
import com.caair.service.AppUserService
import com.caair.service.OauthIdService
import com.caair.service.RoleService
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService
import org.apache.log4j.Logger
import org.springframework.transaction.annotation.Transactional

class CreateAppUserActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())


    private static final String APP_USER = "appuser"
    private static final String OAUTHID = "oauthid"
    private static final String APP_USER_ROLE = "appuserrle"
    private static final String APP_USER_SAVE_SUCCESS_MESSAGE = "User information has been saved successfully"
    private static final String APP_USER_INVALID_NAME = "User name already exists"

    SpringSecurityService springSecurityService
    AppUserService appUserService
    OauthIdService oauthIdService
    AppUserRoleService appUserRoleService
    def oauthService
    SpringSecuritySigninService springSecuritySigninService
    RoleService roleService
    /**
     * 1. check Validation
     * 2. build customer object
     *
     * @param params - serialized parameters from UI
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional(readOnly = true)
    public Map executePreCondition(Map params) {
        try {
            String url = "https://www.googleapis.com/oauth2/v1/userinfo"
            def googleResource = oauthService.getGoogleResource(params.googleAccessToken, url)

            def googleResponse = JSON.parse(googleResource?.getBody())
            params.put("img", googleResponse?.picture)
            OAuthID oAuthID = oauthIdService.findByAccessToken(googleResponse.id)
            AppUser appUser;

            if (!oAuthID) {
                appUser = new AppUser();
                appUser.username = googleResponse.name;
                appUser.password = "abc123"
                appUser.enabled = true
                appUser.accountExpired = false
                appUser.accountLocked = false
                appUser.passwordExpired = false
                appUser.gender = googleResponse.gender
                appUser.imagePath = googleResponse.picture
                params.put(APP_USER, appUser)

                OAuthID oAuthID1 = new OAuthID()
                oAuthID1.accessToken = googleResponse.id
                oAuthID1.userId = appUser.id
                oAuthID1.provider = "google"
                params.put(OAUTHID, oAuthID1)

                AppUserRole appUserRole = new AppUserRole()
                appUserRole.userId = appUser.id
                appUserRole.roleId = roleService.findByAuthority("ROLE_CUSTOMER").id
                params.put(APP_USER_ROLE, appUserRole)
            } else {
                appUser = (AppUser) appUserService.read(oAuthID.userId)
                params.put(APP_USER, appUser)
            }
            params.put(APP_USER, appUser)
            return params
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    /**
     * 1. receive customer object from executePreCondition method
     * 2. create new customer
     * This method is in transactional block and will roll back in case of any exception
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional
    public Map execute(Map result) {
        try {

            AppUser appUser = (AppUser) result.get(APP_USER)
            OAuthID oAuthID = (OAuthID) result.get(OAUTHID)
            AppUserRole appUserRole = (AppUserRole) result.get(APP_USER_ROLE)
            // save new customer object in DB
            if (!appUser.id) {
                appUserService.create(appUser)
                oAuthID.userId = appUser.id
                appUserRole.userId = appUser.id
                oauthIdService.create(oAuthID)
                appUserRoleService.create(appUserRole)
                springSecurityService.clearCachedRequestmaps()
                result.put(APP_USER, appUser)
            }
            return result
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    /**
     * There is no postCondition, so return the same map as received
     *
     * @param result - resulting map from execute
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map executePostCondition(Map result) {
        if (!springSecurityService.loggedIn) {
            AppUser appUser = (AppUser) result.get(APP_USER)
            springSecuritySigninService.signIn(appUser)
        }
        return result
    }

    /**
     * 1. put success message
     *
     * @param result -map from execute/executePost method
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildSuccessResultForUI(Map result) {
        return super.setSuccess(result, APP_USER_SAVE_SUCCESS_MESSAGE)
    }

    /**
     * The input-parameter Map must have "isError:true" with corresponding message
     *
     * @param result -map returned from previous methods
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildFailureResultForUI(Map result) {
        return result
    }

    /**
     * Build customer object
     *
     * @param params - serialize parameters from UI
     * @param user - an object of AppUser
     * @return - customer object
     */
    private AppUser getAppUser(Map params) {
        AppUser appUser = new AppUser(params)
        return appUser
    }

    /**
     * 1. check for duplicate customer name
     * 2. check for duplicate customer code
     *
     * @param customer -object of Customer
     * @param user - an object of AppUser
     * @return -a string containing null value or error message depending on duplicate check
     */
    private String checkValidation(Map params) {
        if (!params.name || !params.totalSeat) {
            return ERROR_FOR_INVALID_INPUT
        }
        String name = params.name.toString()
        AppUser appUser = findByName(name);
        //write your validation message here
        if (appUser != null) {
            return APP_USER_INVALID_NAME
        }


        return null
    }


    private AppUser findByName(String name) {
        AppUser appUser = appUserService.findByName(name);
        return appUser
    }

}

