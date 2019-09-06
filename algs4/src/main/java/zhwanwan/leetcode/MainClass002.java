package zhwanwan.leetcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*ListNode ln = null;
        if((l1 != null && l2 == null)) {
            ln = l1;
        } else if (l1 == null && l2 !=null) {
            ln = l2;
        } else if(l1!=null && l2!=null){
            int sum = 0,flag = 0;
            ln = new ListNode(0);
            ListNode p = ln;
            while(l1 != null && l2 != null) {
                sum = l1.val + l2.val + flag;
                l1 = l1.next;
                l2 = l2.next;
                p.next = new ListNode(sum%10);
                p = p.next;
                flag = sum/10;
            }
            while(l1 != null){
                sum = l1.val + flag;
                l1 = l1.next;
                p.next = new ListNode(sum%10);
                p = p.next;
                flag = sum/10;
            }
            while(l2 != null){
                sum = l2.val + flag;
                l2 = l2.next;
                p.next = new ListNode(sum%10);
                p = p.next
                flag = sum/10;
            }
            if(flag > 0)
                p.next = new ListNode(flag);
        }
        return ln.next;*/

        ListNode ln = new ListNode(0);
        ListNode p = ln;
        int temp = 0;
        int aux = 0;
        while (l1 != null || l2 != null) {
            int a1 = 0, a2 = 0;
            if (l1 != null) {
                a1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                a2 = l2.val;
                l2 = l2.next;
            }
            temp = a1 + a2 + aux;
            aux = temp / 10;
            temp %= 10;
            p.next = new ListNode(temp);
            p = p.next;
        }
        if (aux > 0)
            p.next = new ListNode(aux);

        return ln.next;

    }
}

public class MainClass002 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }
        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode l1 = stringToListNode(line);
            line = in.readLine();
            ListNode l2 = stringToListNode(line);
            ListNode ret = new Solution().addTwoNumbers(l1, l2);
            String out = listNodeToString(ret);
            System.out.println(out);
        }
    }
}