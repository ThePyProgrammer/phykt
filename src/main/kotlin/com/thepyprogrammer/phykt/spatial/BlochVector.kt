package com.thepyprogrammer.phykt.spatial

import com.thepyprogrammer.linkt.base.Angle
import com.thepyprogrammer.linkt.Vector

/**
 * TODO
 */
open class BlochVector(
    val radial: Double = 1.0,
    val polar: Angle = Angle(),
    val azimuthal: Angle = Angle()
): Vector() {


    fun getCoords(theta: Angle, phi: Angle) {

    }
}