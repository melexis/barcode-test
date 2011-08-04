package com.melexis.barcode

class CondPropertyController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [condPropertyInstanceList: CondProperty.list(params), condPropertyInstanceTotal: CondProperty.count()]
    }

    def create = {
        def condPropertyInstance = new CondProperty()
        condPropertyInstance.properties = params
        return [condPropertyInstance: condPropertyInstance]
    }

    def save = {
        def condPropertyInstance = new CondProperty(params)
        if (condPropertyInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'condProperty.label', default: 'CondProperty'), condPropertyInstance.id])}"
            redirect(action: "show", id: condPropertyInstance.id)
        }
        else {
            render(view: "create", model: [condPropertyInstance: condPropertyInstance])
        }
    }

    def show = {
        def condPropertyInstance = CondProperty.get(params.id)
        if (!condPropertyInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'condProperty.label', default: 'CondProperty'), params.id])}"
            redirect(action: "list")
        }
        else {
            [condPropertyInstance: condPropertyInstance]
        }
    }

    def edit = {
        def condPropertyInstance = CondProperty.get(params.id)
        if (!condPropertyInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'condProperty.label', default: 'CondProperty'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [condPropertyInstance: condPropertyInstance]
        }
    }

    def update = {
        def condPropertyInstance = CondProperty.get(params.id)
        if (condPropertyInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (condPropertyInstance.version > version) {
                    
                    condPropertyInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'condProperty.label', default: 'CondProperty')] as Object[], "Another user has updated this CondProperty while you were editing")
                    render(view: "edit", model: [condPropertyInstance: condPropertyInstance])
                    return
                }
            }
            condPropertyInstance.properties = params
            if (!condPropertyInstance.hasErrors() && condPropertyInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'condProperty.label', default: 'CondProperty'), condPropertyInstance.id])}"
                redirect(action: "show", id: condPropertyInstance.id)
            }
            else {
                render(view: "edit", model: [condPropertyInstance: condPropertyInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'condProperty.label', default: 'CondProperty'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def condPropertyInstance = CondProperty.get(params.id)
        if (condPropertyInstance) {
            try {
                condPropertyInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'condProperty.label', default: 'CondProperty'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'condProperty.label', default: 'CondProperty'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'condProperty.label', default: 'CondProperty'), params.id])}"
            redirect(action: "list")
        }
    }
}
