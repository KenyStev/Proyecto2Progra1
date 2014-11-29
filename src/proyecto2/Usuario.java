/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author Rolando
 */
public class Usuario {
    private String email, password, name, tipo;
    private Date fecha;
    public static String ADMINISTRADOR = "administrador",
            CONTENIDO = "contenido", LIMITADO = "limitado";
    Scanner scan = new Scanner(System.in);
    
    /**
     * 
     * @param email
     * @param pass
     * @param nombre
     * @param tipo 
     */
    public Usuario(String email, String pass, String nombre, String tipo){
        this.email = email;
        this.password = pass;
        this.name = nombre;
        this.tipo = tipo;
        fecha = Date.from(Instant.now());
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return name;
    }
    /**
     * Indica si el usuario es de el tipo del parametro
     * @param tipo
     * @return true si el tipo de usuario es igual al tipo del parametro
     */
    public boolean validateTipo(String tipo){
        boolean validate = tipo.equals(this.tipo);
        return validate;
    }
    /**
     * Ingresa una ontraseña y valida sea la del usuario
     * @return true si las contraseñas coinciden
     */
    public boolean pass(){
      boolean access=false;
     System.out.print("Ingrese su password: ");
     String pass = scan.next();
     if(pass.equals(password)){
     access = true;
     }
     return access;                
    }
    /**
     * Ingresa un email y valida que sea el del usuario
     * @return true si los emails coinciden
     */
    public boolean emailPass(){
    boolean access = false;
    System.out.print("Ingrese su email de Usuario");
    String namePass = scan.next();
    if(namePass.equals(email)){
    access = true;
    }
    return access;    
    }
    /**
     * Pide que se ingrese el email y password del usuario 
     * @return true si abmos coinciden con los del usuario 
     */
    public boolean access(){
    boolean access = false;
    if(pass() && emailPass()){
    access = true;
    }
    return access;
    }
    /**
     * Muestra un submenu para cambiar el email, password y nombre del usuario
     */
    public void Profile(){
        int opt = 0;
        String pass;
        System.out.println("Email: "+email+", Nombre: "+name+", Fecha de ingreso: "+fecha);
        do{
            System.out.println("\t1.Cambiar mi email");
            System.out.println("\t2.Cambiar mi password");
            System.out.println("\t3.Cambiar mi nombre");
            System.out.println("\t4.Regresar al menu de Reportes");
            System.out.print("Escoja su opcion: ");
            opt = scan.nextInt();
            switch(opt){
                case 1: if(pass()){
                            System.out.print("Ingresa el nuevo email: ");
                            setEmail(scan.next());
                        }
                        else{
                            System.out.println("Password Incorrecto");
                        }
                break;
                case 2: if(pass()){
                            System.out.print("Ingrese el nuevo password");
                            setPassword(scan.next());
                        }
                        else{
                            System.out.println("Password Incorrecto");    
                        }
                break;
                case 3: if(pass()){
                            System.out.print("Ingrese el nuevo nombre");
                            setName(scan.next());
                        }
                        else{
                            System.out.println("Password Incorrecto");    
                        }
                break;
                case 4: //salir
            }
    }while(opt!=4);
    }
}
