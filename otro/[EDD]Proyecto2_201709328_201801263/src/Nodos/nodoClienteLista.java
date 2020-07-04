/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import Nodos.nodoCliente;
/**
 *
 * @author Audrie
 */
public class nodoClienteLista {
    public nodoClienteLista Siguiente;
   private String DPI;
   private String Nombre;
   private String Apellidos;
   private String Genero;
   private String Fecha;
   private int Telefono;
   private String Direccion;

    public nodoClienteLista( String DPI, String Nombre, String Apellidos, String Genero, String Fecha, int Telefono, String Direccion) {
        Siguiente = null;
        this.DPI = DPI;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Genero = Genero;
        this.Fecha = Fecha;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
    }
   
    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public nodoClienteLista getSiguiente() {
        return Siguiente;
    }

    public void setSiguiente(nodoClienteLista Siguiente) {
        this.Siguiente = Siguiente;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }
}
