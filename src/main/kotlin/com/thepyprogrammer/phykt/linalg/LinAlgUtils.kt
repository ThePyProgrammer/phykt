package com.thepyprogrammer.phykt.linalg

import com.thepyprogrammer.ktlib.array.DoubleArray2D
import com.thepyprogrammer.ktlib.array.each
import com.thepyprogrammer.ktlib.array.zeros

typealias VectorArray = Array<Vector>

fun vectorOf(vararg vals: Double) = Vector(*vals)
inline fun <reified T: Number> vectorOf(vararg vals: T) = Vector(*vals.each { it.toDouble() }.toDoubleArray())

fun columnVectorOf(vararg vals: Double) = vals.toTypedArray().toColumnVector()
inline fun <reified T: Number> columnVectorOf(vararg vals: T) = vals.each{ it.toDouble() }.toColumnVector()
fun columnVectorOf(vec: Vector) = vec.toTypedArray().toColumnVector()

fun vectorArrayOf(arr: DoubleArray2D): VectorArray = arr.vectorize()

fun matrixOf(vararg rows: Vector) = Matrix(*rows)
fun matrixOf(rows: List<Vector>) = Matrix(*rows.toTypedArray())
fun matrixOf(array: DoubleArray2D) = array.toMatrix()

fun zeroVectorOf(size: Int) = vectorOf(*zeros(size))
fun zeroMatrixOf(m: Int = 2, n: Int = 2) = Matrix(*vectorArrayOf(zeros(m, n)))
fun zeroMatrixOf(size: Int) = matrixOf((0..size).each { zeroVectorOf(it) }.toList())
fun identityMatrixOf(size: Int) = zeroMatrixOf(size).apply { (0..size).forEach { this[it, it] = 1.0 } }

fun DoubleArray.toVector(): Vector = vectorOf(*this)
fun Array<Double>.toVector(): Vector = toDoubleArray().toVector()

inline fun <reified T: Number> Array<T>.toColumnVector(): Matrix = matrixOf(*each { vectorOf(it) })
fun DoubleArray.toColumnVector(): Matrix = toTypedArray().toColumnVector()
inline fun <reified T: Number> List<T>.toColumnVector(): Matrix = toTypedArray().toColumnVector()

fun DoubleArray2D.vectorize(): Array<Vector> = each { it.toVector() }
fun DoubleArray2D.toMatrix() = matrixOf(*vectorize())