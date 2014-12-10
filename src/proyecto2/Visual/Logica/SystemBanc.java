/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.Visual.Logica;


/**
 *
 * @author Rolando
 */
public class SystemBanc {

    public static Banco bank = new Banco();
    
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
//            Login();
        }
    }
    
    public static boolean Login(String user, String pass){
        return bank.login(user, pass);
    }
    
    public static boolean callsMenuBank(int opt, Usuario user, CuentaBancaria account, CuentaBancaria account2){
            switch(opt){
                case 1: //Agregar una cuenta.
                    return bank.addAccount(account);
                case 2: //Depositar en cuenta.
                    //bank.depositBalance();
                    break;
                case 3: //Retirar de Cuenta.
                    //bank.removeBalance();
                    break;
                case 4: //Registrar Intereses.
                    bank.recordInterest();
                    break;
                case 5: //Transferencia a Terceros.
                    //bank.trasferBalance();
                    break;
                case 6: //Desactivar cuentas.
                    bank.lookAccount();
                    break;
                case 7: //Cancelar Cuenta.
                    bank.cancelAccount();
                    break;
                case 8: //Crear Usuarios
                    return bank.createUser(user);
                case 9: //Reportes
                    bank.records();
                    break;
                case 10: //Cerrar Sesión
                    bank.logout();
                    break;
                case 11: //Salir
                    System.exit(0);
                    break;
            }
            return false;
    }
    
    public static String title(){
        return ("       * *                     * *\n"+
        "  ******                   ******\n"+
        " *** *                     *** *\n"+
        "  **** YSTEM BANKERO ****\n"+
        "  * ***                    * ***\n"+
        "******                     ******\n"+
        "  * *                      * *");
    }
    
}
