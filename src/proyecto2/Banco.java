/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.util.Scanner;

/**
 *
 * @author zerokull
 */
public class Banco {
    private Scanner scan = new Scanner(System.in);
    private CuentaBancaria cuentas[];
    private Usuario users[]; //Se pueden eliminar usuarios?
    private Usuario activo;
    private int counterOfUsers=0;

    public Banco() {
        this.activo = null;
        cuentas = new CuentaBancaria[200];
        users = new Usuario[50];
        users[counterOfUsers++] = new Usuario("admin@bank.com", "soyeladmin", "Keny", "Administrador"); //Crear variables static del tipo deusuario en class usuario.
    }
    
    public void menu(){
        System.out.println("*****   MENU BANCO   *****");
        System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s: ",
                "1- Agregar una cuenta.","2- Depositar en cuenta.","3- Retirar de Cuenta.",
                "4- Registrar Intereses.","5- Transferencia a Terceros.","6- Desactivar cuentas.",
                "7- Cancelar Cuenta.","8- Crear Usuarios","9- Reportes","10- Cerrar Sesión","11- Salir");
    }
    
    public void login(){
        System.out.print("Ingrese nombre de Usuario: ");
        String user= scan.next();
        System.out.print("Ingrese pasword: ");
        String pass= scan.next();
        
        for (Usuario usuario : users) {
            if(usuario != null && usuario.getNombre().equals(user) 
                    && usuario.getPassword().equals(pass)){
                activo = usuario;
                break;
            }
        }
        if(activo==null){
            System.out.println("Usuario o contrasenia Incorrecta!");
        }
    }
    
    public void logout(){
        activo = null;
    }
}
