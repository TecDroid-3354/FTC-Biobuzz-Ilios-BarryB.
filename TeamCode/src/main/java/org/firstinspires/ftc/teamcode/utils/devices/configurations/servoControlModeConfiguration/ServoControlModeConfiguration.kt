package org.firstinspires.ftc.teamcode.utils.devices.configurations.servoControlModeConfiguration

import org.firstinspires.ftc.teamcode.utils.devices.controlModes.ServoControlMode

sealed interface ServoControlModeConfiguration {

    val controlMode: ServoControlMode
}