/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import static Interfaz.ClienteVentana.ListaEmpleado;
import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class Funciones {
    public static ArrayList <String> listaTiketPrueba = new ArrayList();//Array falso de tikets solo para pruebas
    
    /*
    Basicamente este metodo toma una cadena y retorna solo la parte del ID guiado x
    indices tiene capacidad para retornar hasta un id maximo de 999999 se le puede
    poner mas o menos ademas el recorrido de busqeda para el ID inicia del indice 3
    */
    /*public static String getIDTiket(String pCadena){
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
    }*/
    /*
    recibe como parametro un ID de algun tiket, recorre la lista de tikets y 
    despliega la informacion del tiket en el jTexArea
    */
    /*public static String cargaInfoTiket(String pID){
            String result = null;
            ArrayList <Tickets> ArrayListTicket =  ManejadorDeListas.getListaDePendientes();//Array de los tickets
            for (Tickets ticket: ArrayListTicket) {   
            if(Integer.toString(ticket.getIDTicket()).equals(pID)){
                result = "\n\nIdentificador: ";
                result += Integer.toString(ticket.getIDTicket());
                result += "\nAsunto: ";
                result += ticket.getAsunto();
            }
        }
    return result;
    }*/
    //metodo temporal para cargar la lista es un array simple de strings
    public static void cargarLista(){ 
        listaTiketPrueba.add("Tiket 1");
        listaTiketPrueba.add("Tiket 2");
        listaTiketPrueba.add("Tiket 3");
        listaTiketPrueba.add("Tiket 4");
        listaTiketPrueba.add("Tiket 5");
        for (String tiket: listaTiketPrueba) {           
            ListaEmpleado.add(tiket);
        }
        //System.out.println("¡Todos los Clientes cargados!");
    }
}
