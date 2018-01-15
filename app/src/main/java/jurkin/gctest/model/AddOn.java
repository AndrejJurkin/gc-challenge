package jurkin.gctest.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public class AddOn extends RealmObject implements Parcelable {

    @PrimaryKey
    private String id;

    private AddOnCategory category;

    private String name;

    private String description;

    private int displaySeq;

    public String getId() {
        return id;
    }

    public AddOnCategory getCategory() {
        return category;
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
        dest.writeParcelable(this.category, flags);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.displaySeq);
    }

    public AddOn() {
    }

    protected AddOn(Parcel in) {
        this.id = in.readString();
        this.category = in.readParcelable(AddOnCategory.class.getClassLoader());
        this.name = in.readString();
        this.description = in.readString();
        this.displaySeq = in.readInt();
    }

    public static final Parcelable.Creator<AddOn> CREATOR = new Parcelable.Creator<AddOn>() {
        @Override
        public AddOn createFromParcel(Parcel source) {
            return new AddOn(source);
        }

        @Override
        public AddOn[] newArray(int size) {
            return new AddOn[size];
        }
    };
}
