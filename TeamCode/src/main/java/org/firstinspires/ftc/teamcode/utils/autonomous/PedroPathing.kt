package org.firstinspires.ftc.teamcode.utils.autonomous

import com.pedropathing.algorithm.Foresight
import com.pedropathing.algorithm.ForesightConfig
import com.pedropathing.controllers.Controller
import com.pedropathing.math.Vector2D
import com.pedropathing.revhub.drivetrains.Mecanum
import com.pedropathing.revhub.drivetrains.MecanumConfig
import com.pedropathing.revhub.localizers.OTOSConfig
import com.pedropathing.revhub.localizers.OTOSLocalizer
import com.pedropathing.revhub.localizers.PinpointConfig
import com.pedropathing.revhub.localizers.PinpointLocalizer
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.teamcode.pedroPathing.procedures.ForesightTuner
import org.firstinspires.ftc.teamcode.pedroPathing.procedures.Tests
import org.firstinspires.ftc.teamcode.utils.units.Distance
import org.firstinspires.ftc.teamcode.utils.units.LinearVelocity
import java.util.Optional

object PedroPathing {

    private object MotorDefaultNames {

        // Front right motor default name, declared in the Control HUB configuration
        const val FRONT_RIGHT_MOTOR_NAME: String            = "frontRight"

        // Back right motor default name, declared in the Control HUB configuration
        const val BACK_RIGHT_MOTOR_NAME : String            = "backRight"

        // Front left motor default name, declared in the Control HUB configuration
        const val FRONT_LEFT_MOTOR_NAME : String            = "frontLeft"

        // Back left motor default name, declared in the Control HUB configuration
        const val BACK_LEFT_MOTOR_NAME  : String            = "backLeft"
    }

    // Mecanum default configuration
    private val mecanumDefaultConstants         : MecanumConfig     = MecanumConfig {}
    // GoBilda Pinpoint Odometry computer default configuration
    private val pinpointDefaultConfiguration    : PinpointConfig    = PinpointConfig {}

