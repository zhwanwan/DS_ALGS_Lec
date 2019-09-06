package com.ds.lec05.stack;

import java.util.*;

/**
 * 波兰表达式(PolishNotation)
 * 前缀表达式又称波兰式，前缀表达式的运算符位于操作数之前
 * 举例说明： (3+4)×5-6 对应的前缀表达式就是 - × + 3 4 5 6
 * <p>
 * 前缀表达式的计算机求值
 * 从右至左扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算（栈顶元素 和 次顶元素），
 * 并将结果入栈；重复上述过程直到表达式最左端，最后运算得出的值即为表达式的结果
 * <p>
 * 例如: (3+4)×5-6 对应的前缀表达式就是 - × + 3 4 5 6 , 针对前缀表达式求值步骤如下:
 * <p>
 * 从右至左扫描，将6、5、4、3压入堆栈
 * 遇到+运算符，因此弹出3和4（3为栈顶元素，4为次顶元素），计算出3+4的值，得7，再将7入栈
 * 接下来是×运算符，因此弹出7和5，计算出7×5=35，将35入栈
 * 最后是-运算符，计算出35-6的值，即29，由此得出最终结果
 * ---------------------------------------------------------------------------------------------------------------------
 * 逆波兰表达式(ReversePolishNotation--RPN)
 * 后缀表达式又称逆波兰表达式,与前缀表达式相似，只是运算符位于操作数之后
 * 举例说明： (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 –
 * <p>
 * 后缀表达式的计算机求值
 * 从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算（次顶元素和栈顶元素），
 * 并将结果入栈；
 * 重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果
 * <p>
 * 1.扫描给出的计算表达式
 * 例如: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:
 * <p>
 * 从左至右扫描，将3和4压入堆栈；
 * 遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
 * 将5入栈；
 * 接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
 * 将6入栈；
 * 最后是-运算符，计算出35-6的值，即29，由此得出最终结果
 *
 * @author zhwanwan
 * @create 2019-09-01 10:09
 */
public class ReversePolishNotation {

    public static void main(String[] args) {
        //中缀表达式转后缀表达式
        String infixNotation = "1+((2+3)*4)-5";
        String postfixNotation = convert(infixNotation);
        System.out.printf("%s 的后缀表达式：%s\n", infixNotation, postfixNotation);
        //计算后缀表达式值
        String rpn = "3 4 + 5 * 6 -";
        System.out.printf("%s = %d\n", rpn, calculate(rpn));
        System.out.printf("%s = %d\n", postfixNotation, calculate(postfixNotation));


    }

    /**
     * 中缀表达式转后缀表达式
     * 1 + ( ( 2 + 3 )* 4) - 5 => 1 2 3 + 4 * + 5 -
     * 思路：
     * 1) 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     * 2) 从左至右扫描中缀表达式；
     * 3) 遇到操作数时，将其压s2；
     * 4) 遇到运算符时，比较其与s1栈顶运算符的优先级：
     * 1.如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     * 2.否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     * 3.否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
     * 5) 遇到括号时：
     * 1.如果是左括号“(”，则直接压入s1
     * 2.如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6) 重复步骤2至5，直到表达式的最右边
     * 7) 将s1中剩余的运算符依次弹出并压入s2
     * 8) 依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     *
     * @param infixNotation 中缀表达式
     * @return 后缀表达式
     */
    public static String convert(String infixNotation) {
        infixNotation = replace(infixNotation);
        Deque<String> s1 = new LinkedList<>();
        List<String> s2 = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(infixNotation, " ");
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.matches("\\d+")) //正则表达式匹配数字
                s2.add(token);
            else if ("(".equals(token))
                s1.push(token);
            else if (")".equals(token)) {
                while (!"(".equals(s1.peek()))
                    s2.add(s1.pop());
                s1.pop(); //弹出左括号
            } else {
                //遇到运算符时
                /*if (s1.isEmpty() || "(".equals(s1.peek()))
                    s1.push(token);
                else {
                    OPERATOR top = OPERATOR.getOperator(s1.peek());
                    OPERATOR current = OPERATOR.getOperator(token);
                    if (current.priority > top.priority)
                        s1.push(token);
                    else {
                        s2.add(s1.pop());
                        s1.push(token);
                    }
                }*/
                while (!s1.isEmpty()
                        && !"(".equals(s1.peek()) //左括号优先级最大
                        && OPERATOR.getOperator(token).priority <= OPERATOR.getOperator(s1.peek()).priority
                )
                    s2.add(s1.pop());
                s1.push(token);
            }
        }
        while (!s1.isEmpty())
            s2.add(s1.pop());
        return String.join(" ", s2);
    }

    /**
     * 逆波兰表达式的运算
     * 3 4 + 5 × 6 –
     * 1)从左至右扫描，将3和4压入堆栈；
     * 2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
     * 3)将5入栈；
     * 4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
     * 5)将6入栈；
     * 6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     *
     * @param rpn
     * @return
     */
    public static int calculate(String rpn) {
        rpn = replace(rpn);
        StringTokenizer st = new StringTokenizer(rpn, " ");
        Deque<Integer> operands = new LinkedList<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.matches("\\d+"))
                operands.push(Integer.parseInt(token));
            else {
                if (!isOperator(token))
                    throw new RuntimeException("运算符有误！");
                OPERATOR operator = OPERATOR.getOperator(token);
                int num2 = operands.pop();
                int num1 = operands.pop();
                int res = cal(num1, num2, operator);
                operands.push(res);
            }
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
