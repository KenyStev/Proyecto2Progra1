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
    private Usuario users[], activo;
    private int counterOfUsers=0;
    
    public Banco() {
        this.activo = null;
        cuentas = new CuentaBancaria[200];
        users = new Usuario[50];
        users[counterOfUsers++] = new Usuario("admin@bank.com", "soyeladmin", "Ing.Goches", Usuario.ADMINISTRADOR);
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
     * y lo reemplaza en la posicion de la que salio
     */
    public void logout(){
        reemplazarUser();
        activo = null;
    }
    
    /**
     * Busca si la cuenta existe y devuelve el numero de la posicion de la en el arreglo se cuentas
     * sino existe devuelve -1
     * @param numero espera el numero de la cuenta
     * @param activa espera el tipo de cuenta que desea validar si es true son las activas sino son todas;
     * @return index of position or -1 if don't exist
     */
    public int validarIndex(int numero, boolean activa){
        int index = -1;
        if(activa){
//            if(validarCuentaActiva(numero)){
            for (int i = 0; i<cuentas.length; i++) {
                if(cuentas[i] != null && cuentas[i].validarCuenta(numero) && cuentas[i].getState()){
                    index=i;
                    break;
                }
            }
//            }
        }else{
//            if(validarCuenta(numero)){
            for (int i = 0; i<cuentas.length; i++) {
                if(cuentas[i] != null && cuentas[i].validarCuenta(numero)){
                    index=i;
                    break;
                }
            }
//            }
        }
        return index;
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
    
    public boolean validarCuentaActiva(int numero){
        for (CuentaBancaria cuenta : cuentas) {
            if(cuenta != null && cuenta.validarCuenta(numero) && cuenta.getState()){
                return true;
            }
        }
        return false;
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
            tipo = Usuario.selectUserType();
            users[counterOfUsers++] = new Usuario(email, pass, nombre, tipo);
        }
        else{
            System.out.println("\033[31mNo se permite ingresar un nuevo usuario");
        }
    }
    private void searchUser(boolean estado){
        for(CuentaBancaria temp : cuentas){
            if(temp!=null && temp.getState()==estado){
                temp.toString();
            }
        }
    }
    /**
     * Mustra los datos de codigo-nombre-saldo-tipo-fecha_creacion de cada cuenta bancaria
     * activas o desactivadas segun escoja el usuario
     */        
    public void listAccounts(){
        System.out.println("Quiere listar \n1-Activas \n2-Desactivadas ");
        int type = scan.nextInt();
        switch(type){
            case 1: searchUser(true);
            break;
            case 2: searchUser(false);
            break;
            default: System.out.println("Tipo de cuenta invalido");
        }
    }
    
    private int searchAccount(String type){
        int cant=0;
        for(CuentaBancaria temp: cuentas){
            if(temp!=null && temp.getTipo().equals(type) && temp.getState()==true){
            cant++;
            }
        }
        return cant;
    }
    /**
     * Muestra el total de cuentas Planilla, Normal y VIP que el banco tiene activas
     */
    public void showAccounts(){
        System.out.println("Total de cuentas Planilla: "+searchAccount("PLANILLA"));
        System.out.println("Total de cuentas Normal: "+searchAccount("NORMAL"));
        System.out.println("Total de cuentas VIP: "+searchAccount("VIP"));
    }
    /**
     * Mustra la suma en lempiras de los depositos hechos por tidas las cuentas
     */
    public void totalMontoDepositos(){
        int suma = 0;
        for(CuentaBancaria temp : cuentas){
            if(temp!=null){
                suma += temp.getMontoDepositosHechos();
            }
        }
        System.out.println("Total de lempiras depositados: "+suma);
    }
    /**
     * Calcula la cantidad total de depositos o retiros hechos y lo imprime en pantalla
     * @param retDep 
     */
    public void totalRetDep(String retDep){
        int suma = 0;
        for(CuentaBancaria temp: cuentas){
            if(temp!=null){
                switch(retDep){
                    case "retiros": suma += temp.getRetirosHechos();
                        break;
                    case "depositos": suma += temp.getDepositosHechos();
                }
            }
        }
        System.out.println("Total de "+retDep+" hechos: "+suma);
    }
    /**
     * Calcula el total de transferencias hechas y su total en lempiras transferidos y lo imprime en pantalla
     */
    public void totalTrans(){
        int suma= 0, sumaMonto = 0;
        for(CuentaBancaria temp : cuentas){
            if(temp!=null){
                suma += temp.getTransHechas();
                sumaMonto += temp.getMontoTransHechas();
            }
        }
        System.out.println("Total de transferencias hechas: "+suma+"\nTotal en "
                + "lempiras transferidos: "+sumaMonto+"lps.");
    }
    
    /**
     * Muestra un menu para ver los reportes del banco
     */
    public void records(){
        boolean bul = true;
        do{ switch(recordsMenu()){
            case 1: listAccounts();
                break;
            case 2: showAccounts();
                    totalMontoDepositos();
                break;
            case 3:totalRetDep("retiros");
                   totalRetDep("depositos");
                   totalTrans();
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
    private boolean add(String nombre, String tipo, int numero){
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
        boolean agregada = false;
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
                    agregada = add(nombre, tipo, num); //intenta agregar la cuenta y devuelve true si fue agregada
                    if(!agregada){ //Si no fue agregar es porque esta lleno el arreglo
                        System.out.println("\033[31mLimite de cuentas Lleno!");
                        break;
                    }
                }else{
                    System.out.println("\033[31mYa existe una cuenta con ese numero!");
                }
            }while(!agregada);
        }else{
            System.out.println("\033[31mNo Tiene Permiso para Agregar cuenta Ingeniero!");
        }
        return agregada;
    }
    
    /**
     * Especificamente hace el deposito en la cuenta, y tiene todos los permisos para hacerlo
     * siempre y cuando la cuenta exista.
     * @param num numero de la cuenta bancaria
     * @param monto monto a depositar
     */
    private boolean deposit(int index, double monto){
        cuentas[index].addSaldo(monto, true);
        System.out.printf("Depositado: %.2f en cuenta: %d. Saldo Actual: %.2f\n",
                monto, cuentas[index].getNum(), cuentas[index].getSaldo());
        return true;
    }
    
    /**
     * Metodo que se encarga de hacer todo el proceso de deposito
     * @return true if was deposited
     */
    public boolean depositBalance(){
        boolean state = false;
        do{
            System.out.print("Ingrese el Numero de Cuenta: ");
            int num = scan.nextInt();
            if(num==-1){
                break;
            }
            int index = validarIndex(num, state);
            if(index>=0){
                System.out.print("Ingrese el monto a depositar: ");
                double monto = scan.nextDouble();
                state = deposit(index, monto);
            }else{
                System.out.println("\033[31mLa Cuenta no Existe!");
            }
        }while(!state);
        return state;
    }
    
    private boolean remove(int index, double monto){
        boolean removed = cuentas[index].retirarSaldo(monto);
        if(removed){
            System.out.printf("Retirados: %.2f de la cuenta: %d. Saldo Actual: %.2f\n",
                    monto, cuentas[index].getNum(), cuentas[index].getSaldo());
        }else{
            System.out.println("\033[31mMonto mayor al saldo disponible!");
        }
        return removed;
    }
    
    public boolean removeBalance(){
        boolean state=false;
        if(!activo.validateTipo(Usuario.LIMITADO)){
            do{
                System.out.print("Ingrese el Numero de la Cuenta: ");
                int num = scan.nextInt();
                if(num==-1){
                    break;
                }
                int index = validarIndex(num, !state);
                if(index>=0){
                    System.out.print("Ingrese el monto a Retirar: ");
                    double monto = scan.nextDouble();
                    state = remove(index, monto);
                }else{
                    System.out.println("\033[31mNumero de cuenta no valido!");
                }
            }while(!state);
        }else{
            System.out.println("\033[31mNo tiene permisos para retirar dinero Ingeniero!");
        }
        return state;
    }
}
