/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package proyecto2.Visual.Logica;

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
        scan.useDelimiter("\n");
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
    public boolean login(String user, String pass){
        boolean state = false;
        
        for (Usuario usuario : users) {
            if(usuario != null && usuario.access(pass, user)){
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
    private int searchIndex(int numero, boolean activa){
        int index = -1;
        if(activa){
            for (int i = 0; i<cuentas.length; i++) {
                if(cuentas[i] != null && cuentas[i].validarCuenta(numero) && cuentas[i].isActiva()){
                    index=i;
                    break;
                }
            }
            
        }else{
            for (int i = 0; i<cuentas.length; i++) {
                if(cuentas[i] != null && cuentas[i].validarCuenta(numero)){
                    index=i;
                    break;
                }
            }
        }
        return index;
    }
    
    /**
     * Valida que la cuenta exista en el arreglo de cuentas
     * @param numero
     * @return
     */
    private boolean validarCuenta(int numero){
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
    private void reemplazarUser(){
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
    public boolean createUser(Usuario user){
        
        if(counterOfUsers < 50){
            for (int x=0; x<counterOfUsers; x++) {
                if(users[x].getEmail().equals(user.getEmail())){
                    return false;
                }
            }
            users[counterOfUsers++] = new Usuario(user);
            return true;
        }
        return false;
    }
    
    private String search(boolean estado){
        String list = "Numero\tNombre\t\tSaldo\tTipo   \tFecha de Creacion\n";
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta != null && cuenta.isActiva() == estado) {
                list += cuenta.toString() + "\n";
            }
        }
        return list;
    }
    
    /**
     * Mustra los datos de codigo-nombre-saldo-tipo-fecha_creacion de cada cuenta bancaria
     * activas o desactivadas segun escoja el usuario
     */
    public String listAccounts(int type){
//        System.out.print("Que tipo quiere listar: \n1-Activas \n2-Desactivadas\nEscoja su Opcion: ");
//        int type = scan.nextInt();
        switch(type){
            case 1: return search(true);
//            break;
            case 2: return search(false);
//            break;
            default: return null;
        }
    }
    
    private int searchAccount(String type){
        int cant=0;
        for(CuentaBancaria temp: cuentas){
            if(temp!=null && temp.validarTypeAccount(type) && temp.isActiva()){
                cant++;
            }
        }
        return cant;
    }
    
    /**
     * Muestra el total de cuentas Planilla, Normal y VIP que el banco tiene activas
     */
    public String showAccounts(){
        String list = "";
        list+="Total de cuentas Planilla: "+searchAccount(CuentaBancaria.PLANILLA);
        list+="\nTotal de cuentas Normal: "+searchAccount(CuentaBancaria.NORMAL);
        list+="\nTotal de cuentas VIP: "+searchAccount(CuentaBancaria.VIP);
        list+="\nTotal de lempiras depositados: "+CuentaBancaria.montoDepositos;
        return list;
    }
    
    /**
     * Calcula la cantidad total de depositos o retiros hechos y lo imprime en pantalla
     * @param retDep
     */
    public String totalRetDep(String retDep){
        String list="";
        switch(retDep){
            case "retiros":
                list="Total de "+retDep+" hechos: "+CuentaBancaria.retirosHechos+"\nTotal en lempiras "
                        + retDep + ": "+CuentaBancaria.montoRetiros+" lps.";
                break;
            case "depositos":
                list="Total de "+retDep+" hechos: "+CuentaBancaria.depositosHechos+"\nTotal en lempiras "
                        + retDep + ": "+CuentaBancaria.montoDepositos+" lps.";
                break;
            case "transferencias":
                list="Total de "+retDep+" hechos: "+CuentaBancaria.transHechas+"\nTotal en lempiras "
                        + retDep + ": "+CuentaBancaria.montoTrans+" lps.";
        }
        return list;
    }
    
    public String activitis(){
        String list = totalRetDep("retiros") + "\n";
        list += totalRetDep("depositos") + "\n";
        list += totalRetDep("transferencias");
        return list;
    }
    
    /**
     * Muestra un menu para ver los reportes del banco
     */
    public void records(){
        
//        boolean bul = true;
//        do{ 
//            switch(recordsMenu()){
//            case 1: listAccounts();
//            break;
//            case 2: showAccounts();
//            break;
//            case 3:
//            totalRetDep("retiros");
//            totalRetDep("depositos");
//            totalRetDep("transferencias");
//            break;
//            case 4: activo.Profile();
//            break;
//            case 5: //Regresar al menu principal
//                bul = false;
//        }
//        }while(bul);
    }
    
    /**
     * Funcion privada que agrega una cueanta bancaria en la primera posicion vacia que encuentra
     * si no encuentra una posicion vacia es porque el arreglo estaba lleno, tiene todos los permisos para agregar cuentas;
     * @param nombre nombre del duenio de la cuenta
     * @param tipo tipo de cuenta creada
     * @param numero numero de la cuenta
     * @return true iff was added
     */
    private boolean add(CuentaBancaria account){
        boolean state = false;
        for (int i = 0; i < cuentas.length; i++) {
            if(cuentas[i]==null){
                cuentas[i] = new CuentaBancaria(account);
                state = true;
                break;
            }
        }
        return state;
    }
    
    /**
     * Hace todo el proceso para agregar una nueva cuenta bancaria
     */
    public boolean addAccount(CuentaBancaria account){
        if(!activo.validateTipo(Usuario.LIMITADO)){
            if(account.getNum()<1){
                return false;
            }
            if(!validarCuenta(account.getNum())){
                if(add(account)){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Funcion privada que hace el deposito en la cuenta, y tiene todos los permisos para hacerlo.
     * solo puede ser usada por el banco.
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
     */
    public void depositBalance(){
        boolean state = false;
        double monto;
        do{
            System.out.print("Ingrese el Numero de Cuenta: ");
            int num = scan.nextInt();
            if(num==-1){
                break;
            }
            int index = searchIndex(num, state);
            if(index>=0){
                do{
                    System.out.print("Ingrese el monto a depositar: ");
                    monto = scan.nextDouble();
                    if(monto >= 1){
                        break;
                    }
                    else{
                        System.out.println("El monto debe ser un numero positivo!");
                    }
                }while(true);
                state = deposit(index, monto);
            }else{
                System.out.println("\033[31mLa Cuenta no Existe!");
            }
        }while(!state);
    }
    
    /**
     * Funcion privada de uso exclusivo del banco para retirar dinero de la cuenta
     * con todos los permisos.
     * @param index indice de la cuenta a la que se la va a retirar el monto
     * @param monto el monot a ser retirado
     * @return true if was removed (is remove if there are approch money)
     */
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
    
    /**
     * Hace el proceso para remover dinero de una cuenta vancaria
     * validando todo lo que sea necesario.
     */
    public void removeBalance(){
        boolean state=false;
        double monto;
        if(!activo.validateTipo(Usuario.LIMITADO)){
            do{
                System.out.print("Ingrese el Numero de la Cuenta: ");
                int num = scan.nextInt();
                if(num==-1){
                    break;
                }
                int index = searchIndex(num, !state);
                if(index>=0){
                    do{
                        System.out.print("Ingrese el monto a Retirar: ");
                        monto = scan.nextDouble();
                        if(monto >=1 ){
                            break;
                        }
                        else{
                            System.out.println("El monto debe ser un numero positivo");
                        }
                    }while(true);
                    state = remove(index, monto);
                }else{
                    System.out.println("\033[31mNumero de cuenta no valido!");
                }
            }while(!state);
        }else{
            System.out.println("\033[31mNo tiene permisos para retirar dinero Ingeniero!");
        }
    }
    
    /**
     * Registra los intereses para todas las cuentas activas que tenga el banco
     */
    public String recordInterest(){
        String list="";
        for (int i = 0; i < cuentas.length; i++) {
            if(cuentas[i]!=null){
                list+=cuentas[i].registrarInteres();
            }
        }
        return list;
    }
    
    /**
     * Transfiere dinero de una cuenta a otra validando lo que sea necesario
     * como que usuario esta haciendo la transferencia y que halla suficiente dinero
     * para ser retirado
     */
    public void trasferBalance(){
        boolean state = false;
        double monto;
        if(!activo.validateTipo(Usuario.LIMITADO)){
            do{
                System.out.print("Numero de Cuenta Origen: ");
                int origen = scan.nextInt();
                if(origen==-1){break;}
                System.out.print("Numero de cuenta Destino: ");
                int destino = scan.nextInt();
                if(destino==-1){break;}
                
                if(origen==destino){
                    System.out.println("\033[31mLos numeros de cuenta deben ser distintos!");
                    continue;
                }
                
                int indexO = searchIndex(origen, !state);
                int indexD = searchIndex(destino, state);
                
                if(indexO>=0 && indexD>=0){
                    do{
                        System.out.print("Ingrese el monto a Transferir: ");
                        monto = scan.nextDouble();
                        if(monto >= 1){
                            break;
                        }
                        else{
                            System.out.println("El monto debe ser positivo!");
                        }
                    }while(true);
                    state = cuentas[indexO].transferencia(monto, cuentas[indexD]);
                    if(state){
                        System.out.printf("Transferidos: %.2f de %d a %d",
                                monto, origen, destino);
                    }else{
                        System.out.printf("\033[31mNo hay suficiente dinero para retirar de la cuenta origen! %d\n",
                                origen);
                    }
                }else{
                    System.out.println("\033[31mNumeros de Cuenta Incorrectos!");
                }
            }while(!state);
        }else{
            System.out.println("\033[31mNo tiene permisos para hacer trasferencias Ingeniero!");
        }
    }
    
    public boolean lookAccount(int num){
            int index = searchIndex(num, true);
            if(index>=0){
                    cuentas[index].disable();
                    return true;
            }
        return false;
    }
    
    public void cancelAccount(){
        if(activo.validateTipo(Usuario.ADMINISTRADOR)){
            System.out.print("Ingrese el numero de cuenta: ");
            int num = scan.nextInt();
            int index = searchIndex(num, false);
            if(index>=0){
                System.out.printf("Realmente desea cancelar la cuenta %d? (Si/No): ", cuentas[index].getNum());
                String resp = scan.next();
                if(resp.equalsIgnoreCase("si")){
                    System.out.println("Cancelada cuenta: "+cuentas[index].toString());
                    cuentas[index] = null;
                }
            }else{
                System.out.println("\033[31mLa cuenta no existe!");
            }
        }else{
            System.out.println("\033[31mNo tiene permisos para cancelar cuentas Ingeniero!");
        }
    }
    
    public Usuario getActivo(){
        return activo;
    }
}
