package com.stanly.ar9am;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by nizar on 08/07/17.
 */

public class Data extends RealmObject {

    @PrimaryKey
    private int id;
    private String date;
    private RealmList<girl> storedGirls;

    public RealmList<girl> getStoredGirls() {
        return storedGirls;
    }

    public void setStoredGirls(RealmList<girl> storedGirls) {
        this.storedGirls = storedGirls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Data() {
    }

    public Data(int id, String date) {
        this.id = id;
        this.date = date;
    }
}
