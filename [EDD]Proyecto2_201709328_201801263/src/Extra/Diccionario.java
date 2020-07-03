/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extra;

/**
 *
 * @author Audrie
 */
public class Diccionario {
    private String nombreDiccionario;
    private String Diccionario[][];
    private String codigoEspacio;

    public Diccionario(String nombreDiccionario, String[][] Diccionario) {
        this.nombreDiccionario = nombreDiccionario;
        this.Diccionario = Diccionario;
    }
    
    public void InsertarDiccionario(String nombreDiccionario, String[][] Diccionario){
        Diccionario nuevo = new Diccionario(nombreDiccionario, Diccionario);
    }

    public Diccionario(){}
    public String getNombreDiccionario() {
        return nombreDiccionario;
    }

    public void setNombreDiccionario(String nombreDiccionario) {
        this.nombreDiccionario = nombreDiccionario;
    }

    public String[][] getDiccionario() {
        return Diccionario;
    }

    public void setDiccionario(String[][] Diccionario) {
        this.Diccionario = Diccionario;
    }

    public String getCodigoEspacio() {
        return codigoEspacio;
    }

    public void setCodigoEspacio(String codigoEspacio) {
        this.codigoEspacio = codigoEspacio;
    }
    
    
}
