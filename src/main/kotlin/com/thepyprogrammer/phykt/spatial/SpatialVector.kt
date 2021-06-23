package com.thepyprogrammer.phykt.spatial

import com.thepyprogrammer.linkt.base.Angle
import com.thepyprogrammer.linkt.base.Point
import com.thepyprogrammer.linkt.Vector
import kotlin.math.*

open class SpatialVector(
    open var x: Double = 0.0,
    open var y: Double = 0.0,
    open var z: Double = 0.0
): Vector(x, y, z) {
    infix fun cross(other: SpatialVector) =
        SpatialVector(
            y * other.z - z * other.y,
            z * other.x - x * other.z,
            x * other.y - y * other.x
        )

    infix fun angleFrom(other: SpatialVector) =
        Angle(acos(dot(other) / (magnitude * other.magnitude)))

    val angle: Angle
        get() = angleFrom(SpatialVector(1.0))

    override infix operator fun times(other: Double) = super.times(other).toSpatialVector()
    override infix operator fun div(other: Double) = super.div(other).toSpatialVector()



    val target: Point get() = Point(x, y, z)
}