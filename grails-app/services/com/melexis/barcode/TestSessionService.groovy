package com.melexis.barcode

class TestSessionService {

    static transactional = true
    static sessions = [:]

    def sshservice = new SshService()

    def associateLotAndConditionWithUser(String lot, String condition, String user) {
        sessions[user] = new TestSession(lot: lot,
                                     condition: condition)
    }

    def associateTestcellWithUser(String testcell, String user) {
        def s = sessions[user]
        s.testcell = testcell
        sessions[user] = s
    }

    def executeSessionForUser(String user) {
        def session = sessions[user]

        Lot lot = Lot.findByName(session.lot)
        TestCell testcell = TestCell.findByName(session.testcell)

        def addresses = testcell?.testers*.address
        def condition = lot?.conditions.find {cond -> cond.name == session.condition}
        def command = condition?.condProperties.find {prop -> prop.key == 'cmd'}?.value

        addresses?.each {
            address -> sshservice.executeOnHost(address, command, 'shiftl')
        }
    }

    def testsessionForUser(String user) {
        sessions[user]
    }

    def clearSessionForUser(String user) {
        sessions.remove(user)
        sessions
    }
}

class TestSession implements Serializable {
    String lot
    String condition
    String testcell

    public String toString() {
        "Testsession lot '${lot} condition '${condition}' on testcell '${testcell}'"
    }
}
