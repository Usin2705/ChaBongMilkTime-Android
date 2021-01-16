package com.usin.chabongmilktime.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * [MilkTime] is the data class to represent Cha Bong's Milk time text, amount, diaper change
 * and vitamin D intake
 */

data class MilkTime(
    val intID: String,
    val stringMilkTime: String,
    val intMilkAmount: Int,
    val isDiaperChange: Boolean,
    val isVitaminD: Boolean
    //TODO("Change vitamin D to int to store amount of drop. Might need more work for parent, should it be?")
)