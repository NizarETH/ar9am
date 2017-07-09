/**
 * 
 */
package com.stanly.ar9am;




import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class ElementSwipe  extends RealmObject {

   @PrimaryKey
   private int id;


	private String image;
	private String caption;
	private int page_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {

        this.image = image;


        }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }



    public ElementSwipe() {
    }

    public ElementSwipe(int id, String image, String caption, int page_id) {
        this.id = id;
        this.image = image;
        this.caption = caption;
        this.page_id = page_id;
    }
}