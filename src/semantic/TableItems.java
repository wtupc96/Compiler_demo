package semantic;

/**
 * Created by wtupc96 on 2017/6/23.
 */
public class TableItems {
    public String name;
    public int type;
    public String value;

    public TableItems(String name, int type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(Name: " + name + ", Type: " + type + ", Value: " + value + ")";
    }
}
