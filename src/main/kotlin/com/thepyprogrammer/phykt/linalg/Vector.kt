package com.thepyprogrammer.phykt.linalg

import com.thepyprogrammer.ktlib.math.`**`
import com.thepyprogrammer.phykt.spatial.SpatialVector
import kotlin.math.pow


open class Vector(
    vararg val values: Double,
    private val isUnit: Boolean = false
): ArrayList<Double>(values.toList())  {
    open operator fun unaryPlus() = this

    open operator fun unaryMinus() = this * -1

    val sum: Double
        get() = fold(0.0) { acc, it -> acc + it }

    val mean: Double
        get() = sum / size

    val squareSum: Double
        get() = fold(0.0) { acc, it -> acc + it `**` 2 }

    val squareMean: Double
        get() = squareSum / size

    val rms: Double
        get() = squareMean.pow(0.5)

    val magnitude: Double
        get() = squareSum.pow(0.5)

    val mag: Double
        get() = squareSum.pow(0.5)

    val unitVector: Vector
        get() = if(isUnit) this else Vector(*map { it / mag }.toDoubleArray(), isUnit=true)

    val ndim: Int
        get() = size

    val transpose: Matrix
        get() = columnVectorOf(this)


    infix fun dot(other: Vector) = (0..minOf(size, other.size)).fold(0.0) { acc, it -> acc + this@Vector[it] * other[it] }


    infix operator fun times(other: Vector) = this dot other

    infix operator fun times(other: Matrix) = Matrix(this) * other

    open infix operator fun times(other: Double) = Vector(*map { it * other }.toDoubleArray())

    infix operator fun times(other: Float) = this * other.toDouble()
    infix operator fun times(other: Int) = this * other.toDouble()
    infix operator fun times(other: Short) = this * other.toDouble()
    infix operator fun times(other: Long) = this * other.toDouble()


    open infix operator fun div(other: Double) = Vector(*map { it / other }.toDoubleArray())

    infix operator fun div(other: Float) = this / other.toDouble()
    infix operator fun div(other: Int) = this / other.toDouble()
    infix operator fun div(other: Short) = this / other.toDouble()
    infix operator fun div(other: Long) = this / other.toDouble()


    fun toSpatialVector() = when {
        isEmpty() -> SpatialVector()
        size == 1 -> SpatialVector(get(0))
        size == 2 -> SpatialVector(get(0), get(1))
        size >= 3 -> SpatialVector(get(0), get(1), get(2))
        else -> SpatialVector()
    }

    override fun toString() = joinToString(separator = ", ", prefix = "(", postfix = ")")

    fun main() {

    }

}