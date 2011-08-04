package com.melexis.barcode

class Tester {

    String name
    String address
    String site

    static belongsTo = [testCell: TestCell]
    static hasMany = [testerProperties: TesterProperty]

    static constraints = {
    }
}
