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
        users[counterOfUsers++] = new Usuario("admin@bank.com", "soyeladmin", "Keny", Usuario.ADMINISTRADOR);
    }
    
    public int menu(){
        System.out.println("\n\033[36m*****   MENU BANCO   *****");
        System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s: ",
                "1- Agregar una cuenta.","2- Depositar en cuenta.","3- Retirar de Cuenta.",
                "4- Registrar Intereses.","5- Transferencia a Terceros.","6- Desactivar cuentas.",
                "7- Cancelar Cuenta.","8- Crear Usuarios","9- Reportes","10- Cerrar Sesión","11- Salir",
                "Escoja su Opcion");
        return scan.nextInt();
    }
    
    public int recordsMenu(){
        System.out.println("---------MENU DE REPORTES---------");
        System.out.printf("%s\n%s\n%s\n%s\n%s\n%s: ",
                "1- Lista de Cuentas.","2- Estadistica.","3- Actividades.",
                "4- Mi Perfil.","5- Regresar al Menu Principal.","Escoja su Opcion");
        return scan.nextInt();
    }
    
    /**
     * Verifica que exista un usuario con el nombre y contrasenias dadas
     * y si lo hay, entonces hace el login
     * @return true if was logged or false if wasn't logged
     */
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
    }
    
    /**
     * Cierra sesion que esta abierta con el perfil de usuario activo
     */
    public void logout(){
        reemplazarUser();
        activo = null;
    }
    
    /**
     * Valida que la cuenta exista en el arreglo de cuentas
     * @param numero
     * @return 
     */
    public boolean validarCuenta(int numero){
        boolean existe = false;
        for (CuentaBancaria cuenta : cuentas) {
            if(cuenta != null && cuenta.validarCuenta(numero)){
                existe=true;
                break;
            }
        }
        return existe;
    }
    
    /**
     * Reemplaza el usuario activo en el espacio de donde salio
     * en caso de que haya sido modificado el perfil
     */
    public void reemplazarUser(){
        for (int i = 0; i < users.length; i++) {
            if(activo.getEmail().equals(users[i].getEmail())){
                users[i] = activo;
                break;
            }
        }
    }

    /**
     * Si el usuario actual es de tipo administrador y la cantidad de usuarios es menor de 50
     * pide ingresar el email, nombre, password y tipo para crear un nuevo usuario.
     */
    public void createUser(){
        String email, nombre, pass, tipo;
        boolean ciclo;
        if(activo.validateTipo(Usuario.ADMINISTRADOR) && counterOfUsers < 50){
            do{ System.out.print("Ingrese su email: ");
            email = scan.next();
            ciclo = false;
                for (int x=0; x<counterOfUsers; x++) {
                    if(users[x].getEmail().equals(email)){
                    ciclo = true;
                        System.err.println("Ese email ya existe");
                    }
                }
            }while(ciclo);
            System.out.print("Ingrese su nombre completo: ");
            nombre = scan.next();
            System.out.print("Ingrese el password: ");
            pass = scan.next();
            System.out.println("Ingrese el tipo de usuario: ");
            tipo = scan.next();
            users[counterOfUsers++] = new Usuario(email, pass, nombre, tipo);
        }
        else{
            System.err.println("No se permite ingresar un nuevo usuario");
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
    
    /**
     * Agrega una cueanta bancaria en la primera posicion vacia que encuentra
     * si no encuentra una posicion vacia es porque el arreglo estaba lleno
     * @param nombre
     * @param tipo
     * @param numero
     * @return true iff was added
     */
    public boolean add(String nombre, String tipo, int numero){
        boolean state = false;
        for (int i = 0; i < cuentas.length; i++) {
            if(cuentas[i]==null){
                cuentas[i] = new CuentaBancaria(nombre, tipo, numero);
                state = true;
                break;
            }
        }
        return state;
    }
    
    /**
     * Hace todo el proceso para agregar una nueva cuenta bancaria
     * @return true if was added
     */
    public boolean addAccount(){
        boolean done = true;
        if(!activo.validateTipo(Usuario.LIMITADO)){
            System.out.print("Ingrese el Nombre del Cliente: ");
            String nombre = scan.next();
            int num;
            do{
                System.out.print("Ingrese numero de Cuenta: ");
                num = scan.nextInt();
                
                if(num==-1){
                    break;
                }
                
                if(!validarCuenta(num)){
                    String tipo = CuentaBancaria.selectAccountType();
                    boolean agregada = add(nombre, tipo, num); //intenta agregar la cuenta y debuelbe true si fue agregada
                    if(!agregada){ //Si no fue agregar es porque esta lleno el arreglo
                        System.err.println("Limite de cuentas Lleno!");
                        break;
                    }
                    done = false;
                }else{
                    System.err.println("Ya existe una cuenta con ese numero!");
                }
            }while(done);
        }else{
            System.err.println("No Tiene Permiso para Agregar cuenta Profesor!");
        }
        return !done;
    }
    
    /**
     * Especificamente hace el deposito en la cuenta, y tiene todos los permisos para hacerlo
     * siempre y cuando la cuenta exista.
     * @param num numero de la cuenta bancaria
     * @param monto monto a depositar
     */
    public void deposit(int num, double monto){
        for (int i = 0; i < cuentas.length; i++) {
            if(cuentas[i] != null && cuentas[i].validarCuenta(num)){
                cuentas[i].addSaldo(monto);
                System.out.printf("Depositado: %.2f en cuenta: %d: ", monto, num);
                break;
            }
        }
    }
    
    /**
     * Metodo que se encarga de hacer todo el proceso de deposito
     * @return true if was deposited
     */
    public boolean depositBalance(){
        boolean state = false;
        System.out.print("Ingrese el Numero de Cuenta: ");
        int num = scan.nextInt();
        if(validarCuenta(num)){
            System.out.print("Ingrese el monto a depositar: ");
            double monto = scan.nextDouble();
            deposit(num, monto);
        }else{
            System.out.println("\033[31mLa Cuenta no Existe!");
        }
        return state;
    }
}
