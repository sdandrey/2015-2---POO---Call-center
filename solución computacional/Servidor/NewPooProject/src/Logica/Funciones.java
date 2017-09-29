/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import static Interfaz.ServidorVentana.Lista4;
import Interfaz.VnServidorReportes;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Fernando
 */
public class Funciones {
    public static boolean origenDeLosTiempos=false;

    public static void busquedaTiketsReportes(){
        
        
        if(origenDeLosTiempos==true && (VnServidorReportes.cbTipoBusqueda.getSelectedItem().toString().equals("Fernando"))){
            ArrayList <Tickets> tiketsEncontrados;
            DefaultListModel modelo = new DefaultListModel();
            
            tiketsEncontrados = getTiketsAtendidosTodosUnEmpleado("Fernando");
            for(int i = 0; i < tiketsEncontrados.size() ; i++){
                String strInfoParaReportes = Integer.toString(tiketsEncontrados.get(i).getIDTicket());
                
                
                
                VnServidorReportes.jlReportes.add(Integer.toString(tiketsEncontrados.get(i).getIDTicket()));
                
                
            }  
            }   
        
        
        if(origenDeLosTiempos==true && (VnServidorReportes.cbTipoBusqueda.getSelectedItem().toString().equals("Junior"))){
            ArrayList <Tickets> tiketsEncontrados2;
            tiketsEncontrados2 = getTiketsAtendidosTodosUnEmpleado("Fernando");
            for(int i = 0; i < tiketsEncontrados2.size() ; i++){
                
                VnServidorReportes.jlReportes.add(Integer.toString(tiketsEncontrados2.get(i).getIDTicket()));
                
                
            }   
        }
        
        if(origenDeLosTiempos==true && (VnServidorReportes.cbTipoBusqueda.getSelectedItem().toString().equals("Luis"))){
            ArrayList <Tickets> tiketsEncontrados3;
            tiketsEncontrados3 = getTiketsAtendidosTodosUnEmpleado("Fernando");
            for(int i = 0; i < tiketsEncontrados3.size() ; i++){
                
                VnServidorReportes.jlReportes.add(Integer.toString(tiketsEncontrados3.get(i).getIDTicket()));
            }   
        }
        
        if(origenDeLosTiempos==true && (VnServidorReportes.cbTipoBusqueda.getSelectedItem().toString().equals("Todos"))){
            ArrayList <Tickets> tiketsEncontrados4;
            tiketsEncontrados4 = getTiketsAtendidosTodosUnEmpleado("Fernando");
            for(int i = 0; i < tiketsEncontrados4.size() ; i++){
                
                VnServidorReportes.jlReportes.add(Integer.toString(tiketsEncontrados4.get(i).getIDTicket()));
                
            }
            tiketsEncontrados4 = getTiketsAtendidosTodosUnEmpleado("Junior");
            for(int i = 0; i < tiketsEncontrados4.size() ; i++){
                
                VnServidorReportes.jlReportes.add(Integer.toString(tiketsEncontrados4.get(i).getIDTicket()));
                
            }
            tiketsEncontrados4 = getTiketsAtendidosTodosUnEmpleado("Luis");
            for(int i = 0; i < tiketsEncontrados4.size() ; i++){
                
                VnServidorReportes.jlReportes.add(Integer.toString(tiketsEncontrados4.get(i).getIDTicket()));
                
            }
        } 
    }
        
    public static ArrayList <Tickets> getTiketsAtendidosTodosUnEmpleado(String empleado){
        ArrayList <Tickets> tiketsEncontrados = new ArrayList();
        for(int i = 0; i < ManejadorDeListas.MegaLista.size(); i++){
            if (ManejadorDeListas.MegaLista.get(i).getID_EMPLEADO().equals(empleado)){
                tiketsEncontrados.add(ManejadorDeListas.MegaLista.get(i));
            }
        } 
        return tiketsEncontrados;
    }

    
    
    /*
    Basicamente este metodo toma una cadena y retorna solo la parte del ID guiado x
    indices tiene capacidad para retornar hasta un id maximo de 999999 se le puede
    poner mas o menos ademas el recorrido de busqeda para el ID inicia del indice 3
    */
    public static String getIDTiket(String pCadena){
	int incioIndex = 8;
        String pID = pCadena.substring(7,8);
        for (int i = 9; i < 13; i++){
            try {
                Integer.parseInt(pCadena.substring(incioIndex,i));
                pID += pCadena.substring(incioIndex,i);
                incioIndex++;
            }
            catch (NumberFormatException nfe) {
                break;
            }
        }
    return pID;
    }
    /*
    recibe como parametro un ID de algun tiket, recorre la lista de tikets y 
    despliega la informacion del tiket en el jTexArea
    */
    public static String cargaInfoTiket(String pID){
            String result = null;
            ArrayList <Tickets> ArrayListTicket =  ManejadorDeListas.getListaDePendientes();//Array de los tickets
            for (Tickets ticket: ArrayListTicket) {   
            if(Integer.toString(ticket.getIDTicket()).equals(pID)){
                result = "\n\nIdentificador: ";
                result += Integer.toString(ticket.getIDTicket());
                result += "\nAsunto: ";
                result += ticket.getAsunto();
                result += "\nCategoría: Sin categoría";
            }
        }
    return result;
    }
    
