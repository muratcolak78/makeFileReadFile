package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField tfFoderName, tfFileName;
    private String path;
    @FXML
    private Button buttonReadFile;
    @FXML
    private TextArea tAReadFile, tAWritesomething;
    @FXML
    private File file ;
    @FXML
    private FileWriter fileWriter;

    @FXML
    protected void onHelloButtonClick() throws IOException {

        makeFile(tfFoderName.getText(), tfFileName.getText());
        writeSometihngs(path, tAWritesomething.getText());
    }

    @FXML
    protected void onReadButtonClick() throws IOException {
        fileReader(file);
    }

    public void makeFile(String folderName, String fileName) {

        try {
            file = new File(revCreateDirectory(folderName) + "\\" + fileName);
            path = revCreateDirectory(folderName) + "\\" + fileName;
            if (file.createNewFile()) {
                welcomeText.setText("File created: " + file.getName());
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        tAReadFile.setText("");
    }

    public String revCreateDirectory(String pathName) {
        String path = "";
        //To create single directory/folder
        /*File file = new File("C:\\Users\\Deneme\\file.txt");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }*/
        //To create multiple directories/folders
        File files = new File("C:\\Users\\Public\\Documents\\" + pathName);
        if (!files.exists()) {
            if (files.mkdirs()) {
                System.out.println("Multiple directories are created!");
                path = "C:\\Users\\Public\\Documents\\" + pathName;
            } else {
                System.out.println("Failed to create multiple directories!");
            }
        }
        return path;
    }

    public void fileReader(File readfile) throws IOException {


        BufferedReader br = new BufferedReader(new FileReader(readfile));
        String text = "";
        try {
            String line;
            while ((line = br.readLine()) != null) {
                text += line+"\n";
                tAReadFile.setText(text);
            }
        } finally {
            br.close();
        }

    }

    @FXML
    public void writeSometihngs(String filePath, String text) throws IOException {
        fileWriter = new FileWriter(file, true);

        fileWriter.write(text);

        fileWriter.close();

    }
}