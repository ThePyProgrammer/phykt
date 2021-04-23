package com.thepyprogrammer.phykt.spatial

import com.thepyprogrammer.phykt.base.Angle
import com.thepyprogrammer.phykt.linalg.Vector
import kotlin.math.*

open class SpatialVector(
    open val x: Double = 0.0,
    open val y: Double = 0.0,
    val z: Double = 0.0
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

}