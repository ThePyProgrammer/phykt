package com.thepyprogrammer.phykt.types

import com.thepyprogrammer.phykt.quantity.Quantity
import com.thepyprogrammer.phykt.unit.kg
import com.thepyprogrammer.phykt.unit.m
import com.thepyprogrammer.phykt.unit.s

class Force(override var value: Double): Quantity(value, kg * m/ s.pow(2)) {
    constructor(quantity: Quantity): this(quantity.value)
}