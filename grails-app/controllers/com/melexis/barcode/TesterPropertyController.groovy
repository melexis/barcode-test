package com.melexis.barcode

class TesterPropertyController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [testerPropertyInstanceList: TesterProperty.list(params), testerPropertyInstanceTotal: TesterProperty.count()]
    }

    def create = {
        def testerPropertyInstance = new TesterProperty()
        testerPropertyInstance.properties = params
        return [testerPropertyInstance: testerPropertyInstance]
    }

    def save = {
        def testerPropertyInstance = new TesterProperty(params)
        if (testerPropertyInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'testerProperty.label', default: 'TesterProperty'), testerPropertyInstance.id])}"
            redirect(action: "show", id: testerPropertyInstance.id)
        }
        else {
            render(view: "create", model: [testerPropertyInstance: testerPropertyInstance])
        }
    }

    def show = {
        def testerPropertyInstance = TesterProperty.get(params.id)
        if (!testerPropertyInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'testerProperty.label', default: 'TesterProperty'), params.id])}"
            redirect(action: "list")
        }
        else {
            [testerPropertyInstance: testerPropertyInstance]
        }
    }

    def edit = {
        def testerPropertyInstance = TesterProperty.get(params.id)
        if (!testerPropertyInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'testerProperty.label', default: 'TesterProperty'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [testerPropertyInstance: testerPropertyInstance]
        }
    }

    def update = {
        def testerPropertyInstance = TesterProperty.get(params.id)
        if (testerPropertyInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (testerPropertyInstance.version > version) {
                    
                    testerPropertyInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'testerProperty.label', default: 'TesterProperty')] as Object[], "Another user has updated this TesterProperty while you were editing")
                    render(view: "edit", model: [testerPropertyInstance: testerPropertyInstance])
                    return
                }
            }
            testerPropertyInstance.properties = params
            if (!testerPropertyInstance.hasErrors() && testerPropertyInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'testerProperty.label', default: 'TesterProperty'), testerPropertyInstance.id])}"
                redirect(action: "show", id: testerPropertyInstance.id)
            }
            else {
                render(view: "edit", model: [testerPropertyInstance: testerPropertyInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'testerProperty.label', default: 'TesterProperty'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def testerPropertyInstance = TesterProperty.get(params.id)
        if (testerPropertyInstance) {
            try {
                testerPropertyInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'testerProperty.label', default: 'TesterProperty'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'testerProperty.label', default: 'TesterProperty'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'testerProperty.label', default: 'TesterProperty'), params.id])}"
            redirect(action: "list")
        }
    }
}
