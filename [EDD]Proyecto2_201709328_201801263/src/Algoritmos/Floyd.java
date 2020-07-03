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

/**
 *
 * @author Audrie
 */
public class Floyd {
    grafoRutas Grafo = new grafoRutas();
    
    public String MetodoFloyd(int indexO, int indexD){
        int vertices=Vertice.getNumVertices();
        long matrizAdyacencia[][]= new long[vertices][vertices];
        for(int a=0; a<vertices; a++){
            for(int b = 0; b<vertices; b++){
                matrizAdyacencia[a][b]=buscarPeso(a, b);
            }
        }
        String caminos[][]= new String [vertices][vertices];
        String caminosAux[][]= new String [vertices][vertices];
        String caminoRecorrido= "", cadena="", caminitos="";
        int i, j, k;
        float temporal1, temporal2, temporal3, temporal4, minimo;
        
        for(i=0; i<vertices; i++){
            for(j=0; j<vertices; j++){
                caminos[i][j]= "";
                caminosAux[i][j]="";
            }
        } 
        for(k=0; k<vertices; k++){
            for(i=0; i<vertices; i++){
                for(j=0; j<vertices; j++){
                    temporal1= matrizAdyacencia[i][j];
                    temporal2= matrizAdyacencia[i][k];
                    temporal3= matrizAdyacencia[k][j];
                    temporal4= temporal2+temporal3;
                    
                    minimo= Math.min(temporal1, temporal4);
                    if(temporal1 != temporal4){
                        if(minimo==temporal4){
                            caminoRecorrido="";
                            caminosAux[i][j]=k+"";
                            caminos[i][j]= caminosR(i,k, caminosAux, caminoRecorrido)+ (k+1);
                        }
                    }
                    matrizAdyacencia[i][j]=(long) minimo;
                    
                }
            }
        }
        for(i=0; i<vertices; i++){
            for(j=0; j<vertices; j++){
                cadena= cadena+"["+matrizAdyacencia[i][j]+"]";
            }
            cadena=cadena +"\n";
        }
        for(i=0; i<vertices; i++){
            for(j=0; j<vertices; j++){
                if(matrizAdyacencia[i][j]!=1000000000){
                    if(i!=j){ 
                        if(caminos[i][j].equals("")){
                            caminitos += "De ("+  (i+1)+"--->"+(j+1)+") irse por...("+ (i+1)+", "+(j+1)+")\n";
                            Grafo.CaminoMinimo(i, j, caminos[i][j], indexO, indexD);
                        }else{
                            caminitos += "De ("+  (i+1)+"--->"+(j+1)+") irse por...("+ (i+1)+", "+caminos[i][j]+", "+(j+1)+")\n";
                            Grafo.CaminoMinimo(i, j, caminos[i][j], indexO, indexD);
                        }
                       
                        
                    }
                }
            }            
        }
        return "Los diferentes caminos más cortos entre vertices son:\n "+caminitos;
    }
        
    public String caminosR(int i, int k, String[][] caminosAux, String caminoRecorrido){
        if(caminosAux[i][k].equals("")){
            return "";
        }else{
            caminoRecorrido += caminosR(i,Integer.parseInt(caminosAux[i][k]), caminosAux, caminoRecorrido)+ (Integer.parseInt(caminosAux[i][k].toString())+1)+", ";
            return caminoRecorrido;
        }
    }
    
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
        int Peso=999999999;
        nodoOrigen Origin=Vertice.BuscarPorIndex(V);
        
        if(Origin.getLstNodosDetino().getInicio()!= null){
            nodoDestino Destin=Origin.getLstNodosDetino().BuscarPorIndex(A);
            if(V!=A){
                if(Destin != null){
                    Peso=Destin.getTiempo();
                }else{
                    Peso=999999999;
                }
            }else{
                Peso=0;
                
            }            
        } else if(Origin.getLstNodosDetino().getInicio() ==null & V==A){
            Peso=0;
        }     
        return Peso;
    }    
}
