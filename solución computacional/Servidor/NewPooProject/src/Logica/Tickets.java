

package Logica;

import java.io.Serializable;




public class Tickets implements Serializable{
    /**
     * Atributos
     */
    private String FechayHoraRecepcion;
    private String ID_CLIENTE;    
    private String asunto;
    private  int   IDTicket;
    private String categoria;
    private String ID_EMPLEADO;
    private String fechayHoraAtencion;
    private String tiempoSegundos;
    private String Comentario;
    private String estado;
    private String comentarioNoLiberado;

    public Tickets(){}
    public Tickets(String FechayHoraRecepcion, String ID_CLIENTE, String asunto, int IDTicket){
        this.FechayHoraRecepcion = FechayHoraRecepcion;
        this.ID_CLIENTE = ID_CLIENTE;
        this.asunto = asunto;
        this.IDTicket = IDTicket;
        this.Comentario=Comentario;
        this.estado="Pendiente";
        this.comentarioNoLiberado="";
    }

    public Tickets(String FechayHoraRecepcion, String ID_CLIENTE, String asunto, 
                   int IDTicket, String categoria, String ID_EMPLEADO, 
                   String fechayHoraAtencion,String tiempoSegundos, 
                   String Comentario, String estado){
    
        this.FechayHoraRecepcion=FechayHoraRecepcion;
        this.ID_CLIENTE=ID_CLIENTE;    
        this.asunto=asunto;
        this.IDTicket=IDTicket;
        this.categoria=categoria;
        this.ID_EMPLEADO=ID_EMPLEADO;
        this.fechayHoraAtencion=fechayHoraAtencion;
        this.tiempoSegundos=tiempoSegundos;
        this.Comentario=Comentario;
        
        this.Comentario=Comentario;
        this.estado="Sin atender";
    }
    public String getComentarioLiberado(){
        return this.comentarioNoLiberado;
    }
    public void setComentarioLiberado(String comentario){
        this.comentarioNoLiberado=comentario;
    }
    
    public Tickets(String asunto, int IDTicket, String categoria, String estado){
        this.asunto = asunto;
        this.IDTicket = IDTicket;
        this.categoria = categoria;
        this.estado = estado;
    }
    public String getEstadoActual(){
        return "❧ ID:  " + getIDTicket()+"   Asunto: "+getAsunto()+" "+getEstado();
    }
    
    
    
    /**
     * Stters and Getters
     */
    public String getFechayHoraRecepcion() {
        return FechayHoraRecepcion;
    }

    public void setFechayHoraRecepcion(String FechayHoraRecepcion) {
        this.FechayHoraRecepcion = FechayHoraRecepcion;
    }

    public String getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(String ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }
    
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    public int getIDTicket() {
        return IDTicket;
    }

    public void setIDTicket(int IDTicket) {
        this.IDTicket = IDTicket;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
 
    public String getID_EMPLEADO() {
        return ID_EMPLEADO;
    }

    public void setID_EMPLEADO(String ID_EMPLEADO) {
        this.ID_EMPLEADO = ID_EMPLEADO;
    }

    public String getFechayHoraAtencion() {
        return fechayHoraAtencion;
    }

    public void setFechayHoraAtencion(String fechayHoraAtencion) {
        this.fechayHoraAtencion = fechayHoraAtencion;
    }

    public String getTiempoSegundos() {
        return tiempoSegundos;
    }

    public void setTiempoSegundos(String tiempoSegundos) {
        this.tiempoSegundos = tiempoSegundos;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String toString(){
        String datos = "Asunto: " + getAsunto() + "\n" +
                       "ID: " + getIDTicket() + "\n" +
                       "Categoria: " + getCategoria() + "\n" +
                       "Estado: " + getEstado() + "\n" ;
        return datos;
    }
    
}
