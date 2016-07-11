class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.${format})?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'oauthCallBack', action: 'index')

        "404"(view: '/error404')
        "500"(view: '/error500')
    }
}
