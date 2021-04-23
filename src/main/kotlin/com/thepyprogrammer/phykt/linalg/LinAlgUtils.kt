package com.thepyprogrammer.phykt.linalg

import com.thepyprogrammer.ktlib.array.zeros

fun vectorOf(vararg vals: Double) = Vector(*vals)

fun columnVectorOf(vararg vals: Double) = run {
    val list = mutableListOf<Vector>()
    vals.forEach {
        list.add(vectorOf(it))
    }
    Matrix(*list.toTypedArray())
}

fun vectorArrayOf(arr: Array<Array<Double>>) = run {
    val list = mutableListOf<Vector>()
    arr.forEach {
        list.add(vectorOf(*it.toDoubleArray()))
    }
    list.toTypedArray()
}

fun matrixOf(vararg rows: Vector) = Matrix(*rows)

fun zeroMatrixOf(m: Int = 2, n: Int = 2) = run {
    val array = vectorArrayOf(zeros(m, n))
    Matrix(*array)
}