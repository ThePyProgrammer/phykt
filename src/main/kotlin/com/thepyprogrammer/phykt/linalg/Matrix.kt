package com.thepyprogrammer.phykt.linalg

import com.thepyprogrammer.ktlib.array.zeros

class Matrix(vararg rows: Vector): ArrayList<Vector>(rows.toList()) {
    init {
        forEach {
            val addSize = n - it.size
            it.addAll(zeros(addSize))
        }
    }

    val m: Int
        get() = size

    val n: Int
        get() {
            var maxSize = 0
            forEach {
                if(it.size > maxSize) maxSize = it.size
            }
            return maxSize
        }

    infix operator fun times(other: Matrix) = run {
        val minSize = minOf(n, other.m)
        val matrix = zeroMatrixOf()
        for(i in 0..minSize) {
            for(j in 0..minSize) {
                matrix[i, j] = getColumn(i) * other[j]
            }
        }
        matrix
    }

    infix operator fun times(other: Vector) = this * Matrix(other)

    fun getColumn(index: Int) = run {
        val arr = mutableListOf<Double>()
        forEach {
            if(index >= it.size) arr.add(0.0)
            else arr.add(it[index])
        }
        vectorOf(*arr.toDoubleArray())
    }

    operator fun get(m: Int, n: Int) = this[m][n]

    infix operator fun get(index: Pair<Int, Int>) = this[index.first, index.second]

    operator fun set(m: Int, n: Int, other: Double) {
        this[m][n] = other
    }
    operator fun set(index: Pair<Int, Int>, other: Double) {
        this[index.first, index.second] = other
    }

}