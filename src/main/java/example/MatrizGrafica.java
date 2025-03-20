package example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

public class MatrizGrafica extends JPanel {
    private static int LINHAS; // Número de linhas
    private static int COLUNAS; // Número de colunas
    private int tamanhoCelula = 1; // Tamanho inicial da célula
    private float[][] matrizCores; // Matriz de cores
    private final float[] coresPredefinidas = {0.0f, 0.125f, 0.25f, 0.50f, 0.625f, 0.75f, 0.95f}; // Tons distintos no espectro HSB

    // getHSBColor
    public MatrizGrafica() {
        // Inicializa a matriz com cor branca

        Matrix matrix = new Matrix("src/arquivos_trabalho/trabalho 1 - grafos/data/UNIFOR_grayscale.txt");
        LINHAS = matrix.getMatrix().length;
        COLUNAS = matrix.getMatrix()[0].length;

        float matriz[][] = matrix.getMatrix();

        matrizCores = new float[LINHAS][COLUNAS];
        float test = 0;
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                test = matrix.getMatrix()[i][j]/255.0f;
                matrizCores[i][j] = test;
            }
        }


        // Adiciona MouseListener para detectar cliques
        addMouseListener(new MouseAdapter() {
            private int aux = 1;

            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = e.getX() / tamanhoCelula;
                int coluna = e.getY() / tamanhoCelula;

                Graphs graph = new Graphs(matrizCores);
                graph.buildAllGraph();

                if (linha >= 0 && linha < LINHAS && coluna >= 0 && coluna < COLUNAS) {

                    graph.floodFill(coluna,linha,coresPredefinidas[aux]);
                    aux++;
                    if (aux == coresPredefinidas.length-1){
                        aux = 0;
                    }

                    repaint(); // Re-desenha a tela
                }
            }
        });


        // Adiciona KeyListener para zoom via teclado
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == '+') {
                    aumentarZoom();
                } else if (e.getKeyChar() == '-') {
                    diminuirZoom();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Ajusta o tamanho do painel para suportar scroll
        setPreferredSize(new Dimension(COLUNAS * tamanhoCelula, LINHAS * tamanhoCelula));
        // Desenha a matriz
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                g.setColor(Color.getHSBColor(matrizCores[i][j], 1.0f, 1.0f));
                g.fillRect(j * tamanhoCelula, i * tamanhoCelula, tamanhoCelula, tamanhoCelula);
                //g.setColor(Color.BLACK);
                g.drawRect(j * tamanhoCelula, i * tamanhoCelula, tamanhoCelula, tamanhoCelula);
            }
        }
    }

    private void aumentarZoom() {
        if (tamanhoCelula < 15) { // Limite para evitar zoom excessivo
            tamanhoCelula += 1;
            repaint();
            revalidate();
        }
    }

    private void diminuirZoom() {
        if (tamanhoCelula > 1) { // Evita que fique muito pequeno
            tamanhoCelula -= 1;
            repaint();
            revalidate();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Balde de tinta");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MatrizGrafica matrizPanel = new MatrizGrafica();
            JScrollPane scrollPane = new JScrollPane(matrizPanel);

            frame.add(scrollPane);
            frame.setSize(1000, 700);
            frame.setVisible(true);
        });
    }
}
