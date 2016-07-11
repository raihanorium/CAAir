grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.7
grails.project.source.level = 1.7
//grails.project.war.file = "target/${appName}-${appVersion}.war"


grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.

        runtime 'javax.mail:javax.mail-api:1.5.1'
        runtime 'com.sun.mail:javax.mail:1.5.1'
    }

    plugins {
//        To configure Tomcat servlet container to run this application in both development and production environment
        build ':tomcat:7.0.52.1'

//        Provides integration between Grails and Hibernate 3 through GORM
        runtime ':hibernate:3.6.10.15'

//        Database Migration Plugin, which help manage database changes while developing Grails applications.
        runtime ":database-migration:1.4.0"

//        This plugin provides the Resources framework for Grails
        runtime ':resources:1.2.8'

//        Spring Security Core plugin
        compile "org.grails.plugins:spring-security-core:2.0.0"

        // Provides oAuth integration for Grails, using the Scribe framework. in this project wwe use this
        // plugin to integrate with google+
        compile ":oauth:2.6.1"


//         @todo quartz:1.0.2 plugin will be used to send notification to customer before 48 hours of flight departure.
//        compile ":quartz:1.0.2"


        /**
         * @todo LDAP authentication support for the Spring Security plugin.
         *
         * spring-security-ldap:2.0.1 is commented because of conflicting with spring-security-core:2.0.0.
         * after successful integration with  active directory, simply uncomment spring-security-ldap:2.0.1
         * And comment spring-security-core:2.0.0 added above
         */
//        compile "org.grails.plugins:spring-security-ldap:2.0.1"

    }
}
