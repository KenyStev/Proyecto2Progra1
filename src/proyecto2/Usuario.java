/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;
import java.util.Date;
/**
 *
 * @author Rolando
 */
public class Usuario {
    public String email, password, nombre, tipo;
    public Date fecha;
    
    public Usuario(String email, String pass, String nombre, String tipo){
        this.email = email;
        this.password = pass;
        this.nombre = nombre;
        this.tipo = tipo;
        fecha = new Date();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }
    
}
