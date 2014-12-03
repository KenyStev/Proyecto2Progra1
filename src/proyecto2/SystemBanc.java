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
            System.out.println("\033[32m   * *                * *");
            System.out.println("\033[33m  ******             ******");
            System.out.println("\033[33m *** \033[32m*              \033[33m*** \033[32m*");
            System.out.println("\033[33m  **** \033[32mYSTEM BANKERO \033[33m****");
            System.out.println("\033[32m  * \033[33m***              \033[32m* \033[33m***");
            System.out.println("\033[33m******             ******");
            System.out.println("\033[32m  * *                * *");
            Login();
        }
    }
    
    public static void Login(){
        boolean state = bank.login();
        if(state){
            callsMenuBank();
        }else{
            System.err.println("\033[31mUsuario o contrasenia Incorrecto!");
        }
    }
    
    public static void callsMenuBank(){
        boolean state = true;
        do{
            int resp = bank.menu();
            switch(resp){
                case 1: //Agregar una cuenta.
                    bank.addAccount();
                    break;
                case 2: //Depositar en cuenta.
                    bank.depositBalance();
                    break;
                case 3: //Retirar de Cuenta.
                    bank.removeBalance();
                    break;
                case 4: //Registrar Intereses.
                    bank.recordInterest();
                    break;
                case 5: //Transferencia a Terceros.
                    bank.trasferBalance();
                    break;
                case 6: //Desactivar cuentas.
                    break;
                case 7: //Cancelar Cuenta.
                    break;
                case 8: //Crear Usuarios
                    bank.createUser();
                    break;
                case 9: //Reportes
                    bank.records();
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
