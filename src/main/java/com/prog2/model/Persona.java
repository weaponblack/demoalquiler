package com.prog2.model;

import com.prog2.helpers.IFormatCSV;

public abstract class Persona implements IFormatCSV{

    
    protected String nombre;
    protected String identificacion;
    protected String direccion;
    protected String telefono;
    protected String correo;

    public Persona(){}

    public Persona(String nombre, String identificacion,String direccion,String telefono,String correo){
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Persona(Persona p){
        this(p.identificacion,p.nombre,p.direccion,p.telefono,p.correo);
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public String getIdentificacion() {
        return identificacion;
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
    public boolean equals(Object p) {
        if (p == this){
            return false;
        }

        if(!(p instanceof Oficina)){
            return false;
        }

        Persona persona = (Persona) p;
        
        return persona.identificacion.equals(this.identificacion);
    }

    @Override
    public String toString() {
        return String.format(
            "%-15s %-10s %-25s %-14s %-10s", 
            nombre,identificacion, direccion, telefono, correo);
    }

    @Override
    public String toCSV() {
        return String.format(
           "%s;%s;%s;%s;%s", nombre,identificacion, direccion, telefono, correo
        );
    }

}
