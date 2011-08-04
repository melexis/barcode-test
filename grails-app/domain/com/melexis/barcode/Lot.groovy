package com.melexis.barcode

class Lot {

    String name
    String device

    static hasMany = [conditions: Condition]

    static constraints = {
        hasMany

    }

    public String toString() {
        "${name}"
    }
}
