package com.melexis.barcode

import com.jcraft.jsch.*

class SshService {

    static transactional = true

    def timeout = 5000l
    def retry_interval = 500l
    def thread = null

    def executeOnHost(String address, String command) {
        def session = openSession(address)
        executeCommand(session, command)
    }

    def executeCommand(Session session, String command) {
        def out = new ByteArrayOutputStream()
        def channel = session.openChannel("exec")
        channel.command = command
        channel.outputStream = out
        channel.extOutputStream = out

        channel.connect()
        // wait till finished
        thread = Thread.start {
            while (!channel.isClosed()) {
                if (thread == null) {
                    return
                }

                sleep(retry_interval)
            }
        }

        thread.join(timeout)

        if (thread.isAlive()) {
            thread = null
        }

        out.toString()
    }

    def openSession(String hostname) {
        String username = System.getProperty("user.name")
        JSch jsch = new JSch()
        jsch.setKnownHosts("/home/${username}/.ssh/known_hosts")
        jsch.addIdentity("/home/${username}/.ssh/id_rsa")

        Session session = jsch.getSession(username, hostname, 22)
        session.connect()
        session
    }
}
