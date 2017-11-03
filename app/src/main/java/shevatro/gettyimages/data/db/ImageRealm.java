package shevatro.gettyimages.data.db;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class ImageRealm extends RealmObject {
    public static final String DT_NAME = "dt";
    @Required
    private String name;
    @Required
    private String uri;
    //used for sorting
    private long dt = System.currentTimeMillis();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}