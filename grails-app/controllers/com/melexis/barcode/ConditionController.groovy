package com.melexis.barcode

class ConditionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [conditionInstanceList: Condition.list(params), conditionInstanceTotal: Condition.count()]
    }

    def create = {
        def conditionInstance = new Condition()
        conditionInstance.properties = params
        return [conditionInstance: conditionInstance]
    }

    def save = {
        def conditionInstance = new Condition(params)
        if (conditionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'condition.label', default: 'Condition'), conditionInstance.id])}"
            redirect(action: "show", id: conditionInstance.id)
        }
        else {
            render(view: "create", model: [conditionInstance: conditionInstance])
        }
    }

    def show = {
        def conditionInstance = Condition.get(params.id)
        if (!conditionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'condition.label', default: 'Condition'), params.id])}"
            redirect(action: "list")
        }
        else {
            [conditionInstance: conditionInstance]
        }
    }

    def edit = {
        def conditionInstance = Condition.get(params.id)
        if (!conditionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'condition.label', default: 'Condition'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [conditionInstance: conditionInstance]
        }
    }

    def update = {
        def conditionInstance = Condition.get(params.id)
        if (conditionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (conditionInstance.version > version) {
                    
                    conditionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'condition.label', default: 'Condition')] as Object[], "Another user has updated this Condition while you were editing")
                    render(view: "edit", model: [conditionInstance: conditionInstance])
                    return
                }
            }
            conditionInstance.properties = params
            if (!conditionInstance.hasErrors() && conditionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'condition.label', default: 'Condition'), conditionInstance.id])}"
                redirect(action: "show", id: conditionInstance.id)
            }
            else {
                render(view: "edit", model: [conditionInstance: conditionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'condition.label', default: 'Condition'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def conditionInstance = Condition.get(params.id)
        if (conditionInstance) {
            try {
                conditionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'condition.label', default: 'Condition'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'condition.label', default: 'Condition'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'condition.label', default: 'Condition'), params.id])}"
            redirect(action: "list")
        }
    }
}
