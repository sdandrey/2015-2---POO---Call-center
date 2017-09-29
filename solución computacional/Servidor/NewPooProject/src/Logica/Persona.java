package Logica;

import java.util.ArrayList;



public class Persona {
    private String nombre;
    public String correo;
    public String contraseña;
    private String categoria;
    public boolean conectado;
    private boolean tipo;
    
    private Persona admn ;
    private ArrayList listaEmpleados= new ArrayList();
    private Persona rojo;
    private Persona verde;
    private Persona amarillo;
   

    public Persona(String nombre, String correo, String contraseña, String categoria, boolean tipo){
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.categoria = categoria;
        this.tipo = tipo;
        
    }
    public void conectar(){
        conectado=true;
    }
    public void desconectar(){
        conectado=false;
    }
    public boolean getEstado(){
        return conectado;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public String isTipo() {
        String nombreTipo;
        if(tipo == false){
            nombreTipo = "Empleado";
        }
        else{
            nombreTipo = "Administrador";
        }
        return nombreTipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
    
    //Metodo que si no encuentra la persona retorna null, de otro modo retorna el administrador
    
    //Metodo que si no encuentra la persona retorna null, de otro modo retorna la persona
    
    
    public String toString(){
        String datos = "Nombre: " + getNombre() + "\n" +
                       "Correo: " + getCorreo() + "\n" +
                       "Contraseña: " + getContraseña() + "\n" +
                       "Categoria: " + getCategoria() + "\n" +
                       "Tipo: " + isTipo() + "\n";
        return datos;
    }
    
    
}
