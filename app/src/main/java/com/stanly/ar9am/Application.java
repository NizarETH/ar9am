package com.stanly.ar9am;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by nizar on 07/07/17.
 */

public class Application extends RealmObject {

    @PrimaryKey
    private int id;
    private RealmList<girl> girls;
    private RealmList<ElementSwipe> elements;


    public RealmList<ElementSwipe> getElements() {
        return elements;
    }

    public void setElements(RealmList<ElementSwipe> elements) {
        this.elements = elements;
    }

    public int getId_app() {
        return id;
    }

    public void setId_app(int id) {
        this.id = id;
    }

    public RealmList<girl> getGirls() {
        return girls;
    }

    public void setGirls(RealmList<girl> girls) {
        this.girls = girls;
    }

    public Application() {
    }

    public Application(int id, RealmList<girl> girls) {
        this.id = id;
        this.girls = girls;
    }
}
