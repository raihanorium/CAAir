import integration.caair.BootstrapService

class BootStrap {

    BootstrapService bootstrapService

    def init = { servletContext ->

//      Load initial data needed to run this application
        bootstrapService.init()

    }
    def destroy = {
//      @todo add your logic here while shutting down this application
    }

}