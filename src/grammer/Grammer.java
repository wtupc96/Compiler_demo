package grammer;

import lexical.KVMap;
import lexical.Scan;

import java.util.ArrayList;
import java.util.Stack;

import static lexical.Scan.getResultList;
import static lexical.Scan.input;

/**
 * Created by Administrator on 2017/6/14.
 */
public class Grammer {
    public static final ArrayList<Step> stateTableA = new ArrayList<>();
    public static final ArrayList<Step> stateTableH = new ArrayList<>();
    public static final ArrayList<Step> stateTableX = new ArrayList<>();
    public static final ArrayList<Step> stateTableI = new ArrayList<>();
    public static final ArrayList<Step> stateTableI2 = new ArrayList<>();
    public static final ArrayList<Step> stateTableF = new ArrayList<>();
    public static final ArrayList<Step> stateTableF2 = new ArrayList<>();
    public static final ArrayList<Step> stateTableY = new ArrayList<>();
    public static final ArrayList<Step> stateTableB = new ArrayList<>();
    public static final ArrayList<Step> stateTableJ = new ArrayList<>();
    public static final ArrayList<Step> stateTableJ2 = new ArrayList<>();
    public static final ArrayList<Step> stateTableC = new ArrayList<>();
    public static final ArrayList<Step> stateTableD = new ArrayList<>();
    public static final ArrayList<Step> stateTableD2 = new ArrayList<>();
    public static final ArrayList<Step> stateTableG = new ArrayList<>();
    public static final ArrayList<Step> stateTableG2 = new ArrayList<>();
    public static final ArrayList<Step> stateTableZ = new ArrayList<>();
    public static final ArrayList<Step> stateTableK = new ArrayList<>();
    public static final ArrayList<Step> stateTableK2 = new ArrayList<>();
    public static final ArrayList<Step> stateTableE = new ArrayList<>();
    public static final ArrayList<Step> stateTableM = new ArrayList<>();
    public static final ArrayList<Step> stateTableS = new ArrayList<>();
    public static final ArrayList<ArrayList<Step>> stateTable = new ArrayList<>();
    public static final String[] generators = {
            "B=F;", "identifierC", "[D]", "episilon", "FE", ",FE", "episilon", "++FG", "--FG", "(F)G", "-FG", "identifierG", "constantG",
            "HG", "episilon", "+F", "-F", "*F", "classJ;", "BK", ",BK", "episilon", "identifier(J);", "if(O)then{V}N", "else{V}", "episilon",
            "!OP", "(O)P", "BropBP", "TRUEP", "FALSEP", "QP", "episilon", "&O", "|G", "switch(B){R}", "caseB:{V}S", "caseB:{V}S", "episilon",
            "while(O)do{V}", "do{V}while(O);", "UV", "episilon", "A", "I", "L", "M", "T"
    };
    public static ArrayList<KVMap> kvMapArrayList;
    private static Stack<String> analyseStack = new Stack<>();

