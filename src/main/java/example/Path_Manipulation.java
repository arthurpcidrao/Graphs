package example;

import java.io.*;
import java.util.ArrayList;

public class Path_Manipulation {
    //private String filePath;
    private ArrayList<String> fileLines;

    public Path_Manipulation() {
        System.out.println("Path_Manipulation creation");
    }

    public void readFile(String filePath) {
        this.fileLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                this.fileLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : this.fileLines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Arquivo salvo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public ArrayList<String> getFileLines(){
        return fileLines;
    }

}
