package com.prog2.model;
import java.util.Calendar;

import com.prog2.helpers.IFormatCSV;
import com.prog2.helpers.UtilDate;

public class Alquiler implements IFormatCSV {

    private Calendar fechaHoraInicio;
    private Calendar fechaHoraFin;
    private Persona cliente;
    private Vehiculo vehiculo;
    private Persona empleado;

    public Alquiler (){}

    public Alquiler(String strFechaHoraInicio, String strFechaHoraFin,Cliente cliente,Vehiculo vehiculo,Persona empleado){
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.empleado = empleado;
        this.fechaHoraInicio = UtilDate.strToCalendar(strFechaHoraInicio, "yyyy-MM-dd hh:mm a");
        this.fechaHoraFin =  UtilDate.strToCalendar(strFechaHoraFin, "yyyy-MM-dd hh:mm a");
    }

    public Alquiler(Calendar fechaHoraInicio, Calendar fechaHoraFin,Persona cliente,Vehiculo vehiculo,Persona empleado){
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.empleado = empleado;
        this.fechaHoraFin = fechaHoraFin;
        this.fechaHoraInicio = fechaHoraInicio;
    }
    

    public Alquiler(Alquiler a){
        this(a.fechaHoraInicio, a.fechaHoraFin, a.cliente, a.vehiculo, a.empleado);
    }

    public Calendar getFechaHoraInicio() {
        return fechaHoraInicio;
    }
    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = UtilDate.strToCalendar(fechaHoraInicio, "yyyy-MM-dd hh:mm a");
    }

    public Calendar getFechaHoraFin() {
        return fechaHoraFin;
    }
    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = UtilDate.strToCalendar(fechaHoraFin, "yyyy-MM-dd hh:mm a");
    }

    public long getHoras(){
        return (long) UtilDate.getHours(fechaHoraFin,fechaHoraInicio);
    }

    public double getValor(){
        return getHoras()*vehiculo.getValorHora();
    }

    public Persona getEmpleado() {
        return empleado;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public void setEmpleado(Persona empleado) {
        this.empleado = empleado;
    }
    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public boolean equals(Object a) {
        if (a == this){
            return false;
        }

        if(!(a instanceof Alquiler)){
            return false;
        }

        Alquiler alquiler = (Alquiler) a;
        
        return alquiler.cliente.equals(this.cliente) && 
        alquiler.empleado.equals(this.empleado) && 
        alquiler.fechaHoraFin.equals(this.fechaHoraInicio) &&
        alquiler.fechaHoraInicio.equals(this.fechaHoraInicio) &&
        alquiler.vehiculo.equals(this.vehiculo);
    }

    @Override
    public String toString() {
        return String.format(
            "%-20s %-15s %-15s %-15s %-5s %-5s", 
            cliente.getNombre(),
            vehiculo.getId()+"-"+vehiculo.getMarca(),
            UtilDate.calendarToStr(fechaHoraInicio),   
            UtilDate.calendarToStr(fechaHoraFin),
            getHoras(),
            getValor()
             );
    }

    @Override
    public String toCSV() {
        return String.format(
           "%s;%s;%s;%s;%f;%d", cliente.getNombre(),
           vehiculo.getId()+"-"+vehiculo.getMarca(),
           UtilDate.calendarToStr(fechaHoraInicio),   
           UtilDate.calendarToStr(fechaHoraFin),
           getHoras(),
           getValor()
        );
    }
}