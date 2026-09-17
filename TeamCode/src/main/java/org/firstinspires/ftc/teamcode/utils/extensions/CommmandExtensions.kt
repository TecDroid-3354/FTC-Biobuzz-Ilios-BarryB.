@file:Suppress("FunctionName", "unused")

package org.firstinspires.ftc.teamcode.utils.extensions

import com.seattlesolvers.solverslib.command.Command
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.command.RunCommand
import com.seattlesolvers.solverslib.command.SubsystemBase
import com.seattlesolvers.solverslib.command.WaitCommand
import com.seattlesolvers.solverslib.command.WaitUntilCommand
import org.firstinspires.ftc.teamcode.utils.units.Time

/**
 * Just creates an [InstantCommand] based of the [Runnable] and with the desired [com.seattlesolvers.solverslib.command.SubsystemBase] requirements.
 * @param requirements The [com.seattlesolvers.solverslib.command.SubsystemBase]s necessary to perform the [Runnable]
 * @return an [InstantCommand] with the above specifications.
 */
fun Runnable.InstantCommand(vararg requirements: SubsystemBase): InstantCommand {
    return InstantCommand(this, *requirements)
}

/**
 * Just creates a [RunCommand] based of the [Runnable] and with the desired [SubsystemBase] requirements.
 * @param requirements The [SubsystemBase]s necessary to perform the [Runnable]
 * @return a [RunCommand] with the above specifications.
 */
fun Runnable.RunCommand(vararg requirements: SubsystemBase): RunCommand {
    return RunCommand(this, *requirements)
}

/**
 * Performs a [WaitUntilCommand] with the specified condition, and then performs an [InstantCommand] with the
 * specified requirements
 * @param condition What must become true before scheduling the [InstantCommand]
 * @param requirements The [com.seattlesolvers.solverslib.command.SubsystemBase]s necessary to perform the [Runnable]
 * @return a [Command] with the above specifications.
 */
fun Runnable.InstantCommandAfterCondition(condition: () -> Boolean, vararg requirements: SubsystemBase): Command {
    return WaitUntilCommand(condition).andThen(this.InstantCommand(*requirements))
}

/**
 * Performs a [WaitUntilCommand] with the specified time delay, and then performs an [InstantCommand] with the
 * specified requirements
 * @param time How long before scheduling the [InstantCommand]
 * @param requirements The [SubsystemBase]s necessary to perform the [Runnable]
 * @return a [Command] with the above specifications.
 */
fun Runnable.InstantCommandAfterTime(time: Time, vararg requirements: SubsystemBase): Command {
    return WaitCommand(time.millis.toLong()).andThen(this.InstantCommand(*requirements))
}