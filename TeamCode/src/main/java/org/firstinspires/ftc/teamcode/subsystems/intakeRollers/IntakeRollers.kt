package org.firstinspires.ftc.teamcode.subsystems.intakeRollers

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.Command
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.command.SubsystemBase
import org.firstinspires.ftc.teamcode.utils.devices.OpMotorEx

class IntakeRollers(hardwareMap : HardwareMap) : SubsystemBase() {
    lateinit var intakeRollersMotor: OpMotorEx
    init {
        intakeRollersMotor = OpMotorEx(hardwareMap,IntakeRollersConstants.identification.intakeId)
        configureMotors()
    }

    fun configureMotors(){
        intakeRollersMotor.applyConfigurationAndResetEncoder(IntakeRollersConstants.configuration.conf)
    }

    fun enableIntakeRollers(){
        intakeRollersMotor.setPower(1.0)
    }

    fun disableIntakeRollers(){
        intakeRollersMotor.setPower(0.0)
    }

    fun enableIntakeRollersCMD(): Command {
        return InstantCommand({ enableIntakeRollers() })
    }

    fun disableIntakeRollersCMD() : Command {
        return InstantCommand({disableIntakeRollers()})
    }

}