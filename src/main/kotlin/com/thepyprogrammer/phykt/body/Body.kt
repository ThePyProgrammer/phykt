package com.thepyprogrammer.phykt.body

import com.thepyprogrammer.phykt.types.*
import com.thepyprogrammer.phykt.unit.kg
import com.thepyprogrammer.phykt.unit.m
import com.thepyprogrammer.phykt.unit.s

class Body {
    val mass: Mass = Mass(1.0)

    val position: Distance = Distance(0.0)
    val velocity: Velocity = Velocity(0.0)
    val acceleration: Acceleration = Acceleration(0.0)

    val momentum: Momentum
        get() = (mass * velocity) as Momentum

    val netForce: Force
        get() = (mass * acceleration) as Force

    val kineticEnergy: Energy
        get() = (mass * velocity.pow(2) / 2) as Energy
}