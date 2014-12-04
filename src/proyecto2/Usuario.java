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
    public static String ADMINISTRADOR = "ADMINISTRADOR",
            CONTENIDO = "CONTENIDO", LIMITADO = "LIMITADO";
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
     * @param password
     * @return true si las contraseñas coinciden
     */
    public boolean pass(String password){
        boolean access=false;
        if(password.equals(this.password)){
            access = true;
        }
        return access;
    }
    /**
     * Ingresa un email y valida que sea el del usuario
     * @param email
     * @return true si los emails coinciden
     */
    public boolean emailPass(String email){
        boolean access = false;
        if(email.equals(this.email)){
            access = true;
        }
        return access;
    }
    /**
     * Pide que se ingrese el email y password del usuario
     * @param pass
     * @param email
     * @return true si abmos coinciden con los del usuario
     */
    public boolean access(String pass, String email){
        boolean access = false;
        if(pass(pass) && emailPass(email)){
            access = true;
        }
        return access;
    }
    /**
     * Muestra un menu para que el usuario escoja el tipo de usuario
     * @return un String con el tipo de usuario seleccionado
     */
    public static String selectUserType(){
        Scanner scan = new Scanner(System.in);
        do{
            System.out.printf("\n***TIPOS DE USUARIO***\n1-%s\n2-%s\n3-%s\nEscoja uno: ",
                    ADMINISTRADOR, CONTENIDO, LIMITADO);
            int opt = scan.nextInt();
            switch(opt){
                case 1: return ADMINISTRADOR;
                case 2: return CONTENIDO;
                case 3: return LIMITADO;
                default: System.out.println("Opcion Incorrecta");
            }
        }while(true);
    }
    /**
     * Muestra un submenu para cambiar el email, password y nombre del usuario
     */
    public void Profile(){
        int opt = 0;
        System.out.println("Email: "+email+", Nombre: "+name+", Fecha de ingreso: "+fecha);
        do{
            System.out.println("\t1.Cambiar mi email");
            System.out.println("\t2.Cambiar mi password");
            System.out.println("\t3.Cambiar mi nombre");
            System.out.println("\t4.Regresar al menu de Reportes");
            System.out.print("Escoja su opcion: ");
            opt = scan.nextInt();
            if(opt==4){
               break;
            }
            else if(opt<1 || opt > 4){
                System.out.println("Opcion Invalida");
            }
            else{
                System.out.println("Ingrese su password");
                if(pass(scan.next())){
                    switch(opt){
                        case 1:     System.out.print("Ingresa el nuevo email: ");
                        setEmail(scan.next());
                        break;
                        case 2:     System.out.print("Ingrese el nuevo password");
                        setPassword(scan.next());
                        break;
                        case 3:     System.out.print("Ingrese el nuevo nombre");
                        setName(scan.next());
                        break;
                    }
                }
                else{
                    System.out.println("Password Incorrecto");
                }
            }
        }while(opt!=4);
    }
}
