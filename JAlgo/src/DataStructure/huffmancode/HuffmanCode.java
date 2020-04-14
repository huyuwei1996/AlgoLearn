package DataStructure.huffmancode;

import java.io.*;
import java.util.*;

// 赫夫曼编码(数据压缩)
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);

        List<Node> nodes = getNodes(contentBytes);
        System.out.println(nodes);
        // 测试创建的赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("赫夫曼树的前序遍历:");
        huffmanTreeRoot.preOrder();

        // 测试生成的赫夫曼编码
        System.out.println("赫夫曼编码:");
//        getHuffmanCodes(huffmanTreeRoot, "", stringBuilder);
//        System.out.println(huffmanCodes);
        Map<Byte, String> code = getHuffmanCodes(huffmanTreeRoot); //
        System.out.println(code); //

        // 测试生成的赫夫曼编码后的字节数组
        System.out.println("赫夫曼编码后的字节数组:");
        byte[] huffmanCodeBytes = zip(contentBytes, code);
        System.out.println(Arrays.toString(huffmanCodeBytes));
        System.out.println(huffmanCodeBytes.length);

        // 一个方法
        byte[] huffmanCodeBytes1 = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(huffmanCodeBytes1));

    }

    // 1.统计，并转成Node数组
    private static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<Node>();

        Map<Byte, Integer> map = new HashMap<>();
        // 使用map统计每个byte出现的次数
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }

        // 遍历，把每个键值对，转成Node对象，放到集合中
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    // 2.通过List创建对应的赫夫曼树，返回根节点
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);

            Node left = nodes.get(0);
            Node right = nodes.get(1);
            // 没有data，只有权值
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }


    // Byte=>ASCII  String=>编码的字符串(拼接的路径)
    // StringBuilder存储叶子节点的路径
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 3.通过赫夫曼树，生成对应的赫夫曼编码
     *
     * @param node          传入的节点
     * @param code          表示路径：左子节点=0，右子节点=1
     * @param stringBuilder 用于拼接路径
     */
    private static void getHuffmanCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);

        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) { // 非叶子节点
                // 递归处理
                getHuffmanCodes(node.left, "0", stringBuilder2);
                getHuffmanCodes(node.right, "1", stringBuilder2);
            } else {
                //说明是一个叶子节点，这次后就可以结束了
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    private static Map<Byte, String> getHuffmanCodes(Node root) {
        getHuffmanCodes(root, "", stringBuilder);
        return huffmanCodes;
    }

    // 5. 生成字符串对应的赫夫曼编码数据(字节数组)
    private static byte[] zip(byte[] contentBytes, Map<Byte, String> huffmanCodes) {
        // 遍历byte数组，把原始数据转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : contentBytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        // 计算要返回的byte数组的长度
        // 可以一句话int len = (stringBuilder.length() + 7) / 8
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        // 每8位做一个byte，放入结果的数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) { // 每8位(步长为8)
            String strByte;
            if (i + 8 > stringBuilder.length()) { // 不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将strByte转成一个byte，放到结果的数组
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2); // 二进制
            index++;
        }
        return huffmanCodeBytes;
    }

    // 使用一个方法，将前面的所有封装起来，便于我们的调用
    private static byte[] huffmanZip(byte[] contentBytes) {
        List<Node> nodes = getNodes(contentBytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        Map<Byte, String> code = getHuffmanCodes(huffmanTreeRoot);
        return zip(contentBytes, code);
    }

//    public static void zipFile(String srcFile, String dstFile) {
//        OutputStream os = null;
//        ObjectOutputStream oos = null;
//        FileInputStream is =null;
//        try {
//            is = new FileInputStream(srcFile);
//            byte[] b = new byte[is.available()];
//            is.read(b);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
}


class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight; // 从小到大
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}