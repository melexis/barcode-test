package com.melexis.barcode

import grails.test.*

class TestSessionServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAssociateLotnameAndConditionWithUser() {
        String lotname = 'A12345'
        String condition = '25'
        String username = 'brh'

        def testSessionService = new TestSessionService()

        testSessionService.associateLotAndConditionWithUser(lotname, condition, username)
        def session = testSessionService.sessions[username]

        assertEquals lotname, session.lot
        assertEquals condition, session.condition
    }

    void testStartSession() {
        def lots = [
            new Lot(name: 'A12345',
                    device: '12106',
                    conditions: [new Condition(name: '25',
                                              condProperties: [new CondProperty(key: 'cmd',
                                                                                value: './25')])])
        ]

        def testcells = [
            new TestCell(name: "Rasco45",
                         testers: [
                             new Tester(name: "test0123",
                                        address: "test0123.sensors.elex.be",
                                        site: "ieper"),
                             new Tester(name: "test0456",
                                        address: "test0456.sensors.elex.be",
                                        site: "ieper"),
                             new Tester(name: "test0789",
                                        address: "test0789.sensors.elex.be")
                         ])
        ]


        // mocks
        mockDomain(Lot, lots)
        mockDomain(TestCell, testcells)

        def sshservice = mockFor(SshService)
        sshservice.demand.executeOnHost(3) {command, host -> "ok"}

        def ts = new TestSessionService()
        ts.sshservice = sshservice.createMock()

        ts.associateLotAndConditionWithUser("A12345", "25", "brh")
        ts.associateTestcellWithUser("Rasco45", "brh")
        ts.executeSessionForUser("brh")
    }
}
