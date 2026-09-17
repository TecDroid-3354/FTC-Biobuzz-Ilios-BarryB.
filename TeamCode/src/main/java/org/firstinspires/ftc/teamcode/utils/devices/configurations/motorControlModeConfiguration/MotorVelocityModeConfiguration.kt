package org.firstinspires.ftc.teamcode.utils.devices.configurations.motorControlModeConfiguration

import com.qualcomm.robotcore.hardware.PIDCoefficients
import com.seattlesolvers.solverslib.controller.wpilibcontroller.SimpleMotorFeedforward
import org.firstinspires.ftc.teamcode.utils.devices.controlModes.MotorControlMode

class MotorVelocityModeConfiguration: MotorControlModeConfiguration {

    override val controlMode        : MotorControlMode
        get() = MotorControlMode.VELOCITY

    var velocityCoefficients        : PIDCoefficients           = PIDCoefficients()

    var feedforwardCoefficients     : SimpleMotorFeedforward    = SimpleMotorFeedforward(0.0, 0.0, 0.0)

    fun withVelocityCoefficients(value: PIDCoefficients)        : MotorVelocityModeConfiguration {
        this.velocityCoefficients = value
        return this
    }

    fun withFeedforwardCoefficients(value: SimpleMotorFeedforward): MotorVelocityModeConfiguration {
        this.feedforwardCoefficients = value
        return this
    }
}