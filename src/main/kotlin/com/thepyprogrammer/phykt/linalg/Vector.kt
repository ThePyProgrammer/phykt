package com.thepyprogrammer.phykt.linalg

import com.thepyprogrammer.ktlib.array.each
import com.thepyprogrammer.phykt.spatial.SpatialVector
import kotlin.math.pow


open class Vector(
    vararg val values: Double,
    private val isUnit: Boolean = false
): ArrayList<Double>(values.toList())  {
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
        get() {
            return Vector(*values.toTypedArray().each { it / mag }.toDoubleArray(), isUnit=true)
        }

    val ndim: Int
        get() = size

    val transpose: Matrix
        get() = columnVectorOf(*values)


    infix fun dot(other: Vector): Double {
        val minSize = minOf(size, other.size)
        var sum = 0.0
        for(i in 0..minSize) {
            sum += get(i) * other[i]
        }
        return sum
    }


    infix operator fun times(other: Vector) = this dot other


    infix operator fun times(other: Matrix) = Matrix(this) * other

    open infix operator fun times(other: Double): Vector {
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



    open infix operator fun div(other: Double) = Vector(*values.toTypedArray().each { it / other }.toDoubleArray())

    infix operator fun div(other: Float) = this / other.toDouble()
    infix operator fun div(other: Int) = this / other.toDouble()
    infix operator fun div(other: Short) = this / other.toDouble()
    infix operator fun div(other: Long) = this / other.toDouble()


    override infix operator fun get(index: Int) = values[index]
    
    
    fun toSpatialVector() = when {
        values.isEmpty() -> SpatialVector()
        values.size == 1 -> SpatialVector(values[0])
        values.size == 2 -> SpatialVector(values[0], values[1])
        values.size >= 3 -> SpatialVector(values[0], values[1], values[2])
        else -> SpatialVector()
    }

}