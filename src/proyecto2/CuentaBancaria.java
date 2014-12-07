/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author zerokull
 */
public class CuentaBancaria {
    private String name, tipo;
    private int numero;
    private double tasaInteres, saldo;
    private boolean activa;
    private Date fechaCreacion;
    public static int retirosHechos=0, depositosHechos=0, transHechas=0;
    public static double montoRetiros=0, montoDepositos=0, montoTrans=0;
    
    public static String PLANILLA="PLANILLA", NORMAL="NORMAL", VIP="VIP";

    /**
     * Construye un nuevo objeto de tipo CuentaBancaria con sus datos especificos
     * @param name nombre del duenio de la cuenta
     * @param tipo tipo de la cuenta 
     * @param numero 
     */
    public CuentaBancaria(String name, String tipo, int numero) {
        this.name = name;
        this.tipo = tipo;
        this.numero = numero;
        switch(tipo){
            case "PLANILLA": tasaInteres=0; break;
            case "NORMAL": tasaInteres=0.02; break;
            case "VIP": tasaInteres=0.04; break;
        }
        setSaldo(500);
        fechaCreacion = Date.from(Instant.now());
        active();
    }
    
    /**
     * Muestra un menu que permite seleccionar un tipo de cuenta bancaria
     * y retorna el tipo seleccionado
     * @return a String with the type of the account selected
     */
    public static String selectAccountType(){
        Scanner scan = new Scanner(System.in);
        do{
            System.out.printf("\n***TIPOS DE CUENTA***\n1-%s\n2-%s\n3-%s\nEscoja uno: ",
                PLANILLA, NORMAL, VIP);
            int opt = scan.nextInt();
            switch(opt){
                case 1: return PLANILLA;
                case 2: return NORMAL;
                case 3: return VIP;
                default: System.out.println("\033[31mOpcion Incorrecta!");
            }
        }while(true);
    }
    
    /**
     * Agrega saldo a la cuenta bancaria
     * @param monto
     */
    public void addSaldo(double monto, boolean registrarComoDeposito){
        if(activa){
         this.saldo+=monto;
         if(registrarComoDeposito){
             montoDepositos+=monto;
         }
        }else{
            saldo+=(monto-(monto*0.2));
            if(registrarComoDeposito){
                montoDepositos+=(monto-(monto*0.2));
            }
            active();
        }
        if(registrarComoDeposito){
            depositosHechos++;
        }
    }
    
    /**
     * Retira el monto dado de la centa siempre que sea menor
     * @param monto
     * @return true if was removed else false
     */
    public boolean retirarSaldo(double monto){
        boolean retirable = validarRetiro(monto);
        if(retirable){
            saldo-=monto;
            retirosHechos++;
            montoRetiros+=monto;
        }
        return retirable;
    }
    
    /**
     * 
     * @param monto
     * @param depositTo
     * @return 
     */
    public boolean transferencia(double monto, CuentaBancaria depositTo){
        boolean state = retirarSaldo(monto);
        if(state){
            depositTo.addSaldo(monto, true);
            transHechas++;
            montoTrans+=monto;
        }
        return state;
    }
    
    /**
     * 
     * @param monto
     * @return 
     */
    public boolean validarRetiro(double monto){
        return monto<saldo;
    }
    
    /**
     * Valida que el numero de la cuenta sea el mismo
     * @param numero
     * @return 
     */
    public boolean validarCuenta(int numero){
        return this.numero==numero;
    }
    
    /**
     * Valida que la cuenta sea del tipo dado en el parametro
     * @param type
     * @return 
     */
    public boolean validarTypeAccount(String type){
        return tipo.equals(type);
    }
    
    /**
     * Registra los intereses mandando false en el segundo parametro de la funcion porque
     * no lo toma como deposito
     */
    public void registrarInteres(){
        if(activa){
            double saldoViejo = saldo;
            addSaldo(saldo*tasaInteres, false);
            System.out.printf("\tIntereses en cuenta: %d; saldo: %.2f - tasa: %.2f - Intereses: %.2f - Total: %.2f\n",
                    numero, saldoViejo, tasaInteres, (saldoViejo*tasaInteres), saldo);
        }
    }
    
    /*Funciones Set*/
    private void setSaldo(double saldo){
        this.saldo = saldo;
    }
    /*-----------------*/
    
    /*Funciones Get*/
    public int getNum(){
        return numero;
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public boolean isActiva(){
        return activa;
    }

    public String getTipo() {
        return tipo;
    }
    
    
    public int getRetirosHechos(){
        return retirosHechos;
    }
    
    public int getDepositosHechos(){
        return depositosHechos;
    }
    
    public int getTransHechas(){
        return transHechas;
    }
    
    public double getMontoRetirosHechos(){
        return montoRetiros;
    }
    
    public double getMontoDepositosHechos(){
        return montoDepositos;
    }
    
    public double getMontoTransHechas(){
        return montoTrans;
    }
    /*-----------------*/
    
    private void active(){
        activa=true;
    }
    
    public void disable(){
        activa=false;
    }
    
    /**
     * Retorna los datos de la cuenta en el orden:
     * @return numero - name - saldo - tipo - fechaCreacion
     */
    @Override
    public String toString(){
        return numero + "   \t" + name + "\t\t" + saldo + "\t" + tipo + "   \t" + fechaCreacion.toString();
    }
}
