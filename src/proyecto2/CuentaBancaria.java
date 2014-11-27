/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

/**
 *
 * @author zerokull
 */
public class CuentaBancaria {
    private String name, tipo;
    private int numero;
    private double tasaInteres, saldo;
    private boolean activa;

    public CuentaBancaria(String name, String tipo, int numero, double tasaInteres) {
        this.name = name;
        this.tipo = tipo;
        this.numero = numero;
        this.tasaInteres = tasaInteres;
        saldo = 500;
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
    
    /*Funciones Set*/
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
    
    /*Funciones Get*/
    public int getNum(){
        return numero;
    }
    
    public double getSaldo(){
        return saldo;
    }
}
