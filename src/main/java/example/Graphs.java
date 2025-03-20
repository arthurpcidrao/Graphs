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


    public void buildGraph(int i, int j, Set<Integer> visited){  // hash para acesso em O(1)
        Queue<Integer> queue = new LinkedList<>();
        int start = i * this.cols + j;
        queue.add(start);

        int currentIndex = 0;
        while (!queue.isEmpty()) {
            //int currentIndex = stack.pop();
            currentIndex = queue.poll();

            if (visited.contains(currentIndex)) {
                continue;
            }

            visited.add(currentIndex);

            int x = currentIndex / this.cols;
            int y = currentIndex % this.cols;


            if (x > 0 && matrix[x][y] == matrix[x - 1][y]) {
                addEdge(currentIndex, (x - 1) * this.cols + y);
                queue.add((x - 1) * this.cols + y);
            }

            if (x < this.rows - 1 && matrix[x][y] == matrix[x + 1][y]) {
                addEdge(currentIndex, (x + 1) * this.cols + y);
                queue.add((x + 1) * this.cols + y);
            }

            if (y > 0 && matrix[x][y] == matrix[x][y - 1]) {
                addEdge(currentIndex, x * this.cols + (y - 1));
                queue.add(x * this.cols + (y - 1));
            }

            if (y < this.cols - 1 && matrix[x][y] == matrix[x][y + 1]) {
                addEdge(currentIndex, x * this.cols + (y + 1));
                queue.add(x * this.cols + (y + 1));
            }


            if (x > 0 && y > 0 && matrix[x][y] == matrix[x - 1][y - 1]) { // diagonal superior esquerda
                addEdge(currentIndex, (x - 1) * this.cols + (y - 1));
                queue.add((x - 1) * this.cols + (y - 1));
            }

            if (x < this.rows - 1 && y > 0 && matrix[x][y] == matrix[x + 1][y - 1]) { // diagonal inferior esquerda
                addEdge(currentIndex, (x + 1) * this.cols + (y - 1));
                queue.add((x + 1) * this.cols + (y - 1));
            }

            if (x > 0 && y < this.cols - 1 && matrix[x][y] == matrix[x - 1][y + 1]) { // diagonal superior direita
                addEdge(currentIndex, (x - 1) * this.cols + (y + 1));
                queue.add((x - 1) * this.cols + (y + 1));
            }

            if (x < this.rows - 1 && y < this.cols - 1 && matrix[x][y] == matrix[x + 1][y + 1]) { // diagonal inferior direita
                addEdge(currentIndex, (x + 1) * this.cols + (y + 1));
                queue.add((x + 1) * this.cols + (y + 1));
            }
        }
    }

    // Deu stackoverflow, sugerindo que há muitas chamadas recursivas e meu computador não tankou
    public void buildGraphRecursive(int i, int j, Set<Integer> visited) {

        int currentIndex = i * (this.cols) + j;

        if (visited.contains(currentIndex)) {
            return;
        }

        visited.add(currentIndex);

        if (i > 0 && matrix[i][j] == matrix[i - 1][j]) {
            addEdge(currentIndex, (i - 1) * this.cols + j);  // cima
            buildGraph(i - 1, j, visited);
        }

        if (i < this.rows - 1 && matrix[i][j] == matrix[i + 1][j]) {
            addEdge(currentIndex, (i + 1) * this.cols + j);
            buildGraph(i + 1, j, visited);
        }

        if (j > 0 && matrix[i][j] == matrix[i][j - 1]) {
            addEdge(currentIndex, i * this.cols + j - 1);
            buildGraph(i, j - 1, visited);
        }

        if (j < this.cols - 1 && matrix[i][j] == matrix[i][j + 1]) {
            addEdge(currentIndex, i * this.cols + j + 1);
            buildGraph(i, j + 1, visited);
        }


        if (i>0 && j>0 && matrix[i][j] == matrix[i-1][j-1]) {
            addEdge(currentIndex, (i - 1) * this.cols + j - 1);  // diagonal esquerda cima
            buildGraph(i - 1, j - 1, visited);
        }

        if (i<(rows-1) && j>0 && matrix[i][j] == matrix[i+1][j-1]) {
            addEdge(currentIndex, (i + 1) * this.cols + j - 1);  // diagonal esquerda baixo
            buildGraph(i + 1, j - 1, visited);
        }

        if (i>0 && j<(cols-1) && matrix[i][j] == matrix[i-1][j+1]) {
            addEdge(currentIndex, (i - 1) * this.cols + j + 1);  // diagonal direita cima
            buildGraph(i - 1, j + 1, visited);
        }

        if (i<(rows-1) && j<(cols-1) && matrix[i][j] == matrix[i+1][j+1]) {
            addEdge(currentIndex, (i + 1) * this.cols + j + 1);  // diagonal direita baixo
            buildGraph(i + 1, j + 1, visited);
        }
    }


    public void floodFill(int i, int j, float color) {
        int start = i*this.cols + j;
        boolean visited[] = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);
        this.matrix[i][j] = color;
        int current = 0;
        while (!queue.isEmpty()) {
            current = queue.poll();
            int x = current / cols; // pega o valor inteiro de i*cols + j
            int y = current % cols; // pega o resto da divisão i*cols + j

            for (int neighbor : this.adjacencyList.get(current)) {
                int nx = neighbor / cols;
                int ny = neighbor % cols;

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

