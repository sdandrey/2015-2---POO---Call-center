package Logica;

import static Interfaz.ServidorVentana.Lista;
import java.util.ArrayList;

import java.io.IOException;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import jxl.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.*;
import jxl.read.biff.BiffException;



public abstract class MyExell {
    
    public static int ID_Secuencia = 0;
    
    /*======Atributos Exell Tickets======*/
    public static Workbook libroDeTrabajo;
    public static WritableWorkbook copiaDeLibro;
    
    public static WritableSheet hojaTiketsPendientes;
    public static WritableSheet hojaTiketsVerdes;
    public static WritableSheet hojaTiketsAmarillos;
    public static WritableSheet hojaTiketsRojos;
            
    
    /*======Atributos Exell Tickets======*/
    public static WritableWorkbook exellBaseDatosMGE;
    
    public static WritableSheet hojaTiketsAtendidosMGE;
    public static WritableSheet hojaTiketsLiberadosMGE;
    
    
    public static void open(String path){
        try{
            /*Abrir el exell*/
            File temp = new File(path);
            libroDeTrabajo = Workbook.getWorkbook(temp);
        }
        //----------------------------------------------------------
        catch (IOException ioex) {
            System.out.println("ERROR---->>"+ioex.getMessage());
        } 
        catch (NumberFormatException nfe) {
            System.out.println("ERROR---->>"+nfe.getMessage());    
        }
        catch (BiffException biff){
            System.out.println("ERROR---->>"+biff.getMessage());
        }
        //----------------------------------------------------------
    }
    
    
     /**
     * Metodo que carga los tikets pendientes del .xls 
     * Ademas Actualiza El JList Lista en la ventana de Servidor Ventana
     * @return Lista con Tikets con la informacion principal, hora de ingreso, ID cliente y Asunto
     */
    public static ArrayList<Tickets> Open_Load_And_ReturnListOfTickets(String path){
        //Abriendo Archivo .xls
        open(path);
        //Hoja 0 es donde permanesen los tickets sin categorizar
        Sheet hojaActual = libroDeTrabajo.getSheet(0);
        
        ArrayList<Tickets> listaTikets = new ArrayList();
        int numFilas = hojaActual.getRows();
        for( int fila = 0; fila+1 < numFilas; fila++ ){
             
     
//Validacion de cargar solo Tickets Pendientes
            Cell celdaEstado =  hojaActual.getCell(4,fila+1);
            if (celdaEstado.getContents()==""){
            
            
            ID_Secuencia ++;
                
            Date fechaHoraActual = new Date();
            String strFechaHora = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa").format(fechaHoraActual);
            
            
            //String para el Jlist para desplegar la informcion de los tikets    
            String strDisplayToLista = "❧ ID:  ";
            
            
            Cell celdaIDcliente = hojaActual.getCell(1,fila+1);
            String strIDcliente = celdaIDcliente.getContents();       
            
            strDisplayToLista += Integer.toString(ID_Secuencia);
     
            Cell celdaFechaHora = hojaActual.getCell(0,fila+1);
            String strFechaHoraArchivo = celdaFechaHora.getContents();       
         
            strDisplayToLista += "   Asunto: ";
            
            Cell celdaAsunto = hojaActual.getCell(2,fila+1);
            String strAsunto = celdaAsunto.getContents();
            strDisplayToLista += strAsunto;
                    
            Tickets ticket = new Tickets(strFechaHora, strIDcliente, strAsunto ,ID_Secuencia);        
            //Tickets ticket = new Tickets(strFechaHora, strIDcliente, strAsunto ,ID_Secuencia);
            listaTikets.add(ticket);
            Lista.add(strDisplayToLista); // Adding to JList Lista in Interfaz
            }
        }
        
        return listaTikets;
        
    }

