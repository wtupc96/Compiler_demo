package lexical;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/6/13.
 */
public class Scan {
    private static final ArrayList<String> keywords = new ArrayList<>(Arrays.asList("int", "double", "char", "boolean", "if", "then", "else", "while", "do", "switch", "case"));
    private static char[] proc;
    private static ArrayList<KVMap> resultList = new ArrayList<>();
    private static StringBuffer currentWord = new StringBuffer();
    private static int type = -1;
    private static int row = 1;
    private static boolean result = true;
    private static String lexicalResult = "";

    public static ArrayList<KVMap> getResultList() {
        return resultList;
    }

    public static void input(String prog) {
        proc = prog.toCharArray();
    }

    public static boolean handle() {
        int length = proc.length;
        for (int i = 0; i < length; ++i) {
            while (i < length && (proc[i] == ' ' || proc[i] == '\n' || proc[i] == '\t')) {
                if (proc[i] == '\n') {
                    ++row;
                }
                ++i;
            }
            if (i < length) {
                currentWord.append(proc[i]);
                if (Character.isAlphabetic(proc[i])) {
                    // May be a identifier or keyword
                    i = handleStartWithAlphabetic(i, length);
                } else if (Character.isDigit(proc[i]) || proc[i] == '-') {
                    // Maybe a number
                    i = handleNumberOrMinus(i, length);
                } else {
                    switch (proc[i]) {
                        case '\'':
                            type = 15;
                            if (i + 1 < length) {
                                currentWord.append(proc[i + 1]);
                            } else {
                                result = false;
                                type = -1;
                                currentWord = new StringBuffer("No character.");
                            }
                            if (i + 2 < length && proc[i + 2] == '\'') {
                                currentWord.append(proc[i + 2]);
                                i = i + 2;
                            } else {
                                result = false;
                                type = -1;
                                currentWord = new StringBuffer("Not a pair of single quotes.");
                            }
                            break;
                        case '+':
                            type = 18;
                            if (i + 1 < length && proc[i + 1] == '+') {
                                type = 19;
                                currentWord.append(proc[i + 1]);
                                ++i;
                            }
                            break;
                        case '*':
                            type = 22;
                            break;
                        case '=':
                            type = 23;
                            if (i + 1 < length && proc[i + 1] == '=') {
                                type = 26;
                                currentWord.append(proc[i + 1]);
                                ++i;
                            }
                            break;
                        case '<':
                            type = 24;
                            if (i + 1 < length && proc[i + 1] == '=') {
                                type = 25;
                                currentWord.append(proc[i + 1]);
                                ++i;
                            }
                            break;
                        case '>':
                            type = 28;
                            if (i + 1 < length && proc[i + 1] == '=') {
                                type = 29;
                                currentWord.append(proc[i + 1]);
                                ++i;
                            }
                            break;
                        case '&':
                            type = 30;
                            break;
                        case '|':
                            type = 31;
                            break;
                        case '!':
                            type = 32;
                            if (i + 1 < length && proc[i + 1] == '=') {
                                type = 27;
                                currentWord.append(proc[i + 1]);
                                ++i;
                            }
                            break;
                        case '[':
                            type = 33;
                            break;
                        case ']':
                            type = 34;
                            break;
                        case '(':
                            type = 35;
                            break;
                        case ')':
                            type = 36;
                            break;
                        case '{':
                            type = 37;
                            break;
                        case '}':
                            type = 38;
                            break;
                        case ';':
                            type = 39;
                            break;
                        case ':':
                            type = 40;
                            break;
                        case ',':
                            type = 41;
                            break;
                        default:
                            result = false;
                            type = -1;
                            currentWord = new StringBuffer("Unsupported character: " + proc[i]);
                    }
                    resultList.add(new KVMap(row, type, currentWord.toString(), getClass(type)));
                    currentWord = new StringBuffer();
                }
            }
        }
        return result;
    }

    private static int handleNumberOrMinus(int i, int length) {
        int j = i + 1;
        type = 20;
        if (j < length && proc[j] == '-') {
            type = 21;
            currentWord.append(proc[i + 1]);
            resultList.add(new KVMap(row, type, currentWord.toString(), getClass(type)));
            currentWord = new StringBuffer();
            return j;
        }
        type = 13;
        int state = 2;
        for (; j < length; ++j) {
            if (state == 2) {
                if (Character.isDigit(proc[j])) {
                    currentWord.append(proc[j]);
                } else if (proc[j] == '.') {
                    currentWord.append(proc[j]);
                    state = 3;
                    type = 14;
                } else {
                    break;
                }
            } else if (Character.isDigit(proc[j])) {
                currentWord.append(proc[j]);
            } else {
                break;
            }
        }
        resultList.add(new KVMap(row, type, currentWord.toString(), getClass(type)));
        currentWord = new StringBuffer();
        return j - 1;
    }

    private static int handleStartWithAlphabetic(int i, int length) {
        int j = i + 1;
        for (; j < length; ++j) {
            if (Character.isAlphabetic(proc[j]) || Character.isDigit(proc[j]) || proc[j] == '_') {
                currentWord.append(proc[j]);
            } else {
                break;
            }
        }
        type = 12;
        String current = currentWord.toString();
        if (keywords.contains(current)) {
            type = keywords.indexOf(current) + 1;
        } else if (current.equals("TRUE")) {
            type = 16;
        } else if (current.equals("FALSE")) {
            type = 17;
        }
        resultList.add(new KVMap(row, type, currentWord.toString(), getClass(type)));
        currentWord = new StringBuffer();
        // j - 1 means the last valid character.
        return j - 1;
    }

    public static void printLexicalResult() {
        for (KVMap element : resultList) {
            lexicalResult += "Row number: " + element.row + "  < " + element.type + ", " + element.value + ", " + element.classi + " >\n";
            System.out.println("Row number: " + element.row + "  < " + element.type + ", " + element.value + ", " + element.classi + " >");
        }
    }

    public static String getClass(int type) {
        if (type >= 1 && type <= 11) {
            return "keyword";
        }
        if (type == 12) {
            return "identifier";
        }
        if (type >= 13 && type <= 17) {
            return "constant";
        }
        if (type >= 18 && type <= 32) {
            return "operator";
        }
        if (type >= 33 && type <= 41) {
            return "bound";
        }
        return "error";
    }

    public static void main(String[] args) {
        input("int a[100,200,300,400],b;\n" +
                "b = 1;\n" +
                "a[11, 22, 33, 44] = 1 * b + 3 + (++0);");
        System.out.println(handle());
        printLexicalResult();
    }

    public static String lexicalAnalysis(String arg) {
        lexicalResult = "";
        input(arg);
        System.out.println(handle());
        printLexicalResult();
        System.out.println(lexicalResult);
        return lexicalResult;
    }

}
