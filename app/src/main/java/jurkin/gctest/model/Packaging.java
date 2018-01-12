package jurkin.gctest.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public class Packaging extends RealmObject implements Parcelable {

    private String description;

    private float price;

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeFloat(this.price);
    }

    public Packaging() {
    }

    protected Packaging(Parcel in) {
        this.description = in.readString();
        this.price = in.readFloat();
    }

    public static final Parcelable.Creator<Packaging> CREATOR = new Parcelable.Creator<Packaging>() {
        @Override
        public Packaging createFromParcel(Parcel source) {
            return new Packaging(source);
        }

        @Override
        public Packaging[] newArray(int size) {
            return new Packaging[size];
        }
    };
}
