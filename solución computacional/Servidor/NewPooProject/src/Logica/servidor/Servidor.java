package Logica.servidor;

import Interfaz.Login;
import Interfaz.ServidorVentana;
import static Interfaz.ServidorVentana.Lista4;
import static Interfaz.ServidorVentana.Lista5;
import static Interfaz.ServidorVentana.Lista6;
import Logica.ManejadorDeListas;
import Logica.Persona;
import Logica.Tickets;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 * Servidor de chat. Acepta conexiones de clientes, crea un hilo para
 * atenderlos, y espera la siguiente conexion.
 *
 * @author Chuidiang
 *
 */
public class Servidor implements Runnable {

    ArrayList listaEmpleados = new ArrayList();
    ObjectInputStream objetoentrante;
    DataInputStream dataInput;
    DataOutputStream saliente;
    ObjectOutputStream objetosaliente;
    boolean sucess = false;
    ArrayList listaConexiones = new ArrayList();
    HiloDeCliente_1 cli;
    ArrayList historial;
    ServerSocket socketServidor;

    private void registrarPersonas() {
        historial = new ArrayList();
        Persona admn = new Persona("Admin", "admin@tec.ac.cr", "adminadmin", null, true);
        Persona rojo = new Persona("Fernando", "fernando@tec.ac.cr", "201048339", "Rojo", true);
        Persona verde = new Persona("Luis", "luis@tec.ac.cr", "2014063730", "Amarillo", true);
        Persona amarillo = new Persona("Junior", "junior@tec.ac.cr", "2013056233", "Verde", true);
        listaEmpleados.add(admn);
        listaEmpleados.add((Persona) verde);
        listaEmpleados.add((Persona) rojo);
        listaEmpleados.add((Persona) amarillo);

    }

    public ArrayList getHistorial() {
        return historial;
    }
    /**
     * Lista en la que se guaradara toda la conversacion
     */
    private DefaultListModel charla = new DefaultListModel();

    /**
     * Instancia esta clase.
     *
     * @param args
     */
    private ServidorVentana ventana;
    int puerto;
    /**
     * Se mete en un bucle infinito para ateder clientes, lanzando un hilo para
     * cada uno de ellos.
     */
    public Servidor(ServidorVentana ventana,Login parent) {
        registrarPersonas();
        this.ventana = ventana;
        ventana.setServidor(this);
        ventana.setListaEmpleados(listaEmpleados);
        ventana.setConectados();
        puerto=parent.getPuerto();
    }

    public ArrayList getListaConexiones() {
        return listaConexiones;
    }

    public ArrayList getListaEmpleados() {
        return listaEmpleados;
    }

    public void sendRojo() throws IOException {

        objetosaliente.writeObject(ManejadorDeListas.ListaDeRojos);
    }

    public void sendAmarillo() throws IOException {

        objetosaliente.writeObject(ManejadorDeListas.ListaDeAmarillos);
    }

    public void sendVerde() throws IOException {
       // System.out.println("ENTRO EN VERDEEEE");
        ManejadorDeListas.ListaDeVerdes.size();
        
        objetosaliente.writeObject(ManejadorDeListas.ListaDeVerdes);
       // System.out.println("SALIOS EN VERDEEEE");
    }

    public void loggin(String login) throws IOException {
        String nombre = "";
        String tipo = "";
        Persona temp = null;
        for (int i = 1; i < listaEmpleados.size(); i++) {

            temp = (Persona) listaEmpleados.get(i);
            if (login.equals(temp.correo + " " + temp.contraseña)) {
                if (!temp.getEstado()) {
                    sucess = true;

                    listaEmpleados.set(i, temp);

                }
                break;

            }

        }
        if (sucess) {
            saliente.writeInt(0);
            nombre = temp.getNombre();
            tipo = temp.getCategoria();
            temp.conectar();

            ventana.setListaEmpleados(listaEmpleados);
            ventana.setConectados();
            saliente.writeUTF(nombre);
            saliente.writeUTF(tipo);
            historial.add(nombre + " se ha conectado");
            ManejadorDeListas.cargaListaActividadReciente((String) historial.get(historial.size() - 1));
        } else {
            saliente.writeInt(-1);
        }
        sucess = false;

    }