        public static void save_All_Changes(String path) {
        
            //Fecha y Hora para el Nombre del Archivo Final
            Date fechaHoraActualParaFile = new Date();
            String strFechaHoraParaFile = new SimpleDateFormat("ddMMyyyy").format(fechaHoraActualParaFile);    

            //Abrir archivo .xls para clonarlo
            //open(path);

            try {
                copiaDeLibro = Workbook.createWorkbook(new File(path+"/TICKETS_"+strFechaHoraParaFile+".xls")); 
             //   System.out.println(path+"TICKETS_"+strFechaHoraParaFile+".xls");
                //copiaDeLibro =  Workbook.createWorkbook(new File (" TICKETS_"+cortar(path)+strFechaHoraParaFile+".xls"),libroDeTrabajo);
                //copiaDeLibro =  Workbook.createWorkbook(new File (path+"/TICKETS_"+strFechaHoraParaFile+".xls"),libroDeTrabajo);
                hojaTiketsPendientes = copiaDeLibro.createSheet("Tickets Pendientes", 0);
                hojaTiketsVerdes = copiaDeLibro.createSheet("Tickets Verdes", 1);
                hojaTiketsAmarillos = copiaDeLibro.createSheet("Tickets Amarillo", 2);
                hojaTiketsRojos = copiaDeLibro.createSheet("Tickets Rojos", 3);
                
                insertSheet(ManejadorDeListas.getListaDePendientes(),hojaTiketsPendientes);
                insertSheet(ManejadorDeListas.getListaDeVerdes(),hojaTiketsVerdes);
                insertSheet(ManejadorDeListas.getListaDeAmarillos(),hojaTiketsAmarillos);
                insertSheet(ManejadorDeListas.getListaDeRojos(),hojaTiketsRojos);
                
                copiaDeLibro.write();
                //copiaDeLibro.write();
                copiaDeLibro.close();
                
            } catch (IOException wse) {
               System.out.println("ERROR---->>"+wse.getMessage());
            } catch (WriteException ex) {
            Logger.getLogger(MyExell.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        private static void insertSheet(ArrayList<Tickets> lista , WritableSheet hojaTikets){
            
            
            
        try {
            /**************************Etiquetas de titulos*********************************/
            Label lblFechayHoraRecepcionTitulo = new Label(0,0,"Fecha y Hora de Resepción");
            hojaTikets.addCell(lblFechayHoraRecepcionTitulo);
            Label lblID_ClienteTitulo = new Label(1,0,"ID_Cliente");
            hojaTikets.addCell(lblID_ClienteTitulo);
            Label lblAsuntoTitulo = new Label(2,0,"Asunto");
            hojaTikets.addCell(lblAsuntoTitulo);
            Label lblTicketIDTitulo = new Label(3,0,"ID_Ticket");
            hojaTikets.addCell(lblTicketIDTitulo);
            Label lblCategoriaTitulo = new Label(4,0,"Categoria");
            hojaTikets.addCell(lblCategoriaTitulo);
            Label lblID_EmpleadoTitulo = new Label(5,0,"ID_Empleado");
            hojaTikets.addCell(lblID_EmpleadoTitulo);
            Label lblFechayHoraAtencionTitulo = new Label(6,0,"Fecha y Hora de Atencion");
            hojaTikets.addCell(lblFechayHoraAtencionTitulo);
            Label lblTiempoEnSegundosTitulo = new Label(7,0,"Tiempo En Segundos");
            hojaTikets.addCell(lblTiempoEnSegundosTitulo);
            Label lblComentarioTitulo = new Label(8,0,"Comentario");
            hojaTikets.addCell(lblComentarioTitulo);
            Label lblEstadoTitulo = new Label(9,0,"Estado");
            hojaTikets.addCell(lblEstadoTitulo);                        
            
            
            if (lista.size()>0){
                
                
                for(int i =0; i<lista.size(); i++){
                            
                    try {
                        Tickets tempTiket = lista.get(i);
                        
                        Label lblFechayHoraRecepcion = new Label(0,i+1,tempTiket.getFechayHoraRecepcion());
                        hojaTikets.addCell(lblFechayHoraRecepcion);
                        Label lblID_CLIENTE = new Label(1,i+1,tempTiket.getID_CLIENTE());
                        hojaTikets.addCell(lblID_CLIENTE);
                        Label lblasunto = new Label(2,i+1,tempTiket.getAsunto());
                        hojaTikets.addCell(lblasunto);
                        Label lblIDTicket = new Label(3,i+1,Integer.toString(tempTiket.getIDTicket()));
                        hojaTikets.addCell(lblIDTicket);
                        Label lblcategoria= new Label(4,i+1,tempTiket.getCategoria());
                        hojaTikets.addCell(lblcategoria);
                        Label lblID_EMPLEADO = new Label(5,i+1,tempTiket.getID_EMPLEADO());
                        hojaTikets.addCell(lblID_EMPLEADO);
                        Label lblfechayHoraAtencion = new Label(6,i+1,tempTiket.getFechayHoraAtencion());
                        hojaTikets.addCell(lblfechayHoraAtencion);
                        Label lbltiempoSegundos = new Label(7,i+1,tempTiket.getTiempoSegundos());
                        hojaTikets.addCell(lbltiempoSegundos);
                        Label lblComentario = new Label(8,i+1,tempTiket.getComentario());
                        hojaTikets.addCell(lblComentario);
                        Label lblestado = new Label(9,i+1,tempTiket.getEstado());
                        hojaTikets.addCell(lblestado);
                        
                        
                    } catch (WriteException ex) {
                        System.out.println("Error EN GUARDAR "+ex.getMessage());
                    }
                }   
            }
        } catch (WriteException ex) {
            Logger.getLogger(MyExell.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        /**
         * Metodo para extraer  el path del archivo que se clonara para generar un nuevo archivo en ese lugar
         * @param direccion del archivo original que sera clonado
         * @return path del archivo
         */
        public static String cortar(String direccion){

            String cutString="";
            int indice=0;

            for (int i = 0; i<direccion.length();i++){    
                    if(direccion.charAt(i)=='/'){
                        indice=i;
                    }
                    if(direccion.charAt(i)=='.'){
                        String temp2="";
                        for(int u=0;u<direccion.length();u++){
                            if(u==indice){
                                return "/"+temp2;
                            }
                            temp2=temp2+direccion.charAt(u);
                        }
                    }
                    cutString = cutString+direccion.charAt(i);
            }
            return cutString;
        }
        
        public static void MegaExellGet(int CASE){
            
        try{
            File fileMegaExell = new File("exellBaseDatosMGE.xls");  /*Abrir MegaExell*/
            Workbook MegaExell = Workbook.getWorkbook(fileMegaExell);
            
            if(MegaExell.getNumberOfSheets()>1){
            
            Sheet hojaMaster = MegaExell.getSheet(CASE);
            
            
            int numFilas = hojaMaster.getRows();
            System.out.println("NUMERO ROWS"+numFilas);
            for( int fila = 0; fila+1 < numFilas; fila++ ){
        
                Cell celdaFechayHoraRecepcion =  hojaMaster.getCell(0,fila+1);
                Cell celdaID_CLIENTE =  hojaMaster.getCell(1,fila+1);    
                Cell celdaasunto =  hojaMaster.getCell(2,fila+1);
                Cell celdaIDTicket =  hojaMaster.getCell(3,fila+1);
                Cell celdacategoria =  hojaMaster.getCell(4,fila+1);
                Cell celdaID_EMPLEADO =  hojaMaster.getCell(5,fila+1);
                Cell celdafechayHoraAtencion =  hojaMaster.getCell(6,fila+1);
                Cell celdatiempoSegundos =  hojaMaster.getCell(7,fila+1);
                Cell celdaComentario =  hojaMaster.getCell(8,fila+1);
                Cell celdaestado =  hojaMaster.getCell(9,fila+1);            
            
                String strFechayHoraRecepcion = celdaFechayHoraRecepcion.getContents();
                String strID_CLIENTE = celdaID_CLIENTE.getContents();
                String strAsunto = celdaasunto.getContents();
                String strIDTicket = celdaIDTicket.getContents();
                String strCategoria = celdacategoria.getContents();
                String strID_EMPLEADO = celdaID_EMPLEADO.getContents();
                String strFechayHoraAtencion = celdafechayHoraAtencion .getContents();
                String strTiempoSegundos = celdatiempoSegundos.getContents();
                String strComentario = celdaComentario.getContents();
                String strEstado = celdaestado.getContents();
                
                       
                Tickets ticket = new Tickets();
                
                ticket.setFechayHoraRecepcion(strFechayHoraRecepcion);
                ticket.setID_CLIENTE(strID_CLIENTE);
                ticket.setAsunto(strAsunto); 
                ticket.setIDTicket(Integer.parseInt(strIDTicket));
                ticket.setCategoria(strCategoria);
                ticket.setID_EMPLEADO(strID_EMPLEADO);
                ticket.setFechayHoraAtencion(strFechayHoraAtencion);
                ticket.setTiempoSegundos(strTiempoSegundos);
                ticket.setComentario(strComentario);
                ticket.setEstado(strEstado);
             
                
                if (CASE == 0){ManejadorDeListas.MegaLista.add(ticket);}
                else{ManejadorDeListas.MegaLiberados.add(ticket);}
                
                

                                
            }
        }
        MegaExell.close();
        
        }
        
        //----------------------------------------------------------
        catch (IOException | NumberFormatException | BiffException ioex) {
            System.out.println("ERROR---->>"+ioex.getMessage());
            
            
        }
        
        //----------------------------------------------------------

        //----------------------------------------------------------
        
        
        }
        
        
        
        public static void generateMegaExell (int pagina,ArrayList<Tickets> lista){
           
        try {
            exellBaseDatosMGE = Workbook.createWorkbook(new File("exellBaseDatosMGE.xls"));
            
            hojaTiketsAtendidosMGE = exellBaseDatosMGE.createSheet("Tickets Atendidos", 0);
            hojaTiketsLiberadosMGE = exellBaseDatosMGE.createSheet("Tickets Liberados", 1);
            
            System.out.println("\n"+"\n+"+"\n"+"ESTO TIENE LISTA LIBERADOS>"+ManejadorDeListas.MegaLiberados.size()+"<ESO TIENE"+"\n"+"\n"+"\n");
                    
            insertSheet(ManejadorDeListas.MegaLista,hojaTiketsAtendidosMGE);
            insertSheet(ManejadorDeListas.MegaLiberados,hojaTiketsLiberadosMGE);
            
            exellBaseDatosMGE.write();
            try {
                exellBaseDatosMGE.close();
            } catch (WriteException ex) {Logger.getLogger(MyExell.class.getName()).log(Level.SEVERE, null, ex);}
            } catch (IOException ex) {Logger.getLogger(MyExell.class.getName()).log(Level.SEVERE, null, ex);}
        }
        
        
}

