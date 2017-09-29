package Logica.servidor;


import Logica.ManejadorDeListas;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Hilo encargado de atender a un cliente.
 * @author Chuidiang                     
 */
public class HiloDeCliente_1 implements Runnable, ListDataListener
{
    /** Lista en la que se guarda toda la charla */
    

    /** Socket al que est� conectado el cliente */
    private Socket socket;

    /** Para lectura de datos en el socket */
    private DataInputStream dataInput;

    /** Para escritura de datos en el socket */
    private DataOutputStream dataOutput;
    private Servidor servidor;
    ObjectOutputStream salienteServidor;

    /**
     * Crea una instancia de esta clase y se suscribe a cambios en la charla.
     * @param charla La lista de textos que componen la charla del chat
     * @param socket Socket con el cliente.
     */
    public HiloDeCliente_1(DefaultListModel charla, Socket socket) throws IOException
    {
        
      
        this.socket = socket;
        ArrayList lista = ManejadorDeListas.ListaDeRojos;
        //lista.add("PERE");
        
        
        try
        {
            salienteServidor=new ObjectOutputStream(socket.getOutputStream());
        salienteServidor.writeObject(lista);
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void actualizarLista() throws IOException{
        ArrayList lista = ManejadorDeListas.getLists();
        //lista.add("PERE");
        //salienteServidor=new ObjectOutputStream(socket.getOutputStream());
        salienteServidor.writeObject(lista);
    }

    /**
     * Atiende el socket. Todo lo que llega lo mete en la charla.
     */
    public void run()
    {
        try
        {
            while (true)
            {
                
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void actualizaLista() throws IOException{
        
        ArrayList lista = ManejadorDeListas.ListaDeRojos;
        //lista.add("PERE");
        salienteServidor.writeObject(lista);
    }

    /**
     * Env�a el �ltimo texto de la charla por el socket.
     * Se avisa a este m�todo cada vez que se mete algo en charla, incluido
     * cuando lo mete este mismo hilo. De esta manera, lo que un cliente escriba,
     * se le reenviar� para que se muestre en el textArea.
     */
    

    /** No hace nada */
    public void intervalRemoved(ListDataEvent e)
    {
    }

    /** No hace nada */
    public void contentsChanged(ListDataEvent e)
    {
    }

    @Override
    public void intervalAdded(ListDataEvent e) {
        
    }
}
