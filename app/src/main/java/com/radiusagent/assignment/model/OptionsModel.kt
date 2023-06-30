package com.radiusagent.assignment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OptionsModel(
    @SerializedName("name")
    var name: String="",
    @SerializedName("icon")
    var icon: String="",
    @SerializedName("id")
    var id: String="",
) : Parcelable