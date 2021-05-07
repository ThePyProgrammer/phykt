package com.thepyprogrammer.phykt.types

import com.thepyprogrammer.phykt.quantity.Quantity
import com.thepyprogrammer.phykt.unit.ohm

class Resistance(override var value: Double): Quantity(value, ohm) {
    constructor(quantity: Quantity): this(quantity.value)

    infix fun current(resistance: Power) = Current(this * resistance)
    override fun dot(other: Quantity): Quantity {
        TODO("Not yet implemented")
    }

    override fun cross(other: Quantity): Quantity {
        TODO("Not yet implemented")
    }

}