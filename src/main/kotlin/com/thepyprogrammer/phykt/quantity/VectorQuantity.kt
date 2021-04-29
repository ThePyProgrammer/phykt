package com.thepyprogrammer.phykt.quantity

import com.thepyprogrammer.phykt.spatial.SpatialVector
import com.thepyprogrammer.phykt.unit.Unit

open class VectorQuantity(val vector: SpatialVector, override val unit: Unit): Quantity(vector.magnitude, unit) {
    open var x: Double
        get() = vector.x
        set(other) { vector.x = other }

    open var y: Double
        get() = vector.y
        set(other) { vector.y = other }

    open var z: Double
        get() = vector.z
        set(other) { vector.z = other }

    val mag: Double
        get() = vector.mag

    override var value: Double = vector.mag
        get() = vector.magnitude

    constructor(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0, unit: Unit = Unit()): this(SpatialVector(x, y, z), unit)
    constructor(x: Double = 0.0, y: Double = 0.0, unit: Unit = Unit()): this(SpatialVector(x, y, 0.0), unit)
    constructor(x: Double = 0.0, unit: Unit = Unit()): this(SpatialVector(x, 0.0, 0.0), unit)

    init {
        assert(!unit.isScalar)
    }

    override infix fun dot(other: Quantity): Quantity {
        return when (other) {
            is ScalarQuantity -> quantityOf((vector * other.value), unit *other.unit)
            is VectorQuantity -> quantityOf(vector.dot(other.vector), unit * other.unit)
            else -> this
        }
    }

    override infix fun cross(other: Quantity): Quantity {
        return when (other) {
            is ScalarQuantity -> quantityOf((vector * other.value), unit *other.unit)
            is VectorQuantity -> quantityOf(vector.cross(other.vector), unit * other.unit)
            else -> this
        }
    }


}