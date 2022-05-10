package com.prog2.model;

public class Cliente extends Persona {

    private String tarjetaCredito;
    
    public Cliente() {}

    public Cliente(String nombre, String identificacion,String direccion,String telefono,String correo,String tarjetaCredito) {
        super(nombre,identificacion,direccion,telefono,correo);
        this.tarjetaCredito = tarjetaCredito;
    }

    public Cliente(Cliente c){
        this(c.nombre,c.identificacion,c.direccion,c.telefono,c.correo,c.tarjetaCredito);
    }

    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    @Override
    public String toString() {
        String strPersona = super.toString();
        return String.format("%-123s %-20s", strPersona,tarjetaCredito);
    }

    @Override
    public String toCSV() {
        String strPersona = super.toCSV();
         return String.format("%s;%s", strPersona,tarjetaCredito);
    }


}
