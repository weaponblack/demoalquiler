package com.prog2.model;

import com.prog2.helpers.IFormatCSV;

public class Oficina implements IFormatCSV {

    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;

    public Oficina(){}

    public Oficina(String nombre, String direccion, String telefono, String correo){
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Oficina(Oficina o){
        this(o.nombre, o.direccion,o.telefono,o.correo);
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this){
            return false;
        }

        if(!(o instanceof Oficina)){
            return false;
        }

        Oficina oficina = (Oficina) o;
        
        return oficina.nombre.equals(this.nombre);
    }

    @Override
    public String toString() {
        return String.format(
            "%-15s %-35s %-14s %s", 
            nombre, direccion, telefono, correo
         );
    }

    @Override 
    public String toCSV() {                               //Prueba utilizacion del formato toCSV
        return String.format(
           "%s;%s;%s;%s", nombre, direccion, telefono, correo
        );
    }
    
}
