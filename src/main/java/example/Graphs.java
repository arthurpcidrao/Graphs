package example;

import java.util.*;

public class Graphs {
    private final float[][] matrix;
    private final int rows;
    private final int cols;
    private final int vertices;
    private final HashMap<Integer, LinkedList<Integer>> adjacencyList;

    public Graphs(float[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.vertices = this.rows * this.cols;
        this.adjacencyList = new HashMap<>();
    }

    public void addEdge(int from, int to) {
        if (adjacencyList.containsKey(from)) {
            adjacencyList.get(from).add(to);
        }
        else{
            this.adjacencyList.putIfAbsent(from, new LinkedList<>());
            this.adjacencyList.get(from).add(to);
        }
    }

    public void printAdjacencyList(){
        for (var entry : this.adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            System.out.println(entry.getValue());
        }
    }


    public void buildAllGraph(){

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {

                if (i > 0 && matrix[i][j] == matrix[i - 1][j]) {
                    addEdge(i*this.cols + j, (i - 1) * this.cols + j);
                }

                if (i < this.rows - 1 && matrix[i][j] == matrix[i + 1][j]) {
                    addEdge(i*this.cols + j, (i + 1) * this.cols + j);
                }

                if (j > 0 && matrix[i][j] == matrix[i][j - 1]) {
                    addEdge(i*this.cols + j, i * this.cols + (j - 1));
                }

                if (j < this.cols - 1 && matrix[i][j] == matrix[i][j + 1]) {
                    addEdge(i*this.cols + j, i * this.cols + (j + 1));
                }


                if (i > 0 && j > 0 && matrix[i][j] == matrix[i - 1][j - 1]) {
                    addEdge(i*this.cols + j, (i - 1) * this.cols + (j - 1));
                }

                if (i < this.rows - 1 && j > 0 && matrix[i][j] == matrix[i + 1][j - 1]) {
                    addEdge(i*this.cols + j, (i + 1) * this.cols + (j - 1));
                }

                if (i > 0 && j < this.cols - 1 && matrix[i][j] == matrix[i - 1][j + 1]) {
                    addEdge(i*this.cols + j, (i - 1) * this.cols + (j + 1));
                }

                if (i < this.rows - 1 && j < this.cols - 1 && matrix[i][j] == matrix[i + 1][j + 1]) {
                    addEdge(i*this.cols + j, (i + 1) * this.cols + (j + 1));
                }
            }
        }
    }


    public void floodFill(int i, int j, float color) {
        int start = i * this.cols + j;
        boolean visited[] = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);
        this.matrix[i][j] = color;
        int current = 0;
        while (!queue.isEmpty()) {
            current = queue.poll();

            for (int neighbor : this.adjacencyList.get(current)) {
                int nx = neighbor / cols; // pega o valor inteiro de i*cols + j
                int ny = neighbor % cols; // pega o resto da divisão i*cols + j

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    this.matrix[nx][ny] = color;
                    queue.add(neighbor);
                }
            }
        }
    }

    public float[][] getMatrix() {
        return matrix;
    }


    public void printNewMatrix() {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                int test = (int) matrix[i][j];
                System.out.print(test + " ");
            }
            System.out.println();
        }
    }
}

