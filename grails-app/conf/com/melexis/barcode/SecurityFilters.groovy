package com.melexis.barcode

class SecurityFilters {
    def filters = {
        loginCheck(controller:'*', action:'*') {
            before = {
                if(!session.user && actionName != "login") {
                    session.controller = controllerName
                    session.action = actionName

                    redirect(controller:"user",action:"login")
                    return false
                }
            }
       }
    }
}
