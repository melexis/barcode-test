package com.melexis.barcode

class SshServiceIntegrationTest extends GroovyTestCase {

    def testExecuteCommand() {
        def ssh = new SshService()

        assertEquals "hello world", ssh.executeOnHost("10.32.21.4", "echo hello world").trim()
    }
}
