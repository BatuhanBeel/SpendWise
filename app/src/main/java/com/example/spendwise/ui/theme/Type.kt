package com.example.spendwise.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.spendwise.R


private val fontFamilyInter = FontFamily(
    Font(
        resId = R.font.inter_regular,
    ),
    Font(
        resId = R.font.inter_light,
        weight = FontWeight.Light
    ),
    Font(
        resId = R.font.inter_thin,
        weight = FontWeight.Thin
    ),
    Font(
        resId = R.font.inter_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.inter_extra_bold,
        weight = FontWeight.ExtraBold
    ),
    Font(
        resId = R.font.inter_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.inter_semi_bold,
        weight = FontWeight.SemiBold
    )
)

private val fontFamilyJetbrainsMono = FontFamily(
    Font(
        resId = R.font.jetbrains_mono_regular,
    ),
    Font(
        resId = R.font.jetbrains_mono_light,
        weight = FontWeight.Light
    ),
    Font(
        resId = R.font.jetbrains_mono_thin,
        weight = FontWeight.Thin
    ),
    Font(
        resId = R.font.jetbrains_mono_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.jetbrains_mono_extra_bold,
        weight = FontWeight.ExtraBold
    ),
    Font(
        resId = R.font.jetbrains_mono_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.jetbrains_mono_semi_bold,
        weight = FontWeight.SemiBold
    )
)

val typography = Typography(
    displayLarge = TextStyle(
        fontFamily = fontFamilyJetbrainsMono,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.2).sp
    ),
    displayMedium = TextStyle(
        fontFamily = fontFamilyJetbrainsMono,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = fontFamilyJetbrainsMono,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = fontFamilyJetbrainsMono,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fontFamilyJetbrainsMono,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fontFamilyJetbrainsMono,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fontFamilyInter,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.2.sp
    ),
    titleSmall = TextStyle(
        fontFamily = fontFamilyInter,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (0.5).sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (0.2).sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = (0.4).sp
    ),
    labelLarge = TextStyle(
        fontFamily = fontFamilyInter,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (0.1).sp
    ),
    labelMedium = TextStyle(
        fontFamily = fontFamilyInter,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = (0.5).sp,
    ),
    labelSmall = TextStyle(
        fontFamily = fontFamilyInter,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = (0.5).sp,
    )
)