    public static void init() {
        {
            stateTableA.add(new Step("identifier", 0));
        }
        {
            stateTableH.add(new Step("identifier", 1));
        }
        {
            stateTableX.add(new Step("[", 2));
            stateTableX.add(new Step(")", 3));
            stateTableX.add(new Step(",", 3));
            stateTableX.add(new Step("&", 3));
            stateTableX.add(new Step("|", 3));
            stateTableX.add(new Step("rop", 3));
            stateTableX.add(new Step("=", 3));
            stateTableX.add(new Step(":", 3));
            stateTableX.add(new Step(";", 3));
        }
        {
            stateTableI.add(new Step("identifier", 4));
            stateTableI.add(new Step("++", 4));
            stateTableI.add(new Step("--", 4));
            stateTableI.add(new Step("(", 4));
            stateTableI.add(new Step("-", 4));
            stateTableI.add(new Step("constant", 4));
        }
        {
            stateTableI2.add(new Step("]", 6));
            stateTableI2.add(new Step(",", 5));
        }
        {
            stateTableF.add(new Step("identifier", 11));
            stateTableF.add(new Step("++", 7));
            stateTableF.add(new Step("--", 8));
            stateTableF.add(new Step("(", 9));
            stateTableF.add(new Step("-", 10));
            stateTableF.add(new Step("constant", 12));
        }
        {
            stateTableF2.add(new Step("]", 14));
            stateTableF2.add(new Step(")", 14));
            stateTableF2.add(new Step("+", 13));
            stateTableF2.add(new Step("-", 13));
            stateTableF2.add(new Step("*", 13));
            stateTableF2.add(new Step(",", 14));
            stateTableF2.add(new Step(";", 14));
        }
        {
            stateTableY.add(new Step("+", 15));
            stateTableY.add(new Step("-", 16));
            stateTableY.add(new Step("*", 17));
        }
        {
            stateTableB.add(new Step("class", 18));
        }
        {
            stateTableJ.add(new Step("identifier", 19));
        }
        {
            stateTableJ2.add(new Step(")", 21));
            stateTableJ2.add(new Step(",", 20));
            stateTableJ2.add(new Step(";", 21));
        }
        {
            stateTableC.add(new Step("identifier", 22));
        }
        {
            stateTableD.add(new Step("if", 23));
            stateTableD.add(new Step("switch", 35));
        }
        {
            stateTableD2.add(new Step("else", 24));
        }
        {
            stateTableG.add(new Step("identifier", 28));
            stateTableG.add(new Step("(", 27));
            stateTableG.add(new Step("!", 26));
            stateTableG.add(new Step("TRUE", 29));
            stateTableG.add(new Step("FALSE", 30));
        }
        {
            stateTableG2.add(new Step(")", 32));
            stateTableG2.add(new Step("&", 31));
            stateTableG2.add(new Step("|", 31));
        }
        {
            stateTableZ.add(new Step("&", 33));
            stateTableZ.add(new Step("|", 34));
        }
        {
            stateTableK.add(new Step("case", 36));
        }
        {
            stateTableK2.add(new Step("case", 38));
            stateTableK2.add(new Step("}", 38));
        }
        {
            stateTableE.add(new Step("while", 39));
            stateTableE.add(new Step("do", 40));
        }
        {
            stateTableM.add(new Step("identifier=", 43));
            stateTableM.add(new Step("identifier[", 43));
            stateTableM.add(new Step("identifier(", 45));
            stateTableM.add(new Step("class", 44));
            stateTableM.add(new Step("if", 46));
            stateTableM.add(new Step("switch", 46));
            stateTableM.add(new Step("while", 47));
            stateTableM.add(new Step("do", 47));
        }
        {
            stateTableS.add(new Step("identifier", 41));
            stateTableS.add(new Step("class", 41));
            stateTableS.add(new Step("if", 41));
            stateTableS.add(new Step("switch", 41));
            stateTableS.add(new Step("while", 41));
            stateTableS.add(new Step("do", 41));
            stateTableS.add(new Step("}", 42));
        }
        {
            stateTable.add(stateTableA);
            stateTable.add(stateTableH);
            stateTable.add(stateTableX);
            stateTable.add(stateTableI);
            stateTable.add(stateTableI2);
            stateTable.add(stateTableF);
            stateTable.add(stateTableF2);
            stateTable.add(stateTableY);
            stateTable.add(stateTableB);
            stateTable.add(stateTableJ);
            stateTable.add(stateTableJ2);
            stateTable.add(stateTableC);
            stateTable.add(stateTableD);
            stateTable.add(stateTableD2);
            stateTable.add(stateTableG);
            stateTable.add(stateTableG2);
            stateTable.add(stateTableZ);
            stateTable.add(stateTableK);
            stateTable.add(stateTableK2);
            stateTable.add(stateTableE);
            stateTable.add(stateTableM);
            stateTable.add(stateTableS);
        }
    }

    public static void main(String[] args) {
        init();
        input("do{x=y;}while(z<x);");
        Scan.handle();
        setKvMapArrayList();
        System.out.println(handle());
        System.out.println(analyseStack);
//        for (ArrayList<Step> state : stateTable) {
//            for (Step s : state) {
//                System.out.println(s.input + "    " + s.stepTo);
//            }
//            System.out.println();
//        }

//        System.out.println(stateTable);
//        System.out.println(generators.length);
    }

    public static void setKvMapArrayList() {
        Grammer.kvMapArrayList = getResultList();
    }

