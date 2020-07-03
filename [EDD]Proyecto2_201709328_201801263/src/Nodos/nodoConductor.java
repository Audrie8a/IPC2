/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

/**
 *
 * @author Audrie
 */
public class nodoConductor {    

    //nodo Lista Circular doblemente enlazada
    
    
    private String DPI;
    private String Nombre;
    private String Apellidos;
    private char tipoLicencia;
    private String Genero;
    private String telefono;
    private String Dirección;
    public nodoConductor Sig;
    public nodoConductor Ant;
    public int contador;
    
    public nodoConductor(String Dpi, int Contador){
        this.DPI=Dpi;
        this.contador=Contador;
    }
    
    
    public nodoConductor(){}
    
    public nodoConductor(String DPI, String Nombre, String Apellidos, char tipoLicencia, String Genero, String telefono, String Dirección) {
        this.DPI = DPI;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.tipoLicencia = tipoLicencia;
        this.Genero = Genero;
        this.telefono = telefono;
        this.Dirección = Dirección;
        Ant=null;
        Sig=null;
        contador=0;
    }

    public String getDPI() {
        return DPI;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public char getTipoLicencia() {
        return tipoLicencia;
    }

    public String getGenero() {
        return Genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDirección() {
        return Dirección;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public void setTipoLicencia(char tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDirección(String Dirección) {
        this.Dirección = Dirección;
    }
    
    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
    
    @Override public String toString() {
        return "nodoConductor{" + "DPI=" + DPI + ", Nombre=" + Nombre + ", Apellidos=" + Apellidos + ", tipoLicencia=" + tipoLicencia + ", Genero=" + Genero + ", telefono=" + telefono + ", Direcci\u00f3n=" + Dirección + '}';
    }
    
}
