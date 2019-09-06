package zhwanwan.algs;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;

/**
 * • Push operands onto the operand stack.
 * • Push operators onto the operator stack.
 * • Ignore left parentheses.
 * • On encountering a right parenthesis, pop an operator, pop the requisite number of operands,
 * and push onto the operand stack the result of applying that operator to those operands.
 *
 * @author wangzhen
 * @create 2019-04-30 1:53 PM
 */
public class Evaluate {

    public static void main(String[] args) {
        Stack<String> optr = new Stack<>();
        Stack<Double> oprd = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        while (!StdIn.isEmpty()) {
            //read token, push if operator
            String s = StdIn.readString();
            if ("(".equals(s)) ;
            else if ("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s) || "sqrt".equals(s))
                optr.push(s);
            else if (")".equals(s)) {
                //pop,evaluate,and push result if token is ")"
                String op = optr.pop();
                double v = oprd.pop();
                if (op.equals("+"))
                    v = oprd.pop() + v;
                else if (op.equals("-"))
                    v = oprd.pop() - v;
                else if (op.equals("*"))
                    v = oprd.pop() * v;
                else if (op.equalsIgnoreCase("sqrt"))
                    v = Math.sqrt(v);
                oprd.push(v);
            } else {
                oprd.push(Double.parseDouble(s));
            }
        }
        StdOut.println(oprd.pop()); //print expression value
    }
}
