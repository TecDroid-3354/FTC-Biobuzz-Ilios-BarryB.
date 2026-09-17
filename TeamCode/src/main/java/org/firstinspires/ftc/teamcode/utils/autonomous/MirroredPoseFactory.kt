@file:Suppress("unused")

package org.firstinspires.ftc.teamcode.utils.autonomous

import com.pedropathing.api.PoseFactory
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit

abstract class MirroredPoseFactory(unit: AngleUnit) {

    val poseFactory: PoseFactory = when (unit) {
        AngleUnit.DEGREES -> PoseFactory.degrees().mirrorY(70.75).mirrorX(70.75)
        AngleUnit.RADIANS -> PoseFactory.radians().mirrorY(70.75).mirrorX(70.75)
    }
}