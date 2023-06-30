package com.radiusagent.assignment.presenter.facility

import android.annotation.SuppressLint
import android.util.Log
import com.radiusagent.assignment.api.Api
import com.radiusagent.assignment.api.Retrofit
import com.radiusagent.assignment.model.AssignmentModel
import com.radiusagent.assignment.model.FacilitiesModel
import com.radiusagent.assignment.presenter.BasePresenter
import com.radiusagent.assignment.realm.RealmDb
import com.radiusagent.assignment.utils.Pref
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FacilityPresenter: BasePresenter<FacilityPresenter.View>() {

    private val TAG = FacilityPresenter::class.java.simpleName

    private val apiService = Retrofit.create()
    private val subscriptions = CompositeDisposable()
    fun getListFromApi()  {
        //check last update
        val lastCheckedMillis=Pref().getLastUpdate()
        val now = System.currentTimeMillis()
        val diffMillis: Long = now - lastCheckedMillis
        if (diffMillis >= 3600000 * 24) {
            Pref().setLastUpdate(now)
            //call api
            val subscriber = apiService!!.getFacilityList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data: AssignmentModel ->
                    RealmDb.saveFacilities(data)
                    view?.notifyDataSetChanged(data)
                }
            subscriptions.add(subscriber)

        } else {
            Log.e(TAG, "getListFromApi: " )
            //get data from database
            val disposable=RealmDb.getFacilities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it != null) {
                        view?.notifyDataSetChanged(it)
                    } else {
                        view?.showError()
                    }
                }
            subscriptions.add(disposable)
        }

    }

    override fun destroy() {
        subscriptions.clear()
    }


    interface View {
        fun notifyDataSetChanged(assignmentModel: AssignmentModel)
        fun showError()
    }
}


