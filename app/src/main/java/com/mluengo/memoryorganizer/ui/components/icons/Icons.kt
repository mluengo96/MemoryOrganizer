package com.mluengo.memoryorganizer.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val App_badging: ImageVector
    get() {
        if (_App_badging != null) {
            return _App_badging!!
        }
        _App_badging = ImageVector.Builder(
            name = "App_badging",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(80f, 480f)
                quadToRelative(0f, -88f, 34f, -163f)
                reflectiveQuadToRelative(93f, -130f)
                reflectiveQuadToRelative(136f, -83.5f)
                reflectiveQuadTo(508f, 81f)
                quadToRelative(17f, 2f, 27f, 14.5f)
                reflectiveQuadToRelative(7f, 29.5f)
                reflectiveQuadToRelative(-16.5f, 27f)
                reflectiveQuadToRelative(-30.5f, 9f)
                quadToRelative(-69f, -3f, -129.5f, 19.5f)
                reflectiveQuadTo(259f, 247f)
                reflectiveQuadToRelative(-72.5f, 103.5f)
                reflectiveQuadTo(160f, 480f)
                quadToRelative(0f, 134f, 93f, 227f)
                reflectiveQuadToRelative(227f, 93f)
                quadToRelative(69f, 0f, 128.5f, -26.5f)
                reflectiveQuadTo(712f, 701f)
                quadToRelative(46f, -48f, 68f, -109f)
                reflectiveQuadToRelative(19f, -127f)
                quadToRelative(-1f, -17f, 9f, -30.5f)
                reflectiveQuadToRelative(27f, -16.5f)
                reflectiveQuadToRelative(29.5f, 7f)
                reflectiveQuadToRelative(14.5f, 27f)
                quadToRelative(6f, 87f, -22.5f, 164f)
                reflectiveQuadTo(774f, 752f)
                quadToRelative(-57f, 62f, -133f, 95f)
                reflectiveQuadTo(480f, 880f)
                quadToRelative(-83f, 0f, -156f, -31.5f)
                reflectiveQuadTo(197f, 763f)
                reflectiveQuadToRelative(-85.5f, -127f)
                reflectiveQuadTo(80f, 480f)
                moveToRelative(640f, -120f)
                quadToRelative(-50f, 0f, -85f, -35f)
                reflectiveQuadToRelative(-35f, -85f)
                reflectiveQuadToRelative(35f, -85f)
                reflectiveQuadToRelative(85f, -35f)
                reflectiveQuadToRelative(85f, 35f)
                reflectiveQuadToRelative(35f, 85f)
                reflectiveQuadToRelative(-35f, 85f)
                reflectiveQuadToRelative(-85f, 35f)
            }
        }.build()
        return _App_badging!!
    }

private var _App_badging: ImageVector? = null
