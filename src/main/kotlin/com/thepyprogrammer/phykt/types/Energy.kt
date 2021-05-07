package com.thepyprogrammer.phykt.types

import com.thepyprogrammer.phykt.quantity.Quantity
import com.thepyprogrammer.phykt.unit.kg
import com.thepyprogrammer.phykt.unit.m
import com.thepyprogrammer.phykt.unit.s

class Energy(override var value: Double): Quantity(value, kg*m.pow(2)/ s.pow(2)) {
    constructor(quantity: Quantity): this(quantity.value)

    override fun dot(other: Quantity): Quantity {
        TODO("Not yet implemented")
    }

    override fun cross(other: Quantity): Quantity {
        TODO("Not yet implemented")
    }
}