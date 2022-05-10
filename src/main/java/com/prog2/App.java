package com.prog2;
import java.util.ArrayList;
import com.prog2.controllers.*;
import com.prog2.helpers.*;
import com.prog2.model.*;

public class App {

    private static CtrlVehiculos vehiculos;
    private static CtrlPersonas clientes;
    private static CtrlPersonas empleados;
    private static CtrlOficinas oficinas;
    private static CtrlAlquileres alquileres;

    public static void main(String[] args) throws Exception {
    
    // System.out.println("\nOficina 1er Alquiler: " +
    // alquileres.get(0).getEmpleado().getOficina().getNombre()); // Ejercicio del
    // documento

    crearInstancias();

    do{
        int opcion = leerOpcion();
        switch (opcion) {
            case 1:
                crearOficinas();
                break;
            case 2:
                listarOficinas();
                break;
            case 3:
                crearEmpleados();
                break;
            case 4:
                listarEmpleados();
                break;
            case 5:
                crearClientes();
                break;
            case 6:
                listarClientes();
                break;
            case 7:
                creaVehiculos();
                break;
            case 8:
                listarVehiculos();
                break;
            case 9:
                crearAlquileres();
                break;
            case 10:
                listarAlquileres();
                break;
            case 11:
                listaPersonas();
                break;
            case 12:
                dispoVehiculos();
                break;
            case 13:
                recaudoAlquileres(); 
                break; 
            case 14:
                recuadoOficinas();  
                break;
            case 99:
                System.exit(0);
                break;
            default:
                System.out.println("Opción inválida");
        }
    }while(true);

    }   

    private static void listarInstancias() {
        listarOficinas();
        listarEmpleados();
        listarClientes();
        listarVehiculos();
        listarAlquileres();
        listaPersonas();
    }

    private static void listaPersonas() {
        ArrayList<Persona> personas = new ArrayList<>();
        personas.addAll(empleados.getList());
        personas.addAll(clientes.getList());
        System.out.println("\nPERSONAS");
        System.out.println("-".repeat(100));

        for (Persona p : personas) {
            System.out.println(p);
        }
        System.out.println("-".repeat(100));
        System.out.printf("Se cuenta con %d personas. \n", personas.size());
        System.out.println("-".repeat(100));
    }

    private static void listarOficinas() {
        System.out.println("\nOFICINAS" + "\n" + "-".repeat(90));
        //for (Oficina oficina : oficinas.getList()) { //Prueba de impresion con el metodo CSV
            //System.out.println(oficina.toCSV());
        System.out.printf("%-15s %-35s %-14s %s", "NOMBRE", "DIRECCION", "TELEFONO", "CORREO");
        System.out.println("\n" + "-".repeat(90));
        for (Oficina oficinas : oficinas.getList()) {
            System.out.println(oficinas);
        }
        System.out.println("-".repeat(90));
        System.out.println("Total oficias: " + oficinas.getList().size());
        System.out.println("-".repeat(90));

    }
 
    private static void listarEmpleados() {
        System.out.println("\nEMPLEADOS" + "\n" + "-".repeat(100));
        System.out.printf("%-15s %-10s %-25s %-14s %-10s %-20s", "NOMBRE","IDENTIFICACION", "DIRECCION", "TELEFONO", "CORREO", "OFICINA");
        System.out.println("\n" + "-".repeat(100));
        for (Persona e : empleados.getList()) {
                System.out.println(e);
        }
        System.out.println("-".repeat(100));
     }

    private static void listarClientes() {
        System.out.println("\nCLIENTES" + "\n" + "-".repeat(100));
        System.out.printf("%-15s %-10s %-25s %-14s %-10s %-20s", "NOMBRE","IDENTIFICACION", "DIRECCION", "TELEFONO", "CORREO", "TARJETA");
        System.out.println("\n" + "-".repeat(100));
        for (Persona c : clientes.getList()) {
                System.out.println(c);
        }
        System.out.println("-".repeat(100));
    }

    private static void listarVehiculos() {
        System.out.println("\nVEHICULOS" + "\n" + "-".repeat(65));
        System.out.printf("%-15s %-10s %-10s %-10s %-10s %s", "ID-MARCA", "MODELO", "ASIENTOS", "Vr BASE", "Vr HORA",
                "DISP.");
        System.out.println("\n" + "-".repeat(65));
        for (Vehiculo v : vehiculos.getList()) {
            System.out.println(v);
        //for (Vehiculo vehiculo : vehiculos.getList()) { //Prueba de impresion con el metodo CSV
            //System.out.println(vehiculo.toCSV());

        }
        System.out.println("-".repeat(65));
    }

