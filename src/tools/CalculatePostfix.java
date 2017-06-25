package tools;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by wtupc96 on 2017/6/25.
 */
public class CalculatePostfix {
    private static Stack<Integer> operator = new Stack<>();
    private static Stack<String> op = new Stack<>();

    private static int handle() {
        while (!op.empty()) {
            switch (op.pop()) {
                case "(":
                    return -1;
                case "++":
                    operator.push(operator.pop() + 1);
                    break;
                case "--":
                    operator.push(operator.pop() - 1);
                    break;
                case "*":
                    operator.push(operator.pop() * operator.pop());
                    break;
                case "+":
                    operator.push(operator.pop() + operator.pop());
                    break;
                case "-":
                    int x = operator.pop();
                    operator.push(operator.pop() - x);
                    break;
            }
        }
        return operator.pop();
    }

    public static int calculate(ArrayList<String> expr) {
        if (expr.size() == 1) {
            return Integer.valueOf(expr.get(0));
        }
        for (String item : expr) {
            try {
                operator.push(Integer.valueOf(item));
            } catch (Exception e) {
                if (!item.equals("$")) {
                    if (!op.empty() && !item.equals("(")) {
                        switch (item) {
                            case ")":
                                handle();
                            case "*":
                                if (!op.empty()) {
                                    switch (op.pop()) {
                                        case "++":
                                            operator.push(operator.pop() + 1);
                                            break;
                                        case "--":
                                            operator.push(operator.pop() - 1);
                                            break;
                                    }
                                }
                                break;
                            case "+":
                            case "-":
                                if (!op.empty()) {
                                    switch (op.pop()) {
                                        case "++":
                                            operator.push(operator.pop() + 1);
                                            break;
                                        case "--":
                                            operator.push(operator.pop() - 1);
                                            break;
                                        case "*":
                                            operator.push(operator.pop() * operator.pop());
                                            break;
                                        case "+":
                                            operator.push(operator.pop() + operator.pop());
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    op.push(item);

                }
            }
        }
        return handle();
    }
}
