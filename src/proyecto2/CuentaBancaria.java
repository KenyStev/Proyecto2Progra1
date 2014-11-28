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

    public CuentaBancaria(String name, String tipo, int numero) {
        this.name = name;
        this.tipo = tipo;
        this.numero = numero;
        switch(tipo){
            case "PLANILLA": tasaInteres=0; break;
            case "NORMAL": tasaInteres=0.02; break;
            case "VIP": tasaInteres=0.04; break;
        }
        saldo = 500;
        fechaCreacion = Date.from(Instant.now());
        activa = true;
    }
    
    public void addSaldo(double saldo){
        if(activa){
         this.saldo+=saldo;   
        }else{
            this.saldo=(saldo-(saldo*0.2));
            activa = true;
        }
    }
    
    public boolean retirarSaldo(double monto){
        boolean retirado = false;
        if(monto<saldo){
            saldo-=monto;
            retirado = true;
        }
        return retirado;
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
    /*-----------------*/
    
    @Override
    public String toString(){
        return numero + " - " + name + " - " + saldo + " - " + tipo + " - " + fechaCreacion.toString();
    }
}
