
package com.funsies.jeffrey.randomuserapi.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name implements Parcelable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("first")
    @Expose
    private String first;
    @SerializedName("last")
    @Expose
    private String last;
    public final static Creator<Name> CREATOR = new Creator<Name>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Name createFromParcel(Parcel in) {
            Name instance = new Name();
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            instance.first = ((String) in.readValue((String.class.getClassLoader())));
            instance.last = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Name[] newArray(int size) {
            return (new Name[size]);
        }

    }
    ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(first);
        dest.writeValue(last);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return first + " " + last;
    }
}
