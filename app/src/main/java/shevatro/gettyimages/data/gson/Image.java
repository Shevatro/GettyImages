
package shevatro.gettyimages.data.gson;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("display_sizes")
    @Expose
    private List<DisplaySize> displaySizes = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<DisplaySize> getDisplaySizes() {
        return displaySizes;
    }

    public void setDisplaySizes(List<DisplaySize> displaySizes) {
        this.displaySizes = displaySizes;
    }

}
