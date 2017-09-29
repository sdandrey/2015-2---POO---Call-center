
package Logica;

import static Interfaz.VnServidorReportes.ListaActividadReciente;
import Logica.Tickets;
import com.toedter.calendar.JCalendar;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class ManejadorDeListas {


    public static ArrayList<Tickets> ListaDePendientes = new ArrayList();
    public static ArrayList<Tickets> ListaDeVerdes = new ArrayList();
    public static ArrayList<Tickets> ListaDeAmarillos = new ArrayList();
    public static ArrayList<Tickets> ListaDeRojos = new ArrayList();
    public static int arrgloTiketsLiberados[]={0,0,0};
    public static ArrayList<Tickets> MegaLista = new ArrayList();
    public static ArrayList<Tickets> MegaLiberados = new ArrayList();
     
    public static void addNewTiketsToLocalListPendientes(ArrayList<Tickets> ListaDePendientesInsert){
        for (int i = 0; i < ListaDePendientesInsert.size();i++){
            ListaDePendientes.add(ListaDePendientesInsert.get(i));
        }
    }
    public static ArrayList getLists(){
        ArrayList temp=new ArrayList();
        temp.add(ListaDeVerdes);
        temp.add(ListaDeAmarillos);
        temp.add(ListaDeRojos);
        return temp;
    }
    
    public static void cargaListaActividadReciente(String actividad){
        
        if(actividad!=null){
            try{
                    ListaActividadReciente.add(actividad);
            }catch(Exception e){
                System.out.println("La lista aun no se ha inicializado");
            }
         
           
        }
        
    }
    public static void clearActividadReciente(){
        ListaActividadReciente.clear();
    }
    public static void setActividadReciente(ArrayList lista){
        clearActividadReciente();
        for(int i=0;i<lista.size();i++){
          ListaActividadReciente.add((String)lista.get(i));
      
        }
        }

    public static void addNewTiketsToLocalListVerdes(ArrayList<Tickets> ListaDeVerdesInsert){
        for (int i = 0; i < ListaDeVerdesInsert.size();i++){
            ListaDeVerdes.add(ListaDeVerdesInsert.get(i));
        }
    }

    public static void addNewTiketsToLocalListAmarillos(ArrayList<Tickets> ListaDeAmarillosInsert){
        for (int i = 0; i < ListaDeAmarillosInsert.size();i++){
            ListaDeAmarillos.add(ListaDeAmarillosInsert.get(i));
        }
    }
    
    public static void addNewTiketsToLocalListRojos(ArrayList<Tickets> ListaDeRojosInsert){
        for (int i = 0; i < ListaDeRojosInsert.size();i++){
            ListaDeRojos.add(ListaDeRojosInsert.get(i));
        }
    }
    public static int getDia(String fecha){
        String temp="";
        for(int i=0;i<fecha.length();i++){
        if(fecha.charAt(i)=='/'){
            return Integer.parseInt(temp);
        }
        temp=temp+fecha.charAt(i);
        }
        System.out.println("Error en la funcion get dia");
        return -1;
    }
    public static int getMes(String fecha){
        String temp="";
        int contador=0;
        for(int i=0;i<fecha.length();i++){
        if((fecha.charAt(i)=='/')&&contador==0){
            temp="";
            contador++;
            //continue;
        }else if((fecha.charAt(i)=='/')&&contador==1){
            return Integer.parseInt(temp);
            //continue;
        }
        else{
            temp=temp+fecha.charAt(i);
        }
        
        }
        System.out.println("Error en la funcion get mes");
        return -1;
    }
    public static int getAno(String fecha){
        String temp="";
        int contador=0;
        for(int i=0;i<fecha.length();i++){
        if((fecha.charAt(i)=='/')){
            temp="";
            contador++;
            continue;
        }
        if((fecha.charAt(i)==' ')){
            
            return Integer.parseInt(temp);
        }
        
        temp=temp+fecha.charAt(i);
        
        }
        //System.out.println("Error en la funcion get mes");
        return Integer.parseInt(temp);
    }        
    
  
    public static ArrayList<Tickets> getListaDePendientes() {
        return ListaDePendientes;
    }

    public static ArrayList<Tickets> getListaDeAmarillos() {
        return ListaDeAmarillos;
    }

    public static ArrayList<Tickets> getListaDeRojos() {
        return ListaDeRojos;
    }
    
    public static ArrayList<Tickets> getListaDeVerdes() {
        return ListaDeVerdes;
    }
    
    public static void setListaDePendientes(ArrayList<Tickets> ListaDePendientes) {
        ManejadorDeListas.ListaDePendientes = ListaDePendientes;
    }


    public static void finderThenInsert(String StrOfJList, ArrayList<Tickets> ListaDestino){
        String MyID = "";
        
        for(int n = 7; n < StrOfJList.length();n++){
            if(StrOfJList.charAt(n) == ' '){
                //Si encuentra el espacio en blanco ese es el numero que buscamos
                //Enpesamos busqueda
                for(int i = 0; i < ListaDePendientes.size() ;i ++){
                    //Si encuentra el objeto con el mismo ID
                    if(Integer.toString(ListaDePendientes.get(i).getIDTicket()).equals(MyID)){
                        //ListaDePendientes.get(i).setCategoria(MyID);
                        ListaDestino.add(ListaDePendientes.get(i));
                        ListaDePendientes.remove(i);
                
                        //System.out.println("Se encontro el objeto ya fue agregado");
                        break;
                    }
                    //Si no sigue buscando
                    //else{System.out.println("No se ha encontado tiket con->"+MyID+"<- en conparacion con->"+Integer.toString(ListaDePendientes.get(i).getIDTicket())+"<-");}
                }
            }
            MyID = MyID+StrOfJList.charAt(n);
        }
        for(int i = 0; i < ListaDePendientes.size() ;i ++){
        }    
    }
    public static void getCantidadTiketsAtendidos(){
    }
    
    public static int tiempoSegundos(ArrayList<Tickets> lista, String idEmpleado){
        int largoLista = lista.size();
        int recorrido = 0;
        int tiempoTotal = 0;
        while(recorrido < largoLista){
            if(lista.get(recorrido).getID_EMPLEADO().equals(idEmpleado)){
                tiempoTotal = tiempoTotal + Integer.parseInt(lista.get(recorrido).getTiempoSegundos());
            }
            recorrido = recorrido +1;
        }
        return tiempoTotal;
    }
    
    public static int tiempoSegEmpleado(String idEmpleado){
        int tiempoTotalEmpleado = 0;
        tiempoTotalEmpleado = tiempoSegundos(ListaDeVerdes, idEmpleado);
        tiempoTotalEmpleado = tiempoSegundos(ListaDeAmarillos, idEmpleado);
        tiempoTotalEmpleado = tiempoSegundos(ListaDeRojos, idEmpleado);
        return tiempoTotalEmpleado;
    }
    
    public static  ArrayList<Tickets> ticketEntreFecha(String fecha1,String fecha2, ArrayList<Tickets> lista){
        System.out.println("Comienza la funcion");
        System.out.println(fecha1);
        System.out.println(fecha2);
        
        int dia1=getDia(fecha1);
        int mes1=getMes(fecha1);
        int annio1=getAno(fecha1);
        
        int dia2=getDia(fecha2);
        int mes2=getMes(fecha2);
        int annio2=getAno(fecha2);
        System.out.println(dia1);
        System.out.println(mes1);
        System.out.println(annio1);
        System.out.println(dia2);
        System.out.println(mes2);
        System.out.println(annio2);
        ArrayList<Tickets> listaFecha = new ArrayList<Tickets>();
        
        Calendar calendarDesde = new GregorianCalendar(annio1, mes1, dia1);
	Calendar calendarHasta = new GregorianCalendar(annio2, mes2, dia2);
        
        if(calendarDesde.after(calendarHasta)){
            System.out.println("saliooooooo");
            return listaFecha;} //Botar el metodo si la fecha de haste es mayor que la fecha desde
        
        
        for(int i = 0; i < lista.size();i++){

            Calendar calendarMiTicket = new GregorianCalendar(getAno(lista.get(i).getFechayHoraAtencion()), 
                                                              getMes(lista.get(i).getFechayHoraAtencion()),
                                                              getDia(lista.get(i).getFechayHoraAtencion()));
            
            if(calendarMiTicket.after(calendarDesde) && calendarMiTicket.before(calendarHasta)){
                System.out.println("ESTE ITEM SI ENTREO>"+i+"<");
                        
                listaFecha.add(lista.get(i));
            } 
        
        }
        System.out.println("termina la funcion");
        return listaFecha;
        
    }
    public String getFecha(JCalendar calendario){
        return calendario.getDate().getDay()+"/"+calendario.getDate().getMonth()+"/"+calendario.getDate().getYear();
    }
    

}
