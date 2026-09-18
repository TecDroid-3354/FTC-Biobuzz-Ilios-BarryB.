package org.firstinspires.ftc.teamcode.subsystems.intakeDeploy

import org.firstinspires.ftc.teamcode.utils.devices.configurations.servoControlModeConfiguration.ServoPositionModeConfiguration
import org.firstinspires.ftc.teamcode.utils.units.Angle

class IntakeDeployConstants {
    object configuration{
        val range = Angle.fromDegrees(170.0) // Check this value with design
        val leadServoInverted = false
        val followerServoInverted = false

        val gearRatio = 1.0


        val leadServoConf = ServoPositionModeConfiguration()
            .withInverted(leadServoInverted)
            .withRange(range)

        val followerServoConf = ServoPositionModeConfiguration()
            .withInverted(followerServoInverted)
            .withRange(range)



    }

    object identification{
        val leadServo = "intakeDeployLeadServo"
        val followerServo = "intakeDeployFollowerServo"
    }
}