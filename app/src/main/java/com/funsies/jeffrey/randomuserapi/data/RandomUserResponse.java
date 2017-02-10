
package com.funsies.jeffrey.randomuserapi.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

public class RandomUserResponse implements Parcelable
{

    @SerializedName("results")
    @Expose
    private List<Person> results = null;
    @SerializedName("info")
    @Expose
    private Info info;
    public final static Creator<RandomUserResponse> CREATOR = new Creator<RandomUserResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RandomUserResponse createFromParcel(Parcel in) {
            RandomUserResponse instance = new RandomUserResponse();
            in.readList(instance.results, (Person.class.getClassLoader()));
            instance.info = ((Info) in.readValue((Info.class.getClassLoader())));
            return instance;
        }

        public RandomUserResponse[] newArray(int size) {
            return (new RandomUserResponse[size]);
        }

    }
    ;

    private void k(JSONObject k) {

    }

    public List<Person> getResults() {
        return results;
    }

    public void setResults(List<Person> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
        dest.writeValue(info);
    }

    public int describeContents() {
        return  0;
    }

}
