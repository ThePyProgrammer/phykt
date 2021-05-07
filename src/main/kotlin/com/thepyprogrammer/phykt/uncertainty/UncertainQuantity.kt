package com.thepyprogrammer.phykt.uncertainty

import com.thepyprogrammer.phykt.quantity.Quantity
import com.thepyprogrammer.phykt.quantity.ScalarQuantity
import com.thepyprogrammer.phykt.unit.Unit

class UncertainQuantity(
    override var value: Double = 0.0,
    var uncertainty: Uncertainty = Uncertainty(0.0),
    override var unit: Unit = Unit("")
): Quantity(value, unit) {
    // unary operator funcs
    override operator fun unaryMinus() = UncertainQuantity(-value, uncertainty, unit)

    constructor(quantity: Quantity = ScalarQuantity(0.0, Unit()), uncertainty: Uncertainty = Uncertainty(0.0)): this(quantity.value, uncertainty, quantity.unit)

    infix operator fun plus(other: UncertainQuantity) = run {
        if(other.unit == unit) UncertainQuantity(other.value + value, uncertainty + other.uncertainty, unit)
        else this
    }

    override infix operator fun plus(other: Quantity) = this plus UncertainQuantity(other)

    override infix operator fun plus(other: Double) = UncertainQuantity(value + other, uncertainty, unit)

    override infix operator fun plus(other: Float) = UncertainQuantity(value + other, uncertainty, unit)

    override infix operator fun plus(other: Int) = UncertainQuantity(value + other, uncertainty, unit)

    override infix operator fun plus(other: Short) = UncertainQuantity(value + other, uncertainty, unit)

    override infix operator fun plus(other: Long) = UncertainQuantity(value + other, uncertainty, unit)




    infix operator fun minus(other: UncertainQuantity) = run {
        if(other.unit == unit) UncertainQuantity(other.value - value, uncertainty - other.uncertainty, unit)
        else this
    }

    override infix operator fun minus(other: Quantity) = this minus UncertainQuantity(other)

    override infix operator fun minus(other: Double) = UncertainQuantity(value - other, uncertainty, unit)

    override infix operator fun minus(other: Float) = UncertainQuantity(value - other, uncertainty, unit)

    override infix operator fun minus(other: Int) = UncertainQuantity(value - other, uncertainty, unit)

    override infix operator fun minus(other: Short) = UncertainQuantity(value - other, uncertainty, unit)
    override fun dot(other: Quantity): Quantity {
        TODO("Not yet implemented")
    }

    override fun cross(other: Quantity): Quantity {
        TODO("Not yet implemented")
    }


}