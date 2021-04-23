package com.thepyprogrammer.phykt.linalg

import kotlin.math.pow

open class Vector(
    vararg val values: Double,
    val isUnit: Boolean = false
): ArrayList<Double>(values.toList()) {
    override fun toString() = values.joinToString(separator = ", ", prefix = "(", postfix = ")")

    // unary operator funcs
    open operator fun unaryPlus() = this

    open operator fun unaryMinus() = this * -1


    val sum: Double
        get() = run {
            var summand = 0.0
            values.forEach {
                summand += it
            }
            return summand
        }

    val mean: Double
        get() = sum / size

    val squareSum: Double
        get() = run {
            var summand = 0.0
            values.forEach {
                summand += it.pow(2)
            }
            return summand
        }

    val squareMean: Double
        get() = squareSum / size

    val rms: Double
        get() = squareMean.pow(0.5)

    val magnitude: Double
        get() = squareSum.pow(0.5)

    val mag: Double
        get() = squareSum.pow(0.5)

    val unitVector: Vector
        get() = if(isUnit) this else this / magnitude

    val ndim: Int
        get() = size


    infix fun dot(other: Vector): Double {
        val minSize = minOf(size, other.size)
        var sum = 0.0
        for(i in 0..minSize) {
            sum += get(i) * other[i]
        }
        return sum
    }


    infix operator fun times(other: Vector): Double {
        val minSize = minOf(size, other.size)
        var sum = 0.0
        for(i in 0..minSize) {
            sum += get(i) * other[i]
        }
        return sum
    }


    infix operator fun times(other: Matrix) = Matrix(this) * other

    infix operator fun times(other: Double): Vector {
        val res = Vector(*this.values)
        for(i in 0..size) {
            res[i] *= other
        }
        return res
    }

    infix operator fun times(other: Float) = this * other.toDouble()
    infix operator fun times(other: Int) = this * other.toDouble()
    infix operator fun times(other: Short) = this * other.toDouble()
    infix operator fun times(other: Long) = this * other.toDouble()



    infix operator fun div(other: Double): Vector {
        val list = values.toMutableList()
        for(i in 0..size) {
            list[i] /= other
        }
        return Vector(*list.toDoubleArray(), isUnit=true)
    }

    infix operator fun div(other: Float) = this / other.toDouble()
    infix operator fun div(other: Int) = this / other.toDouble()
    infix operator fun div(other: Short) = this / other.toDouble()
    infix operator fun div(other: Long) = this / other.toDouble()


    override infix operator fun get(index: Int) = values[index]

}