    public static String cargaInfoTiketVerde(String pID){
            String result = null;
            ArrayList <Tickets> ArrayListTicketVerde =  ManejadorDeListas.getListaDeVerdes();//Array de los tickets verdes
            for (Tickets ticket: ArrayListTicketVerde) {   
            if(Integer.toString(ticket.getIDTicket()).equals(pID)){
                result = "\n\nIdentificador: ";
                result += Integer.toString(ticket.getIDTicket());
                result += "\nAsunto: ";
                result += ticket.getAsunto();
                result += "\nCategoría: Verde";
                //result += ticket.getCategoria();
                result += "\nEstado: ";
                result += ticket.getEstado();
            }
        }
    return result;
    }
    
    
    public static String cargaInfoTiketAmarillo(String pID){
            String result = null;
            ArrayList <Tickets> ArrayListTicketAmarillo =  ManejadorDeListas.getListaDeAmarillos();//Array de los tickets
            for (Tickets ticket: ArrayListTicketAmarillo) {   
            if(Integer.toString(ticket.getIDTicket()).equals(pID)){
                result = "\n\nIdentificador: ";
                result += Integer.toString(ticket.getIDTicket());
                result += "\nAsunto: ";
                result += ticket.getAsunto();
                result += "\nCategoría: Amarillo";
                //result += ticket.getCategoria();
                result += "\nEstado: ";
                result += ticket.getEstado();
            }
        }
    return result;
    }
    
    public static String cargaInfoTiketRojo(String pID){
            String result = null;
            ArrayList <Tickets> ArrayListTicketRojos =  ManejadorDeListas.getListaDeRojos();//Array de los tickets
            for (Tickets ticket: ArrayListTicketRojos) {   
            if(Integer.toString(ticket.getIDTicket()).equals(pID)){
                result = "\n\nIdentificador: ";
                result += Integer.toString(ticket.getIDTicket());
                result += "\nAsunto: ";
                result += ticket.getAsunto();
                result += "\nCategoría: Rojo";
                //result += ticket.getCategoria();
                result += "\nEstado: ";
                result += ticket.getEstado();
            }
        }
    return result;
    }
    
    public static String cargaEstadoTiket(String pID){
            String result = null;
            //revisa en pendientes
            ArrayList <Tickets> ArrayListTicket =  ManejadorDeListas.getListaDePendientes();//Array de los tickets
            for (Tickets ticket: ArrayListTicket) {   
                if (Integer.toString(ticket.getIDTicket()).equals(pID)) {
                    result = " Estado: -> ";
                    result += ticket.getEstado();
                    break;
                }
            }
            //revisa en verdes
            ArrayList <Tickets> ArrayListTicketVerde =  ManejadorDeListas.getListaDeVerdes();//Array de los tickets verdes
            for (Tickets ticket: ArrayListTicketVerde){   
                if (Integer.toString(ticket.getIDTicket()).equals(pID)) {
                    result = " Estado: -> ";
                    result += ticket.getEstado();
                    break;
                }
            }
            //revisa en amarillos
            ArrayList <Tickets> ArrayListTicketAmarillo =  ManejadorDeListas.getListaDeAmarillos();//Array de los tickets
            for (Tickets ticket: ArrayListTicketAmarillo){   
                if (Integer.toString(ticket.getIDTicket()).equals(pID)) {
                    result = " Estado: -> ";
                    result += ticket.getEstado();
                    break;
                }
            }
            //revisa en rojos
            ArrayList <Tickets> ArrayListTicketRojos =  ManejadorDeListas.getListaDeRojos();//Array de los tickets
            for (Tickets ticket: ArrayListTicketRojos){   
                if (Integer.toString(ticket.getIDTicket()).equals(pID)) {
                    result = " Estado: -> ";
                    result += ticket.getEstado();
                    break;
                }
            }
    return result;
    }
    
    
    
    /*public static void actualizaListaTiketsVerdes(int indice){            
            ArrayList <Tickets> ArrayListTicketVerde =  ManejadorDeListas.getListaDeVerdes();//Array de los tickets
            
            
            for (Tickets ticket: ArrayListTicketVerde) {
                String pID = "❧ ID:  ";
                pID += ticket.getIDTicket();
                pID += "   Asunto: ";
                pID += ticket.getAsunto();
                pID += " Estado: -> ";
                pID += ticket.getEstado();
                Lista4.add(pID);
            }
        }*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}//
