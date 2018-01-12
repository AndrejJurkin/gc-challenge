package jurkin.gctest.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public class ServingSize implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public ServingSize() {
    }

    protected ServingSize(Parcel in) {
    }

    public static final Parcelable.Creator<ServingSize> CREATOR = new Parcelable.Creator<ServingSize>() {
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
