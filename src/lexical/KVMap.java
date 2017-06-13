package lexical;

/**
 * Created by Administrator on 2017/6/13.
 */
class KVMap {
    public final int type;
    public final String value;
    public int row;

    public KVMap(int row, int type, String value) {
        this.row = row;
        this.type = type;
        this.value = value;
    }
}
