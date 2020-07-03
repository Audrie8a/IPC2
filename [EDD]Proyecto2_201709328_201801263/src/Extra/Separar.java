/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extra;
import Nodos.NodoB;
import Nodos.nodoVehiculo;

/**
 *
 * @author Audrie
 */
public class Separar {
     public NodoB Puntero;
    public Ordenar Clave;
    public Object Dato;

    public Separar(NodoB puntero, Ordenar clave, Object dato) {
        this.Puntero = puntero;
        this.Clave = clave;
        this.Dato = dato;
    }

    public NodoB getPuntero() {
        return Puntero;
    }

    public void setPuntero(NodoB puntero) {
        this.Puntero = puntero;
    }

    public Ordenar getClave() {
        return Clave;
    }

    public void setClave(Ordenar clave) {
        this.Clave = clave;
    }

    public Object getDato() {
        return Dato;
    }

    public void setDato(Object dato) {
        this.Dato = dato;
    }
}
