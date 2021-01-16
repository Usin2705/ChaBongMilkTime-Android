package com.usin.chabongmilktime.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * [MilkTime] is the data class to represent Cha Bong's Milk time text, amount, diaper change
 * and vitamin D intake
 */

data class MilkTime(
    //val intID: String? = null,
    val time: Long? = null,
    val amount: Int? = null,
    val diaper: Boolean? = null,
    val vitaminD: Boolean? = null
    //TODO Add breast milk amount (vs formula milk)
    //TODO("Change vitamin D to int to store amount of drop. Might need more work for parent, should it be?")
)