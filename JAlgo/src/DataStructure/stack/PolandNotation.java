package DataStructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {
        // 完成，将中缀表达式转后缀表达式
        String expression = "1+((2+3)x4)-5";
        List<String> InfixList = toInfixListString(expression);
        System.out.println("InfixList = " + InfixList);

        // 中缀表达式List => 后缀表达式List
        List<String> suffixExpressionList = parseSuffixListString(InfixList);
        System.out.println("suffixExpressionList = " + suffixExpressionList);
        // 结果
        System.out.printf("%s 的结果是 %d", expression, calculate(suffixExpressionList));

        // 逆波兰表达式 - 后缀表达式
//        String suffixExpression = "3 4 + 5 x 6 - ";
//        List<String> list = getListString(suffixExpression);
//        System.out.println("List = " + list);
//        int result = calculate(list);
//        System.out.printf("逆波兰表达式 %s 的结果是 %d\n", suffixExpression, result);
    }

    // 中缀表达式List转后缀表达式List
    public static List<String> parseSuffixListString(List<String> list) {
        Stack<String> s1 = new Stack<String>(); //符号栈
        List<String> s2 = new ArrayList<String>(); // 可以用ArrayList替代s2栈，因为s2没有pop过，而且还需要逆序输出

        for (String item : list) {
            if (item.matches("\\d+")) { //如果是数字，直接加到s2中
                s2.add(item);
            } else if (item.equals("(")) { //左括号直接入s1栈
                s1.push(item);
            } else if (item.equals(")")) { //右括号，需要弹出s1顶部的运算符，加到s2中，直到碰到一个(括号
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); //!!!,将(括号弹出
            } else {
                //如果是其他运算符，需要判断优先级
                while (s1.size() != 0 && getPriority(item) <= getPriority(s1.peek())) {
                    //如果当前优先级小于等于s1栈顶的，就把s1栈顶的运算符一直弹出，加到s2中
                    s2.add(s1.pop());
                }
                s1.push(item);// 否则，把这个优先级高的，压入s1
            }
        }
        // 最后把s1中剩余的运算符依次弹出，加入到s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    // 返回运算符的优先级
    public static int getPriority(String oper) {
        int res = 0;
        switch (oper) {
            case "+":
                res = 1;
                break;
            case "-":
                res = 1;
                break;
            case "x":
                res = 2;
                break;
            case "*":
                res = 2;
                break;
            case "/":
                res = 2;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return res;
    }

    // 中缀表达式，转ArrayList存
    public static List<String> toInfixListString(String s) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        char c;
        String str;
        while (i < s.length()) {
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) { // 非数字
                list.add("" + c);
                i++;
            } else {
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }
        return list;
    }

    public static List<String> getListString(String suffixExpression) {
        List<String> list = new ArrayList<String>();
        String[] split = suffixExpression.split(" ");
        for (String item : split) {
            list.add(item);
        }
        return list;
    }

    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<String>();
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        // 遍历ArrayList，取值运算
        for (String item : list) {
            if (item.matches("\\d+")) { //正则匹配多位数
                stack.push(item);
            } else {
                // 如果是符号
                num2 = Integer.parseInt(stack.pop());
                num1 = Integer.parseInt(stack.pop());
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("x")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
