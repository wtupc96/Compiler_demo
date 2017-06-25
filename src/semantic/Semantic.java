package semantic;

import java.util.ArrayList;

/**
 * Created by wtupc96 on 2017/6/23.
 */
public class Semantic {
    private static final ArrayList<TableItems> symbolTable = new ArrayList<>();
    private static final ArrayList<QuaternionList> quaternions = new ArrayList<>();
    private static final ArrayList<ArrayList<Integer>> dopeVectorList = new ArrayList<>();

    public static ArrayList<ArrayList<Integer>> getDopeVectorList() {
        return dopeVectorList;
    }

    public static ArrayList<TableItems> getSymbolTable() {
        return symbolTable;
    }

    public static ArrayList<QuaternionList> getQuaternions() {
        return quaternions;
    }
}
