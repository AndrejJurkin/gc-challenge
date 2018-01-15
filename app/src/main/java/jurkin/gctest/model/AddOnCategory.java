package jurkin.gctest.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public class AddOnCategory extends RealmObject implements Parcelable {

    @PrimaryKey
    private String id;

    private String name;

    private String description;

    private int displaySeq;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDisplaySeq() {
        return displaySeq;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.displaySeq);
    }

    public AddOnCategory() {
    }

    protected AddOnCategory(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.displaySeq = in.readInt();
    }

    public static final Parcelable.Creator<AddOnCategory> CREATOR = new Parcelable.Creator<AddOnCategory>() {
        @Override
        public AddOnCategory createFromParcel(Parcel source) {
            return new AddOnCategory(source);
        }

        @Override
        public AddOnCategory[] newArray(int size) {
            return new AddOnCategory[size];
        }
    };
}
