package grammer;

import lexical.KVMap;

import java.util.ArrayList;
import java.util.Stack;

import static lexical.Scan.getResultList;

/**
 * Created by Administrator on 2017/6/14.
 */
public class Grammer {
    private static final ArrayList<Step> stateTableA = new ArrayList<>();
    private static final ArrayList<Step> stateTableH = new ArrayList<>();
    private static final ArrayList<Step> stateTableX = new ArrayList<>();
    private static final ArrayList<Step> stateTableI = new ArrayList<>();
    private static final ArrayList<Step> stateTableI2 = new ArrayList<>();
    private static final ArrayList<Step> stateTableF = new ArrayList<>();
    private static final ArrayList<Step> stateTableF2 = new ArrayList<>();
    private static final ArrayList<Step> stateTableY = new ArrayList<>();
    private static final ArrayList<Step> stateTableB = new ArrayList<>();
    private static final ArrayList<Step> stateTableJ = new ArrayList<>();
    private static final ArrayList<Step> stateTableJ2 = new ArrayList<>();
    private static final ArrayList<Step> stateTableC = new ArrayList<>();
    private static final ArrayList<Step> stateTableD = new ArrayList<>();
    private static final ArrayList<Step> stateTableD2 = new ArrayList<>();
    private static final ArrayList<Step> stateTableG = new ArrayList<>();
    private static final ArrayList<Step> stateTableG2 = new ArrayList<>();
    private static final ArrayList<Step> stateTableZ = new ArrayList<>();
    private static final ArrayList<Step> stateTableK = new ArrayList<>();
    private static final ArrayList<Step> stateTableK2 = new ArrayList<>();
    private static final ArrayList<Step> stateTableE = new ArrayList<>();
    private static final ArrayList<Step> stateTableM = new ArrayList<>();
    private static final ArrayList<Step> stateTableS = new ArrayList<>();
    private static final ArrayList<ArrayList<Step>> stateTable = new ArrayList<>();
    private static final String[] generators = {
            "B=F;", "identifierC", "[D]", "episilon", "FE", ",FE", "episilon", "++FG", "--FG", "(F)G", "-FG", "identifierG", "constantG",
            "HG", "episilon", "+F", "-F", "*F", "classJ;", "BK", ",BK", "episilon", "identifier(J);", "if(O)then{V}N", "else{V}", "episilon",
            "!OP", "(O)P", "BropBP", "TRUEP", "FALSEP", "QP", "episilon", "&O", "|O", "switch(B){R}", "caseB:{V}S", "caseB:{V}S", "episilon",
            "while(O)do{V}", "do{V}while(O);", "UV", "episilon", "A", "I", "L", "M", "T"
    };
    private static final Stack<String> analyseStack = new Stack<>();
    private static final ArrayList<String> firstA = new ArrayList<>();
    private static final ArrayList<String> firstH = new ArrayList<>();
    private static final ArrayList<String> firstX = new ArrayList<>();
    private static final ArrayList<String> firstI = new ArrayList<>();
    private static final ArrayList<String> firstI2 = new ArrayList<>();
    private static final ArrayList<String> firstF = new ArrayList<>();
    private static final ArrayList<String> firstF2 = new ArrayList<>();
    private static final ArrayList<String> firstY = new ArrayList<>();
    private static final ArrayList<String> firstB = new ArrayList<>();
    private static final ArrayList<String> firstJ = new ArrayList<>();
    private static final ArrayList<String> firstJ2 = new ArrayList<>();
    private static final ArrayList<String> firstC = new ArrayList<>();
    private static final ArrayList<String> firstD = new ArrayList<>();
    private static final ArrayList<String> firstD2 = new ArrayList<>();
    private static final ArrayList<String> firstG = new ArrayList<>();
    private static final ArrayList<String> firstG2 = new ArrayList<>();
    private static final ArrayList<String> firstZ = new ArrayList<>();
    private static final ArrayList<String> firstK = new ArrayList<>();
    private static final ArrayList<String> firstK2 = new ArrayList<>();
    private static final ArrayList<String> firstE = new ArrayList<>();
    private static final ArrayList<String> firstM = new ArrayList<>();
    private static final ArrayList<String> firstS = new ArrayList<>();
    private static final ArrayList<ArrayList<String>> firstSet = new ArrayList<>();
    private static ArrayList<KVMap> kvMapArrayList;
    private static int line = 0;
    private static boolean result = true;

