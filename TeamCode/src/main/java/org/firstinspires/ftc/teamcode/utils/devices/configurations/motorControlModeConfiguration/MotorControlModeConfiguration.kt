package org.firstinspires.ftc.teamcode.utils.devices.configurations.motorControlModeConfiguration

import org.firstinspires.ftc.teamcode.utils.devices.controlModes.MotorControlMode

sealed interface MotorControlModeConfiguration {

    val controlMode: MotorControlMode
}