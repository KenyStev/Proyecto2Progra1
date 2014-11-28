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
       Usuario user = new Usuario("admin@bank.com", "soyeladmin", "Rolando","administrador" );
       user.Profile();
        System.out.println(user.password);
        System.out.println(user.email);
        System.out.println(user.name);
    }
    
}
