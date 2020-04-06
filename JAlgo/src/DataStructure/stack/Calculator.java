package DataStructure.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "7+2*6-4"; // 如何处理多位数
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index = 0; // 用一个索引来扫描表达式
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";
        while (index < expression.length()) { // 退出循环条件：当index一直+1，大于或等于表达式的长度时
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) { // 如果是运算符
                if (!operStack.isEmpty()) { // 符号栈如果不为空，就要比较下运算符的优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        // 如果当前运算符优先级小于等于栈内的，取值运算
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper); // 把运算结果放入数栈
                        operStack.push(ch); // 记住！运算完把当前这个运算符放入符号栈
                        numStack.push(res);
                    } else { // 如果当前运算符优先级高，继续压入符号栈
                        operStack.push(ch);
                    }
                } else { // 如果符号栈为空，也直接压入符号栈
                    operStack.push(ch);
                }
            } else { // 如果是数字
                keepNum += ch; // 考虑下多位数，放在一个string里
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        // 看下一位如果是运算符就不继续取。此处index+1容易越界
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";// ⚠️注意清空
                    }
                }
            }
            index++;
        }
        // 当表达式扫描完，就顺序的从数栈和符号栈，去出数和符号，进行运算
        while (!operStack.isEmpty()) { // 只要符号栈不为空，就可以一直运算
            // 如果当前运算符优先级小于等于栈内的，取值运算
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        // 最后数栈中只有一个数，就是结果
        System.out.printf("表达式 %s = %d\n", expression, numStack.pop());
    }
}

// 用数组模拟栈
class ArrayStack2 {
    public int maxSize;
    public int top = -1;
    public int[] stack;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    // 判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
        }
        top++;
        stack[top] = value;
    }

    // 出栈 - 把栈顶数据返回
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top]; // 保存栈顶的数据
        top--;
        return value; // 返回这个数据
    }

    // 查看展示，从栈顶遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    // 运算
    public int cal(int num1, int num2, int oper) {
        int value = 0;
        switch (oper) {
            case '+':
                value = num2 + num1;
                break;
            case '-':
                value = num2 - num1;
                break;
            case '*':
                value = num2 * num1;
                break;
            case '/':
                value = num2 / num1;
                break;
        }
        return value;
    }

    // 判断是否为运算符
    public boolean isOper(int oper) {
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    // 返回运算符的优先级，自定义的
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    // 看一下栈顶的值
    public int peek() {
        return stack[top];
    }
}