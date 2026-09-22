package org.firstinspires.ftc.teamcode.autonomous.paths.examplePaths

import com.pedropathing.api.Paths.*
import com.pedropathing.paths.Path
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.teamcode.utils.Alliance
import org.firstinspires.ftc.teamcode.utils.autonomous.MirroredPoseFactory

/**
 * Example line to follow with Pedro Pathing.
 * It will move forward by 48 inches and rotate 90 degrees in the counterclockwise direction
 */
class Line(alliance: Alliance): MirroredPoseFactory(AngleUnit.DEGREES, alliance) {
    private val start = poseFactory.of(36.0, 10.0, 90.0)
    private val path1 = poseFactory.of(36.0, 58.0, 180.0)

    fun line(): Path = line(start, path1).linear(start, path1)
}