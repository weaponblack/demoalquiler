package com.prog2.helpers;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class UtilFiles {

    public static final String FILE_PATH = "./data/";

    public static boolean fileExists(String fileName) {
        Path dirPath = Paths.get(fileName);
        return Files.exists(dirPath) && !Files.isDirectory(dirPath);
    }

    public static boolean pathExists(String path) {
        Path folder = Paths.get(path);
        return Files.exists(folder) && Files.isDirectory(folder);
    }

    public static void createFolderIfNotExists(String folder) throws IOException {
        if (!pathExists(folder)) {
            Path dirPath = Paths.get(folder);
            Files.createDirectories(dirPath);
        }
    }

    public static String getPath(String path) {
        Path parentPath = Paths.get(path).getParent();
        return parentPath == null ? null : parentPath.toString();
    }

    public static void initPath(String filePath) throws IOException {
        String path = getPath(filePath);
        createFolderIfNotExists(path);

    }

    public static String readText(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    public static void writeText(String fileName, String content) throws IOException {
        initPath(fileName);
        Path path = Paths.get(fileName);
        Files.write(path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
    }

    public static void writeText(String fileName, List<String> lines) throws IOException {
        initPath(fileName);
        Path path = Paths.get(fileName);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public static void writeText(List<?> list,String fileName) throws IOException{
        initPath(fileName);
        try(FileWriter fw = new FileWriter(new File(fileName), StandardCharsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(fw)){
            for (int i = 0; i < list.size(); i++){
                writer.append(list.get(i).toString());
                writer.newLine();
            }
        }
    }
    public static void writeCSV(List<?> list,String fileName) throws IOException{
        initPath(fileName);
        try(FileWriter fw = new FileWriter(new File(fileName), StandardCharsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(fw)){
            for (int i = 0; i < list.size(); i++){
                IFormatCSV aux = (IFormatCSV) list.get(i);
                writer.append(aux.toCSV());
                writer.newLine();
            }
        }
    }
}
