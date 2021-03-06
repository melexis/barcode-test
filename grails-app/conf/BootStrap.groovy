import com.melexis.barcode.*

class BootStrap {

    def init = {
        if (!User.count()) {
            new User(username: 'brh',
                     password: 'brhbrh').save(failOnError: true)
        }

        if (!Lot.count()) {
            new Lot(name: "A12345", device: "12106").save(failOnError: true)
            new Lot(name: "T54321", device: "16201").save(failOnError: true)
            new Lot(name: "T54321X1", device: "16201").save(failOnError: true)
            new Lot(name: "T54321X2", device: "16201").save(failOnError: true)
            new Lot(name: "T12345", device: "90316").save(failOnError: true)
            new Lot(name: 'Z_SVR', device: '90360ABBAS').save(failOnError: true)
        }


        if (!Condition.count()) {
            new Condition(name: "25",
                          lot: Lot.findByName("A12345"),
                          condProperties: [
                              new CondProperty(key: "cmd", value: "./25").save(failOnError: true)
                          ]).save(failOnError: true)
            new Condition(name: "125",
                          lot: Lot.findByName("A12345"),
                          condProperties: [
                              new CondProperty(key: "cmd", value: "./125").save(failOnError: true),
                              new CondProperty(key: "spec", value: "spec 1").save(failOnError: true)
                          ]).save(failOnError: true)
            new Condition(name: "25",
                          lot: Lot.findByName("T54321"),
                          condProperties: [
                              new CondProperty(key: "cmd", value: "./25").save(failOnError: true)
                          ]).save(failOnError: true)
            new Condition(name: "125",
                          lot: Lot.findByName("T54321X1"),
                          condProperties: [
                              new CondProperty(key: "cmd", value: "./125").save(failOnError: true)
                          ]).save(failOnError: true)
            new Condition(name: "125",
                          lot: Lot.findByName("T54321X2"),
                          condProperties: [
                              new CondProperty(key: "cmd", value: "./125").save(failOnError: true)
                          ]).save(failOnError: true)
            new Condition(name: 'test',
                          lot: Lot.findByName('T12345'),
                          condProperties: [
                              new CondProperty(key: 'cmd', value: 'xeyes&').save(failOnError:true)
                          ]).save(failOnError: true)

            new Condition(name: 'FT125',
                          lot: Lot.findByName('Z_SVR'),
                          condProperties: [
                              new CondProperty(key: 'cmd', value: "sh -c 'DISPLAY=127.0.0.1:0.0 sh /tmp/test.sh &'").save(failOnError:true)
                          ]).save(failOnError: true)
        }

        if (!TestCell.count()) {
            new TestCell(name: 'rasco1').addToTesters(name: 'rasco test', address: '10.32.20.109', site: 'ieper').save()
        }


    }
    def destroy = {
    }
}

