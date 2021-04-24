package com.thepyprogrammer.phykt.types

import com.thepyprogrammer.phykt.quantity.Quantity
import com.thepyprogrammer.phykt.quantity.VectorQuantity
import com.thepyprogrammer.phykt.unit.m
import com.thepyprogrammer.phykt.unit.s

class Acceleration(
    override var x: Double = 0.0,
    override var y: Double = 0.0,
    override var z: Double = 0.0
): VectorQuantity(x, y, z, m/s.pow(2)) {
    constructor(quantity: Quantity): this(quantity.value)
    constructor(quantity: VectorQuantity): this(quantity.x, quantity.y, quantity.z)

}