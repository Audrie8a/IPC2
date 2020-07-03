/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import Estructuras.grafoRutas;

/**
 *
 * @author Audrie
 */
public class nodoDestino {
    
    private String nombreDestino;
    private int tiempo;
    private int Index=0;
    //private nodoOrigen VertexNode;
    public nodoDestino sig;  
    public nodoOrigen miVertice;
    private int IndexMiVertice;
    
    
    public nodoDestino(){}
    
    public nodoDestino(String nombreDestino, int tiempo) {
        this.nombreDestino = nombreDestino;
        this.tiempo = tiempo;
        this.sig = null;
        this.miVertice=grafoRutas.Vertice.BuscarVertice(nombreDestino);
        
    }
    
    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
     @Override
    public String toString() {
        return "nodoDestino{" + "nombreDestino=" + nombreDestino + ", tiempo=" + tiempo + '}';
    }
    
    public void setIndex(int ind){
        Index= ind;
    }
    
    public int getIndex(){
        return Index;
    }
    
    public void setIndexMiVertice(int indx){
        IndexMiVertice= indx;
    }
    
    public int getIndexMiVertice(){
        return IndexMiVertice;
    }
}
