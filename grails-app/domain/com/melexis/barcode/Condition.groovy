package com.melexis.barcode

class Condition {

    String name

    static belongsTo = [lot: Lot]
    static hasMany = [condProperties: CondProperty]

    static constraints = {
    }

    public String toString() {
        name
    }
}
