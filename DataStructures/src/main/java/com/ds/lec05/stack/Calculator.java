package com.ds.lec05.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 栈的应用
 * 实现综合计算器-自定义优先级[priority]--中缀表达式
 * <p>
 * 7+2*3+12-(5+1)-(5+3)-4/2 = 9
 * 1.支持多位数
 * 2.支持括号
 * <p>
 * 算法思路:
 * 7+2*3+12-5+1-(5+3)-4/2
 * <p>
 * 操作数  符号栈
 * 7	  +
 * 13	  +
 * -7	  -
 * 4	  /
 * 2
 * <p>
 * 1.+
 * 符号栈为空
 * 当前符号直接放入符号栈
 * 符号栈不为空
 * 1.查看符号栈栈顶元素，并与当前元素比较
 * a.如果栈顶元素优先级小于当前符号，则将当前符号放入符号栈中。
 * b.否则(>=)，则计算
 * <p>
 * 字符串扫描结束：
 * 操作数	操作符
 * 11
 *
 * @author zhwanwan
 * @create 2019-08-20 22:03
 */
public class Calculator {

    public static void main(String[] args) {
        String expression = "7+2*(3+12)-(5+1)-(5+3)-4/2";
        String exp = replace(expression);
        /*StringTokenizer st = new StringTokenizer(exp, " ");
        while (st.hasMoreTokens()) {
            System.out.print(st.nextToken() + " ");
        }*/
        System.out.printf("%s = %d\n", expression, getResult(exp));
    }


    /**
     * 计算表达式的值
     *
     * @param expression
     * @return
     */
    public static int getResult(String expression) {
        String exp = replace(expression);
        StringTokenizer st = new StringTokenizer(exp, " ");
        Deque<Integer> operands = new LinkedList<>();
        Deque<String> operators = new LinkedList<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if ("(".equals(token)) //如果是左括号，则push到符号栈
                operators.push(token);
            else if (")".equals(token)) { //如果是右括号，则优先计算结果
                int num2 = operands.pop();
                int num1 = operands.pop();
                OPERATOR operator = OPERATOR.getOperator(operators.pop());
                int result = cal(num1, num2, operator);
                operands.push(result);
                operators.pop();
            } else if (isOperator(token)) { //如果是符号
                if (operators.isEmpty() || "(".equals(operators.peek())) //此处需要考虑左括号
                    operators.push(token);
                else {
                    //计算结果并push到数字栈
                    OPERATOR top = OPERATOR.getOperator(operators.peek());
                    OPERATOR current = OPERATOR.getOperator(token);
                    if (top.priority >= current.priority) {
                        int num2 = operands.pop();
                        int num1 = operands.pop();
                        OPERATOR operator = OPERATOR.getOperator(operators.pop());
                        int result = cal(num1, num2, operator);
                        operands.push(result);
                    }
                    operators.push(token);
                }
            } else { //数字入栈
                operands.push(Integer.parseInt(token));
            }
        }
        //当表达式扫描完毕，顺序地从数栈和符号栈中pop出相应的数和符号，并运行。
        while (true) {
            if (operators.isEmpty())
                break;
            int num2 = operands.pop();
            int num1 = operands.pop();
            OPERATOR operator = OPERATOR.getOperator(operators.pop());
            int result = cal(num1, num2, operator);
            operands.push(result);
        }
        return operands.pop();
    }

    private static String replace(String expression) {
        return expression.replaceAll("\\*", " * ")
                .replaceAll("\\+", " + ")
                .replaceAll("-", " - ")
                .replaceAll("/", " / ")
                .replaceAll("\\(", " ( ")
                .replaceAll("\\)", " ) ");
    }

    private static int cal(int num1, int num2, OPERATOR operator) {
        int result = 0;
        switch (operator) {
            case PLUS:
                result = num1 + num2;
                break;
            case MINUS:
                result = num1 - num2;
                break;
            case DIVIDE:
                result = num1 / num2;
                break;
            case MULTIPLY:
                result = num1 * num2;
                break;
            default:
                break;
        }
        return result;
    }

    private static boolean isOperator(String str) {
        return OPERATOR.getOperator(str) != null;
    }

}
