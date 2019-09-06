package zhwanwan.algs;

import java.util.*;

/**
 * ZOOM-2019.05
 * 表达式求值:
 * 输入: 2+3*4-10/5
 * 输出: 12
 *
 * @author zhwanwan
 * @create 2019-05-27 5:08 PM
 */
public class ExpressionCalculation {

    private enum OPERATOR {

        PLUS('+', 0),
        SUBTRACT('-', 0),
        MULTIPLY('*', 1),
        DIVIDE('/', 1);

        OPERATOR(char ope, int p) {
            this.ope = ope;
            this.p = p;
        }

        private char ope;

        private int p;

        public static int getP(char c) {
            return Arrays.stream(OPERATOR.values())
                    .filter(ope -> ope.ope == c).findFirst().get().p;
        }
    }

    public static int getExpressionValueV2(String exp) {
        char[] chars = (exp + "$").replace(" ", "").toCharArray();
        Deque<Integer> ods = new LinkedList<>();
        Deque<Character> ors = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                sb.append(chars[i]);
            } else {
                char newOr = chars[i];
                Integer newOd = Integer.valueOf(sb.toString());
                sb.delete(0, sb.length());
                if (ors.isEmpty()) {
                    ods.push(newOd);
                } else {
                    char topOr = ors.peek();
                    int topP = OPERATOR.getP(topOr);
                    int newP = -1;
                    if (newOr != '$') {
                        newP = OPERATOR.getP(newOr);
                    }
                    if (topP < newP) {
                        ods.push(newOd);
                    }
                    while (topP >= newP && !ors.isEmpty()) {
                        switch (topOr) {
                            case '+': {
                                ors.pop();
                                ods.push(ods.pop() + newOd);
                                break;
                            }
                            case '-': {
                                ors.pop();
                                ods.push(ods.pop() - newOd);
                                break;
                            }
                            case '*': {
                                ors.pop();
                                ods.push(ods.pop() * newOd);
                                break;
                            }
                            case '/': {
                                ors.pop();
                                ods.push(ods.pop() / newOd);
                                break;
                            }
                        }
                        if (!ors.isEmpty()) {
                            topOr = ors.peek();
                            topP = OPERATOR.getP(topOr);
                            if (newOr != '$')
                                newOd = ods.pop();
                        }
                    }
                }
                if (newOr != '$')
                    ors.push(newOr);
            }
        }

        return ods.pop();
    }

    public static int getExpressionValueV1(String exp) {

        char[] chars = (exp + "$").replace(" ", "").toCharArray();
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                sb.append(chars[i]);
            } else {
                char c1 = chars[i];
                Integer num = Integer.valueOf(sb.toString());
                sb.delete(0, sb.length());
                //判断符号栈是否为空
                if (!operators.isEmpty()) {
                    char c = operators.peek();
                    switch (c) {
                        case '*': {
                            Integer x = operands.pop();
                            int temp = x * num;
                            operands.push(temp);
                            operators.pop();
                            break;
                        }
                        case '/': {
                            Integer x = operands.pop();
                            int temp = x / num;
                            operands.push(temp);
                            operators.pop();
                            break;
                        }
                        default: {
                            operands.push(num);
                        }

                    }
                } else {
                    operands.push(num);
                }
                if (c1 != '$') //结束符
                    operators.push(c1);
            }
        }

        Collections.reverse(operands);
        Collections.reverse(operators);

        while (!operators.isEmpty()) {
            char c = operators.pop();
            int x, y = 0;
            x = operands.pop();
            if (!operands.isEmpty()) {
                y = operands.pop();
            }
            switch (c) {
                case '+': {
                    operands.push(x + y);
                    break;
                }
                case '-': {
                    operands.push(x - y);
                }
            }
        }

        int result = operands.pop();

        return result;
    }

    public static void main(String[] args) throws Exception {
        /*Runtime r = Runtime.getRuntime();
        Process process = r.exec("cmd /c start C:\\WINDOWS\\system32\\calc.exe");*/

        System.out.println("2 + 3 * 4 - 10 / 5-11*2+4*9 + 12".replace(" ", "").toCharArray());
        System.out.println(2 + 3 * 4 - 10 / 5 - 11 * 2 + 4 * 9 + 12);
        System.out.println(getExpressionValueV1("2 + 3 * 4 - 10 / 5-11*2+4*9+12"));
        System.out.println(getExpressionValueV2("2 + 3 * 4 - 10 / 5-11*2+4*9+12"));

    }
}
