package com.thepyprogrammer.phykt.types

import com.thepyprogrammer.phykt.quantity.Quantity
import com.thepyprogrammer.phykt.unit.kg
import com.thepyprogrammer.phykt.unit.s

class Time(override var value: Double): Quantity(value, s) {
    constructor(quantity: Quantity): this(quantity.value)
}