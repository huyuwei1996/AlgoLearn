package DataStructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    public static void main(String[] args) {
        int n = 5;
        String[] Vertexs = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String vertex : Vertexs) {
            graph.insertVertex(vertex);
        }
        // A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();

//        graph.dfs();
        graph.bfs();
    }

    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges;
    private boolean[] isVisited;

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0; //已经初始化过了，其实默认为0
        isVisited = new boolean[n];
    }

    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    public void dfs(boolean[] isVisited, int i) {
        System.out.print(getValueByIndex(i) + " => ");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    public void bfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    public void bfs(boolean[] isVisited, int i) {
        int u;  //队列的头节点
        int w;  //邻接点
        LinkedList queue = new LinkedList(); // 用队列记录已访问过的节点顺序
        System.out.print(getValueByIndex(i) + " => ");
        isVisited[i] = true;
        queue.addLast(i); // 将访问过的节点加入到队列中
        while (!queue.isEmpty()) {
            u = (Integer) queue.removeFirst(); //取出队列的头节点
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + " => ");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }

    }

    // 获取第一个邻接点的下标
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * @param v1 正在访问的下标(v1这一行，v2的下一个邻接点)
     * @param v2 第一个邻接点
     * @return 返回第一个邻接点的下一个邻接点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 返回节点的数量
    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    // 返回节点i对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    // 插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     第几个节点对应的下标
     * @param v2     第几个节点对应的下标
     * @param weight 两个节点的权值（1表示关联，0表示不关联）
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
