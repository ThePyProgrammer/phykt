package com.thepyprogrammer.phykt.types

import com.thepyprogrammer.phykt.quantity.Quantity
import com.thepyprogrammer.phykt.unit.W

class Power(override var value: Double): Quantity(value, W) {
    constructor(quantity: Quantity): this(quantity.value)

    infix fun current(resistance: Resistance) = Current((this / resistance).pow(0.5))



    infix fun resistance(current: Current) = Resistance(this / current.pow(0.5))
}