    public static boolean handle() {
        analyseStack.push("V");
        String input = "";
        for (int k = 0; k < kvMapArrayList.size(); ) {
            System.out.println(analyseStack);
            switch (kvMapArrayList.get(k).classi) {
                case "keyword":
                    if (kvMapArrayList.get(k).type >= 1 && kvMapArrayList.get(k).type <= 4) {
                        input = "class";
                    } else {
                        switch (kvMapArrayList.get(k).value) {
                            case "if":
                            case "switch":
                            case "else":
                            case "case":
                            case "while":
                            case "do":
                                input = kvMapArrayList.get(k).value;
                                break;
                        }
                    }
                    break;
                case "identifier":
                    input = "identifier";
                    break;
                case "constant":
                    switch (kvMapArrayList.get(k).value) {
                        case "TRUE":
                        case "FALSE":
                            input = kvMapArrayList.get(k).value;
                            break;
                        default:
                            input = "constant";
                    }
                    break;
                case "operator":
                    if (kvMapArrayList.get(k).type >= 24 && kvMapArrayList.get(k).type <= 29) {
                        input = "rop";
                    } else {
                        switch (kvMapArrayList.get(k).value) {
                            case "++":
                            case "--":
                            case "+":
                            case "-":
                            case "*":
                            case "&":
                            case "|":
                            case "!":
                            case "=":
                                input = kvMapArrayList.get(k).value;
                                break;
                        }
                    }
                    break;
                case "bound":
                    switch (kvMapArrayList.get(k).value) {
                        case "[":
                        case "]":
                        case "(":
                        case ")":
                        case ":":
                        case ";":
                        case "}":
                        case ",":
                            input = kvMapArrayList.get(k).value;
                            break;
                    }
                    break;
            }
            String top = analyseStack.pop();
            analyseStack.push(top);

            if (Character.isUpperCase(top.toCharArray()[0])) {
                analyseStack.pop();
                String temp = input;
                boolean flag = false;
                if (top.equals("U") && input.equals("identifier")) {
                        input += kvMapArrayList.get(k + 1).value;
                        flag = true;
                }
                ArrayList<Step> row = stateTable.get(top.toCharArray()[0] - 'A');
                int i = 0;
                for (; i < row.size(); ++i) {
                    if (row.get(i).input.equals(input)) {
                        break;
                    }
                }
                if (flag) {
                    input = temp;
                }
                if (i == row.size()) {
                    System.out.println("Input Error!");
                    return false;
                } else {
                    String generate = generators[row.get(i).stepTo];
                    if (generate.equals("episilon")) {
                    } else {
                        char[] strChar = generate.toCharArray();
                        for (int j = generate.length() - 1; j >= 0; --j) {
                            if (Character.isLowerCase(strChar[j])) {
                                int r = j - 1;
                                for (; r >= 0; --r) {
                                    if (!Character.isLowerCase(strChar[r])) {
                                        break;
                                    }
                                }
                                analyseStack.push(new String(strChar, r + 1, j - r));
                                j = r + 1;
                            } else if (Character.isUpperCase(strChar[j])) {
                                if (strChar[j] == 'E') {
                                    if (j - 3 >= 0 && new String(strChar, j - 3, 4).equals("TRUE")) {
                                        analyseStack.push("TRUE");
                                        j = j - 3;
                                    } else if (j - 4 >= 0 && new String(strChar, j - 4, 5).equals("FALSE")) {
                                        analyseStack.push("FALSE");
                                        j = j - 4;
                                    }
                                }
                                    analyseStack.push(new String(strChar, j, 1));
                            } else {
                                if (j - 1 >= 0 && (new String(strChar, j - 1, 2).equals("++") || new String(strChar, j - 1, 2).equals("--"))) {
                                    analyseStack.push(new String(strChar, j - 1, 2));
                                    j = j - 1;
                                } else {
                                    analyseStack.push(new String(strChar, j, 1));
                                }
                            }
                        }
                    }
                }
            } else {
                String over = analyseStack.pop();
                analyseStack.push(over);
                switch (over) {
                    case "++":
                    case "--":
                    case "[":
                    case "]":
                    case "(":
                    case ")":
                    case "+":
                    case "-":
                    case "*":
                    case ",":
                    case "if":
                    case "switch":
                    case "then":
                    case "else":
                    case "case":
                    case "&":
                    case "|":
                    case "!":
                    case "rop":
                    case "TRUE":
                    case "FALSE":
                    case "while":
                    case "do":
                    case "=":
                    case ":":
                    case ";":
                    case "{":
                    case "}":
                        if (over.equals("rop")) {
                            if (kvMapArrayList.get(k).type >= 24 && kvMapArrayList.get(k).type <= 29) {
                                analyseStack.pop();
                            } else {
                                System.out.println("Invalid symbol!");
                                return false;
                            }
                        } else if (kvMapArrayList.get(k).value.equals(over)) {
                            analyseStack.pop();
                        } else {
                            System.out.println("Invalid symbol!");
                            return false;
                        }
                        ++k;
                        break;
                    case "identifier":
                        if (kvMapArrayList.get(k).classi.equals("identifier")) {
                            analyseStack.pop();
                        } else {
                            System.out.println("Not an identifier!");
                            return false;
                        }
                        ++k;
                        break;
                    case "constant":
                        if (kvMapArrayList.get(k).classi.equals("constant")) {
                            analyseStack.pop();
                        } else {
                            System.out.println("Not a constant!");
                            return false;
                        }
                        ++k;
                        break;
                    case "class":
                        if (kvMapArrayList.get(k).type >= 1 && kvMapArrayList.get(k).type <= 4) {
                            analyseStack.pop();
                        } else {
                            System.out.println("Not a class!");
                            return false;
                        }
                        ++k;
                        break;
                }
            }
        }
        if(analyseStack.size() == 1){
            if(analyseStack.pop().equals("V")){
                return true;
            }
        }
        return false;
    }
}
