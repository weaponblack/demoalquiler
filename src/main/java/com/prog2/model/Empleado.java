package com.prog2.model;

public class Empleado extends Persona {

    private Oficina oficina;

    public Empleado(){}

    public Empleado(String nombre, String identificacion,String direccion,String telefono,String correo,Oficina oficina){
        super(nombre,identificacion,direccion,telefono,correo);
        this.oficina = oficina;
    }

    public Empleado(Empleado e){
        this(e.nombre,e.identificacion,e.telefono,e.direccion,e.correo,e.oficina);
    }

    public Oficina getOficina() {
        return oficina;
    }
    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    @Override
    public String toString() {
        String strPersona = super.toString();
        return String.format("%-123s %-20s", strPersona, oficina.getNombre());
    }
    @Override
    public String toCSV() {
        String strPersona = super.toCSV();
         return String.format("%s;%s", strPersona, oficina.getNombre());
    }
    
}
