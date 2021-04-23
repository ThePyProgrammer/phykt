package com.thepyprogrammer.phykt.spatial

import com.thepyprogrammer.phykt.base.Complex
import com.thepyprogrammer.phykt.base.Point

class ArgandVector(val real: Double = 0.0, val imag: Double = 0.0): Vector2D(real, imag) {
    val complex = Complex(real, imag)

    val conjugate: ArgandVector
        get() = ArgandVector(real, -imag)

    val coordinate: Point
        get() = Point(real, imag)

    val polarCoordinate: Point
        get() = Point(real, -imag)
}