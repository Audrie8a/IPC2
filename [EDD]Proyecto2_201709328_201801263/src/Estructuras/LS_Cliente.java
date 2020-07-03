/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;
import Nodos.nodoClienteLista;
import javax.swing.JOptionPane;
/**
 *
 * @author Audrie
 */
public class LS_Cliente {
 
    private nodoClienteLista inicio;

    public LS_Cliente() {
        inicio = null;
    }
    
    
    
    public void InsertarCliente(String Dpi,String nombre, String Apellido, String Genero,String Fecha, int Telefono, String Direccion){
        nodoClienteLista nodo= new nodoClienteLista(Dpi, nombre,Apellido,Genero,Fecha,Telefono,Direccion);
        if (inicio==null) {
            inicio=nodo;
            inicio.Siguiente=null;
        }else{
            nodoClienteLista aux=inicio;
            while (aux.Siguiente!=null) {
                aux = aux.Siguiente;
            }
            aux.Siguiente=nodo;
            nodo.Siguiente=null;
        }
    }
    
    public boolean Buscar(String Dpi){
        nodoClienteLista aux= inicio;
        boolean encontrado=false;
        while (aux!=null && encontrado!=true) {
            if (aux.getDPI().equals(Dpi)) {
                encontrado = true;
            }else{
                aux=aux.Siguiente;
            }
        }
        return encontrado;
    }
    

    
    public nodoClienteLista DevolverNodo(String Dpi) {
        if (Buscar(Dpi)) {
            nodoClienteLista aux=inicio;
            while (aux.getDPI().equals(Dpi)==false) {                
                aux=aux.Siguiente;
            }
            return aux;
        }else{
            JOptionPane.showMessageDialog(null,"NO SE ENCONTRO   "+Dpi,"RAYOS",JOptionPane.WARNING_MESSAGE);
            return null;
        }  
    }
    
    public void Eliminar(String Dpi){
        if (Buscar(Dpi)) {
            if (inicio.getDPI().equals(Dpi)) {
                inicio=inicio.Siguiente;
            }else{
                nodoClienteLista aux=inicio;
                while (aux.Siguiente.getDPI().equals(aux)==false) {                    
                    aux=aux.Siguiente;
                }
                nodoClienteLista auxSiguiente = aux.Siguiente.Siguiente;
                aux.Siguiente=auxSiguiente;
            }
        }
    }

    public nodoClienteLista getInicio() {
        return inicio;
    }
}
