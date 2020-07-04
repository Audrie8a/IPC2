/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import static Estructuras.grafoRutas.Vertice;
import Nodos.nodoDestino;
import Nodos.nodoOrigen;

/**
 *
 * @author Audrie
 */
public class Dijkstra {
    public static int[] Dijkstra= new int[Vertice.getNumVertices()];
    int n=Vertice.getNumVertices();
   
    
    public int minDistance(int dist[], Boolean sptSet[]){
        int min = Integer.MAX_VALUE;
        int min_index=-1;
        
        for(int v=0; v<n; v++){
            if(sptSet[v]==false && dist[v]<=min){
                min=dist[v];
                min_index=v;
            }
        }
        return min_index;
    }
    
    void printSolution(int dist[], int r){
        
        System.out.println("Vertex Distance from Source");
        for(int i=0; i<n;i++){
            System.out.println(i+"tt"+dist[i]);
            Dijkstra[i]= dist[i];
        }
    }
    
    public void dijkstra2(int src){
        int dist[]= new int [n];
        Boolean sptSet[]= new Boolean[n];
        for(int i=0; i<n; i++){
            dist[i]=Integer.MAX_VALUE;
            sptSet[i]=false;
        }
        dist[src]=0;
        
        for(int count =0; count <n-1; count++){
            int u = minDistance(dist, sptSet);
            sptSet[u]=true;
            for(int v=0;v<n;v++){
                if(!sptSet[v] && buscarPeso(u, v) !=0 && dist[u]!=Integer.MAX_VALUE && dist[u]+buscarPeso(u, v)<dist[v]){
                    dist[v]=dist[u]+buscarPeso(u, v);
                    //Aquí debo cachar la  ciudad
                }
            }
        }
        printSolution(dist, n);
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
