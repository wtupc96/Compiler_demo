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

    public static void input(String prog) {
        proc = prog.toCharArray();
    }

    public static void handle() {
        int length = proc.length;
        for (int i = 0; i < length; ++i) {
            while (i < length && (proc[i] == ' ' || proc[i] == '\n' || proc[i] == '\t')) {
                if (proc[i] == '\n') {
                    ++row;
                }
                ++i;
            }
            if(i < length){
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
                                type = -1;
                                currentWord = new StringBuffer("No character.");
                            }
                            if (i + 2 < length && proc[i + 2] == '\'') {
                                currentWord.append(proc[i + 2]);
                                i = i + 2;
                            } else {
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
                        default:
                            type = -1;
                            currentWord = new StringBuffer("Not supported character: " + proc[i]);
                    }
                    resultList.add(new KVMap(row, type, currentWord.toString()));
                    currentWord = new StringBuffer();
                }
            }
        }
    }

    private static int handleNumberOrMinus(int i, int length) {
        int j = i + 1;
        type = 20;
        if (j < length && proc[j] == '-') {
            type = 21;
            currentWord.append(proc[i + 1]);
            resultList.add(new KVMap(row, type, currentWord.toString()));
            currentWord = new StringBuffer();
            return j;
        }
        type = 20;
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
        resultList.add(new KVMap(row, type, currentWord.toString()));
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
        resultList.add(new KVMap(row, type, currentWord.toString()));
        currentWord = new StringBuffer();
        // j - 1 means the last valid character.
        return j - 1;
    }

    public static void printLexicalResult() {
        for (KVMap element : resultList) {
            System.out.println("Row number: " + element.row + "  < " + element.type + ", " + element.value + " >");
        }
    }

    public static void main(String[] args) {
        input("package lexical;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.Arrays;\n" +
                "\n" +
                "/**\n" +
                " * Created by Administrator on 2017/6/13.\n" +
                " */\n" +
                "public class Scan {\n" +
                "    private static final ArrayList<String> keywords = new ArrayList<>(Arrays.asList(\"int\", \"double\", \"char\", \"boolean\", \"if\", \"then\", \"else\", \"while\", \"do\", \"switch\", \"case\"));\n" +
                "    private static char[] proc;\n" +
                "    private static ArrayList<KVMap> resultList = new ArrayList<>();\n" +
                "    private static StringBuffer currentWord = new StringBuffer();\n" +
                "    private static int type = -1;\n" +
                "    private static int row = 1;\n" +
                "\n" +
                "    public static void input(String prog) {\n" +
                "        proc = prog.toCharArray();\n" +
                "    }\n" +
                "\n" +
                "    public static void handle() {\n" +
                "        int length = proc.length;\n" +
                "        for (int i = 0; i < length; ++i) {\n" +
                "            while (i < length && (proc[i] == ' ' || proc[i] == '\\n' || proc[i] == '\\t')) {\n" +
                "                if (proc[i] == '\\n') {\n" +
                "                    ++row;\n" +
                "                }\n" +
                "                ++i;\n" +
                "            }\n" +
                "            if(i < length){\n" +
                "                currentWord.append(proc[i]);\n" +
                "                if (Character.isAlphabetic(proc[i])) {\n" +
                "                    // May be a identifier or keyword\n" +
                "                    i = handleStartWithAlphabetic(i, length);\n" +
                "                } else if (Character.isDigit(proc[i]) || proc[i] == '-') {\n" +
                "                    // Maybe a number\n" +
                "                    i = handleNumberOrMinus(i, length);\n" +
                "                } else {\n" +
                "                    switch (proc[i]) {\n" +
                "                        case '\\'':\n" +
                "                            type = 15;\n" +
                "                            if (i + 1 < length) {\n" +
                "                                currentWord.append(proc[i + 1]);\n" +
                "                            } else {\n" +
                "                                type = -1;\n" +
                "                                currentWord = new StringBuffer(\"No character.\");\n" +
                "                            }\n" +
                "                            if (i + 2 < length && proc[i + 2] == '\\'') {\n" +
                "                                currentWord.append(proc[i + 2]);\n" +
                "                                i = i + 2;\n" +
                "                            } else {\n" +
                "                                type = -1;\n" +
                "                                currentWord = new StringBuffer(\"Not a pair of single quotes.\");\n" +
                "                            }\n" +
                "                            break;\n" +
                "                        case '+':\n" +
                "                            type = 18;\n" +
                "                            if (i + 1 < length && proc[i + 1] == '+') {\n" +
                "                                type = 19;\n" +
                "                                currentWord.append(proc[i + 1]);\n" +
                "                                ++i;\n" +
                "                            }\n" +
                "                            break;\n" +
                "                        case '*':\n" +
                "                            type = 22;\n" +
                "                            break;\n" +
                "                        case '=':\n" +
                "                            type = 23;\n" +
                "                            if (i + 1 < length && proc[i + 1] == '=') {\n" +
                "                                type = 26;\n" +
                "                                currentWord.append(proc[i + 1]);\n" +
                "                                ++i;\n" +
                "                            }\n" +
                "                            break;\n" +
                "                        case '<':\n" +
                "                            type = 24;\n" +
                "                            if (i + 1 < length && proc[i + 1] == '=') {\n" +
                "                                type = 25;\n" +
                "                                currentWord.append(proc[i + 1]);\n" +
                "                                ++i;\n" +
                "                            }\n" +
                "                            break;\n" +
                "                        case '>':\n" +
                "                            type = 28;\n" +
                "                            if (i + 1 < length && proc[i + 1] == '=') {\n" +
                "                                type = 29;\n" +
                "                                currentWord.append(proc[i + 1]);\n" +
                "                                ++i;\n" +
                "                            }\n" +
                "                            break;\n" +
                "                        case '&':\n" +
                "                            type = 30;\n" +
                "                            break;\n" +
                "                        case '|':\n" +
                "                            type = 31;\n" +
                "                            break;\n" +
                "                        case '!':\n" +
                "                            type = 32;\n" +
                "                            if (i + 1 < length && proc[i + 1] == '=') {\n" +
                "                                type = 27;\n" +
                "                                currentWord.append(proc[i + 1]);\n" +
                "                                ++i;\n" +
                "                            }\n" +
                "                            break;\n" +
                "                        case '[':\n" +
                "                            type = 33;\n" +
                "                            break;\n" +
                "                        case ']':\n" +
                "                            type = 34;\n" +
                "                            break;\n" +
                "                        case '(':\n" +
                "                            type = 35;\n" +
                "                            break;\n" +
                "                        case ')':\n" +
                "                            type = 36;\n" +
                "                            break;\n" +
                "                        case '{':\n" +
                "                            type = 37;\n" +
                "                            break;\n" +
                "                        case '}':\n" +
                "                            type = 38;\n" +
                "                            break;\n" +
                "                        case ';':\n" +
                "                            type = 39;\n" +
                "                            break;\n" +
                "                        case ':':\n" +
                "                            type = 40;\n" +
                "                            break;\n" +
                "                        default:\n" +
                "                            type = -1;\n" +
                "                            currentWord = new StringBuffer(\"Not supported character: \" + proc[i]);\n" +
                "                    }\n" +
                "                    resultList.add(new KVMap(row, type, currentWord.toString()));\n" +
                "                    currentWord = new StringBuffer();\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    private static int handleNumberOrMinus(int i, int length) {\n" +
                "        int j = i + 1;\n" +
                "        type = 20;\n" +
                "        if (j < length && proc[j] == '-') {\n" +
                "            type = 21;\n" +
                "            currentWord.append(proc[i + 1]);\n" +
                "            resultList.add(new KVMap(row, type, currentWord.toString()));\n" +
                "            currentWord = new StringBuffer();\n" +
                "            return j;\n" +
                "        }\n" +
                "        type = 20;\n" +
                "        int state = 2;\n" +
                "        for (; j < length; ++j) {\n" +
                "            if (state == 2) {\n" +
                "                if (Character.isDigit(proc[j])) {\n" +
                "                    currentWord.append(proc[j]);\n" +
                "                } else if (proc[j] == '.') {\n" +
                "                    currentWord.append(proc[j]);\n" +
                "                    state = 3;\n" +
                "                    type = 14;\n" +
                "                } else {\n" +
                "                    break;\n" +
                "                }\n" +
                "            } else if (Character.isDigit(proc[j])) {\n" +
                "                currentWord.append(proc[j]);\n" +
                "            } else {\n" +
                "                break;\n" +
                "            }\n" +
                "        }\n" +
                "        resultList.add(new KVMap(row, type, currentWord.toString()));\n" +
                "        currentWord = new StringBuffer();\n" +
                "        return j - 1;\n" +
                "    }\n" +
                "\n" +
                "    private static int handleStartWithAlphabetic(int i, int length) {\n" +
                "        int j = i + 1;\n" +
                "        for (; j < length; ++j) {\n" +
                "            if (Character.isAlphabetic(proc[j]) || Character.isDigit(proc[j]) || proc[j] == '_') {\n" +
                "                currentWord.append(proc[j]);\n" +
                "            } else {\n" +
                "                break;\n" +
                "            }\n" +
                "        }\n" +
                "        type = 12;\n" +
                "        String current = currentWord.toString();\n" +
                "        if (keywords.contains(current)) {\n" +
                "            type = keywords.indexOf(current) + 1;\n" +
                "        } else if (current.equals(\"TRUE\")) {\n" +
                "            type = 16;\n" +
                "        } else if (current.equals(\"FALSE\")) {\n" +
                "            type = 17;\n" +
                "        }\n" +
                "        resultList.add(new KVMap(row, type, currentWord.toString()));\n" +
                "        currentWord = new StringBuffer();\n" +
                "        // j - 1 means the last valid character.\n" +
                "        return j - 1;\n" +
                "    }\n" +
                "\n" +
                "    public static void printLexicalResult() {\n" +
                "        for (KVMap element : resultList) {\n" +
                "            System.out.println(\"Row number: \" + element.row + \"  < \" + element.type + \", \" + element.value + \" >\");\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        input(\"p=0;\\n\" +\n" +
                "                \"    row=1;\\n\" +\n" +
                "                \"    cout<<\\\"Please input string:\\\"<<endl;\\n\" +\n" +
                "                \"    do\\n\" +\n" +
                "                \"    {\\n\" +\n" +
                "                \"        cin.get(ch);\\n\" +\n" +
                "                \"        prog[p++]=ch;\\n\" +\n" +
                "                \"    }\\n\" +\n" +
                "                \"    while(ch!='#');\\n\" +\n" +
                "                \"    p=0;\\n\" +\n" +
                "                \"    do\\n\" +\n" +
                "                \"    {\\n\" +\n" +
                "                \"        scaner();\\n\" +\n" +
                "                \"        switch(syn)\\n\" +\n" +
                "                \"        {\\n\" +\n" +
                "                \"        case 11: cout<<\\\"(\\\"<<syn<<\\\",\\\"<<sum<<\\\")\\\"<<endl; break;  \\n\" +\n" +
                "                \"        case -1: cout<<\\\"Error in row \\\"<<row<<\\\"!\\\"<<endl; break;\\n\" +\n" +
                "                \"        case -2: row=row++;break;\\n\" +\n" +
                "                \"        default: cout<<\\\"(\\\"<<syn<<\\\",\\\"<<token<<\\\")\\\"<<endl;break;\\n\" +\n" +
                "                \"        }\\n\" +\n" +
                "                \"    }\\n\" +\n" +
                "                \"    while (syn!=0);\");\n" +
                "        handle();\n" +
                "        printLexicalResult();\n" +
                "    }\n" +
                "\n" +
                "}\n");
        handle();
        printLexicalResult();
    }

}
