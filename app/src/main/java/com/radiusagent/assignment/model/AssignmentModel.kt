package com.radiusagent.assignment.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap
import kotlinx.parcelize.Parcelize
import java.util.HashMap

@Parcelize
data class AssignmentModel(
    @SerializedName("facilities")
    var facilities: List<FacilitiesModel> = arrayListOf(),

    @SerializedName("exclusions")
    var exclusions:List<List<ExclusionsModel>> = arrayListOf(),
) : Parcelable