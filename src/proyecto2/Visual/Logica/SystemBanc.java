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
    
    public static boolean Login(String user, String pass){
        return bank.login(user, pass);
    }
    
    public static void logout(){
        bank.logout();
    }
    
    public static Usuario getUser(){
        return bank.getActivo();
    }
    
    public static CuentaBancaria searchAccount(int code){
        return bank.searchAccount(code);
    }
    
    public static boolean lookAccount(CuentaBancaria account){
        if(bank.lookAccount(account.getNum())){
            return true;
        }
        return false;
    }
    
    public static boolean createUser(Usuario user){
        return bank.createUser(user);
    }
    
    public static boolean validateUser(String tipo){
        return bank.getActivo().validateTipo(tipo);
    }
    
    public static boolean addAccount(CuentaBancaria account){
        return bank.addAccount(account);
    }
    
    public static boolean callsMenuBank(int opt, Usuario user, CuentaBancaria account, CuentaBancaria account2, int num){
            switch(opt){
                case 1: //Agregar una cuenta.
//                    return bank.addAccount(account);
                case 2: //Depositar en cuenta.
                    //bank.depositBalance();
                    break;
                case 3: //Retirar de Cuenta.
                    //bank.removeBalance();
                    break;
                case 4: //Registrar Intereses.
//                    bank.recordInterest();
                    break;
                case 5: //Transferencia a Terceros.
                    //bank.trasferBalance();
                    break;
                case 6: //Desactivar cuentas.
//                    return bank.lookAccount(num);
                case 7: //Cancelar Cuenta.
                    //bank.cancelAccount();
                    break;
                case 8: //Crear Usuarios
//                    return bank.createUser(user);
                case 9: //Reportes
//                    bank.records();
                    break;
                case 10: //Cerrar Sesión
//                    bank.logout();
                    break;
                case 11: //Salir
//                    System.exit(0);
                    break;
            }
            return false;
    }
    
}
