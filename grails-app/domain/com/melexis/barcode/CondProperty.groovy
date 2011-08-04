package com.melexis.barcode

class CondProperty {

    String key
    String value

    //static belongsTo = [condition: Condition]

    static constraints = {
    }

    public String toString() {
        "${key}: ${value}"
    }
}
