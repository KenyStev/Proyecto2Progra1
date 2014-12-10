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
    
    public static boolean depositBalance(int code, double monto){
        return bank.depositBalance(code, monto);
    }
    
    public static boolean removeBalance(int code, double monto){
        return bank.removeBalance(code, monto);
    }
    
    public static boolean transferBalance(int origen, int destino, double monto){
        return bank.trasferBalance(origen, destino, monto);
    }
    
}
