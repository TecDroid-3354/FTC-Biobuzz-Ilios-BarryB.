package org.firstinspires.ftc.teamcode.subsystems.intakeRollers

import com.qualcomm.robotcore.hardware.DcMotor
import com.seattlesolvers.solverslib.hardware.motors.Motor
import org.firstinspires.ftc.teamcode.utils.configurations.OpMotorExConfiguration
import org.firstinspires.ftc.teamcode.utils.devices.configurations.genericConfigurations.GenericMotorConfiguration
import org.firstinspires.ftc.teamcode.utils.devices.configurations.motorControlModeConfiguration.MotorPercentageModeConfiguration

class IntakeRollersConstants {
    object configuration{
        val isInverted = false
        val reduction = 1.0
        val zeroPowerBehavior = Motor.ZeroPowerBehavior.FLOAT

        val maxPower = 1.0

        val genericConf = GenericMotorConfiguration()
            .withGearRatio(reduction)
            .withInverted(isInverted)
            .withZeroPowerBehavior(zeroPowerBehavior)

        val controlConf = MotorPercentageModeConfiguration()
            .withMaxPower(maxPower)

        val conf = OpMotorExConfiguration()
            .withGenericMotorConfiguration(genericConf)
            .withControlModeConfiguration(controlConf)
    }

    object identification {
        val intakeId = "intakeMotor"

    }

}