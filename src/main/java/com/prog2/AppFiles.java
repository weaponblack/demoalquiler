package com.prog2;
import java.util.ArrayList;

import com.prog2.helpers.UtilFiles;
import com.prog2.model.Vehiculo;

public class AppFiles {

    public static void main(String[] args) throws Exception{

        boolean existe = UtilFiles.fileExists("./src/main/java/com/prog2/App.java");
        System.out.println("Existe el archivo " + existe);

        existe = UtilFiles.pathExists("./src/main/java/com/prog2");
        System.out.println("Existe carpeta " + existe);

        UtilFiles.createFolderIfNotExists("./data/images/");
        UtilFiles.createFolderIfNotExists("./data/pdf/");

        String path = UtilFiles.getPath("./src/main/java/com/prog2/App.java");
        System.out.println(path);

        UtilFiles.initPath("./data/pruebas/oficinas.txt");

        String texto = UtilFiles.readText("./src/main/java/com/prog2/App.java");
        System.out.println(texto.substring(0,20));

        UtilFiles.writeText("./data/docs/prueba2.txt","contenido de prueba para escritura de archivos");
        
        ArrayList<String> lineas = new ArrayList<>();
        lineas.add("Prueba 1");
        lineas.add("Prueba 2");
        lineas.add("Prueba 3");
        lineas.add("Prueba 4");
        lineas.add("Prueba 5");
        lineas.add("Prueba 6");
        lineas.add("Prueba 7");
        UtilFiles.writeText("./data/docs/prueba3.txt", lineas);

        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Vehiculo("Mazda", "12", 2021, 4, 150, true));
        vehiculos.add(new Vehiculo("fiat", "56", 1820, 1, 10, true));
        vehiculos.add(new Vehiculo("Hyundai", "98", 2019, 9, 5000, false));
        vehiculos.add(new Vehiculo("kia", "78", 2022, 2, 3000, true));

        UtilFiles.writeText(vehiculos, "./data/docs/prueba4.txt");
        UtilFiles.writeCSV(vehiculos, "./data/docs/prueba5.csv");


    }

    
    
}
 