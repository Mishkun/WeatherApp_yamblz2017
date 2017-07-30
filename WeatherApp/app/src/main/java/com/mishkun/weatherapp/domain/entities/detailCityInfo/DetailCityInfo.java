
package com.mishkun.weatherapp.domain.entities.detailCityInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailCityInfo {

    @SerializedName("result")
    @Expose
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
