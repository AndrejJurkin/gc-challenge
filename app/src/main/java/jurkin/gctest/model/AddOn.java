package jurkin.gctest.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public class AddOn extends RealmObject {

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
}
