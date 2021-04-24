package com.thepyprogrammer.phykt.types

import com.thepyprogrammer.phykt.quantity.Quantity
import com.thepyprogrammer.phykt.unit.m

class Distance(override var value: Double): Quantity(value, m) {
    constructor(quantity: Quantity): this(quantity.value)
}