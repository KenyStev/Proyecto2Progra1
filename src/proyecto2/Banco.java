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
    
    public int menu(){
        System.out.println("\n*****   MENU BANCO   *****");
        System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s: ",
                "1- Agregar una cuenta.","2- Depositar en cuenta.","3- Retirar de Cuenta.",
                "4- Registrar Intereses.","5- Transferencia a Terceros.","6- Desactivar cuentas.",
                "7- Cancelar Cuenta.","8- Crear Usuarios","9- Reportes","10- Cerrar Sesión","11- Salir",
                "Escoja su Opcion: ");
        return scan.nextInt();
    }
    
    public int recordsMenu(){
        System.out.println("---------MENU DE REPORTES---------");
        System.out.printf("%s\n%s\n%s\n%s\n%s\n%s: ",
                "1- Lista de Cuentas.","2- Estadistica.","3- Actividades.",
                "4- Mi Perfil.","5- Regresar al Menu Principal.","Escoja su Opcion: ");
        return scan.nextInt();
    }
    
    public boolean login(){
        boolean state = false;
        System.out.print("Ingrese nombre de Usuario: ");
        String user= scan.next();
        System.out.print("Ingrese pasword: ");
        String pass= scan.next();
        
        for (Usuario usuario : users) {
            if(usuario != null && usuario.getEmail().equals(user)
                    && usuario.getPassword().equals(pass)){
                activo = usuario;
                state = true;
                break;
            }
        }
        return state;
//        if(activo==null){
//            System.out.println("Usuario o contrasenia Incorrecta!");
//        }
    }
    
    public void logout(){
        activo = null;
    }
    /**
     * Si el usuario actual es de tipo administrador y la cantidad de usuarios es menor de 50
     * pide ingresar el email, nombre, password y tipo para crear un nuevo usuario.
     */
    public void createUser(){
        String email, nombre, pass, tipo;
        boolean ciclo;
        if(activo.validateTipo("Administrador") && counterOfUsers < 50){
            do{ System.out.print("Ingrese su email: ");
            email = scan.next();
            ciclo = false;
                for (int x=0; x<counterOfUsers; x++) {
                    if(users[x].getEmail().equals(email)){
                    ciclo = true;
                        System.out.println("Ese email ya existe");
                    }
                }
            }while(ciclo);
            System.out.print("Ingrese su nombre completo: ");
            nombre = scan.next();
            System.out.print("Ingrese el password: ");
            pass = scan.next();
            System.out.println("Ingrese el tipo de usuario: ");
            tipo = scan.next();
            users[counterOfUsers++] = new Usuario(email, nombre, pass, tipo);
        }
        else{
            System.out.println("No se permite ingresar un nuevo usuario");
        }
    }
    
    public void records(){
        boolean bul = true;
        do{ switch(recordsMenu()){
            case 1:
                break;
            case 2:
                break;
            case 3: 
                break;
            case 4: activo.Profile();
                break;
            case 5: //Regresar al menu principal
                bul = false;
        }
        }while(bul);
    }
}
