package com.thepyprogrammer.phykt.quantity

import kotlin.math.pow
import com.thepyprogrammer.phykt.unit.Unit


abstract class Quantity(
    open var value: Double = 0.0,
    open val unit: Unit = Unit("")
) : Cloneable, Comparable<Any?> {
//    private lateinit var powers: HashMap<String, Double>

    init {
        value = value * unit.mul + unit.add
    }


    constructor(uv: Quantity) : this(uv.value, uv.unit)

    // unary operator funcs
    open operator fun unaryPlus() = this

    open operator fun unaryMinus() = quantityOf(-value, unit)



    open infix operator fun plus(other: Quantity) = run {
        if(other.unit == unit) quantityOf(other.value + value, unit)
        else this
    }

    open infix operator fun plus(other: Double) = quantityOf(value + other, unit)

    open infix operator fun plus(other: Float) = quantityOf(value + other, unit)

    open infix operator fun plus(other: Int) = quantityOf(value + other, unit)

    open infix operator fun plus(other: Short) = quantityOf(value + other, unit)

    open infix operator fun plus(other: Long) = quantityOf(value + other, unit)




    open infix operator fun minus(other: Quantity) = run {
        if(other.unit == unit) quantityOf(other.value - value, unit)
        else this
    }

    open infix operator fun minus(other: Double) = quantityOf(value - other, unit)

    open infix operator fun minus(other: Float) = quantityOf(value - other, unit)

    open infix operator fun minus(other: Int) = quantityOf(value - other, unit)

    open infix operator fun minus(other: Short) = quantityOf(value - other, unit)

    open infix operator fun minus(other: Long) = quantityOf(value - other, unit)




    infix operator fun times(other: Quantity) = this dot other

    infix operator fun times(other: Double) = quantityOf(value * other, unit)

    infix operator fun times(other: Float) = quantityOf(value * other, unit)

    infix operator fun times(other: Int) = quantityOf(value * other, unit)

    infix operator fun times(other: Short) = quantityOf(value * other, unit)

    infix operator fun times(other: Long) = quantityOf(value * other, unit)





    infix operator fun div(other: Quantity) = quantityOf(value / other.value, unit / other.unit)

    infix operator fun div(other: Double) = quantityOf(value / other, unit)

    infix operator fun div(other: Float) = quantityOf(value / other, unit)

    infix operator fun div(other: Int) = quantityOf(value / other, unit)

    infix operator fun div(other: Short) = quantityOf(value / other, unit)

    infix operator fun div(other: Long) = quantityOf(value / other, unit)






    infix operator fun rem(other: Quantity) = run {
        if(other.unit == unit) quantityOf(other.value % value, unit / other.unit)
        else this
    }

    infix operator fun rem(other: Double) = quantityOf(value % other, unit)

    infix operator fun rem(other: Float) = quantityOf(value % other, unit)

    infix operator fun rem(other: Int) = quantityOf(value % other, unit)

    infix operator fun rem(other: Short) = quantityOf(value % other, unit)

    infix operator fun rem(other: Long) = quantityOf(value % other, unit)




    operator fun inc() = this plus 1
    operator fun dec() = this minus 1

    open infix fun pow(pow: Number): Quantity = quantityOf(value.pow(pow.toDouble()), unit.pow(pow.toDouble()))



    fun toDouble() = value
    fun toFloat() = value.toFloat()
    fun toInt() = value.toInt()
    fun toShort() = value.toInt().toShort()
    fun toLong() = value.toLong()



    // Boolean Functions
    operator fun not() = value == 0.0

    infix fun equals(other: Quantity): Boolean {
        return value == other.value && unit == other.unit
    }

    override infix fun equals(other: Any?): Boolean {
        return if(other is Quantity) value == other.value && unit == other.unit
        else false
    }

    infix operator fun compareTo(uv: Quantity): Int {
        if (value > uv.value) return 1
        return if (value == uv.value) 0 else -1
    }

    override infix operator fun compareTo(other: Any?): Int {
        return if (other is Quantity) {
            compareTo(other)
        } else -1
    }

    public override fun clone(): Quantity {
        return quantityOf(this.value, this.unit)
    }


    override fun toString(): String {
        return "$value $unit"
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + unit.hashCode()
        return result
    }

    abstract infix fun dot(other: Quantity): Quantity
    abstract infix fun cross(other: Quantity): Quantity


}