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
    private Usuario users[];
    private Usuario activo;

    public Banco() {
        this.activo = null;
        cuentas = new CuentaBancaria[200];
        users = new Usuario[50];
    }
    
    public void login(){
        System.out.print("Ingrese nombre de Usuario: ");
        String user= scan.next();
        System.out.print("Ingrese pasword: ");
        String pass= scan.next();
        
        for (Usuario usuario : users) {
//            if(usuario != null && usuario.getNombre().equals(user) 
//                    && usuario.getPassword().equals(pass)){
                activo = usuario;
                break;
//            }
        }
        if(activo==null){
            System.out.println("Usuario o contrasenia Incorrecta!");
        }
    }
    
    public void logout(){
        activo = null;
    }
}
