package com.thepyprogrammer.phykt.quantity

import com.thepyprogrammer.linkt.spatial.SpatialVector
import com.thepyprogrammer.phykt.types.*
import com.thepyprogrammer.phykt.unit.*
import com.thepyprogrammer.phykt.unit.Unit


fun scalarQuantityOf(value: Double = 0.0, unit: Unit = Unit()): Quantity {
    val quantity = ScalarQuantity(value, unit)
    return when(unit) {
        A -> Current(quantity)
        W -> Power(quantity)
        ohm -> Resistance(quantity)
        kg -> Mass(quantity)
        s -> Time(quantity)
        m -> Distance(quantity)
        m/s -> Velocity(quantity)
        m/s.pow(2) -> Acceleration(quantity)
        kg*m.pow(2) -> Inertia(quantity)
        kg*m/s -> Momentum(quantity)
        kg*m/s.pow(2) -> Force(quantity)
        kg*m.pow(2)/s.pow(2) -> Energy(quantity)
        else -> quantity
    }
}

fun vectorQuantityOf(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0, unit: Unit = Unit()): VectorQuantity {
    val vectorQuantity = VectorQuantity(x, y, z, unit)
    return vectorQuantity
}

fun quantityOf(value: Double = 0.0, unit: Unit = Unit()) = scalarQuantityOf(value, unit)
fun quantityOf(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0, unit: Unit = Unit()) = vectorQuantityOf(x, y, z, unit)

fun quantityOf(vector: SpatialVector = SpatialVector(), unit: Unit = Unit()) = quantityOf(vector.x, vector.y, vector.z, unit)
