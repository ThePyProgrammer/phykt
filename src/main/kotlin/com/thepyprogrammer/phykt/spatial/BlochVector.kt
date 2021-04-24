package com.thepyprogrammer.phykt.spatial

import com.thepyprogrammer.phykt.base.Angle
import com.thepyprogrammer.phykt.linalg.Vector

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