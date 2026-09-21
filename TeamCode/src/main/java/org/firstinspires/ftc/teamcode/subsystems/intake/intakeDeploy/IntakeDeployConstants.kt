package org.firstinspires.ftc.teamcode.subsystems.intake.intakeDeploy

import org.firstinspires.ftc.teamcode.utils.devices.configurations.servoControlModeConfiguration.ServoPositionModeConfiguration
import org.firstinspires.ftc.teamcode.utils.units.Angle

object IntakeDeployConstants {

    object Identification{
        const val INTAKE_DEPLOY_LEAD_SERVO_ID = "deployLeadServo"
        const val INTAKE_DEPLOY_FOLLOWER_SERVO_ID = "deployFollowerServo"
    }

    object Configuration{
        // TODO Define if Run to Position is necessary, if not standard Position Mode will be used
    }
}