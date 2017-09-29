/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



       
        /*
        tempTiket.getID_CLIENTE();
        tempTiket.getCategoria();
        tempTiket.getTiempoSegundos();
        */
package Logica;

import Interfaz.VnServidorReportes;
import static Interfaz.VnServidorReportes.dataset;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author root
 */
public abstract class Reporte {

    public static ArrayList<Tickets> ListaDePendientes = new ArrayList();
    public static ArrayList<Tickets> ListaDeVerdes = new ArrayList();
    public static ArrayList<Tickets> ListaDeAmarillos = new ArrayList();
    public static ArrayList<Tickets> ListaDeRojos = new ArrayList();
    
    
    
    public static int findTiketsAtendidos(ArrayList<Tickets> ListaAConocer,String IDcliente) {
        
        int numeroDeTiketsAtendidos = 0; 
        
        /*Buscar en la lista los tiketes atendidos por el empleado*/
        for(int i = 0;i < ListaAConocer.size();i++){
            if(ListaAConocer.get(i).getID_CLIENTE().equals(IDcliente)){
                numeroDeTiketsAtendidos ++;
            }
        }
 
        return numeroDeTiketsAtendidos;
    }
    
    public static int findTiempoAtencionIndividual(ArrayList<Tickets> ListaAConocer,String IDcliente) {
        
        int intTiempoSegundosPromedio = 0;
        
        for(int i = 0;i < ListaAConocer.size();i++){
                if(ListaAConocer.get(i).getID_CLIENTE().equals(IDcliente)){
                    /*Combertimos y sumamos las cantidades en segundos*/
                    intTiempoSegundosPromedio += Integer.parseInt(ListaAConocer.get(i).getTiempoSegundos());
                }
        }
        
        /*Dividimos el tiempo en segundos total entre la cantidad de Tikets atendidos*/
        intTiempoSegundosPromedio = intTiempoSegundosPromedio / findTiketsAtendidos(ListaAConocer,IDcliente);
                
        return intTiempoSegundosPromedio;
        }
    
    
    
    
    /**
     * Calcula el tiempo promedio total de atencion de una lista;
     * @param ListaAConocer
     * @param IDcliente
     * @return 
     */
    public static String findTiempoAtencionTotal(ArrayList<Tickets> ListaAConocer,String IDcliente) {
       
        /*El tiempo promedio de duracion es igual a la suma del tiempo que duro cada empleado divido entre 3*/
        String strTiempoSegundosPromedioTotal = Integer.toString((
            findTiempoAtencionIndividual(ListaAConocer,"Luis")+
            findTiempoAtencionIndividual(ListaAConocer,"Fernando")+
            findTiempoAtencionIndividual(ListaAConocer,"Junior"))/3);
        
        return strTiempoSegundosPromedioTotal;
    }
    
    public static int findTiempoAtencionIndividualTotosLosTickets(String Cliente){
        
        int intTiempoAtencionIndividualTotosLosTickets = (
               findTiempoAtencionIndividual(ManejadorDeListas.ListaDeVerdes,Cliente)+
               findTiempoAtencionIndividual(ManejadorDeListas.ListaDeAmarillos,Cliente)+
               findTiempoAtencionIndividual(ManejadorDeListas.ListaDeRojos,Cliente))
                /3
                ;
        
        return intTiempoAtencionIndividualTotosLosTickets;
    }
    
    /*public static ArrayList<Tickets>  findAndGetTiketsAtendidos(){
        
    
    
    }*/
    
    public static int tiketsCoincidenciasEnUnaLista(ArrayList<Tickets> ListaAConocer,String compadador ){ // comparador va a ser el parametro que buscamos (Atendido, Pendiente, sinCategorizar)
        int numCoincidencias = 0;
        
        for(int i=0 ; i < ListaAConocer.size();i++){
            if(ListaAConocer.get(i).getEstado().equals(compadador)){
                numCoincidencias ++;
            }
        }
        return numCoincidencias;
    }
    
    
    public static void actualizarReportesDeCantidades(){
        
        int cantTiketsAtendidos =          
                (tiketsCoincidenciasEnUnaLista(ManejadorDeListas.getListaDeVerdes(),"Atendido")+
                tiketsCoincidenciasEnUnaLista(ManejadorDeListas.getListaDeAmarillos(),"Atendido")+
                tiketsCoincidenciasEnUnaLista(ManejadorDeListas.getListaDeRojos(),"Atendido"));        
        
        int cantTiketsPendientes =
                (tiketsCoincidenciasEnUnaLista(ManejadorDeListas.getListaDeVerdes(),"Pendiente")+
                tiketsCoincidenciasEnUnaLista(ManejadorDeListas.getListaDeAmarillos(),"Pendiente")+
                tiketsCoincidenciasEnUnaLista(ManejadorDeListas.getListaDeRojos(),"Pendiente"));
        
        int cantTiketsEnAtencion =
                (tiketsCoincidenciasEnUnaLista(ManejadorDeListas.getListaDeVerdes(),"En Atencion")+
                 tiketsCoincidenciasEnUnaLista(ManejadorDeListas.getListaDeAmarillos(),"En Atencion")+
                 tiketsCoincidenciasEnUnaLista(ManejadorDeListas.getListaDeRojos(),"En Atencion"));
        
        
        VnServidorReportes.lblTiketsAtendidos.setText(      "Tickets Atendidos:              "+Integer.toString(cantTiketsAtendidos));
        VnServidorReportes.lblTicketsPendientes.setText(    "Tickets Pendientes:             "+Integer.toString(cantTiketsPendientes));             
        VnServidorReportes.lblTicketsEnAtencion.setText(    "Tickets Aun En Atención:        "+Integer.toString(cantTiketsEnAtencion));                
        VnServidorReportes.lblTicketsSinCategorizar.setText("Tickets Sin Categorizar:        "+Integer.toString(ManejadorDeListas.getListaDePendientes().size()));
        VnServidorReportes.lblTicketsEnSistema.setText(     "Total Tickets En El Sistema:    "+Integer.toString(ManejadorDeListas.getListaDePendientes().size()+
                                                                                                                ManejadorDeListas.getListaDeVerdes().size()+
                                                                                                                ManejadorDeListas.getListaDeAmarillos().size()+
                                                                                                                ManejadorDeListas.getListaDeRojos().size()));
    
        dataset.setValue(cantTiketsAtendidos,"Atendidos","Reporte de Tickets");
        dataset.setValue(cantTiketsEnAtencion,"Aun EN Atencion","Reporte de Tickets");
        dataset.setValue(cantTiketsPendientes,"Pendientes","Reporte de Tickets");
        dataset.setValue(ManejadorDeListas.getListaDePendientes().size(),"Sin Categorizar","Reporte de Tickets");
        
        
    }
    
    
    
}