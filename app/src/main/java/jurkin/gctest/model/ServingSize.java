package jurkin.gctest.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public class ServingSize extends RealmObject implements Parcelable {

    private String size;

    private String price;

    public String getSize() {
        return size;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.size);
        dest.writeString(this.price);
    }

    public ServingSize() {
    }

    protected ServingSize(Parcel in) {
        this.size = in.readString();
        this.price = in.readString();
    }

    public static final Creator<ServingSize> CREATOR = new Creator<ServingSize>() {
        @Override
        public ServingSize createFromParcel(Parcel source) {
            return new ServingSize(source);
        }

        @Override
        public ServingSize[] newArray(int size) {
            return new ServingSize[size];
        }
    };
}