    public void desconectarPersona(String nombre) {
        int i = 0;
        for (i = 0; i < this.listaEmpleados.size(); i++) {
            if (((Persona) listaEmpleados.get(i)).getNombre().equals(nombre)) {
                ((Persona) listaEmpleados.get(i)).desconectar();
                this.ventana.setLista(listaEmpleados);
                break;
            }
        }
        historial.add(nombre + " se ha desconectado");
        ManejadorDeListas.cargaListaActividadReciente((String) historial.get(historial.size() - 1));
        this.ventana.setConectados();

    }
    public String getTiempoActual(){
        Date fechaHoraActual = new Date();
        String strFechaHora = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa").format(fechaHoraActual);
        
        return strFechaHora;
            
    }

    public void modificarEstadoTicketAmarillo(int indice, String tipo) throws IOException {
        Tickets temp = (Tickets) ManejadorDeListas.ListaDeAmarillos.get(indice);
     //   System.out.println(this.getOracion(tipo));
        if (this.getOracion(tipo).equals("")) {

            temp.setEstado("En atencion");
            historial.add(this.getName(tipo) + " esta atendiendo el tickete numero: " + this.getTicketeActual(tipo));
            ManejadorDeListas.cargaListaActividadReciente((String) historial.get(historial.size() - 1));
        } else {
            temp.setEstado("Atendido");
        //    System.out.println("ATEMDIDO");
            historial.add(this.getName(tipo) + " ha atendido el tickete numero: " + this.getTicketeActual(tipo));
            //System.out.println(this.getOracion(tipo));
            ManejadorDeListas.cargaListaActividadReciente((String) historial.get(historial.size() - 1));
            temp.setTiempoSegundos(this.getTiempo(tipo));
            temp.setFechayHoraAtencion(this.getTiempoActual());
           // temp.setFechayHoraAtencion(tipo);
            temp.setComentario(this.getOracion(tipo));
            temp.setID_EMPLEADO(this.getName(tipo));
            ManejadorDeListas.MegaLista.add(temp);
            
        }
        ManejadorDeListas.ListaDeAmarillos.set(indice, temp);

    }

    public void modificarEstadoTicketRojo(int indice, String tipo) throws IOException {
        Tickets temp = (Tickets) ManejadorDeListas.ListaDeRojos.get(indice);
      //  System.out.println(this.getOracion(tipo));
        if (this.getOracion(tipo).equals("")) {

            temp.setEstado("En atencion");
            historial.add(this.getName(tipo) + " esta atendiendo el tickete numero: " + this.getTicketeActual(tipo));
            ManejadorDeListas.cargaListaActividadReciente((String) historial.get(historial.size() - 1));
        } else {
            temp.setEstado("Atendido");
        //    System.out.println("ATEMDIDO");
            historial.add(this.getName(tipo) + " ha atendido el tickete numero: " + this.getTicketeActual(tipo));
            //System.out.println(this.getOracion(tipo));
            ManejadorDeListas.cargaListaActividadReciente((String) historial.get(historial.size() - 1));
            temp.setTiempoSegundos(this.getTiempo(tipo));
            temp.setComentario(this.getOracion(tipo));
            temp.setID_EMPLEADO(this.getName(tipo));
            temp.setFechayHoraAtencion(this.getTiempoActual());
            ManejadorDeListas.MegaLista.add(temp);
        }
        ManejadorDeListas.ListaDeRojos.set(indice, temp);

    }

