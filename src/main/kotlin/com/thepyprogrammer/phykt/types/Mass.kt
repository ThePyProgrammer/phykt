package com.thepyprogrammer.phykt.types

import com.thepyprogrammer.phykt.quantity.Quantity
import com.thepyprogrammer.phykt.unit.kg

class Mass(override var value: Double): Quantity(value, kg) {
    constructor(quantity: Quantity): this(quantity.value)
}