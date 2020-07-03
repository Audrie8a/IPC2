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
public class ClaveCadena extends Ordenar {
     private String clave;
    
    public ClaveCadena(String cadena)
    {
        clave = cadena;
    }
    public Object getClave()
    {
        return clave;
    }
    public boolean igualA(Ordenar cadena)
    {
        return clave.equals(cadena.getClave());
    }
    public boolean menorQue(Ordenar cadena)
    {
        return clave.compareTo((String)cadena.getClave()) < 0;
    }
    public boolean mayorQue(Ordenar cadena) {
        return clave.compareTo((String)cadena.getClave()) > 0;
    }
    
    public boolean MenorOIgualQue(Ordenar cadena) {
        return clave.compareTo((String)cadena.getClave()) <= 0;
    }
    
    public boolean MayorOIgualQue(Ordenar cadena) {
        return clave.compareTo((String)cadena.getClave()) >= 0;
    }
    
    public Ordenar Claveminima() {
        return new ClaveCadena("");
    }
}
