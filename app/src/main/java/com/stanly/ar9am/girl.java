package com.stanly.ar9am;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by nizar on 07/07/17.
 */

public class girl extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String ville;
    private String tel;
    private String photo;

    public int getId_girl() {
        return id;
    }

    public void setId_girl(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public girl() {
    }

    public girl(int id, String name, String ville, String tel, String photo) {
        this.id = id;
        this.name = name;
        this.ville = ville;
        this.tel = tel;
        this.photo = photo;
    }
}
