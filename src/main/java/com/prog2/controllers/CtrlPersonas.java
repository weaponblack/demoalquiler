package com.prog2.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import com.prog2.helpers.UtilFiles;
import com.prog2.model.Cliente;
import com.prog2.model.Empleado;
import com.prog2.model.Oficina;
import com.prog2.model.Persona;

public class CtrlPersonas {

    private CtrlOficinas oficinas;
    private ArrayList<Persona> personas;
    private String fileName;

    public CtrlPersonas(Class<? extends Persona> type) {
        personas = new ArrayList<>();
        fileName = UtilFiles.FILE_PATH + (type.getSimpleName().equals("Empleado") ? "empleados" : "clientes");

        if (UtilFiles.fileExists(fileName + ".csv")) {
            loadCSV();
        } else {
            System.out.println("Aun no hay registros de " + fileName);
        }
    }

    public CtrlPersonas(Class<? extends Persona> type, CtrlOficinas oficinas) {
        this.oficinas = oficinas;
        personas = new ArrayList<>();

        fileName = UtilFiles.FILE_PATH + (type.getSimpleName().equals("Empleado") ? "empleados" : "clientes");

        if (UtilFiles.fileExists(fileName + ".csv")) {
            loadCSV();
        } else {
            System.out.println("Aun no hay registros de " + fileName);
        }
    }

    public boolean add(Persona persona) throws Exception {
        if (contains(persona)) {
            throw new Exception("Error persona repetida: " + persona.getIdentificacion() + " - " + persona.getNombre());
        }
        boolean ok = personas.add(persona);
        UtilFiles.writeCSV(personas, fileName + ".csv");
        return ok;
    }

    public Persona get(int index) {
        return personas.get(index);
    }

    public ArrayList<Persona> getList() {
        return personas;
    }

    public boolean contains(Persona persona) {
        for (Persona p : personas) {
            if (p.equals(persona)) {
                return true;
            }
        }
        return false;

    }

    private void loadCSV() {
        String text = "";
        try {
            text = UtilFiles.readText(fileName + ".csv");
            Scanner sc = new Scanner(text).useDelimiter(";|[\n]+|[\r\n]");

            while (sc.hasNext()) {
                String nombre = sc.next();
                String identificacion = sc.next();
                String direccion = sc.next();
                String telefono = sc.next();
                String correo = sc.next();

                if (fileName.equals("empleados")) {
                    Oficina oficina = oficinas.get(sc.next());
                    personas.add(new Empleado(nombre, identificacion, direccion, telefono, correo, oficina));
                } else {
                    String tarjetaCredito = sc.next();
                    personas.add(new Cliente(nombre, identificacion, direccion, telefono, correo, tarjetaCredito));
                }
                sc.nextLine();
            }
            sc.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public Persona get(String nombre){
        for(Persona persona : personas){
            if(persona.getNombre().equals(nombre)){
                return persona;
            }
        }
        return null;
    }

}
