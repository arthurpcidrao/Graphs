package example;

public class Matrix {
    private float matrix[][];
    private int rows;
    private int cols;
    private Path_Manipulation path;

    public Matrix(String path) {
        this.path = new Path_Manipulation();
        this.path.readFile(path);

        if (this.path.getFileLines() != null){
            this.rows = this.path.getFileLines().size();
            this.cols = this.path.getFileLines().getFirst().split(" ").length;

            this.matrix = new float[this.rows][this.cols];

            for (int i = 0; i < this.rows; i++) {
                String[] currentString = this.path.getFileLines().get(i).split(" ");
                for (int j = 0; j < this.cols; j++) {
                    this.matrix[i][j] = Integer.parseInt(currentString[j]);
                }
            }
        }
    }

    public float[][] getMatrix(){
        return this.matrix;
    }

    public void printMatrix(float[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
