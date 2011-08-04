package com.melexis.barcode

class TestCellController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def testsessionService = new TestSessionService()

    def index = {
        redirect(action: "list", params: params)
    }

    def associate = {
        def testcell = params.code

        if (testsessionService.testsessionForUser(session.user.username) == null) {
            render(view: 'associate_lot')
        } else {
            def result = testsessionService.associateTestcellWithUser(testcell, session.user.username)
            def testsession = testsessionService.testsessionForUser(session.user.username)
            render(view: 'confirm', model: ['testsession': testsession])
        }
    }

    def cancel = {
        testsessionService.clearSessionForUser(session.user.username)
        render(view: 'cleared')
    }

    def execute = {
        def testers = testsessionService.executeSessionForUser(session.user.username)
        render(view: 'executed', model: ['testers': testers])
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [testCellInstanceList: TestCell.list(params), testCellInstanceTotal: TestCell.count()]
    }

    def create = {
        def testCellInstance = new TestCell()
        testCellInstance.properties = params
        return [testCellInstance: testCellInstance]
    }

    def save = {
        def testCellInstance = new TestCell(params)
        if (testCellInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'testCell.label', default: 'TestCell'), testCellInstance.id])}"
            redirect(action: "show", id: testCellInstance.id)
        }
        else {
            render(view: "create", model: [testCellInstance: testCellInstance])
        }
    }

    def show = {
        def testCellInstance = TestCell.get(params.id)
        if (!testCellInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'testCell.label', default: 'TestCell'), params.id])}"
            redirect(action: "list")
        }
        else {
            [testCellInstance: testCellInstance]
        }
    }

    def edit = {
        def testCellInstance = TestCell.get(params.id)
        if (!testCellInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'testCell.label', default: 'TestCell'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [testCellInstance: testCellInstance]
        }
    }

    def update = {
        def testCellInstance = TestCell.get(params.id)
        if (testCellInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (testCellInstance.version > version) {

                    testCellInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'testCell.label', default: 'TestCell')] as Object[], "Another user has updated this TestCell while you were editing")
                    render(view: "edit", model: [testCellInstance: testCellInstance])
                    return
                }
            }
            testCellInstance.properties = params
            if (!testCellInstance.hasErrors() && testCellInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'testCell.label', default: 'TestCell'), testCellInstance.id])}"
                redirect(action: "show", id: testCellInstance.id)
            }
            else {
                render(view: "edit", model: [testCellInstance: testCellInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'testCell.label', default: 'TestCell'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def testCellInstance = TestCell.get(params.id)
        if (testCellInstance) {
            try {
                testCellInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'testCell.label', default: 'TestCell'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'testCell.label', default: 'TestCell'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'testCell.label', default: 'TestCell'), params.id])}"
            redirect(action: "list")
        }
    }
}
