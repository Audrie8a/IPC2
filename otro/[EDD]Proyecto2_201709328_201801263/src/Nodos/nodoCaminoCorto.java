/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import Estructuras.LS_Adyacencia;

/**
 *
 * @author Audrie
 */
public class nodoCaminoCorto {
    private String nombreOrigen;   
    private String nombreDestino;    
    private int PesoCamino;
    private int numDestinos;
    
    private LS_Adyacencia lstNodosDetino; 
    private int Index=0;
    
    public nodoCaminoCorto sig;
    
    public void setIndex(int nm){
        Index= nm;
    };
    
    public int getIndex(){
        return Index;
    }
    public nodoCaminoCorto(){}

    public nodoCaminoCorto(String nombreOrigen, String nombreDestino, int Peso, LS_Adyacencia lstNodosDetino) {
        this.nombreOrigen = nombreOrigen;
        this.nombreDestino=nombreDestino;
        this.lstNodosDetino = lstNodosDetino;
        this.PesoCamino= Peso;
        numDestinos=this.lstNodosDetino.getNumDestinos();;
        
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    public LS_Adyacencia getLstNodosDetino() {
        return lstNodosDetino;
    }

    public void setLstNodosDetino(LS_Adyacencia lstNodosDetino) {
        this.lstNodosDetino = lstNodosDetino;
    }
    public int getPesoCamino() {
        return PesoCamino;
    }

    public void setPesoCamino(int PesoCamino) {
        this.PesoCamino = PesoCamino;
    }

     public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }
    
    @Override
    public String toString() {
        return "{" + "nombreOrigen=" + nombreOrigen + ", nombreDestino=" + nombreDestino +", Tiempo de Viaje: "+PesoCamino+", Numero de Destinos: "+numDestinos+ '}';
    }

    public int getNumDestinos() {
        return numDestinos;
    }

    public void setNumDestinos(int numDestinos) {
        this.numDestinos = numDestinos;
    }
    
    
}
