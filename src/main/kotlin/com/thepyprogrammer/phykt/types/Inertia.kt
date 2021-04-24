package com.thepyprogrammer.phykt.types

import com.thepyprogrammer.phykt.quantity.Quantity
import com.thepyprogrammer.phykt.unit.kg
import com.thepyprogrammer.phykt.unit.m

class Inertia(override var value: Double): Quantity(value, kg* m.pow(2)) {
    constructor(quantity: Quantity): this(quantity.value)
}