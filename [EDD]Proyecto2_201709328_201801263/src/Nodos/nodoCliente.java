/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;
import Estructuras.LS_Cliente;

/**
 *
 * @author Audrie
 */
public class nodoCliente {
    public nodoCliente siguiente;
    private LS_Cliente ListaDeCliente;
    public char estado;
    public String DPI;
    public String Nombre;
    public String Apellidos;
    public String Genero;
    public String Fecha;
    public int Telefono;
    public String Direccion;
    public int Contador = 0;

    public nodoCliente(String DPI, int CONTADOR) {
        this.DPI = DPI;
        this.Contador = CONTADOR;
    }

    public nodoCliente(String DPI, String Nombre,String apellido, String genero, String fecha, int telefono, String direccion, LS_Cliente ListaCliente) {
        this.DPI = DPI;
        this.Nombre = Nombre;
        this.Apellidos= apellido;
        this.Genero=genero;
        this.Fecha=fecha;
        this.Telefono=telefono;
        this.Direccion=direccion;
        this.ListaDeCliente = ListaCliente;
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

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public nodoCliente getSiguiente() {
        return siguiente;
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

    public int getContador() {
        return Contador;
    }

    public void setContador(int Contador) {
        this.Contador = Contador;
    }
    
    public LS_Cliente getListaDeCliente() {
        return ListaDeCliente;
    }

    public void setListaDeCliente(LS_Cliente ListaDeCliente) {
        this.ListaDeCliente = ListaDeCliente;
    }
    
}
