modules = {
    application {
        resource url: 'js/application.js'
    }

    jquery11 {
        resource url: 'js/jquery-1.12.3.js', disposition: 'head'
    }

    style {
        dependsOn 'jquery11'
        resource url: 'css/bootstrap.css'
        resource url: 'css/styles.css'
        resource url: 'js/bootstrap.min.js'
        resource url:'js/bootstrap-datetimepicker.min.js'
        resource url:'css/bootstrap-datetimepicker.min.css'
    }

    fontAwesome {
        resource url: 'css/font-awesome.min.css'
    }
}