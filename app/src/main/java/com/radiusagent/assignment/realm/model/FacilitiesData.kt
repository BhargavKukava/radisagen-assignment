package com.radiusagent.assignment.realm.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class FacilitiesData:RealmModel {
    @PrimaryKey
    var id=""
    var json:String?=""
}