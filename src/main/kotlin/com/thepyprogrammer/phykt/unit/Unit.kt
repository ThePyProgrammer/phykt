package com.thepyprogrammer.phykt.unit

import com.thepyprogrammer.phykt.quantity.Quantity
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.abs

data class Unit(
    var unit: String = "",
    val unitname: String = "",
    val unitfunc: String = "",
    val mul: Int = 1,
    val add: Int = 0,
    val isScalar: Boolean = false
): HashMap<String, Double>() {

    init {
        fix()
    }

    infix operator fun times(other: Unit): Unit {
        val numer = deriveNumer() + other.deriveNumer()
        val denom = deriveDenom() + other.deriveDenom()
        return Unit(formUnit(numer, denom))
    }

    infix operator fun div(other: Unit): Unit {
        val numer = deriveNumer() + other.deriveDenom()
        val denom = deriveDenom() + other.deriveNumer()
        return Unit(formUnit(numer, denom))
    }

    infix fun pow(pow: Double) = run {
        var numer = ""
        var denom = ""
        for ((key, value1) in this) {
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
        Unit(formUnit(numer, denom))
    }


    infix fun pow(pow: Float) = pow(pow.toDouble())
    infix fun pow(pow: Int) = pow(pow.toDouble())
    infix fun pow(pow: Short) = pow(pow.toDouble())
    infix fun pow(pow: Long) = pow(pow.toDouble())
    infix fun pow(pow: Quantity) = pow(pow.toDouble())



    private fun deriveNumer(): String {
        if (!unit.contains("/")) return unit
        return if (unit.startsWith("1/")) "" else unit.split("/").toTypedArray()[0]
    }

    private fun deriveDenom(): String {
        return if (!unit.contains("/")) "" else unit.split("/").toTypedArray()[1]
    }


    private fun fix() {
        var ognum = unit
        var ogden = ""
        if (unit.contains("/")) {
            val temp = unit.split("/").toTypedArray()
            ognum = temp[0]
            ogden = temp[1]
        }
        for (u in SIs) {
            val (pownum, tempnum) = extractAll(ognum, u)
            val (powden, tempden) = extractAll(ogden, u)

            ognum = tempnum
            ogden = tempden

            this[u] = pownum - powden
        }

        var numer = ""
        var denom = ""

        loop@ for ((key, value1) in this) {
            when {
                value1 == 0.0 -> continue@loop
                value1 == 1.0 -> numer += key
                value1 == -1.0 -> denom += key
                value1 > 0 -> numer += "$key^$value1"
                value1 < 0 -> denom += "$key^" + abs(value1)
            }
        }
        unit = formUnit(numer, denom)
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


    override fun toString() = run {
        when (unit.replace("g", "kg")) {
            "kgm/s^2" -> "N"
            "kgm^2/s^3" -> "W"
            "1/s" -> "Hz"
            "kg/ms^2" -> "Pa"
            "As" -> "C"
            "kgm^2/As^3" -> "V"
            "kgm^2/s^3A^2" -> "Ω"
            "mol/s" -> "kat"
            "A^2s^3/kgm^2" -> "S"
            "A^2s^4/kgm^2" -> "F"
            "kgm^2/As^2" -> "Wb"
            "kg/As^2" -> "T"
            "kgm^2/A^2s^2" -> "H"
            "kgm^2/s^2" -> "J"
            "m^3" -> "kL"
            else -> return unit
        }

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