    public void modificarEstadoTicketVerde(int indice, String tipo) throws IOException {
        Tickets temp = (Tickets) ManejadorDeListas.ListaDeVerdes.get(indice);
      //  System.out.println("++++++++++++++++++++");
      //  System.out.println(this.getOracion(tipo));
      //  System.out.println("++++++++++++++++++++");
        if (this.getOracion(tipo).equals("")) {

            temp.setEstado("En atencion");
            historial.add(this.getName(tipo) + " esta atendiendo el tickete numero: " + this.getTicketeActual(tipo));
            ManejadorDeListas.cargaListaActividadReciente((String) historial.get(historial.size() - 1));
        } else {
            temp.setEstado("Atendido");
          //  System.out.println("ATEMDIDO");
            historial.add(this.getName(tipo) + " ha atendido el tickete numero: " + this.getTicketeActual(tipo));

            ManejadorDeListas.cargaListaActividadReciente((String) historial.get(historial.size() - 1));
//System.out.println(this.getOracion(tipo));

            temp.setComentario(this.getOracion(tipo));
            temp.setTiempoSegundos(this.getTiempo(tipo));
            temp.setID_EMPLEADO(this.getName(tipo));
            temp.setFechayHoraAtencion(this.getTiempoActual());
            ManejadorDeListas.MegaLista.add(temp);
            //Manejado
        }
        ManejadorDeListas.ListaDeVerdes.set(indice, temp);

    }

    public void modificarEstadoTicketLiberadoVerde(int indice, String tipo) {
        Tickets temp = (Tickets) ManejadorDeListas.ListaDeVerdes.get(indice);
        if (this.getName(tipo).equals("Fernando")) {
            ManejadorDeListas.arrgloTiketsLiberados[0] = ManejadorDeListas.arrgloTiketsLiberados[0] + 1;

        } else if (this.getName(tipo).equals("Junior")) {
            ManejadorDeListas.arrgloTiketsLiberados[1] = ManejadorDeListas.arrgloTiketsLiberados[1] + 1;

        } else if (this.getName(tipo).equals("Luis")) {
            ManejadorDeListas.arrgloTiketsLiberados[2] = ManejadorDeListas.arrgloTiketsLiberados[2] + 1;

        }
       // System.out.println(this.getOracion(tipo));

        temp.setEstado("Pendiente");
        historial.add(this.getName(tipo) + " ha liberado el tickete numero: " + this.getTicketeActual(tipo));
        ManejadorDeListas.cargaListaActividadReciente((String) historial.get(historial.size() - 1));
        temp.setComentarioLiberado(this.getOracion(tipo));
temp.setID_EMPLEADO(this.getName(tipo));
temp.setFechayHoraAtencion(this.getTiempoActual());
        ManejadorDeListas.MegaLiberados.add(temp);
        ManejadorDeListas.ListaDeVerdes.set(indice, temp);

    }

    public void modificarEstadoLiberadoTicketAmarillo(int indice, String tipo) {
        Tickets temp = (Tickets) ManejadorDeListas.ListaDeAmarillos.get(indice);
        if (this.getName(tipo).equals("Fernando")) {
            ManejadorDeListas.arrgloTiketsLiberados[0] = ManejadorDeListas.arrgloTiketsLiberados[0] + 1;

        } else if (this.getName(tipo).equals("Junior")) {
            ManejadorDeListas.arrgloTiketsLiberados[1] = ManejadorDeListas.arrgloTiketsLiberados[1] + 1;

        } else if (this.getName(tipo).equals("Luis")) {
            ManejadorDeListas.arrgloTiketsLiberados[2] = ManejadorDeListas.arrgloTiketsLiberados[2] + 1;

        }
      //  System.out.println(this.getOracion(tipo));

        temp.setEstado("Pendiente");
        historial.add(this.getName(tipo) + " ha liberado el tickete numero: " + this.getTicketeActual(tipo));
        ManejadorDeListas.cargaListaActividadReciente((String) historial.get(historial.size() - 1));
        temp.setComentarioLiberado(this.getOracion(tipo));
        temp.setID_EMPLEADO(this.getName(tipo));
        temp.setFechayHoraAtencion(this.getTiempoActual());
        ManejadorDeListas.MegaLiberados.add(temp);
        ManejadorDeListas.ListaDeAmarillos.set(indice, temp);

    }

