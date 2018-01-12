package jurkin.gctest.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public class MealCategory extends RealmObject implements Parcelable {

    @PrimaryKey
    private String id;

    private String name;

    private RealmList<Meal> meals;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public MealCategory() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeTypedList(this.meals);
    }

    protected MealCategory(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.meals = new RealmList<>();
        this.meals.addAll(in.createTypedArrayList(Meal.CREATOR));
    }

    public static final Creator<MealCategory> CREATOR = new Creator<MealCategory>() {
        @Override
        public MealCategory createFromParcel(Parcel source) {
            return new MealCategory(source);
        }

        @Override
        public MealCategory[] newArray(int size) {
            return new MealCategory[size];
        }
    };
}
