package com.radiusagent.assignment.api

import com.radiusagent.assignment.model.AssignmentModel
import io.reactivex.Observable
import retrofit2.http.GET

interface  Api {
    @GET("ad-assignment/db")
    fun getFacilityList(): Observable<AssignmentModel>
}