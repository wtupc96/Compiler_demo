package lexical;

/**
 * Created by Administrator on 2017/6/13.
 */
public class KVMap {
    public final int type;
    public final String value;
    public int row;
    public String classi;

    public KVMap(int row, int type, String value, String classi) {
        this.row = row;
        this.type = type;
        this.value = value;
        this.classi = classi;
    }
}
