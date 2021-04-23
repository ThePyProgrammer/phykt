package com.thepyprogrammer.phykt.quantity

import kotlin.collections.HashMap
import kotlin.math.pow
import com.thepyprogrammer.phykt.unit.Unit


open class Quantity(
    open var value: Double = 0.0,
    open var unit: Unit = Unit("")
) : Cloneable, Comparable<Any?> {
//    private lateinit var powers: HashMap<String, Double>

    init {
        value = value * unit.mul + unit.add
    }


    constructor(uv: Quantity) : this(uv.value, uv.unit)

    // unary operator funcs
    open operator fun unaryPlus() = this

    open operator fun unaryMinus() = Quantity(-value, unit)



    open infix operator fun plus(other: Quantity) = run {
        if(other.unit == unit) Quantity(other.value + value, unit)
        else this
    }

    open infix operator fun plus(other: Double) = Quantity(value + other, unit)

    open infix operator fun plus(other: Float) = Quantity(value + other, unit)

    open infix operator fun plus(other: Int) = Quantity(value + other, unit)

    open infix operator fun plus(other: Short) = Quantity(value + other, unit)

    open infix operator fun plus(other: Long) = Quantity(value + other, unit)




    open infix operator fun minus(other: Quantity) = run {
        if(other.unit == unit) Quantity(other.value - value, unit)
        else this
    }

    open infix operator fun minus(other: Double) = Quantity(value - other, unit)

    open infix operator fun minus(other: Float) = Quantity(value - other, unit)

    open infix operator fun minus(other: Int) = Quantity(value - other, unit)

    open infix operator fun minus(other: Short) = Quantity(value - other, unit)

    open infix operator fun minus(other: Long) = Quantity(value - other, unit)




    infix operator fun times(other: Quantity) = Quantity(value * other.value, unit * other.unit)

    infix operator fun times(other: Double) = Quantity(value * other, unit)

    infix operator fun times(other: Float) = Quantity(value * other, unit)

    infix operator fun times(other: Int) = Quantity(value * other, unit)

    infix operator fun times(other: Short) = Quantity(value * other, unit)

    infix operator fun times(other: Long) = Quantity(value * other, unit)





    infix operator fun div(other: Quantity) = Quantity(value / other.value, unit / other.unit)

    infix operator fun div(other: Double) = Quantity(value / other, unit)

    infix operator fun div(other: Float) = Quantity(value / other, unit)

    infix operator fun div(other: Int) = Quantity(value / other, unit)

    infix operator fun div(other: Short) = Quantity(value / other, unit)

    infix operator fun div(other: Long) = Quantity(value / other, unit)






    infix operator fun rem(other: Quantity) = run {
        if(other.unit == unit) Quantity(other.value % value, unit)
        else this
    }

    infix operator fun rem(other: Double) = Quantity(value % other, unit)

    infix operator fun rem(other: Float) = Quantity(value % other, unit)

    infix operator fun rem(other: Int) = Quantity(value % other, unit)

    infix operator fun rem(other: Short) = Quantity(value % other, unit)

    infix operator fun rem(other: Long) = Quantity(value % other, unit)




    operator fun inc() = this plus 1
    operator fun dec() = this minus 1

    infix fun pow(pow: Double): Quantity = Quantity(value.pow(pow), unit.pow(pow))

    infix fun pow(pow: Float) = pow(pow.toDouble())
    infix fun pow(pow: Int) = pow(pow.toDouble())
    infix fun pow(pow: Short) = pow(pow.toDouble())
    infix fun pow(pow: Long) = pow(pow.toDouble())
    infix fun pow(pow: Quantity) = pow(pow.toDouble())



    fun toDouble() = value.toInt()
    fun toFloat() = value.toInt()
    fun toInt() = value.toInt()
    fun toShort() = value.toInt().toShort()
    fun toLong() = value.toLong()



    // Boolean Functions
    operator fun not() = value == 0.0

    fun equals(other: Quantity): Boolean {
        return value == other.value && unit == other.unit
    }

    override fun equals(other: Any?): Boolean {
        return if(other is Quantity) value == other.value && unit == other.unit
        else false
    }

    operator fun compareTo(uv: Quantity): Int {
        if (value > uv.value) return 1
        return if (value == uv.value) 0 else -1
    }

    override operator fun compareTo(other: Any?): Int {
        return if (other is Quantity) {
            compareTo(other)
        } else -1
    }

    public override fun clone(): Quantity {
        return Quantity(this)
    }


    override fun toString(): String {
        return "$value $unit"
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + unit.hashCode()
        return result
    }


}