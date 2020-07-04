/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

/**
 *
 * @author Audrie
 */
public class nodoHuffman {
    public String texo;
    public int count;
    public nodoHuffman izq;
    public nodoHuffman der;
    public nodoHuffman linker;
    public nodoHuffman linkerBack;
    
    public nodoHuffman(String texto, int count){
        this.texo= texto;
        this.count=count;
        this.izq= null;
        this.der=null;
        this.linker=null;
        this.linkerBack=null;
    }
    
    public nodoHuffman() {
    }
    
    
    
}
