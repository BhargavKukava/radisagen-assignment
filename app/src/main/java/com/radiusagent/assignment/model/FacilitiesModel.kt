package com.radiusagent.assignment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FacilitiesModel(
    @SerializedName("facility_id")
    var facility_id: String="0",
    @SerializedName("name")
    var name: String="",
    @SerializedName("options")
    var options: ArrayList<OptionsModel> = arrayListOf(),
) : Parcelable