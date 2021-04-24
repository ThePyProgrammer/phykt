package com.thepyprogrammer.phykt.base

import com.thepyprogrammer.phykt.spatial.SpatialVector
import kotlin.math.sqrt


data class Point(var x: Double = 0.0, var y: Double = 0.0, var z: Double = 0.0): Cloneable {

    fun set(x: Double) {
        this.x = x
    }
    fun set(x: Double, y: Double) {
        this.x = x
        this.y = y
    }
    fun set(x: Double, y: Double, z: Double) {
        this.x = x
        this.y = y
        this.z = z
    }

    override fun toString(): String {
        return "($x, $y, $z)"
    }

    fun copy(): Point {
        return Point(x, y, z)
    }

    override fun clone(): Point {
        return copy()
    }
    operator fun compareTo(o: Any?): Int {
        return if (o is Point) compareTo(o) else 0
    }

    operator fun compareTo(p: Point): Int {
        return when {
            x != p.x -> (x - p.x).toInt()
            y != p.y -> (y - p.y).toInt()
            else -> (z - p.z).toInt()
        }
    }

    fun fromOrigin(o: Point): Point {
        return Point(x - o.x, y - o.y, z - o.z)
    }

    fun getHypotenuse(): Double {
        return sqrt(x * x + y * y + z * z)
    }

    fun hypotenuseFrom(p: Point): Double {
        return fromOrigin(p).getHypotenuse()
    }


    // Instance methods desugar to prototype methods, eg. Point.prototype.moveBy = function(dx, dy) {}
    fun moveBy(dx: Double, dy: Double, dz: Double) {
        this.x += dx
        this.y += dy
        this.z += dz
    }

    fun vectorTo(p: Point) = run {
        val (x, y, z) = p.fromOrigin(this)
        SpatialVector(x, y, z)
    }
}