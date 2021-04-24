package com.thepyprogrammer.phykt.types

import com.thepyprogrammer.phykt.quantity.Quantity
import com.thepyprogrammer.phykt.unit.A

class Current(override var value: Double): Quantity(value, A) {
    constructor(quantity: Quantity): this(quantity.value)
}