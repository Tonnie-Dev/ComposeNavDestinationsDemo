package com.plcoding.composenavdestinationsdemo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

//Parcelable which Compose Navigation don't support
@Parcelize
data class User(
    val name: String,
    val id: String,
    val created: LocalDateTime
): Parcelable