    init {
        with(mecanumDefaultConstants) {
            frontLeftName       .set(MotorDefaultNames.FRONT_LEFT_MOTOR_NAME)
            backLeftName        .set(MotorDefaultNames.BACK_LEFT_MOTOR_NAME)
            frontRightName      .set(MotorDefaultNames.FRONT_RIGHT_MOTOR_NAME)
            backRightName       .set(MotorDefaultNames.BACK_RIGHT_MOTOR_NAME)

            frontLeftDirection  .set(DcMotorSimple.Direction.REVERSE)
            backLeftDirection   .set(DcMotorSimple.Direction.REVERSE)
            frontRightDirection .set(DcMotorSimple.Direction.FORWARD)
            backRightDirection  .set(DcMotorSimple.Direction.FORWARD)

            manualBrakeMode.set(false)
        }

        with(pinpointDefaultConfiguration) {
            name                .set("pinpoint")
            podType             .set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            xPodDirection       .set(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            yPodDirection       .set(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            xPodOffset          .set(0.0)
            yPodOffset          .set(0.0)
        }
    }

    fun createMecanumConfig(frontLeftDirection: Optional<DcMotorSimple.Direction>, backLeftDirection: Optional<DcMotorSimple.Direction>,
                            frontRightDirection: Optional<DcMotorSimple.Direction>, backRightDirection: Optional<DcMotorSimple.Direction>,
                            useBrakeMode: Optional<Boolean>
    ): MecanumConfig {
        val newConfig = MecanumConfig {}

        if (frontLeftDirection.isPresent) {
            newConfig.frontLeftDirection.set(frontLeftDirection.get())
        }

        if (backLeftDirection.isPresent) {
            newConfig.backLeftDirection.set(backLeftDirection.get())
        }

        if (frontRightDirection.isPresent) {
            newConfig.frontRightDirection.set(frontRightDirection.get())
        }

        if (backRightDirection.isPresent) {
            newConfig.backRightDirection.set(backRightDirection.get())
        }

        if (useBrakeMode.isPresent) {
            newConfig.manualBrakeMode.set(useBrakeMode.get())
        }

        return newConfig
    }

    fun createPinpointConfig(xPodDirection: Optional<GoBildaPinpointDriver.EncoderDirection>, yPodDirection: Optional<GoBildaPinpointDriver.EncoderDirection>,
                             xPodOffset: Optional<Distance>, yPodOffset: Optional<Distance>
    ): PinpointConfig {
        val newConfig = PinpointConfig {}

        if (xPodDirection.isPresent) {
            newConfig.xPodDirection.set(xPodDirection.get())
        }

        if (yPodDirection.isPresent) {
            newConfig.yPodDirection.set(yPodDirection.get())
        }

        if (xPodOffset.isPresent) {
            newConfig.xPodOffset.set(xPodOffset.get().inches)
        }

        if (yPodOffset.isPresent) {
            newConfig.yPodOffset.set(yPodOffset.get().inches)
        }

        return newConfig
    }

    // TODO Finish adding rest of configs
    fun createForesightConfig(forwardVelocity: Optional<LinearVelocity>, strafeVelocity: Optional<LinearVelocity>,
                              forwardDeceleration: Optional<LinearVelocity>, strafeDeceleration: Optional<LinearVelocity>,
                              headingBraking: Optional<Vector2D>, headingKp: Optional<Double>,
    ): ForesightConfig {
        val newConfig = ForesightConfig {}

        if (forwardVelocity.isPresent) {
            newConfig.maxAchievableForwardVelocity.set(forwardVelocity.get().inps)
        }

        if (strafeVelocity.isPresent) {
            newConfig.maxAchievableStrafeVelocity.set(strafeVelocity.get().inps)
        }

        if (forwardDeceleration.isPresent) {
            newConfig.naturalForwardDeceleration.set(forwardDeceleration.get().inps)
        }

        if (strafeDeceleration.isPresent) {
            newConfig.naturalStrafeDeceleration.set(strafeDeceleration.get().inps)
        }

        if (headingBraking.isPresent) {
            newConfig.headingBrakeCoefficients.set(headingBraking.get())
        }

        if (headingKp.isPresent) {
            newConfig.headingFeedback.set(Controller.proportional(headingKp.get()))
        }

        return newConfig
    }

    fun createForesightTunerWithPinpoint(mecanumConfig: MecanumConfig, pinpointConfig: PinpointConfig): ForesightTuner {
        return ForesightTuner(
            { hardwareMap -> PinpointLocalizer(hardwareMap, pinpointConfig) },
            { hardwareMap -> Mecanum(hardwareMap, mecanumConfig) }
        )
    }

    fun createForesightTunerWithOTOS(mecanumConfig: MecanumConfig, otosConfig: OTOSConfig): ForesightTuner {
        return ForesightTuner(
            { hardwareMap -> OTOSLocalizer (hardwareMap, otosConfig) },
            { hardwareMap -> Mecanum (hardwareMap, mecanumConfig) }
        )
    }

    fun createMecanumOnlyTest(mecanumConfig: MecanumConfig): Tests {
        return Tests({ hardwareMap -> Mecanum(hardwareMap, mecanumConfig) }, null, null)
    }

    fun createMecanumWithPinpointTest(mecanumConfig: MecanumConfig, pinpointConfig: PinpointConfig): Tests {
        return Tests(
            { hardwareMap -> Mecanum(hardwareMap, mecanumConfig) },
            { hardwareMap -> PinpointLocalizer(hardwareMap, pinpointConfig) },
            null
        )
    }

    fun createMecanumWithOTOSTest(mecanumConfig: MecanumConfig, otosConfig: OTOSConfig): Tests {
        return Tests(
            { hardwareMap -> Mecanum(hardwareMap, mecanumConfig) },
            { hardwareMap -> OTOSLocalizer(hardwareMap, otosConfig) },
            null
        )
    }

    fun createFullMecanumTestWithPinpoint(mecanumConfig: MecanumConfig, pinpointConfig: PinpointConfig, foresightConfig: ForesightConfig): Tests {
        return Tests(
            { hardwareMap -> Mecanum(hardwareMap, mecanumConfig) },
            { hardwareMap -> PinpointLocalizer(hardwareMap, pinpointConfig) },
            { Foresight(foresightConfig) }
        )
    }

    fun createFullMecanumTestWithOTOS(mecanumConfig: MecanumConfig, otosConfig: OTOSConfig, foresightConfig: ForesightConfig): Tests {
        return Tests(
            { hardwareMap -> Mecanum(hardwareMap, mecanumConfig) },
            { hardwareMap -> OTOSLocalizer(hardwareMap, otosConfig) },
            { Foresight(foresightConfig) }
        )
    }
}