package jurkin.gctest.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */
public class Meal extends RealmObject implements Parcelable {

    @PrimaryKey
    private String id;

    private MealCategory category;

    private String name;

    private String description;

    private ServingSize servingSize;

    private RealmList<String> addOnIds;

    public Meal() {

    }

    public String getId() {
        return id;
    }

    public MealCategory getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return "€" + this.servingSize.getPrice();
    }

    public String getServingSize() {
        return this.servingSize.getSize();
    }

    public RealmList<String> getAddOnIds() {
        return addOnIds;
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
        dest.writeParcelable(this.servingSize, flags);
    }

    protected Meal(Parcel in) {
        this.id = in.readString();
        this.category = in.readParcelable(MealCategory.class.getClassLoader());
        this.name = in.readString();
        this.description = in.readString();
        this.servingSize = in.readParcelable(ServingSize.class.getClassLoader());
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel source) {
            return new Meal(source);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };
}
