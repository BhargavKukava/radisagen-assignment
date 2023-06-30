package com.radiusagent.assignment.realm;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.radiusagent.assignment.model.AssignmentModel;
import com.radiusagent.assignment.model.ExclusionsModel;
import com.radiusagent.assignment.model.FacilitiesModel;
import com.radiusagent.assignment.realm.model.ExclusionData;
import com.radiusagent.assignment.realm.model.FacilitiesData;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmDb {

    public static void init(Application app) {
        Realm.init(app);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static Realm getRealm() {
        return Realm.getDefaultInstance();
    }

    public static void saveFacilities(AssignmentModel results) {
        Realm realm = getRealm();
        realm.beginTransaction();
        realm.delete(FacilitiesData.class);

        FacilitiesData facility = new FacilitiesData();
        facility.setJson(new Gson().toJson(results));
        facility.setId("1");

        realm.copyToRealmOrUpdate(facility);
        realm.commitTransaction();
        realm.close();
    }

    public static Observable<AssignmentModel> getFacilities() {
        Realm realm = getRealm();
        RealmResults<FacilitiesData> results = realm.where(FacilitiesData.class).findAll();
        List<FacilitiesData> unmanagedResults = realm.copyFromRealm(results);

        try {
            Gson gson = new Gson();
            Type type = new TypeToken<AssignmentModel>() {
            }.getType();
            AssignmentModel assignmentModel= gson.fromJson(unmanagedResults.get(0).getJson(), type);
            assert assignmentModel != null;
            return Observable.just(assignmentModel);
        } catch (Exception e) {
            return  Observable.just(new AssignmentModel());
        }
    }
}
