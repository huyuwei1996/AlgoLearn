package DataStructure.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.push(3);
        arrayStack.push(7);
        arrayStack.push(5);
        arrayStack.push(4);
        arrayStack.list();
        System.out.println(arrayStack.pop());
        arrayStack.list();
    }
}

// 用数组模拟栈
class ArrayStack {
    public int maxSize;
    public int top = -1;
    public int[] stack;

    public ArrayStack(int maxSize) {
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
}
