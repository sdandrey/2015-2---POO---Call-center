package Logica.cliente;


import Logica.Tickets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Clase que atiende el socket y las peticiones de usuario. 
 * Lo que llega por el socket lo muestra en el textArea del panel, lo que
 * escribe el usuario en el panel lo env�a por el socket.
 * @author Chuidiang
 *
 */
public class ControlCliente implements ActionListener, Runnable
{
    /** Para lectura de datos del socket */
    private DataInputStream dataInput;

    /** Para escritura de datos en el socket */
    private DataOutputStream dataOutput;

    /** Panel con los controles para el usuario */
    
    ArrayList lista;
    ObjectInputStream objetoEntrante;
    String color;
    /**
     * Contruye una instancia de esta clase, lanzando un hilo para atender al
     * socket.
     * @param socket El socket
     * @param panel El panel del usuario
     */
    public ControlCliente(Socket socket, PanelCliente panel,String color)
    {
        
        this.color=color;
        try
        {
            //this.color=color;
            objetoEntrante=new ObjectInputStream(socket.getInputStream());
            lista=(ArrayList)objetoEntrante.readObject();
            
            
            dataOutput = new DataOutputStream(socket.getOutputStream());
            
            Thread hilo = new Thread(this);
            hilo.start();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Recoge el texto del panel y lo env�a por el socket.
     * El panel llamar� a este m�todo cuando el usuario escriba algo y 
     * pulse el bot�n de "enviar" o pulse "enter" sobre el textfield.
     */
    public void actionPerformed(ActionEvent evento)
    {
        try
        {
            //dataOutput.writeUTF(panel.getTexto());
        } catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
    }

    /**
     * M�todo run para antender al socket. Todo lo que llega por el socket se
     * escribe en el panel.
     */
    public void run()
    {
        ArrayList temp;
        try
        {
            while (true)
            {
                System.out.println("Estos son los elementos de la lista");
                //this.lista=(ArrayList)objetoEntrante.readObject();
                temp=(ArrayList)objetoEntrante.readObject();
                System.out.println("color: "+this.color);
                if(this.color.equals("Rojo")){
                    System.out.println("Rojo");
                    this.lista=(ArrayList)temp.get(2);
                }
                else if(this.color.equals("Amarillo")){
                    System.out.println("Amarillo");
                    this.lista=(ArrayList)temp.get(1);
                }
                else if(this.color.equals("Verde")){
                    System.out.println("Verde");
                    this.lista=(ArrayList)temp.get(0);
                }
                
                
                System.out.println(this.lista.size());
                for(int i =0;i<this.lista.size();i++){
                    Tickets tempt=(Tickets)this.lista.get(i);
                    System.out.println(tempt.getAsunto()+"\n");
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
