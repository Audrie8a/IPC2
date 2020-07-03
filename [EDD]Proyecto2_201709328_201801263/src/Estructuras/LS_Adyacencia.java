/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;
import Algoritmos.CaminoMinimo;
import static Estructuras.grafoRutas.Vertice;
import Nodos.nodoCaminoCorto;
import Nodos.nodoDestino;
import Nodos.nodoOrigen;
import java.awt.Desktop;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
/**
 *
 * @author Audrie
 */
public class LS_Adyacencia {
    
    private nodoDestino Ini;
    private nodoDestino Fin;
    public int numAristas=0;
    public int Index=0;    
    public int numDestinos=0;
    
    public LS_Adyacencia(){
        Ini = null;
        Fin = null;
    }
    
    public void insertarDestino(String nombreDestino, int tiempo){
        int Contador= Index+1;
        nodoDestino Nuevo= new nodoDestino(nombreDestino, tiempo);
        Index=getIndex()+1;
        Nuevo.setIndex(Index);
        if(Ini==null){
            Ini= Nuevo;
            Ini.sig= null;
            Fin= Ini;
            Contador++;
            setNumAristas(Contador);
        }else{
            Fin.sig= Nuevo;
            Nuevo.sig= null;
            Fin= Nuevo;
            Contador++;
            setNumAristas(Contador);
        }
    }
    
    public void Mostrar(){
        nodoDestino actual = new nodoDestino();
        
        actual = Ini;
        while(actual != null){
            System.out.println(actual.toString());
            System.out.println("Index Vertice: "+actual.miVertice.getIndex()+" Index: Destino"+actual.getIndex());
            actual= actual.sig;
        }
    }
    
    public nodoDestino BuscarDestino(String nombreDestino){
        nodoDestino actual = new nodoDestino();
        nodoDestino aux = new nodoDestino();
        actual= Ini;
        do{
            if(actual.getNombreDestino().equals(nombreDestino)){
                System.out.println(actual.toString());
                aux=actual;
                break;
            }
            actual=actual.sig;            
            aux= null;
        }while(actual != null);
        return aux;
    }
    
    public void ModificarDestino(String nombreDestino, String nuevoNombreDestino, int nuevoTiempo){
        nodoDestino actual = new nodoDestino();
        actual = Ini;
        while(actual != null){
            if(actual.getNombreDestino().equals(nombreDestino)){
                actual.setNombreDestino(nuevoNombreDestino);
                actual.setTiempo(nuevoTiempo);              
            }
            actual = actual.sig;
        }
    }
    
    public void EliminarNodoDestino(String nombreDestino){
        nodoDestino actual = new nodoDestino();
        nodoDestino anterior = new nodoDestino();
        actual = Ini;
        anterior= null;
        while(actual != null){
            if(actual.getNombreDestino().equals(nombreDestino)){
                if(actual == Ini){
                    Ini= Ini.sig;
                }else{
                    anterior.sig= actual.sig;
                }                
            }
            anterior= actual;
            actual = actual.sig;
            if(actual==null){                
                JOptionPane.showMessageDialog(null, "Ruta eliminada correctamente");
            }
        }
    }
    
    public nodoDestino BuscarPorIndex(int index){
        nodoDestino aux=null;
        nodoDestino actual;
        if(Ini != null){            
        actual= Ini;
        do{
            if(actual.miVertice.getIndex()==index){
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

    public Image GraficarCaminoMin() {
        Image img = null;
        try {
            File file;
            file = new File("CaminoCorto.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter print = new FileWriter(file);
            BufferedWriter buffer = new BufferedWriter(print);
            buffer.write("digraph g {\n");
            String Contenido = getTxtCaminoMin();
            buffer.write(Contenido);
            buffer.write("}");
            buffer.close();

            try {
                // Execute a command without arguments
                String command = "dot -Tjpg CaminoCorto.txt -o CaminoCorto.jpg";
                Process child = Runtime.getRuntime().exec(command);
                child = Runtime.getRuntime().exec(command);
                img = ImageIO.read(new File("CaminoCorto.jpg"));
                String archivo = "CaminoCorto.jpg";
                File objetofile = new File(archivo);
                Desktop.getDesktop().open(objetofile);

                return img;
            } catch (Exception e) {
                return null;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al graficar");
            return null;
        }
    }

    public String getTxtCaminoMin() {
        String EstiloVertice =" \"width = 1 style = filled, fillcolor = skyblue3];";
        String EstiloEnlace=" [width= 1 style = filled, fillcolor = skyblue3];";
        String EstiloTitualo = " \"width = 5 style = filled, fillcolor = mediumspringgreen];";
        String label="node [shape=record fontname=Arial];";
        
        nodoCaminoCorto nod=grafoRutas.CaminoCorto.BuscarVertice(getInicio().getNombreDestino(), getFin().getNombreDestino());
        
        label+="nodoTitulo"+"[label=\"Origen: "+nod.getNombreOrigen()+", Destino: "+nod.getNombreDestino()+", Total Tiempo: "+nod.getPesoCamino() +EstiloTitualo+"\n";
        
        nodoDestino Aux=getInicio();
        
        if(Aux !=null){
            do{
                label+="nodo"+Aux.getNombreDestino()+"[label=\""+ Aux.getNombreDestino()+EstiloVertice+"\n";
                if(Aux.sig != null){
                    label+="nodo"+Aux.getNombreDestino()+"->"+"nodo"+Aux.sig.getNombreDestino()+EstiloEnlace+"\n"; 
                }
                Aux=Aux.sig;
            }while(Aux != null);
            
        }else{
            label = "nodoNodo [label = \"No hay Nodos en la lista\"   width = 1 style = filled, fillcolor = \"#ccffcc\"];\n";
        }
        
        return label;
    }
    
    public nodoDestino getInicio(){
        return Ini;
    }
    
    public nodoDestino getFin(){
        return Fin;
    }
    
    public void setNumAristas(int A){
        numAristas= A;
    }
    
    public int getNumArtistas(){
        return numAristas;
    }
    
    public int getIndex(){
        return Index;
    }
    
    public void setIndex(int ind){
        Index=ind;
    }
    
    public nodoDestino getIni() {
        return Ini;
    }

    public void setIni(nodoDestino Ini) {
        this.Ini = Ini;
    }

    public int getNumDestinos() {
        return numDestinos;
    }

    public void setNumDestinos(int numDestinos) {
        this.numDestinos = numDestinos;
    }
}
