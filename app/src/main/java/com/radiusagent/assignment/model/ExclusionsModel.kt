package com.radiusagent.assignment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExclusionsModel(
    @SerializedName("facility_id")
    var facility_id: String="0",
    @SerializedName("options_id")
    var options_id: String="",
) : RealmModel,Parcelable