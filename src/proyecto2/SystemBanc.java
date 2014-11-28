/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

/**
 *
 * @author Rolando
 */
public class SystemBanc {

    static Banco bank = new Banco();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        while(true){
            Login();
        }
    }
    
    public static void Login(){
        boolean state = bank.login();
        if(state){
            callsMenuBank();
        }else{
            System.out.println("Usuario o contrasenia Incorrecto!");
        }
    }
    
    public static void callsMenuBank(){
        boolean state = true;
        do{
            int resp = bank.menu();
            switch(resp){
                case 1: //Agregar una cuenta.
                    break;
                case 2: //Depositar en cuenta.
                    break;
                case 3: //Retirar de Cuenta.
                    break;
                case 4: //Registrar Intereses.
                    break;
                case 5: //Transferencia a Terceros.
                    break;
                case 6: //Desactivar cuentas.
                    break;
                case 7: //Cancelar Cuenta.
                    break;
                case 8: //Crear Usuarios
                    break;
                case 9: //Reportes
                    break;
                case 10: //Cerrar Sesión
                    bank.logout();
                    state = false;
                    break;
                case 11: //Salir
                    System.exit(0);
                    break;
            }
        }while(state);
    }
    
}