    private static void listarAlquileres() {
        System.out.println("\nALQUILERES" + "\n" + "-".repeat(100));
        System.out.printf("%-20s %-15s %-15s %-20s %-20s %-5s", "CLIENTE", "VEHICULO", "INICIO", "FIN", "HORAS",
                "TOTAL");
        System.out.println("\n" + "-".repeat(100));
        for (Alquiler a : alquileres.getList()) {
            System.out.println(a);

        }
        System.out.println("-".repeat(100));

    }

    private static void crearInstancias() throws Exception{
        try {
            oficinas = new CtrlOficinas();

            empleados = new CtrlPersonas(Empleado.class, oficinas);
            clientes = new CtrlPersonas(Cliente.class);

            vehiculos = new CtrlVehiculos();
            alquileres = new CtrlAlquileres();

        }catch (Exception e){
            e.printStackTrace();
        }
        
    }

    private static void crearOficinas() {
        String nombre;

        do {
            System.out.println("Ingresar nueva oficina");
            nombre = Keyboard.readString("Nombre: (Intro para terminar): ").trim();

            if (!nombre.equals("Intro")) {
                Oficina oficina = new Oficina();
                oficina.setNombre(nombre);
                oficina.setDireccion(Keyboard.readString("Dirección: "));
                oficina.setTelefono(Keyboard.readString("Teléfono: "));
                oficina.setCorreo(Keyboard.readString("Correo: "));

                try {
                    oficinas.add(oficina);
                    System.out.println("\n--Oficina creada\n");
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        } while (!nombre.equals("Intro"));
    }

    private static void crearEmpleados() {
        String nombre;

        do {
            System.out.println("Ingresar nuevo empleado");
            nombre = Keyboard.readString("Nombre: (Intro para terminar): ").trim();

            if (!nombre.equals("Intro")) {

                Empleado empleado = new Empleado();

                empleado.setNombre(nombre);
                empleado.setIdentificacion(Keyboard.readString("Ingrese la identificacion: "));
                empleado.setDireccion(Keyboard.readString("Ingrese la direccion: "));
                empleado.setTelefono(Keyboard.readString("Ingrese el telefono: "));
                empleado.setCorreo(Keyboard.readString("Ingrese el correo: "));
                empleado.setOficina(getOficina());
                
                try {
                    empleados.add(empleado);
                    System.out.println("\n--Empleado creado\n");
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        } while (!nombre.equals("Intro"));
    }

    private static void crearClientes() {
        String nombre;

        do {
            System.out.println("Ingresar nuevo cliente");
            nombre = Keyboard.readString("Nombre: (Intro para terminar): ").trim();

            if (!nombre.equals("Intro")) {

                Cliente cliente = new Cliente();

                cliente.setNombre(nombre);
                cliente.setIdentificacion(Keyboard.readString("Ingrese la identificacion: "));
                cliente.setDireccion(Keyboard.readString("Ingrese la direccion: "));
                cliente.setTelefono(Keyboard.readString("Ingrese el telefono: "));
                cliente.setCorreo(Keyboard.readString("Ingrese el correo: "));
                cliente.setTarjetaCredito(Keyboard.readString("Ingrese su tarjeta de credito: "));
                
                
                try {
                    clientes.add(cliente);
                    System.out.println("\n--Cliente creado\n");
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        } while (!nombre.equals("Intro"));

    }

    private static void creaVehiculos(){

        String marca;

        do{
            System.out.println("Ingresar nuevo vehiculo");
            marca = Keyboard.readString("Marca: (Intro para terminar): ").trim();

            if (!marca.equals("Intro")){

                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setMarca(marca);
                vehiculo.setId(Keyboard.readString("Ingrese el ID: "));
                vehiculo.setModelo(Keyboard.readInt("Ingrese el año-modelo: "));
                vehiculo.setNumAsientos(Keyboard.readInt("Ingrese le numero de asientos: "));
                vehiculo.setTipo(Keyboard.readDouble("Ingrese el valor base por hora: "));
                vehiculo.setDisponibilidad(Keyboard.readBoolean("ingrese la disponibilidad: "));

                try {
                    vehiculos.add(vehiculo);
                    System.out.println("\n--Vehiculo creada\n");
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }while (!marca.equals("Intro"));
    
    }

    private static void crearAlquileres() {
        String aux;

        do{
            System.out.println("Desea ingresar nuevo alquiler?");
            aux = Keyboard.readString("ingrese si para continuar (Intro para terminar): ").trim();

            if (!aux.equals("Intro")){

                Alquiler alquiler = new Alquiler();

                alquiler.setFechaHoraInicio(Keyboard.readString("Ingrese la fecha inicial con el formato yyyy-MM-dd hh:mm a :"));
                alquiler.setFechaHoraFin(Keyboard.readString("Ingrese la fechafinal con el formato yyyy-MM-dd hh:mm a: "));

                alquiler.setCliente(getCliente());
                alquiler.setEmpleado(getEmpleado());
                alquiler.setVehiculo(getVehiculo());

                try {
                    alquileres.add(alquiler);
                    System.out.println("\n--alquiler creado\n");
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }while (!aux.equals("Intro"));
    }

    private static Oficina getOficina(){ //Permite mostrar oficinas y escoger una 
        System.out.println("Oficinas elegibles: ");
        for (int i = 0; i < oficinas.getList().size();i++) {
            Oficina oficina = oficinas.getList().get(i);
            System.out.println((i+1) +" -" +oficina.getNombre()+" - "+oficina.getDireccion());   
        }
        int num = Keyboard.readInt("\nElija el numero de la oficina: "); 
        return oficinas.get(num-1);

    }

    private static Persona getCliente(){ //Permite mostrar clientes y escoger uno 
        System.out.println("Clientes elegibles: ");
        for (int i = 0; i < clientes.getList().size();i++) {
            Persona cliente = clientes.getList().get(i);
            System.out.println((i+1) +" -"+ cliente.getNombre() +" - " + cliente.getIdentificacion());
        }
        int num = Keyboard.readInt("\nElija el numero de la oficina: "); 
        return clientes.get(num-1);
    }

    private static Persona getEmpleado() { //Permite mostrar empleados y escoger uno
        System.err.println("Empleados elegibles: ");
        for (int i = 0; i < empleados.getList().size(); i++) {
            Persona empleado = empleados.getList().get(i);
            System.out.println((i + 1) + " -" + empleado.getNombre() + " - " + empleado.getIdentificacion());
        }
        int num = Keyboard.readInt("\nElija el numero del empleado: ");
        return empleados.get(num-1);
    }

    private static Vehiculo getVehiculo(){ //Permite mostrar vehiculos DISPONIBLES  y escoger uno
        System.out.println("Vehiculos elegibles: ");
        for (int i = 0; i < vehiculos.getList().size(); i++) {
            if(vehiculos.getList().get(i).getdisponibilidad() == true){
                Vehiculo vehiculo = vehiculos.getList().get(i);
                System.out.println((i+1) + " - " + vehiculo.getId()+", " + vehiculo.getMarca());
            }
        }
         int num = Keyboard.readInt("\nElija el numero del vehiculo: ");
         return vehiculos.getList().get(num-1);
    }

    private static void dispoVehiculos(){ //Funcion para conmutar la disponibilidad del vehiculo seleccionado
        listarVehiculos();
        int num = Keyboard.readInt("ingrese la posicion del vehiculo al que desea cambiar la disponibilidad: "); 
        //Se tomara la posicion del vehiculo para cambiar la disponibilidad del mismo
        if (vehiculos.getList().get(num-1).getdisponibilidad()){ //Segun el valor de la disponibilidad se cambiara al contrario 
            vehiculos.getList().get(num-1).setDisponibilidad(false);
        }
        else{
            vehiculos.getList().get(num-1).setDisponibilidad(true);
        }
    }

    private static void recaudoAlquileres(){

        double total = 0;
        for (Alquiler a : alquileres.getList()) {
            total = total + a.getValor();
        }
        System.out.println("El recaudo total de alquileres es de : " + total);

    }

    private static void recuadoOficinas(){
        double total = 0;
        listarOficinas();
        int num = Keyboard.readInt("Ingrese la posicion de la oficina de la cual quiere saber el total de su recaudo: ");
        Oficina oficinaAux = oficinas.getList().get(num-1);
        for (Alquiler a : alquileres.getList()) {
            Empleado empleado = (Empleado) a.getEmpleado();
            if(empleado.getOficina().equals(oficinaAux)){
                total = total + a.getValor();
            }
        }System.out.println("El recuado total de la oficina "+ oficinaAux.getNombre() + " es de: " + total);

    }

    private static int leerOpcion() {
        String opciones = "\nMenu de opciones:\n" 
                + " 1 - Crear Oficina                      2 - Listar Oficinas\n" 
                + " 3 - Crear Empleado                     4 - Listar Empleados\n"
                + " 5 - Crear Cliente                      6 - Listar Clientes\n" 
                + " 7 - Crear Vehiculo                     8 - Listar Vehiculos\n" 
                + " 9 - Crear Alquiler                    10 - Listar Alquileres\n" 
                + "11 - Listar personas(polimorfismo)     12 - Conmutar disponibilidad\n"
                + "13 - Total recaudo alquileres          14 - Total recaudo por oficinas"
                + "\nElija una opción (99 para salir) > ";
        System.out.println();
        int opcion =  Keyboard.readInt(opciones);
        System.out.println();
        return opcion;
    }
}