    public void modificarEstadoTicketLiberadoRojo(int indice, String tipo) {
        Tickets temp = (Tickets) ManejadorDeListas.ListaDeRojos.get(indice);
        if (this.getName(tipo).equals("Fernando")) {
            ManejadorDeListas.arrgloTiketsLiberados[0] = ManejadorDeListas.arrgloTiketsLiberados[0] + 1;

        } else if (this.getName(tipo).equals("Junior")) {
            ManejadorDeListas.arrgloTiketsLiberados[1] = ManejadorDeListas.arrgloTiketsLiberados[1] + 1;

        } else if (this.getName(tipo).equals("Luis")) {
            ManejadorDeListas.arrgloTiketsLiberados[2] = ManejadorDeListas.arrgloTiketsLiberados[2] + 1;

        }
     //   System.out.println(this.getOracion(tipo));

        temp.setEstado("Pendiente");
        historial.add(this.getName(tipo) + " ha liberado el tickete numero: " + this.getTicketeActual(tipo));
        ManejadorDeListas.cargaListaActividadReciente((String) historial.get(historial.size() - 1));
        temp.setComentarioLiberado(this.getOracion(tipo));
temp.setID_EMPLEADO(this.getName(tipo));
temp.setFechayHoraAtencion(this.getTiempoActual());
        ManejadorDeListas.MegaLiberados.add(temp);
        ManejadorDeListas.ListaDeRojos.set(indice, temp);

    }

    public void rellenarActividadReciente() {
        ManejadorDeListas.clearActividadReciente();
        ManejadorDeListas.setActividadReciente(historial);
    }

    public String getName(String oracion) {
        String temp = "";
        for (int i = 0; i < oracion.length(); i++) {
            if (oracion.charAt(i) == '@') {
                return temp;
            }
            temp = temp + oracion.charAt(i);
           // System.out.println("Este es el valo de temp " + temp);

        }

       // System.out.println("Se ha retornado null");
        return "";
    }

    public String getOracion(String oracion) {
        String temp = "";
        for (int i = 0; i < oracion.length(); i++) {
            if (oracion.charAt(i) == '%') {
                temp = "";
                continue;
            }
            if (oracion.charAt(i) == '&') {
                return temp;
            }
            temp = temp + oracion.charAt(i);

        }

        //System.out.println("Se ha retornado null");
        return temp;
    }

    public String getTicketeActual(String oracion) {
        String temp = "";
        for (int i = 0; i < oracion.length(); i++) {
            if (oracion.charAt(i) == '@') {
                temp = "";
                continue;
            }
            if (oracion.charAt(i) == '%') {
                return temp;
            }
            temp = temp + oracion.charAt(i);

        }

        //System.out.println("Se ha retornado null");
        return temp;
    }

    public String getTiempo(String oracion) {
        String temp = "";
        for (int i = 0; i < oracion.length(); i++) {
            if (oracion.charAt(i) == '&') {
                temp = "";
                continue;
            }
            temp = temp + oracion.charAt(i);
        }
        return temp;
    }

