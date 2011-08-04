package com.melexis.barcode

class TesterController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [testerInstanceList: Tester.list(params), testerInstanceTotal: Tester.count()]
    }

    def create = {
        def testerInstance = new Tester()
        testerInstance.properties = params
        return [testerInstance: testerInstance]
    }

    def save = {
        def testerInstance = new Tester(params)
        if (testerInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'tester.label', default: 'Tester'), testerInstance.id])}"
            redirect(action: "show", id: testerInstance.id)
        }
        else {
            render(view: "create", model: [testerInstance: testerInstance])
        }
    }

    def show = {
        def testerInstance = Tester.get(params.id)
        if (!testerInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tester.label', default: 'Tester'), params.id])}"
            redirect(action: "list")
        }
        else {
            [testerInstance: testerInstance]
        }
    }

    def edit = {
        def testerInstance = Tester.get(params.id)
        if (!testerInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tester.label', default: 'Tester'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [testerInstance: testerInstance]
        }
    }

    def update = {
        def testerInstance = Tester.get(params.id)
        if (testerInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (testerInstance.version > version) {
                    
                    testerInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tester.label', default: 'Tester')] as Object[], "Another user has updated this Tester while you were editing")
                    render(view: "edit", model: [testerInstance: testerInstance])
                    return
                }
            }
            testerInstance.properties = params
            if (!testerInstance.hasErrors() && testerInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tester.label', default: 'Tester'), testerInstance.id])}"
                redirect(action: "show", id: testerInstance.id)
            }
            else {
                render(view: "edit", model: [testerInstance: testerInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tester.label', default: 'Tester'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def testerInstance = Tester.get(params.id)
        if (testerInstance) {
            try {
                testerInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tester.label', default: 'Tester'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tester.label', default: 'Tester'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tester.label', default: 'Tester'), params.id])}"
            redirect(action: "list")
        }
    }
}
