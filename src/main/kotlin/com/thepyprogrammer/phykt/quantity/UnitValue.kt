package com.thepyprogrammer.phykt.quantity

import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.pow


open class UnitValue(var value: Double = 0.0, var unit: String = "") : Cloneable, Comparable<Any?> {
    private lateinit var powers: HashMap<String, Double>

    constructor(uv: UnitValue) : this(uv.value, uv.unit)

    init {
        fix(unit)
    }

    // unary operator funcs
    operator fun unaryPlus() = this

    operator fun unaryMinus() = UnitValue(-value, unit)



    infix operator fun plus(other: UnitValue) = run {
        if(other.unit == unit) UnitValue(other.value + value, unit)
        else this
    }

    infix operator fun plus(other: Double) = UnitValue(value + other, unit)

    infix operator fun plus(other: Float) = UnitValue(value + other, unit)

    infix operator fun plus(other: Int) = UnitValue(value + other, unit)

    infix operator fun plus(other: Short) = UnitValue(value + other, unit)

    infix operator fun plus(other: Long) = UnitValue(value + other, unit)

    infix operator fun plusAssign(other: UnitValue) {
        if(other.unit == unit) value +=other.value
    }

    infix operator fun plusAssign(other: Double) {
        value += other
    }

    infix operator fun plusAssign(other: Float) {
        value += other
    }

    infix operator fun plusAssign(other: Int) {
        value += other
    }

    infix operator fun plusAssign(other: Short) {
        value += other
    }

    infix operator fun plusAssign(other: Long) {
        value += other
    }



    infix operator fun minus(other: UnitValue) = run {
        if(other.unit == unit) UnitValue(other.value - value, unit)
        else this
    }

    infix operator fun minus(other: Double) = UnitValue(value - other, unit)

    infix operator fun minus(other: Float) = UnitValue(value - other, unit)

    infix operator fun minus(other: Int) = UnitValue(value - other, unit)

    infix operator fun minus(other: Short) = UnitValue(value - other, unit)

    infix operator fun minus(other: Long) = UnitValue(value - other, unit)

    infix operator fun minusAssign(other: UnitValue) {
        if(other.unit == unit) value -=other.value
    }

    infix operator fun minusAssign(other: Double) {
        value -= other
    }

    infix operator fun minusAssign(other: Float) {
        value -= other
    }

    infix operator fun minusAssign(other: Int) {
        value -= other
    }

    infix operator fun minusAssign(other: Short) {
        value -= other
    }

    infix operator fun minusAssign(other: Long) {
        value -= other
    }




    infix operator fun times(other: UnitValue) = run {
        val numer = deriveNumer() + other.deriveNumer()
        val denom = deriveDenom() + other.deriveDenom()
        val `val` = value * other.value
        UnitValue(`val`, formUnit(numer, denom))
    }

    infix operator fun times(other: Double) = UnitValue(value * other, unit)

    infix operator fun times(other: Float) = UnitValue(value * other, unit)

    infix operator fun times(other: Int) = UnitValue(value * other, unit)

    infix operator fun times(other: Short) = UnitValue(value * other, unit)

    infix operator fun times(other: Long) = UnitValue(value * other, unit)

    infix operator fun timesAssign(other: Double) {
        value *= other
    }

    infix operator fun timesAssign(other: Float) {
        value *= other
    }

    infix operator fun timesAssign(other: Int) {
        value *= other
    }

    infix operator fun timesAssign(other: Short) {
        value *= other
    }

    infix operator fun timesAssign(other: Long) {
        value *= other
    }




    infix operator fun div(other: UnitValue) = run {
        val numer = deriveNumer() + other.deriveDenom()
        val denom = deriveDenom() + other.deriveNumer()
        val `val` = value / other.value
        UnitValue(`val`, formUnit(numer, denom))
    }

    infix operator fun div(other: Double) = UnitValue(value / other, unit)

    infix operator fun div(other: Float) = UnitValue(value / other, unit)

    infix operator fun div(other: Int) = UnitValue(value / other, unit)

    infix operator fun div(other: Short) = UnitValue(value / other, unit)

    infix operator fun div(other: Long) = UnitValue(value / other, unit)

    infix operator fun divAssign(other: Double) {
        value /= other
    }

    infix operator fun divAssign(other: Float) {
        value /= other
    }

    infix operator fun divAssign(other: Int) {
        value /= other
    }

    infix operator fun divAssign(other: Short) {
        value /= other
    }

    infix operator fun divAssign(other: Long) {
        value /= other
    }





    infix operator fun rem(other: UnitValue) = run {
        if(other.unit == unit) UnitValue(other.value % value, unit)
        else this
    }

    infix operator fun rem(other: Double) = UnitValue(value % other, unit)

    infix operator fun rem(other: Float) = UnitValue(value % other, unit)

    infix operator fun rem(other: Int) = UnitValue(value % other, unit)

    infix operator fun rem(other: Short) = UnitValue(value % other, unit)

