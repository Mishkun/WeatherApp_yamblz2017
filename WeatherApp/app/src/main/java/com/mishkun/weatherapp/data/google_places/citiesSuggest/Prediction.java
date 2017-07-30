
package com.mishkun.weatherapp.data.google_places.citiesSuggest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prediction {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("place_id")
    @Expose
    private String placeId;

    public Prediction(String description, String id, String placeId) {
        this.description = description;
        this.id = id;
        this.placeId = placeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prediction that = (Prediction) o;

        if (!description.equals(that.description)) return false;
        if (!id.equals(that.id)) return false;
        return placeId.equals(that.placeId);
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + placeId.hashCode();
        return result;
    }
}
