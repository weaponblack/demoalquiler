package com.prog2.controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.prog2.helpers.UtilFiles;
import com.prog2.model.Vehiculo;

public class CtrlVehiculos {

    private static ArrayList<Vehiculo> vehiculos;
    String fileName;

    public CtrlVehiculos(){
        vehiculos = new ArrayList<>();
        fileName = UtilFiles.FILE_PATH + "vehiculos";

        if (UtilFiles.fileExists(fileName + ".csv")){
            loadCSV();
        }else{
            System.out.println("Aun no se han creado vehiculos");
        }

    }


    public boolean add(Vehiculo vehiculo)throws Exception{
        if(contains(vehiculo)){
            throw new Exception("Error vehiculo repetido: "+ vehiculo.getId() + " - " + vehiculo.getMarca());
        }
        boolean ok = vehiculos.add(vehiculo);
        UtilFiles.writeCSV(vehiculos, fileName + ".csv");
        return ok;
    }

    public ArrayList<Vehiculo> getList(){
        return vehiculos;
    }

    public boolean contains(Vehiculo vehiculo) {
        for (Vehiculo v : vehiculos) {
            if (v.equals(vehiculo)) {
                return true;
            }
        }
        return false;

    }

    public void toReport()throws Exception{
        UtilFiles.writeText(vehiculos,fileName + ".txt");
    }
    private void loadCSV(){
        String text = "";
        try{
            text = UtilFiles.readText(fileName + ".csv");
            Scanner sc = new Scanner(text).useDelimiter(";|[\n]+|[\r\n]");

            while(sc.hasNext()){
                String id = sc.next();
                String marca = sc.next();
                int modelo = sc.nextInt();
                int numAsientos = sc.nextInt();
                double valorBase = sc.nextDouble();
                boolean disponibilidad = sc.nextBoolean();
                vehiculos.add(new Vehiculo(marca, id, modelo, numAsientos, valorBase, disponibilidad));
                sc.nextLine();
            }
            sc.close();

        } catch(IOException e){
            e.printStackTrace();
        }
            

    }
    public Vehiculo get(String id){
        for(Vehiculo vehiculo : vehiculos){
            if(vehiculo.getId().equals(id)){
                return vehiculo;
            }
        }
        return null;
    }

}
