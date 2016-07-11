package integration.caair

import com.caair.action.bootstrap.BootstrapActionService

/**
 * This class has a init method which is called by Bootstrap.groovy
 */
class BootstrapService {

    BootstrapActionService bootstrapActionService

    public boolean init() {
        Map params = new LinkedHashMap()
        // execute pre condition
        Map preResult = bootstrapActionService.executePreCondition(params)

        // execute the action
        Map executeResult = bootstrapActionService.execute(preResult)

        // execute the post actions
        Map postResult = bootstrapActionService.executePostCondition(executeResult)
        return true
    }
}
