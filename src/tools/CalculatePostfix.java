package tools;

import semantic.QuaternionList;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by wtupc96 on 2017/6/25.
 */
public class CalculatePostfix {
    public static int seqNum;
    public static int numberOfTempVariables;
    public static ArrayList<QuaternionList> quaternionLists;
    private static Stack<Integer> operator = new Stack<>();
    private static Stack<String> op = new Stack<>();

    private static int handle() {
        while (!op.empty()) {
            switch (op.pop()) {
                case "(":
                    return -1;
                case "++":
                    quaternionLists.add(new QuaternionList(seqNum++, "++", String.valueOf(operator.elementAt(operator.size() - 1)), null, "T" + numberOfTempVariables++));
                    operator.push(operator.pop() + 1);
                    break;
                case "--":
                    quaternionLists.add(new QuaternionList(seqNum++, "--", String.valueOf(operator.elementAt(operator.size() - 1)), null, "T" + numberOfTempVariables++));
                    operator.push(operator.pop() - 1);
                    break;
                case "*":
                    quaternionLists.add(new QuaternionList(seqNum++, "*", String.valueOf(operator.elementAt(operator.size() - 1)), String.valueOf(operator.elementAt(operator.size() - 2)), "T" + numberOfTempVariables++));
                    operator.push(operator.pop() * operator.pop());
                    break;
                case "+":
                    quaternionLists.add(new QuaternionList(seqNum++, "+", String.valueOf(operator.elementAt(operator.size() - 1)), String.valueOf(operator.elementAt(operator.size() - 2)), "T" + numberOfTempVariables++));
                    operator.push(operator.pop() + operator.pop());
                    break;
                case "-":
                    int x = operator.pop();
                    quaternionLists.add(new QuaternionList(seqNum++, "*", String.valueOf(operator.elementAt(operator.size() - 2)), String.valueOf(operator.elementAt(operator.size() - 1)), "T" + numberOfTempVariables++));
                    operator.push(operator.pop() - x);
                    break;
            }
        }
        return operator.pop();
    }

    public static int calculate(ArrayList<String> expr, int numberOfTempVariables, ArrayList<QuaternionList> quaternionLists, int seqNum) {
        CalculatePostfix.numberOfTempVariables = numberOfTempVariables;
        CalculatePostfix.quaternionLists = quaternionLists;
        CalculatePostfix.seqNum = seqNum;
        if (expr.size() == 1) {
            return Integer.valueOf(expr.get(0));
        }
        for (String item : expr) {
            try {
                operator.push(Integer.valueOf(item));
            } catch (Exception e) {
                handleOperations(item);
            }
        }
        return handle();
    }

    private static void handleOperations(String item) {
        if (!item.equals("$")) {
            if (!op.empty() && !item.equals("(")) {
                switch (item) {
                    case ")":
                        handle();
                        break;
                    case "*":
                        handleMultiplication();
                        break;
                    case "+":
                    case "-":
                        handleAdditionAndSubtraction();
                        break;
                }
            }
            op.push(item);
        }
    }

    private static void handleMultiplication() {
        if (!op.empty()) {
            switch (op.elementAt(op.size() - 1)) {
                case "(":
                    break;
                case "++":
                    quaternionLists.add(new QuaternionList(seqNum++, "++", String.valueOf(operator.elementAt(operator.size() - 1)), null, "T" + numberOfTempVariables++));
                    operator.push(operator.pop() + 1);
                    op.pop();
                    break;
                case "--":
                    quaternionLists.add(new QuaternionList(seqNum++, "--", String.valueOf(operator.elementAt(operator.size() - 1)), null, "T" + numberOfTempVariables++));
                    operator.push(operator.pop() - 1);
                    op.pop();
                    break;
            }
        }
    }

    private static void handleAdditionAndSubtraction() {
        if (!op.empty()) {
            switch (op.elementAt(op.size() - 1)) {
                case "(":
                    break;
                case "++":
                    quaternionLists.add(new QuaternionList(seqNum++, "++", String.valueOf(operator.elementAt(operator.size() - 1)), null, "T" + numberOfTempVariables++));
                    operator.push(operator.pop() + 1);
                    op.pop();
                    break;
                case "--":
                    quaternionLists.add(new QuaternionList(seqNum++, "--", String.valueOf(operator.elementAt(operator.size() - 1)), null, "T" + numberOfTempVariables++));
                    operator.push(operator.pop() - 1);
                    op.pop();
                    break;
                case "*":
                    quaternionLists.add(new QuaternionList(seqNum++, "*", String.valueOf(operator.elementAt(operator.size() - 1)), String.valueOf(operator.elementAt(operator.size() - 2)), "T" + numberOfTempVariables++));
                    operator.push(operator.pop() * operator.pop());
                    op.pop();
                    break;
                case "+":
                    quaternionLists.add(new QuaternionList(seqNum++, "+", String.valueOf(operator.elementAt(operator.size() - 1)), String.valueOf(operator.elementAt(operator.size() - 2)), "T" + numberOfTempVariables++));
                    operator.push(operator.pop() + operator.pop());
                    op.pop();
                    break;
            }
        }
    }
}
