package com.prog2.controllers;
import java.util.ArrayList;
import java.util.Scanner;
import com.prog2.helpers.UtilFiles;
import com.prog2.model.Oficina;

public class CtrlOficinas {

    private static ArrayList<Oficina> oficinas;
    String fileName;

    public CtrlOficinas() {
        oficinas = new ArrayList<>();
        fileName = UtilFiles.FILE_PATH + "oficinas";

        if (UtilFiles.fileExists(fileName + ".csv")){
            loadCSV();
        }else{
            System.out.println("Aun no se han crado oficinas");
        }
    }

    public  boolean add(Oficina oficina) throws Exception{
        if(contains(oficina)){
            throw new Exception ("Error oficina repetida: "+ oficina.getNombre() + " - " + oficina.getDireccion());
        }    
        boolean ok = oficinas.add(oficina);
        UtilFiles.writeCSV(oficinas, fileName + ".csv");
        return ok;
    }

    public Oficina get(int index){
        return oficinas.get(index);
    }
    public Oficina get(String nombre){
        for(Oficina oficina : oficinas){
            if(oficina.getNombre().equals(nombre)){
                return oficina;
            }
        }
        return null;
    }

    public ArrayList<Oficina> getList() {
        return oficinas;
    }

    public boolean contains(Oficina oficina) {
        for (Oficina o : oficinas) {
            if (o.equals(oficina)) {
                return true;
            }
        }
        return false;

    }
    public void toReport()throws Exception{
        UtilFiles.writeText(oficinas,fileName + ".txt");
    }
    private void loadCSV(){
        String text = "";
        try{
            text = UtilFiles.readText(fileName + ".csv");
            Scanner sc = new Scanner(text).useDelimiter(";|[\n]+|[\r\n]");

            while(sc.hasNext()){
                String nombre = sc.next();
                String direccion = sc.next();
                String telefono = sc.next();
                String correo = sc.next();
                oficinas.add(new Oficina(nombre, direccion, telefono, correo));
                sc.nextLine();
            }
            sc.close();

        } catch(Exception e){
            e.printStackTrace();
        }
            

    }
}