    infix operator fun rem(other: Long) = UnitValue(value % other, unit)

    infix operator fun remAssign(other: Double) {
        value %= other
    }

    infix operator fun remAssign(other: Float) {
        value %= other
    }

    infix operator fun remAssign(other: Int) {
        value %= other
    }

    infix operator fun remAssign(other: Short) {
        value %= other
    }

    infix operator fun remAssign(other: Long) {
        value %= other
    }


    operator fun inc() = this plus 1
    operator fun dec() = this minus 1

    fun pow(pow: Double): UnitValue {
        var numer = ""
        var denom = ""
        for ((key, value1) in powers) {
            val p = value1 * pow
            if (p == 0.0)
                continue
            else if (p == 1.0)
                numer += key
            else if (p == -1.0)
                denom += key
            else if (p > 0)
                numer += "$key^$p"
            else if (p < 0)
                denom += "$key^" + abs(p)
        }
        return UnitValue(value.pow(pow), formUnit(numer, denom))
    }

    fun pow(pow: Float) = pow(pow.toDouble())
    fun pow(pow: Int) = pow(pow.toDouble())
    fun pow(pow: Short) = pow(pow.toDouble())
    fun pow(pow: Long) = pow(pow.toDouble())
    fun pow(pow: UnitValue) = pow(pow.toDouble())



    fun toDouble() = value.toInt()
    fun toFloat() = value.toInt()
    fun toInt() = value.toInt()
    fun toShort() = value.toInt().toShort()
    fun toLong() = value.toLong()



    // Boolean Functions
    operator fun not() = value == 0.0

    fun equals(other: UnitValue): Boolean {
        return value == other.value && unit == other.unit
    }

    override fun equals(other: Any?): Boolean {
        return if(other is UnitValue) value == other.value && unit == other.unit
        else false
    }

    operator fun compareTo(uv: UnitValue): Int {
        if (value > uv.value) return 1
        return if (value == uv.value) 0 else -1
    }

    override operator fun compareTo(other: Any?): Int {
        return if (other is UnitValue) {
            compareTo(other)
        } else -1
    }





    private fun deriveNumer(): String {
        if (!unit.contains("/")) return unit
        return if (unit.startsWith("1/")) "" else unit.split("/").toTypedArray()[0]
    }

    private fun deriveDenom(): String {
        return if (!unit.contains("/")) "" else unit.split("/").toTypedArray()[1]
    }


    private fun fix(unit: String): String {
        var numer = ""
        var denom = ""
        var ognum = unit
        var ogden = ""
        if (unit.contains("/")) {
            val temp = unit.split("/").toTypedArray()
            ognum = temp[0]
            ogden = temp[1]
        }
        powers = hashMapOf()
        for (u in SIs) {
            val (pownum, tempnum) = extractAll(ognum, u)
            val (powden, tempden) = extractAll(ogden, u)

            ognum = tempnum
            ogden = tempden

            powers[u] = pownum - powden
        }

        loop@ for ((key, value1) in powers) {
            when {
                value1 == 0.0 -> continue@loop
                value1 == 1.0 -> numer += key
                value1 == -1.0 -> denom += key
                value1 > 0 -> numer += "$key^$value1"
                value1 < 0 -> denom += "$key^" + abs(value1)
            }
        }
        return formUnit(numer, denom)
    }

    private fun extractAll(template: String, codon: String) = run {
        var m: Matcher
        var p = 0.0
        // Pattern re = Pattern.compile(String.format("(%s\\^)(-?\\d+(\\.\\d+)?)", u));
        var start: Int
        var temp = template
        while (temp.contains("$codon^")) {
            start = temp.indexOf("$codon^")
            var str = ""
            for (i in temp.substring(start + codon.length + 1).toCharArray()) {
                if (Pattern.compile("\\d|\\.").matcher("" + i).also { m = it }
                        .matches()) str += m.group() else break
            }
            p += if (str.contains(".")) str.toDouble() else str.toInt().toDouble()
            temp = temp.replaceFirst("$codon\\^" + str.toRegex(), "")
        }
        while (temp.contains(codon)) {
            p++
            temp = temp.replaceFirst(codon.toRegex(), "")
        }
        ExtractType(p, temp)
    }

    private data class ExtractType(val p: Double, val temp: String)


    public override fun clone(): UnitValue {
        return UnitValue(this)
    }


    override fun toString(): String {
        return "$value $unit"
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + unit.hashCode()
        result = 31 * result + powers.hashCode()
        return result
    }


    companion object {
        private val SIs = arrayOf("kg", "mol", "m", "A", "s", "K", "cd")

        private fun formUnit(numer: String, denom: String) = run {
            if (numer.isNotEmpty() && denom.isNotEmpty())
                "$numer/$denom"
            else if (numer.isNotEmpty())
                numer
            else if (denom.isNotEmpty())
                "1/$denom"
            else ""
        }
    }
}