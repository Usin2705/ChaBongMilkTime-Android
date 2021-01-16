package com.usin.chabongmilktime.utils

import java.text.SimpleDateFormat
import java.util.*

object Const {
    var dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
    var sdfDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    var simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    var shortDateFormat = SimpleDateFormat("dd-MMM  HH:mm", Locale.getDefault())
    var shortTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    const val PATH = "/data/"
    const val TIME = "time"
    const val AMOUNT = "amount"
    const val DIAPER = "diaper"
    const val VITAMIN = "vitaminD"

}