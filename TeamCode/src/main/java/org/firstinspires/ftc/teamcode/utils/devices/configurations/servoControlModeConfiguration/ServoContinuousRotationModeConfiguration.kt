package org.firstinspires.ftc.teamcode.utils.devices.configurations.servoControlModeConfiguration

import org.firstinspires.ftc.teamcode.utils.devices.controlModes.ServoControlMode

class ServoContinuousRotationModeConfiguration: ServoControlModeConfiguration {

    override val controlMode    : ServoControlMode
        get() = ServoControlMode.CONTINUOUS_ROTATION

    var inverted                : Boolean   = false

    var maxPower                : Double    = 1.0

    fun withInverted(value: Boolean)    : ServoContinuousRotationModeConfiguration {
        this.inverted = value
        return this
    }

    fun withMaxPower(value: Double)     : ServoContinuousRotationModeConfiguration {
        this.maxPower = value
        return this
    }
}