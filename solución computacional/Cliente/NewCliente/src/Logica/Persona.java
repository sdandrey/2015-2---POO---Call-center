package Logica;

import java.io.Serializable;
import java.util.ArrayList;



public class Persona implements Serializable {
    private String nombre;
    private String correo;
    private String contraseña;
    private String categoria;
    private boolean conectado;
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
    private void registrarPersonas(){
        Persona admn = new Persona("Admin","admin@tec.ac.cr","12345",null,true);
        Persona rojo = new Persona("Fernando","fernando@tec.ac.cr","12345",null,true);
        Persona verde = new Persona("Luis","luis@tec.ac.cr","12345",null,true);
        Persona amarillo = new Persona("Junior","junior@tec.ac.cr","12345",null,true);
        listaEmpleados.add((Persona)verde);
        listaEmpleados.add((Persona)rojo);
        listaEmpleados.add((Persona)amarillo);
        
        
    }
    //Metodo que si no encuentra la persona retorna null, de otro modo retorna el administrador
    public Persona verificarAdministrador(String usuario, String contraseña){
        if(usuario.equals(admn.correo)&&contraseña.equals(admn.contraseña)){
            return admn;
        }
        return null;
    }
    //Metodo que si no encuentra la persona retorna null, de otro modo retorna la persona
    public Persona verificarEmpleados(String usuario, String contraseña){
        for(int i =0;i<listaEmpleados.size();i++){
            Persona temp= (Persona)listaEmpleados.get(i);
            if(temp.contraseña.equals(contraseña)&&temp.correo.equals(usuario)){
                return temp;
            }
            
        }
        return null;
        
    }
    
    public String toString(){
        String datos = "Nombre: " + getNombre() + "\n" +
                       "Correo: " + getCorreo() + "\n" +
                       "Contraseña: " + getContraseña() + "\n" +
                       "Categoria: " + getCategoria() + "\n" +
                       "Tipo: " + isTipo() + "\n";
        return datos;
    }

    
    
}
