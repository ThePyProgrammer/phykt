package com.thepyprogrammer.phykt.uncertainty



data class Uncertainty(var value: Double) {

    // unary operator funcs
    operator fun unaryPlus() = this
    operator fun unaryMinus() = this


    infix operator fun plus(other: Uncertainty) = Uncertainty(value + other.value)

    infix operator fun plus(other: Double) = Uncertainty(value + other)

    infix operator fun plus(other: Float) = Uncertainty(value + other)

    infix operator fun plus(other: Int) = Uncertainty(value + other)

    infix operator fun plus(other: Short) = Uncertainty(value + other)

    infix operator fun plus(other: Long) = Uncertainty(value + other)




    infix operator fun minus(other: Uncertainty) = Uncertainty(value + other.value)

    infix operator fun minus(other: Double) = Uncertainty(value + other)

    infix operator fun minus(other: Float) = Uncertainty(value + other)

    infix operator fun minus(other: Int) = Uncertainty(value + other)

    infix operator fun minus(other: Short) = Uncertainty(value + other)

    infix operator fun minus(other: Long) = Uncertainty(value + other)





}