package com.thepyprogrammer.phykt.quantity

import java.text.NumberFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.HashMap


data class UnitValue(var value: Double = 0.0, var unit: String = "") : Cloneable, Comparable<Any?> {
    private lateinit var powers: HashMap<String, Double>

    constructor(uv: UnitValue) : this(uv.value, uv.unit) {}

    public override fun clone(): UnitValue {
        return UnitValue(this)
    }

    infix operator fun add(vararg uvs: UnitValue): UnitValue {
        var `val` = value
        for (uv in uvs) {
            if (uv.unit == unit) `val` += uv.value
        }
        return UnitValue(`val`, unit)
    }

    fun iadd(vararg uvs: UnitValue) {
        var `val` = value
        for (uv in uvs) {
            if (uv.unit == unit) `val` += uv.value
        }
        value = `val`
    }

    fun sub(vararg uvs: UnitValue): UnitValue {
        var `val` = value
        for (uv in uvs) {
            if (uv.unit == unit) `val` -= uv.value
        }
        return UnitValue(`val`, unit)
    }

    fun isub(vararg uvs: UnitValue) {
        var `val` = value
        for (uv in uvs) {
            if (uv.unit == unit) `val` -= uv.value
        }
        value = `val`
    }

    fun subtract(uv: UnitValue?): UnitValue {
        return sub(uv!!)
    }

    fun mul(vararg uvs: UnitValue): UnitValue {
        var numer = deriveNumer()
        var denom = deriveDenom()
        var `val` = value
        for (uv in uvs) {
            `val` *= uv.value
            numer += uv.deriveNumer()
            denom += uv.deriveDenom()
        }
        return UnitValue(`val`, formUnit(numer, denom))
    }

    fun multiply(uv: UnitValue?): UnitValue {
        return mul(uv!!)
    }

    operator fun div(uv: UnitValue): UnitValue {
        var numer = deriveNumer()
        var denom: String? = deriveDenom()
        val `val` = value / uv.value
        denom += uv.deriveNumer()
        numer += uv.deriveDenom()
        return UnitValue(`val`, formUnit(numer, denom))
    }

    fun divide(uv: UnitValue): UnitValue {
        return div(uv)
    }

    fun pow(pow: Double): UnitValue {
        var numer = ""
        var denom = ""
        for ((key, value1) in powers!!) {
            val p = value1 * pow
            if (p == 0.0) continue else if (p == 1.0) numer += key else if (p == -1.0) denom += key else if (p > 0) numer += "$key^$p" else if (p < 0) denom += key + "^" + Math.abs(
                p
            )
        }
        return UnitValue(Math.pow(value, pow), formUnit(numer, denom))
    }

    fun negate(): UnitValue {
        return UnitValue(-value, unit)
    }

    private fun deriveNumer(): String? {
        if (!unit!!.contains("/")) return unit
        return if (unit!!.startsWith("1/")) "" else unit!!.split("/").toTypedArray()[0]
    }

    private fun deriveDenom(): String {
        return if (!unit!!.contains("/")) "" else unit!!.split("/").toTypedArray()[1]
    }

    private fun classify(uv: UnitValue): UnitValue {
        if (uv.unit == "kgm^2/s^3") return Power(uv.value)
        return if (uv.unit == "m/s^2") Acceleration(uv.value) else uv
    }

    private fun fix(unit: String?): String? {
        var numer = ""
        var denom = ""
        var ognum = unit
        var ogden = ""
        if (unit!!.contains("/")) {
            val temp = unit.split("/").toTypedArray()
            ognum = temp[0]
            ogden = temp[1]
        }
        powers = HashMap()
        var m: Matcher
        for (u in SIs) {
            var p = 0.0
            // Pattern re = Pattern.compile(String.format("(%s\\^)(-?\\d+(\\.\\d+)?)", u));
            var start: Int
            while (ognum!!.contains("$u^")) {
                start = ognum.indexOf(u + "")
                var str = ""
                for (i in ognum.substring(start + u.length + 1).toCharArray()) {
                    if (Pattern.compile("\\d|\\.").matcher("" + i).also { m = it }
                            .matches()) str += m.group() else break
                }
                p += if (str.contains(".")) str.toDouble() else str.toInt()
                ognum = ognum.replaceFirst(u + "\\^" + str.toRegex(), "")
            }
            while (ognum!!.contains(u)) {
                p++
                ognum = ognum.replaceFirst(u.toRegex(), "")
            }
            while (ogden.contains("$u^")) {
                start = ogden.indexOf(u + "")
                var str = ""
                for (i in ogden.substring(start + u.length + 1).toCharArray()) {
                    if (Pattern.compile("\\d|\\.").matcher("" + i).also { m = it }
                            .matches()) str += m.group() else break
                }
                p -= if (str.contains(".")) str.toDouble() else str.toInt()
                ogden = ogden.replaceFirst(u + "\\^" + str.toRegex(), "")
            }
            while (ogden.contains(u)) {
                p--
                ogden = ogden.replaceFirst(u.toRegex(), "")
            }
            powers!![u] = p
        }
        for ((key, value1) in powers!!) {
            if (value1 == 0) continue else if (value1 == 1) numer += key else if (value1 == -1) denom += key else if (value1 > 0) numer += "$key^$value1" else if (value1 < 0) denom += "$key^" + Math.abs(
                value1
            )
        }
        return formUnit(numer, denom)
    }

    fun removeRandomZeroes(str: String): String {
        var str = str
        if (str.contains(".")) {
            while (str[str.length - 1] == '0') {
                str = str.substring(0, str.length - 1)
            }
            if (str[str.length - 1] == '.') {
                str = str.substring(0, str.length - 1)
            }
        }
        return str.trim { it <= ' ' }
    }

    override fun toString(): String {
        return "$value $unit"
    }

    fun toString(locale: Locale?): String {
        return NumberFormat.getNumberInstance().format(value) + " " + unit
    }

    fun equals(other: UnitValue): Boolean {
        return value == other.value && unit == other.unit
    }

    operator fun compareTo(o: UnitValue): Int {
        if (value > o.value) return 1
        return if (value == o.value) 0 else -1
    }

    override operator fun compareTo(o: Any): Int {
        return if (o is UnitValue) {
            compareTo(o)
        } else -1
    }

    companion object {
        private val SIs = arrayOf("kg", "mol", "m", "A", "s", "K", "cd")
        private fun deriveNumer(unit: String): String {
            if (!unit.contains("/")) return unit
            return if (unit.startsWith("1/")) "" else unit.split("/").toTypedArray()[0]
        }

        private fun deriveDenom(unit: String): String {
            return if (!unit.contains("/")) "" else unit.split("/").toTypedArray()[0]
        }

        private fun formUnit(numer: String?, denom: String?): String? {
            if (numer!!.length > 0 && denom!!.length > 0) return "$numer/$denom" else if (numer.length > 0) return numer else if (denom!!.length > 0) return "1/$denom"
            return ""
        }
    }

    init {
        setUnit(unit)
    }

    override fun compareTo(other: Any?): Int {
        TODO("Not yet implemented")
    }
}