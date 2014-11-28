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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CuentaBancaria retirarDe = new CuentaBancaria("Keny", "VIP", 2136542);
        CuentaBancaria depositTo = new CuentaBancaria("Kenhi", "VIP", 2136542);
        
        System.out.println(retirarDe.toString());
        System.out.println(depositTo.toString());
        
        retirarDe.transferencia(600, depositTo);
        
        System.out.println(retirarDe.toString());
        System.out.println(depositTo.toString());
    }
    
}
