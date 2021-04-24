package com.thepyprogrammer.phykt.quantity

import com.thepyprogrammer.phykt.unit.Unit

class ScalarQuantity(override var value: Double, override var unit: Unit): Quantity(value, unit) {
    override infix fun dot(other: Quantity) = quantityOf(value * other.value, unit * other.unit)

    override infix fun cross(other: Quantity) = quantityOf(value * other.value, unit * other.unit)
}