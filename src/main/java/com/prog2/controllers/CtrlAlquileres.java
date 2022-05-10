package com.prog2.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.prog2.helpers.*;
import com.prog2.model.Alquiler;
import com.prog2.model.Persona;
import com.prog2.model.Vehiculo;

public class CtrlAlquileres {

    public static ArrayList<Alquiler> alquileres;
    private static CtrlPersonas personas;
    private static CtrlVehiculos vehiculos;
    String fileName;

    public CtrlAlquileres() {
        alquileres = new ArrayList<>();
        fileName = UtilFiles.FILE_PATH + "alquileres";

        if (UtilFiles.fileExists(fileName + ".csv")){
            loadCSV();
        }else{
            System.out.println("Aun no se han crado alquileres");
        }
    }

    public boolean add(Alquiler alquiler)throws Exception {
        if (isOverlap(alquiler.getVehiculo(), alquiler.getFechaHoraInicio(), alquiler.getFechaHoraFin())) {
            throw new Exception ("Hay un cruce en el horario, no se puedo alquilar");
        }
        boolean ok = alquileres.add(alquiler);
        UtilFiles.writeCSV(alquileres, fileName + ".csv");
        return ok;
    }

    public ArrayList<Alquiler> getList() {
        return alquileres;

    }

    public boolean contains(Alquiler alquiler) {
        for (Alquiler a : alquileres) {
            if (a.equals(alquiler)) {
                return true;
            }

        }
        return true;
    }

    public boolean isOverlap(Vehiculo vehiculo, String strFechaHoraInicio, String strFechaHoraFin) {
        Calendar fechaHoraInicio = UtilDate.strToCalendar(strFechaHoraInicio, "yyyy-MM-dd hh:mm a");
        Calendar fechaHoraFin = UtilDate.strToCalendar(strFechaHoraFin, "yyyy-MM-dd hh:mm a");
        isOverlap(vehiculo, fechaHoraInicio, fechaHoraFin);
        return false;

    }

    public boolean isOverlap(Vehiculo vehiculo, Calendar fechaHoraInicio, Calendar fechaHoraFin) {
        if (!vehiculo.getdisponibilidad()) {
            return false;
        }
        for (Alquiler a : alquileres) {
            // si hay un vehiculo en la posicion recorrida y este corresponde al vehiculo que se va a alquilar
            if (a.getVehiculo().getId().equals(vehiculo.getId())) {
                // si dicho vehiculo esta alquilado en ese horario
                if (UtilDate.isOverlap(a.getFechaHoraInicio(), a.getFechaHoraFin(), fechaHoraInicio, fechaHoraFin)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void toReport()throws Exception{
        UtilFiles.writeText(alquileres,fileName + ".txt");
    }
    private void loadCSV(){
        String text = "";
        try{
            text = UtilFiles.readText(fileName + ".csv");
            Scanner sc = new Scanner(text).useDelimiter(";|[\n]+|[\r\n]");

            while(sc.hasNext()){
                Calendar fechaHoraInicio = UtilDate.strToCalendar(sc.next(), "yyyy-MM-dd hh:mm a");
                Calendar fechaHoraFin = UtilDate.strToCalendar(sc.next(), "yyyy-MM-dd hh:mm a");
                Persona cliente = personas.get(sc.next());
                Vehiculo vehiculo = vehiculos.get(sc.next());
                Persona empleado = personas.get(sc.next());
                alquileres.add(new Alquiler(fechaHoraInicio, fechaHoraFin, cliente, vehiculo, empleado));
            }
            sc.close();

        } catch(Exception e){
            e.printStackTrace();
        }
            

    }

}