    private static void init() {
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
            stateTableK2.add(new Step("case", 37));
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
        {
            firstA.add("identifier");
        }
        {
            firstH.add("identifier");
        }
        {
            firstX.add("[");
        }
        {
            firstI.add("++");
            firstI.add("--");
            firstI.add("(");
            firstI.add("-");
            firstI.add("identifier");
            firstI.add("constant");
        }
        {
            firstI2.add(",");
        }
        {
            firstF.add("++");
            firstF.add("--");
            firstF.add("(");
            firstF.add("-");
            firstF.add("identifier");
            firstF.add("constant");
        }
        {
            firstF2.add("+");
            firstF2.add("-");
            firstF2.add("*");
        }
        {
            firstY.add("+");
            firstY.add("-");
            firstY.add("*");
        }
        {
            firstB.add("class");
        }
        {
            firstJ.add("identifier");
        }
        {
            firstJ2.add(",");
        }
        {
            firstC.add("identifier");
        }
        {
            firstD.add("if");
            firstD.add("switch");
        }
        {
            firstD2.add("else");
        }
        {
            firstG.add("!");
            firstG.add("(");
            firstG.add("TRUE");
            firstG.add("FALSE");
            firstG.add("identifier");
        }
        {
            firstG2.add("!");
            firstG2.add("|");
        }
        {
            firstZ.add("&");
            firstZ.add("|");
        }
        {
            firstK.add("case");
        }
        {
            firstK2.add("case");
        }
        {
            firstE.add("while");
            firstE.add("do");
        }
        {
            firstM.add("identifier");
            firstM.add("class");
            firstM.add("if");
            firstM.add("switch");
            firstM.add("while");
            firstM.add("do");
        }
        {
            firstS.add("identifier");
            firstS.add("class");
            firstS.add("if");
            firstS.add("switch");
            firstS.add("while");
            firstS.add("do");
        }
        {
            firstSet.add(firstA);
            firstSet.add(firstH);
            firstSet.add(firstX);
            firstSet.add(firstI);
            firstSet.add(firstI2);
            firstSet.add(firstF);
            firstSet.add(firstF2);
            firstSet.add(firstY);
            firstSet.add(firstB);
            firstSet.add(firstJ);
            firstSet.add(firstJ2);
            firstSet.add(firstC);
            firstSet.add(firstD);
            firstSet.add(firstD2);
            firstSet.add(firstG);
            firstSet.add(firstG2);
            firstSet.add(firstZ);
            firstSet.add(firstK);
            firstSet.add(firstK2);
            firstSet.add(firstE);
            firstSet.add(firstM);
            firstSet.add(firstS);
        }
    }

//    public static void main(String[] args) {
//        init();
//        input("");
//        Scan.handle();
//        setKvMapArrayList();
//        System.out.println(handle());
//    }

    private static void setKvMapArrayList() {
        Grammer.kvMapArrayList = getResultList();
    }

