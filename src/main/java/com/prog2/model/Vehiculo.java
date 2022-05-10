package com.prog2.model;

import com.prog2.helpers.IFormatCSV;

public class Vehiculo implements IFormatCSV {

    private String marca;
    private String id;
    private int modelo;
    private int numAsientos;
    private double valorBase;
    private boolean disponibilidad;

    public Vehiculo() {
    }

    public Vehiculo(String marca,String id, int modelo, int numAsientos, double valorBase, boolean disponibilidad) {
        this.marca = marca;
        this.id = id;
        this.modelo = modelo;
        this.numAsientos = numAsientos;
        this.valorBase = valorBase;
        this.disponibilidad = disponibilidad;
    }

    public Vehiculo(Vehiculo v) {
        this(v.marca,v.id, v.modelo, v.numAsientos, v.valorBase, v.disponibilidad);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getNumAsientos() {
        return numAsientos;
    }

    public void setNumAsientos(int numAsientos) {
        this.numAsientos = numAsientos;
    }

    public double getTipo() {
        return valorBase;
    }

    public void setTipo(double valorBase) {
        this.valorBase = valorBase;
    }

    public double getValorHora() { //retorna finalmente el valor redondeado de la formula para hallar el valor por hora de cada vehiculo
        return Math.round((valorBase * (1 + ((modelo - 2000) / 1000.)) ) * 100) / 100d;
    }

    public boolean getdisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    @Override
    public boolean equals(Object v) {
        if (v == this){
            return false;
        }

        if(!(v instanceof Vehiculo)){
            return false;
        }

        Vehiculo vehiculo = (Vehiculo) v;
        
        return vehiculo.id.equals(this.id);
    }

    @Override
    public String toString() {
        String strdisponible = disponibilidad ? "SI" : "NO";
        return String.format(
            "%-15s %-10s %-10s %-10s %-10s %s", 
            id+"-"+marca, modelo, numAsientos,valorBase, getValorHora(),strdisponible);
    }

    @Override
    public String toCSV() {
        return String.format(
           "%s;%s;%d;%d;%f;%b", id,marca, modelo, numAsientos,valorBase, getValorHora(),disponibilidad);
    }

}
