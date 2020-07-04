/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;
import Estructuras.grafoRutas;
import static Estructuras.grafoRutas.Vertice;
import Nodos.nodoDestino;
import Nodos.nodoOrigen;
import javax.swing.JOptionPane;
/**
 *
 * @author Audrie
 */
public class CaminoMinimo {
   
    
    
    public boolean buscarVertice(int V, int A){
        boolean condicion = false;
        nodoOrigen Origin=Vertice.BuscarPorIndex(V);
        if(Origin.getLstNodosDetino().getInicio()!= null){
            nodoDestino Destin=Origin.getLstNodosDetino().BuscarPorIndex(A);
            if(Destin != null){
                condicion= true;                
            }
        }      
        
        return condicion;
    }
    
    public int buscarPeso(int V, int A){
        int Peso=0;
        nodoOrigen Origin=Vertice.BuscarPorIndex(V);
        if(Origin.getLstNodosDetino().getInicio()!= null){
            nodoDestino Destin=Origin.getLstNodosDetino().BuscarPorIndex(A);
            if(Destin != null){
                Peso=Destin.getTiempo();
            }
        }      
        return Peso;
    }
    
}