    public void run() {

        try {
            socketServidor = new ServerSocket(puerto);

            while (true) {

               
                Socket cliente = socketServidor.accept();

                //ObjectInputStream objetoentrante=new ObjectInputStream(cliente.getInputStream());
                dataInput = new DataInputStream(cliente.getInputStream());
                saliente = new DataOutputStream(cliente.getOutputStream());
                objetosaliente = new ObjectOutputStream(cliente.getOutputStream());

                String instruccion = dataInput.readUTF();
                System.out.println("instruccion: " + instruccion);

                if (instruccion.equals("Login")) {

                    String login = dataInput.readUTF();
                    loggin(login);
                } else if (instruccion.equals("ROJO")) {
                    
                    this.sendRojo();

                } else if (instruccion.equals("VERDE")) {
                    this.sendVerde();
                    
                   
                    

                } else if (instruccion.equals("AMARILLO")) {
                    this.sendAmarillo();

                } else if (instruccion.equals("Desconectar")) {
                    String persona = dataInput.readUTF();
                   // System.out.println("Se ha desconectado: " + persona);
                    desconectarPersona(persona);
                    cliente.close();
                } else if (instruccion.equals("ListaROJO")) {
                    int indice = dataInput.readInt();

                    String tipo = dataInput.readUTF();

                    this.modificarEstadoTicketRojo(indice, tipo);
                    actualizarjList();

                    //ManejadorDeListas.ListaDeRojos=(ArrayList)this.objetoentrante.readObject();
                } else if (instruccion.equals("ListaVERDE")) {
                    int indice = dataInput.readInt();

                    String tipo = dataInput.readUTF();

                  //  System.out.println(tipo + "Este es el tip");

                    this.modificarEstadoTicketVerde(indice, tipo);
                    // ManejadorDeListas.ListaDeVerdes=(ArrayList)this.objetoentrante.readObject();
                    actualizarjList();
                } else if (instruccion.equals("ListaAMARILLO")) {
                    int indice = dataInput.readInt();
                    String tipo = dataInput.readUTF();

                    this.modificarEstadoTicketAmarillo(indice, tipo);
                    //ManejadorDeListas.ListaDeAmarillos=(ArrayList)this.objetoentrante.readObject();
                    actualizarjList();
                } else if (instruccion.equals("ListaLiberadoAMARILLO")) {
                    int indice = dataInput.readInt();

                    String tipo = dataInput.readUTF();

                 //   System.out.println(tipo + "Este es el tip");
                    actualizarjList();
                    this.modificarEstadoLiberadoTicketAmarillo(indice, tipo);
                    //ManejadorDeListas.ListaDeAmarillos=(ArrayList)this.objetoentrante.readObject();

                } else if (instruccion.equals("ListaLiberadoROJO")) {
                    int indice = dataInput.readInt();

                    String tipo = dataInput.readUTF();

                 //   System.out.println(tipo + "Este es el tip");

                    this.modificarEstadoTicketLiberadoRojo(indice, tipo);
                    //ManejadorDeListas.ListaDeAmarillos=(ArrayList)this.objetoentrante.readObject();
                    actualizarjList();
                } else if (instruccion.equals("ListaLiberadoVERDE")) {
                    int indice = dataInput.readInt();

                    String tipo = dataInput.readUTF();

                 //   System.out.println(tipo + "Este es el tip");

                    this.modificarEstadoTicketLiberadoVerde(indice, tipo);
                    //ManejadorDeListas.ListaDeAmarillos=(ArrayList)this.objetoentrante.readObject();
                    actualizarjList();
                } else if (instruccion.equals("Reporte")) {
                    mandarReporteIndividual();
                  //  System.out.println("terminno de ejecutar rteporte")
                    //this.saliente.writeUTF("HOLIS");

                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarjList(){
        for(int i=0;i<Lista6.getItemCount();i++){
            if((((Tickets)ManejadorDeListas.ListaDeAmarillos.get(i)).getEstado().equals("Pendiente")||((Tickets)ManejadorDeListas.ListaDeAmarillos.get(i)).getEstado().equals("En atencion")||((Tickets)ManejadorDeListas.ListaDeAmarillos.get(i)).getEstado().equals("Atendido"))&&!Lista6.getItem(i).equals(((Tickets)ManejadorDeListas.ListaDeAmarillos.get(i)).getEstadoActual())){
                Lista6.replaceItem(((Tickets)ManejadorDeListas.ListaDeAmarillos.get(i)).getEstadoActual(), i);
            }
            
            
        }
        for(int i=0;i<Lista4.getItemCount();i++){
            if((((Tickets)ManejadorDeListas.ListaDeVerdes.get(i)).getEstado().equals("Pendiente")||((Tickets)ManejadorDeListas.ListaDeVerdes.get(i)).getEstado().equals("En atencion")||((Tickets)ManejadorDeListas.ListaDeVerdes.get(i)).getEstado().equals("Atendido"))&&!Lista4.getItem(i).equals(((Tickets)ManejadorDeListas.ListaDeVerdes.get(i)).getEstadoActual())){
                Lista4.replaceItem(((Tickets)ManejadorDeListas.ListaDeVerdes.get(i)).getEstadoActual(), i);
            }
            
            
        }
        for(int i=0;i<Lista5.getItemCount();i++){
            if((((Tickets)ManejadorDeListas.ListaDeRojos.get(i)).getEstado().equals("Pendiente")||((Tickets)ManejadorDeListas.ListaDeRojos.get(i)).getEstado().equals("En atencion")||((Tickets)ManejadorDeListas.ListaDeRojos.get(i)).getEstado().equals("Atendido"))&&!Lista5.getItem(i).equals(((Tickets)ManejadorDeListas.ListaDeRojos.get(i)).getEstadoActual())){
                Lista5.replaceItem(((Tickets)ManejadorDeListas.ListaDeRojos.get(i)).getEstadoActual(), i);
            }
            
            
        }
        
    }
    

    public void mandarReporteIndividual() throws IOException {
        
        String tipo = this.dataInput.readUTF();
        System.out.println("Nombre de la persona que lo solicito: " + this.getName(tipo));
        System.out.println("Esta es la fecha: " + this.getFecha(tipo));
        int efectivos = contarEfectivos(this.getName(tipo), this.getFecha(tipo));
        int liberados = contarLiberados(this.getName(tipo), this.getFecha(tipo));
        System.out.println(efectivos+"@"+liberados);
       this.objetosaliente.writeObject(efectivos+"@"+liberados);
       historial.add(this.getName(tipo) + " ha solicitado su reporte para la fecha : " + this.getHora(this.getFecha(tipo)));
        
        //this.saliente.writeUTF(efectivos+"@"+liberados);
        System.out.println("despues de terminar el reporte");

        
        
    }
    public String getHora(String oracion){
        String temp="";
        for(int i=0;i<oracion.length();i++){
            if(oracion.charAt(i)==' '){
                return temp;
            }
            temp=temp+oracion.charAt(i);
        }
        return "";
    }
            

    public int contarEfectivos(String nombre, String fecha) {
        int resultado = 0;
        ArrayList temp = ManejadorDeListas.MegaLista;
        System.out.println("nombre::::"+nombre);
        System.out.println("fecha:::::"+fecha);
        System.out.println(temp.size());
        System.out.println("tañañoo de la lista");
                
        for (int i = 0; i < temp.size(); i++) {
            
            Tickets tictemp = (Tickets) temp.get(i);
            System.out.println("Comparacion: "+tictemp.getID_EMPLEADO()+" "+nombre);
            System.out.println("Comparacion: "+this.getHora(tictemp.getFechayHoraAtencion())+" "+fecha);
            
            if (tictemp.getID_EMPLEADO().equals(nombre) && this.getHora(tictemp.getFechayHoraAtencion()).equals(fecha)) {
                resultado++;
            }

        }
        System.out.println("total de efectivos: "+resultado);
        return resultado;

    }

    public int contarLiberados(String nombre, String fecha) {
        int resultado = 0;
        ArrayList temp = ManejadorDeListas.MegaLiberados;
        System.out.println(temp.size());
        System.out.println("tañañoo de la lista");
                
        for (int i = 0; i < temp.size(); i++) {
            Tickets tictemp = (Tickets) temp.get(i);
            System.out.println("Comparacion: "+tictemp.getID_EMPLEADO()+" "+nombre);
            System.out.println("Comparacion: "+this.getHora(tictemp.getFechayHoraAtencion())+" "+fecha);
            
            if (tictemp.getID_EMPLEADO().equals(nombre) && this.getHora(tictemp.getFechayHoraAtencion()).equals(fecha)) {
                resultado++;
            }

        }
        System.out.println("total de efectivos: "+resultado);
        return resultado;

    }

    public String getFecha(String oracion) {
        String temp = "";
        for (int i = 0; i < oracion.length(); i++) {
            if (oracion.charAt(i) == '@') {
                temp = "";
                continue;
            }
            temp = temp + oracion.charAt(i);
        }
        return temp;
    }
    public void desconectar(){
        try {
            this.socketServidor.close();
        } catch (IOException ex) {
            System.out.println("Error al desconectar");
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
