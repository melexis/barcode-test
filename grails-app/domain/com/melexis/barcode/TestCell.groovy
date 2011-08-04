package com.melexis.barcode

class TestCell {

    String name

    static hasMany = [testers: Tester]

    static constraints = {
    }

    public String toString() {
        name
    }
}
