package example;

import java.util.HashSet;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // essa main não tem parte gráfica, usei para testes
        // se quiser ver a matriz gráfica, vá para a classe matriz gráfica
        // e use a main no final do arquivo
        Matrix matrix = new Matrix("src/arquivos_trabalho/trabalho 1 - grafos/data/UNIFOR_sample.txt");
        matrix.printMatrix(matrix.getMatrix());

        Graphs graph = new Graphs(matrix.getMatrix());
        graph.buildAllGraph();
        graph.floodFill(6,1,2);

        // usei hashmap e hashset para rapidez de busca de dados
        // mapeei somente o que seria usado para conversão de cor

        System.out.println();

        graph.printAdjacencyList();

        System.out.println();

        graph.printNewMatrix();

    }

}
