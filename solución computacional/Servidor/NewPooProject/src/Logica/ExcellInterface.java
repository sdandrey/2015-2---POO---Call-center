
package Logica;

import java.util.ArrayList;


    public abstract interface  ExcellInterface{

     
        public ArrayList<Tickets>  cargarTiketsDeArchivo();
        public ArrayList<Tickets>  cargarTiketsRojosDeArchivo();
        public ArrayList<Tickets>  cargarTiketsVerdesDeArchivo();
        public ArrayList<Tickets>  cargarTiketsAmarillosDeArchivo();
        public void cargarGuardarCambiosEnArchivo(ArrayList<Tickets> listaPendientes,
                                                ArrayList<Tickets> listaVerdes,
                                                ArrayList<Tickets> listaAmarillos,
                                                ArrayList<Tickets> listaRojos);
    
}
