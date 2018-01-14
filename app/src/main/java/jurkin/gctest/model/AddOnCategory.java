package jurkin.gctest.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public class AddOnCategory extends RealmObject {

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
}
