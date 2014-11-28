/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;
import java.time.Instant;
import java.util.Date;

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
    private int retirosHechos=0, depositosHechos=0, transHechas=0;
    private double montoRetiros=0, montoDepositos=0, montoTrans=0;
    
    private static String PLANILLA="PLANILLA", NORMAL="NORMAL", VIP="VIP";

    public CuentaBancaria(String name, String tipo, int numero) {
        this.name = name;
        this.tipo = tipo;
        this.numero = numero;
        switch(tipo){
            case "PLANILLA": tasaInteres=0; break;
            case "NORMAL": tasaInteres=0.02; break;
            case "VIP": tasaInteres=0.04; break;
        }
        saldo = 500; //cuenta como deposito?
        fechaCreacion = Date.from(Instant.now());
        activa = true;
    }
    /**
     * Agrega saldo a la cuenta bancaria
     * @param monto
     */
    public void addSaldo(double monto){
        if(activa){
         this.saldo+=monto;
         montoDepositos+=monto;
        }else{
            this.saldo+=(monto-(monto*0.2));
            montoDepositos+=(monto-(monto*0.2));
            activa = true;
        }
        depositosHechos++;
    }
    
    /**
     * Retira el monto dado de la centa siempre que sea menor
     * @param monto
     * @return true if was removed else false
     */
    public boolean retirarSaldo(double monto){
        boolean retirado = validarRetiro(monto);
        if(retirado){
            saldo-=monto;
            retirosHechos++;
            montoRetiros+=monto;
        }
        return retirado;
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
            depositTo.addSaldo(monto);
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
    
    /*Funciones Set*/
    public void setSaldo(double saldo){
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
    
    public boolean getState(){
        return activa;
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
    
    /**
     * Retorna los datos de la cuenta en el orden:
     * @return numero - name - saldo - tipo - fechaCreacion
     */
    @Override
    public String toString(){
        return numero + " - " + name + " - " + saldo + " - " + tipo + " - " + fechaCreacion.toString();
    }
}
