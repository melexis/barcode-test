package com.melexis.barcode

class LotController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def testsessionService = new TestSessionService()

    def index = {
        redirect(action: "list", params: params)
    }

    def associate = {
        def code = params.code
        def parts = code.split('@')
        def lotname = parts[0]
        def condition = parts[1]

        def result = testsessionService.associateLotAndConditionWithUser(lotname, condition, session.user.username)
        render(view: 'confirm', model: ['lotname': lotname, 'condition': condition])
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [lotInstanceList: Lot.list(params), lotInstanceTotal: Lot.count()]
    }

    def create = {
        def lotInstance = new Lot()
        lotInstance.properties = params
        return [lotInstance: lotInstance]
    }

    def save = {
        def lotInstance = new Lot(params)
        if (lotInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'lot.label', default: 'Lot'), lotInstance.id])}"
            redirect(action: "show", id: lotInstance.id)
        }
        else {
            render(view: "create", model: [lotInstance: lotInstance])
        }
    }

    def show = {
        def lotInstance = Lot.get(params.id)
        if (!lotInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'lot.label', default: 'Lot'), params.id])}"
            redirect(action: "list")
        }
        else {
            [lotInstance: lotInstance]
        }
    }

    def edit = {
        def lotInstance = Lot.get(params.id)
        if (!lotInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'lot.label', default: 'Lot'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [lotInstance: lotInstance]
        }
    }

    def update = {
        def lotInstance = Lot.get(params.id)
        if (lotInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (lotInstance.version > version) {

                    lotInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'lot.label', default: 'Lot')] as Object[], "Another user has updated this Lot while you were editing")
                    render(view: "edit", model: [lotInstance: lotInstance])
                    return
                }
            }
            lotInstance.properties = params
            if (!lotInstance.hasErrors() && lotInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'lot.label', default: 'Lot'), lotInstance.id])}"
                redirect(action: "show", id: lotInstance.id)
            }
            else {
                render(view: "edit", model: [lotInstance: lotInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'lot.label', default: 'Lot'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def lotInstance = Lot.get(params.id)
        if (lotInstance) {
            try {
                lotInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'lot.label', default: 'Lot'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'lot.label', default: 'Lot'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'lot.label', default: 'Lot'), params.id])}"
            redirect(action: "list")
        }
    }
}
