package com.thepyprogrammer.phykt.linalg

import com.thepyprogrammer.ktlib.array.DoubleArray2D
import com.thepyprogrammer.ktlib.array.each
import com.thepyprogrammer.ktlib.array.zeros

fun vectorOf(vararg vals: Double) = Vector(*vals)

fun columnVectorOf(vararg vals: Double) = vals.toTypedArray().toColumnVector()

fun vectorArrayOf(arr: DoubleArray2D): VectorArray = arr.vectorize()

fun matrixOf(vararg rows: Vector) = Matrix(*rows)

fun matrixOf(rows: List<Vector>) = Matrix(*rows.toTypedArray())

fun matrixOf(matrix: DoubleArray2D) = matrixOf(*matrix.vectorize())

fun zeroMatrixOf(m: Int = 2, n: Int = 2) = run {
    val array = vectorArrayOf(zeros(m, n))
    Matrix(*array)
}

typealias VectorArray = Array<Vector>

fun DoubleArray.toVector(): Vector = vectorOf(*this)
fun Array<Double>.toVector(): Vector = toDoubleArray().toVector()


fun Array<Double>.toColumnVector(): Matrix = matrixOf(
    *each { vectorOf(it) }
)

fun DoubleArray.toColumnVector(): Matrix = toTypedArray().toColumnVector()

fun DoubleArray2D.vectorize(): Array<Vector> = each { it.toVector() }