    private static boolean handle() {
        analyseStack.push("V");
        String input = "";
        for (int k = 0; k < kvMapArrayList.size(); ) {
            System.out.println(analyseStack);
            line = kvMapArrayList.get(k).row;
            switch (kvMapArrayList.get(k).classi) {
                case "keyword":
                    int type = kvMapArrayList.get(k).type;
                    if (type >= 1 && type <= 4) {
                        input = "class";
                    } else {
                        String value = kvMapArrayList.get(k).value;
                        switch (value) {
                            case "if":
                            case "switch":
                            case "else":
                            case "case":
                            case "while":
                            case "do":
                                input = value;
                                break;
                        }
                    }
                    break;
                case "identifier":
                    input = "identifier";
                    break;
                case "constant":
                    String value = kvMapArrayList.get(k).value;
                    switch (value) {
                        case "TRUE":
                        case "FALSE":
                            input = value;
                            break;
                        default:
                            input = "constant";
                    }
                    break;
                case "operator":
                    int type2 = kvMapArrayList.get(k).type;
                    if (type2 >= 24 && type2 <= 29) {
                        input = "rop";
                    } else {
                        String value2 = kvMapArrayList.get(k).value;
                        switch (value2) {
                            case "++":
                            case "--":
                            case "+":
                            case "-":
                            case "*":
                            case "&":
                            case "|":
                            case "!":
                            case "=":
                                input = value2;
                                break;
                        }
                    }
                    break;
                case "bound":
                    String value3 = kvMapArrayList.get(k).value;
                    switch (value3) {
                        case "[":
                        case "]":
                        case "(":
                        case ")":
                        case ":":
                        case ";":
                        case "}":
                        case ",":
                            input = value3;
                            break;
                    }
                    break;
            }

            String top;
            if (analyseStack.size() >= 1) {
                top = analyseStack.elementAt(analyseStack.size() - 1);
            } else {
                return false;
            }

            if (!top.equals("TRUE") && !top.equals("FALSE") && Character.isUpperCase(top.toCharArray()[0])) {
                analyseStack.pop();
                String temp = input;
                boolean flag = false;
                if (top.equals("U") && input.equals("identifier")) {
                    if (k + 1 < kvMapArrayList.size()) {
                        input += kvMapArrayList.get(k + 1).value;
                        flag = true;
                    }
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
                    result = false;
                    if (top.length() > 1) {
                        switch (top) {
                            case "TRUE":
                            case "FALSE":
                            case "class":
                            case "identifier":
                            case "constant":
                            case "while":
                            case "do":
                            case "switch":
                            case "if":
                            case "else":
                            case "then":
                            case "case":
                            case "++":
                            case "--":
                                System.out.println("Need \"" + top + "\" at line: " + line);
                        }
                    } else if (Character.isUpperCase(top.toCharArray()[0])) {
                        System.out.println("Need \"" + firstSet.get(top.toCharArray()[0] - 'A') + "\" at line: " + line);
                    } else {
                        System.out.println("Need \"" + top + "\" at line: " + line);
                    }
                    if (analyseStack.empty()) {
                        return false;
                    }
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
                                    } else {
                                        analyseStack.push(new String(strChar, j, 1));
                                    }
                                } else {
                                    analyseStack.push(new String(strChar, j, 1));
                                }
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
                if (!analyseStack.empty()) {
                    String over = analyseStack.elementAt(analyseStack.size() - 1);
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
                                    result = false;
                                    System.out.println("Need \"" + over + "\" at line: " + line);
                                    if (!analyseStack.empty()) {
                                        analyseStack.pop();
                                    } else {
                                        return false;
                                    }
                                }
                            } else if (kvMapArrayList.get(k).value.equals(over)) {
                                analyseStack.pop();
                            } else {
                                System.out.println("Need \"" + over + "\" at line: " + line);
                                result = false;
                                if (!analyseStack.empty()) {
                                    analyseStack.pop();
                                } else {
                                    return false;
                                }
                            }
                            ++k;
                            break;
                        case "identifier":
                            if (kvMapArrayList.get(k).classi.equals("identifier")) {
                                analyseStack.pop();
                            } else {
                                result = false;
                                System.out.println("Not an identifier!");
                                if (!analyseStack.empty()) {
                                    analyseStack.pop();
                                } else {
                                    return false;
                                }
                            }
                            ++k;
                            break;
                        case "constant":
                            if (kvMapArrayList.get(k).classi.equals("constant")) {
                                analyseStack.pop();
                            } else {
                                result = false;
                                System.out.println("Not a constant!");
                                if (!analyseStack.empty()) {
                                    analyseStack.pop();
                                } else {
                                    return false;
                                }
                            }
                            ++k;
                            break;
                        case "class":
                            if (kvMapArrayList.get(k).type >= 1 && kvMapArrayList.get(k).type <= 4) {
                                analyseStack.pop();
                            } else {
                                result = false;
                                System.out.println("Not a class!");
                                if (!analyseStack.empty()) {
                                    analyseStack.pop();
                                } else {
                                    return false;
                                }
                            }
                            ++k;
                            break;
                    }
                } else {
                    return false;
                }
            }
        }
        System.out.println(analyseStack);
        while (!analyseStack.empty()) {
            String top = analyseStack.pop();
            switch (top) {
                case "C":
                case "E":
                case "G":
                case "K":
                case "N":
                case "P":
                case "V":
                    System.out.println(analyseStack);
                    break;
                default:
                    if (top.length() > 1) {
                        switch (top) {
                            case "TRUE":
                            case "FALSE":
                            case "class":
                            case "identifier":
                            case "constant":
                            case "while":
                            case "do":
                            case "switch":
                            case "if":
                            case "else":
                            case "then":
                            case "case":
                            case "++":
                            case "--":
                                System.out.println("Need \"" + top + "\" at line: " + line);
                                break;
                            case "rop":
                                System.out.println("Need a relation operator at line: " + line);
                        }
                    } else if (Character.isUpperCase(top.toCharArray()[0])) {
                        System.out.println("Need \"" + firstSet.get(top.toCharArray()[0] - 'A') + "\" at line: " + line);
                    } else {
                        System.out.println("Need \"" + top + "\" at line: " + line);
                    }

                    System.out.println(analyseStack);
                    return false;
            }
        }
        return result;
    }
}
