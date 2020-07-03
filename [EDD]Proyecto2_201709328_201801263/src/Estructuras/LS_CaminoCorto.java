/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nodos.nodoCaminoCorto;

/**
 *
 * @author Audrie
 */
public class LS_CaminoCorto {
    private nodoCaminoCorto Ini;
    private nodoCaminoCorto Fin;
    private int numVertices=0;
    private int Index=-1;
    private int numAristas;
    
    
    public LS_CaminoCorto(){
        Ini = null;
        Fin = null;
    }
    
    public void insertarVertice(String nombreOrigen,String nombreDestino,int Peso,LS_Adyacencia lista ){
        nodoCaminoCorto nod= BuscarVertice(nombreOrigen, nombreDestino);
        if(nod==null){
            nodoCaminoCorto Nuevo= new nodoCaminoCorto(nombreOrigen, nombreDestino,Peso, lista);
            Index= getIndex()+1;
            Nuevo.setIndex(Index);
            int contador =getNumVertices();
            if(Ini==null){
            Ini= Nuevo;
            Ini.sig= null;
            Fin= Ini;
            contador++;
            setNumVertices(contador);
        }else{
            Fin.sig= Nuevo;
            Nuevo.sig= null;
            Fin= Nuevo;
            contador++;
            setNumVertices(contador);
            
        }
        }
        
    }
    
    public void Mostrar(){
        nodoCaminoCorto actual = new nodoCaminoCorto();
        
        actual = Ini;
        while(actual != null){
            System.out.println(actual.toString());
            System.out.println("CONTAR");            
        System.out.println(actual.getIndex()+"  "+actual.getNombreOrigen());
        actual.getLstNodosDetino().Mostrar();
            actual= actual.sig;
        }
    }

    
    public nodoCaminoCorto BuscarVertice(String nombreOrigen, String nombreDestino){
        nodoCaminoCorto actual = new nodoCaminoCorto();
        nodoCaminoCorto aux = new nodoCaminoCorto();
        if(Ini != null){            
        actual= Ini;
        do{
            if(actual.getNombreOrigen().equals(nombreOrigen) && actual.getNombreDestino().equals(nombreDestino) ){
                //System.out.println(actual.toString());
                aux= actual;
                
                break;
            }
            actual=actual.sig;
            aux= null;
        }while(actual != null);
        }else{
            aux=null;
        }
        
        return aux;
    }
    
    
    
    
    
    public nodoCaminoCorto BuscarPorIndex(int index){
        nodoCaminoCorto aux=null;
        nodoCaminoCorto actual;
        if(Ini != null){            
        actual= Ini;
        do{
            if(actual.getIndex()==index){
                //System.out.println(actual.toString());
                aux= actual;
                
                break;
            }
            actual=actual.sig;
            aux= null;
        }while(actual != null);
        }
        return aux;
    }
    
    public nodoCaminoCorto getInicio(){
        return Ini;
    }
    
    public nodoCaminoCorto getFin(){
        return Fin;
    }
    
    public void setNumVertices(int numV){
        numVertices=numV;
    }
    public int getNumVertices(){
        return numVertices;
    }
    
    public int getIndex(){
        return Index;
    }
    
    public void setIndex(int ind){
        Index= ind;
    }

    public nodoCaminoCorto getIni() {
        return Ini;
    }

    public void setIni(nodoCaminoCorto Ini) {
        this.Ini = Ini;
    }

    public int getNumAristas() {
        return numAristas;
    }

    public void setNumAristas(int numAristas) {
        this.numAristas = numAristas;
    }
    
}
