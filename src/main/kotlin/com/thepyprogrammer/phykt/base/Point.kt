package com.thepyprogrammer.phykt.base

import kotlin.math.sqrt


data class Point(var x: Double = 0.0, var y: Double = 0.0): Cloneable {
    constructor(): this(0.0, 0.0)

    operator fun set(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    override fun toString(): String {
        return "($x, $y)"
    }

    fun copy(): Point {
        return Point(x, y)
    }

    override fun clone(): Point {
        return copy()
    }
    operator fun compareTo(o: Any?): Int {
        return if (o is Point) compareTo(o) else 0
    }

    operator fun compareTo(p: Point): Int {
        return if (x != p.x) (x - p.x).toInt() else (y - p.y).toInt()
    }

    fun fromOrigin(o: Point): Point {
        return Point(x - o.x, y - o.y)
    }

    fun getHypotenuse(): Double {
        return sqrt(x * x + y * y)
    }

    fun hypotenuseFrom(p: Point): Double {
        return fromOrigin(p).getHypotenuse()
    }


    // Instance methods desugar to prototype methods, eg. Point.prototype.moveBy = function(dx, dy) {}
    fun moveBy(dx: Double, dy: Double) {
        this.x += dx
        this.y += dy
    }
}