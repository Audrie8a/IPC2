/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;
import Nodos.nodoOrigen;
/**
 *
 * @author Audrie
 */
public class LS_Vertices {
    private nodoOrigen Ini;
    private nodoOrigen Fin;
    private int numVertices=0;
    private int Index=-1;
    
    public LS_Vertices(){
        Ini = null;
        Fin = null;
    }
    
    public void insertarVertice(String nombreDestino,LS_Adyacencia lista ){
        nodoOrigen Nuevo= new nodoOrigen(nombreDestino, lista);
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
    
    public void Mostrar(){
        nodoOrigen actual = new nodoOrigen();
        
        actual = Ini;
        while(actual != null){
            System.out.println(actual.toString());
            System.out.println("CONTAR");            
        System.out.println(actual.getIndex()+"  "+actual.getNombreOrigen());
        actual.getLstNodosDetino().Mostrar();
            actual= actual.sig;
        }
    }

    
    public nodoOrigen BuscarVertice(String nombreOrigen){
        nodoOrigen actual = new nodoOrigen();
        nodoOrigen aux = new nodoOrigen();
        if(Ini != null){            
        actual= Ini;
        do{
            if(actual.getNombreOrigen().equals(nombreOrigen)){
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
    
    public void ModificarVetice(String nombreOrigen, String nuevoNombreOrigen, LS_Adyacencia lista){
        nodoOrigen actual = new nodoOrigen();
        actual = Ini;
        while(actual != null){
            if(actual.getNombreOrigen().equals(nombreOrigen)){
                actual.setNombreOrigen(nuevoNombreOrigen);
                actual.setLstNodosDetino(lista);              
            }
            actual = actual.sig;
        }
    }
    
    public void EliminarNodoVertice(String nombreOrigen){
        nodoOrigen actual = new nodoOrigen();
        nodoOrigen anterior = new nodoOrigen();
        actual = Ini;
        anterior= null;
        while(actual != null){
            if(actual.getNombreOrigen().equals(nombreOrigen)){
                if(actual == Ini){
                    Ini= Ini.sig;
                }else{
                    anterior.sig= actual.sig;
                }                
            }
            anterior= actual;
            actual = actual.sig;
        }
    }
    
    public nodoOrigen BuscarPorIndex(int index){
        nodoOrigen aux=null;
        nodoOrigen actual;
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
    
    public nodoOrigen getInicio(){
        return Ini;
    }
    
    public nodoOrigen getFin(){
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
    
}
