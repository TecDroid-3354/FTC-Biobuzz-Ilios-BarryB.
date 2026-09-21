package org.firstinspires.ftc.teamcode.constants

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.PIDCoefficients
import com.seattlesolvers.solverslib.controller.wpilibcontroller.SimpleMotorFeedforward
import org.firstinspires.ftc.teamcode.utils.units.Angle
import org.firstinspires.ftc.teamcode.utils.units.AngularVelocity

object DriveMultipliers {
    const val FORWARD_VELOCITY_MULTIPLIER           : Double    = 1.0
    const val LATERAL_VELOCITY_MULTIPLIER           : Double    = 1.0
    const val TURN_VELOCITY_MULTIPLIER              : Double    = -1.0

    const val CONTROLLER_SOTM_LIMIT_MULTIPLIER                 : Double    = 1.0
}

object SubsystemTolerances {

}

object SubsystemLimits {
    val HOOD_MOVEMENT_LIMITS = Angle(0.0)..Angle.fromDegrees(90.0)
}

object SubsystemPresetTargets {
    // Intake Preset RPM Targets //
    val INTAKE_ROLLERS_FLOOR_RPM = AngularVelocity.fromRpm(4000.0)
    val INTAKE_ROLLERS_FLOWER_RPM = AngularVelocity.fromRpm(4000.0)

    // Shooter Preset RPM Targets //
    val FLYWHEEL_PRESET_RPM = AngularVelocity.fromRpm(3000.0)

    // Hood Preset Angle Targets //
    val HOOD_PRESET_ANGLE = Angle.fromDegrees(90.0)

    val HOOD_HOME_ANGLE = Angle.fromDegrees(90.0)
}

@Configurable
object SubsystemConfigurableTargets {
    // Intake Configurable RPM Targets //
    @JvmField
    var INTAKE_ROLLERS_CONFIGURABLE_RPM = 0.0

    // Shooter Configurable RPM Targets //
    @JvmField
    var FLYWHEEL_CONFIGURABLE_RPM = 0.0

    // Hood Configurable Angle Targets //
    @JvmField
    var HOOD_CONFIGURABLE_ANGLE = 0.0
}

@Configurable
object SubsystemControlGains {
    // Intake PID and Feedforward Configurables //
    @JvmField
    var INTAKE_ROLLERS_MOTOR_PID = PIDCoefficients(0.1, 0.0, 0.0)
    @JvmField
    var INTAKE_ROLLERS_MOTOR_FEEDFORWARD = SimpleMotorFeedforward(0.0, 1.0, 0.0)

    // Shooter PID and Feedforward Configurables //
    @JvmField
    var FLYWHEEL_MOTOR_PID = PIDCoefficients(0.01, 0.0, 0.0)
    @JvmField
    var FLYWHEEL_MOTOR_FEEDFORWARD = SimpleMotorFeedforward(0.0, 1.5, 0.0)
}