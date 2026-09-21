package org.firstinspires.ftc.teamcode.subsystems.mecanum

import com.pedropathing.follower.Follower
import com.pedropathing.follower.ManualDrive
import com.pedropathing.math.Pose
import com.pedropathing.math.Velocity
import com.pedropathing.paths.Path
import com.seattlesolvers.solverslib.command.Command
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.command.RunCommand
import com.seattlesolvers.solverslib.command.SubsystemBase
import com.seattlesolvers.solverslib.gamepad.GamepadEx
import com.seattlesolvers.solverslib.geometry.Rotation2d
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D
import org.firstinspires.ftc.teamcode.utils.Alliance
import org.firstinspires.ftc.teamcode.utils.units.Distance

class Mecanum(
    private val follower: Follower,
    private val controller: GamepadEx,
    private val alliance: Alliance
): SubsystemBase() {

    /**
     * Runs in every loop. Follower and telemetry get updated
     */
    override fun periodic() {
        follower.update()
    }

    /**
     * Retrieves the [controller]'s axis readings and converts them into robot's velocity.
     * The axis get multiplied by each [MecanumConstants.Control] Multiplier and its respective alliance multiplier.
     * @return a [RunCommand] which set the [Follower]'s TeleOp drive to the [controller]'s axis.
     */
    fun driveFollowingDriverInput(): Command {
        return RunCommand({
            val fieldCentricDrive = ManualDrive.fieldCentric(
                -controller.leftY * MecanumConstants.Control.FORWARD_VELOCITY_MULTIPLIER * alliance.multiplier,
                controller.leftX * MecanumConstants.Control.LATERAL_VELOCITY_MULTIPLIER * alliance.multiplier,
                controller.rightX * MecanumConstants.Control.TURN_VELOCITY_MULTIPLIER,
                follower.pose().heading()
            )

            follower.manual(fieldCentricDrive)
        })
            .addRequirements(this)
    }

    /**
     * Gets the Follower's current position.
     * @return a [Pose2D] containing the robot's current position in the standard FTC Coordinates
     */
    fun getPose(): Pose {
        return follower.pose()
    }

    /**
     * Gets the [Follower]'s rotation component.
     * @return a [Rotation2d] as the robot's current heading in radians.
     */
    fun getRotation(): Rotation2d {
        return Rotation2d(getPose().heading())
    }

    /**
     * Gets the current [Follower]'s velocity as a [Velocity]
     * @return the current robot's velocity
     */
    fun getVelocity(): Velocity {
        return follower.velocity()
    }

    /**
     * Gets the distance of the chassis to any target passed to this function.
     * Uses the [Pose.distance] method to calculate the distance.
     * @param target the target to get the distance from
     * @return the distance from the robot's center to the specified [target]
     */
    fun getDistanceTo(target: Pose): Distance {
        val distance = follower.pose().distance(target)

        return Distance.fromInches(distance)
    }

    fun followPathCMD(path: Path, holdEnd: Boolean, maxPower: Double): Command {
        return FollowPathCommand(follower, path, holdEnd, maxPower)
    }

    /**
     * Sets a new [Pose2D] to our robot's chassis.
     * @param pose a pose representing the new robot's [Pose2D]. Note that it must be in STANDARD FTC coordinates.
     */
    fun setPose(pose: Pose) {
        follower.setPose(pose)
    }
}