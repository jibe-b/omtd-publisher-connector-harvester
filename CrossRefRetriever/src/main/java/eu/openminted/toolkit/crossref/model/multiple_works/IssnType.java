
package eu.openminted.toolkit.crossref.model.multiple_works;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IssnType {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("type")
    @Expose
    private String type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
