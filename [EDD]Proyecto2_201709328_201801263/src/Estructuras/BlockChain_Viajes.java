/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Extra.Listas;
import static Extra.Listas.Prueba;
import static Extra.Listas.arbolitoB;
import static Extra.Listas.conduc;
import static Extra.Listas.tabl;
import static Extra.Listas.vehi;
import Extra.Ordenar;
import Interfaz.Cliente;
import Nodos.nodoCaminoCorto;
import Nodos.nodoCliente;
import Nodos.nodoConductor;
import Nodos.nodoDestino;
import Nodos.nodoVehiculo;
import Nodos.nodoViaje;
import java.awt.Desktop;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;

//Lo que necesito para encriptar
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Audrie
 */
public class BlockChain_Viajes {
    nodoViaje ini, fin;
    public int Index=-1;
    public nodoVehiculo ArrayReporteVehi[]=new nodoVehiculo[1000];

    public nodoViaje getIni() {
        return ini;
    }

    public void setIni(nodoViaje ini) {
        this.ini = ini;
    }

    public nodoViaje getFin() {
        return fin;
    }

    public void setFin(nodoViaje fin) {
        this.fin = fin;
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int Index) {
        this.Index = Index;
    }
    
      
      public BlockChain_Viajes(){
          ini=null;
          fin=null;
      }
    
      
    public void PrevioInsertarViaje(String Origen, String Destino, String HoraFecha, String llave,String DpiCliente, String DpiConductor, String Placa ){         
        //Encriptamos llave
        String llaveEncriptada= encodeMD5(llave, llave);        
        //Obtenemos la Ruta        
        nodoCaminoCorto Ruta= grafoRutas.CaminoCorto.BuscarVertice(Origen, Destino);
        //Otbenemos el Cliente
        nodoCliente Cliente =tabl.extraerNodo(DpiCliente);
        //Obtenemos Conductor
        nodoConductor Conductor= conduc.Buscar(DpiConductor);
        //Obtenemos Vehículo
        nodoVehiculo Vehiculo=null;
         int i=0; 
            while(Prueba[i]!=null){
                
                if(Prueba[i].getPlaca().equals(Placa)){
                    Vehiculo= Prueba[i];
                }                
                i++;
            }
        if(Ruta != null && Cliente != null && Conductor != null && Vehiculo !=null){
            InsertarViaje(llaveEncriptada, llave, Origen, Destino, HoraFecha, Cliente, Conductor, Ruta, Vehiculo);
        }else{
            JOptionPane.showMessageDialog(null, "Algunos de los datos ingresados no se encuentran cargados en el sistema. Favor verificar, Gracias!");
        }
        
    }  
    public void InsertarViaje(String LLaveEncriptada, String llave,String Origen, String Destino, String HoraFecha, nodoCliente Cliente, nodoConductor Condutor, nodoCaminoCorto Ruta, nodoVehiculo Vehiculo){

        nodoViaje Nuevo = new nodoViaje(Origen, Destino, HoraFecha);
        Index = getIndex()+1;
        Nuevo.setIndex(Index);
        Nuevo.setLlave(llave);
        Nuevo.setLlaveEncriptada(LLaveEncriptada);
        Nuevo.setPtrRutaSimple(Ruta);
        Nuevo.setPtrCliente(Cliente);
        Nuevo.setPtrConductor(Condutor);
        Nuevo.setPtrVehiculo(Vehiculo);       

        if(ini==null){
            ini=Nuevo;
            ini.Sig= ini;
            Nuevo.Ant= fin;
            fin= Nuevo;
        }else{
            fin.Sig= Nuevo;
            Nuevo.Sig= ini;
            Nuevo.Ant=fin;
            fin= Nuevo;
            ini.Ant=fin;
        }
        
    }
    
    public nodoViaje Buscar(String llave){
        String llaveEncriptada= encodeMD5(llave, llave);
        nodoViaje actual = new nodoViaje();
        nodoViaje respuesta = null;
        actual= ini;
        if(actual != null){
            do{
            if(actual.getLlaveEncriptada().equals(llaveEncriptada)){
                System.out.println(actual.toString());
                respuesta=actual;
                break;
            }
            actual=actual.Sig;
            }while(actual != ini);
        }
        
        return respuesta;
    }
    
    
    public void PrevioEditar(String llaveModificar, String llave,String Origen, String Destino, String HoraFecha, String DpiCliente, String DpiConductor, String Placa){
        //Encriptamos llave
        String llaveEncriptada= encodeMD5(llave, llave);        
        //Obtenemos la Ruta        
        nodoCaminoCorto Ruta= grafoRutas.CaminoCorto.BuscarVertice(Origen, Destino);
        //Otbenemos el Cliente
        nodoCliente Cliente =tabl.extraerNodo(DpiCliente);
        //Obtenemos Conductor
        nodoConductor Conductor= conduc.Buscar(DpiConductor);
        //Obtenemos Vehículo
        nodoVehiculo Vehiculo=null;
         int i=0; 
            while(Prueba[i]!=null){
                if(Prueba[i].getPlaca()==Placa){
                    Vehiculo= Prueba[i];
                }           
                i++;     
            }        
        
         if(Ruta != null && Cliente != null && Conductor != null && Vehiculo !=null){
            Editar(llaveModificar, llaveEncriptada, llave, Origen, Destino, HoraFecha, Cliente, Conductor, Ruta, Vehiculo);
        }else{
            JOptionPane.showMessageDialog(null, "Algunos de los datos ingresados no se encuentran cargados en el sistema. Favor verificar, Gracias!");
        }
    
    }
    public void  Editar(String llaveModificar,String LLaveEncriptada, String llave,String Origen, String Destino, String HoraFecha, nodoCliente Cliente, nodoConductor Condutor, nodoCaminoCorto Ruta, nodoVehiculo Vehiculo ){
        nodoViaje actual = new nodoViaje();
        String llaveEncriptada= encodeMD5(llaveModificar, llaveModificar);
        actual= ini;
        do{
            if(actual.getLlaveEncriptada().equals(LLaveEncriptada)){
                actual.setLlave(llave);
                actual.setLlaveEncriptada(LLaveEncriptada);
                actual.setOrigen(Origen);
                actual.setDestino(Destino);
                actual.setFechaHora(HoraFecha);
                actual.setPtrCliente(Cliente);
                actual.setPtrConductor(Condutor);
                actual.setPtrRutaSimple(Ruta);
                actual.setPtrVehiculo(Vehiculo);
                break;
            }
            actual=actual.Sig;
        }while(actual != ini);        
    }
   
    public void Eliminar(String llave){        
        String llaveEncriptada= encodeMD5(llave, llave);
        nodoViaje anterior= new nodoViaje();
        nodoViaje actual =new nodoViaje();
        actual = ini;
        anterior= fin;
        do{
            if(actual.getLlaveEncriptada().equals(llaveEncriptada)){
             
                if(actual==ini){
                    ini= ini.Sig;
                    fin.Sig=ini;
                    ini.Ant= fin;
                }else if(actual==fin){
                    fin= anterior;
                    ini.Ant=fin;
                    fin.Sig= ini;
                }else{
                    anterior.Sig= actual.Sig;
                    actual.Sig.Ant= anterior;
                }
            }            
            anterior=actual;
            actual = actual.Sig;
        }while(actual != ini);
    }
    
    public void MostrarC(){
        nodoViaje actual = new nodoViaje();
        actual= ini;
        do{
            System.out.println(actual.toString());
            actual= actual.Sig;
        }while(actual != ini);
    }
    
    public String encodeMD5(String llave, String texto){
        String encriptacion="";
        try {
            MessageDigest md5= MessageDigest.getInstance("MD5"); //Indico el tipo de encriptacion
            byte[] llaveArreglo = md5.digest(llave.getBytes("utf-8"));  //Primer arreglo, Se usa utf-8, en caso de que existan tildes
            byte[] byteLlaveArrglo= Arrays.copyOf(llaveArreglo, 24);    //Segundo arreglo, copia del primer arreglo a base 24
            SecretKey llaveSecreta = new SecretKeySpec(byteLlaveArrglo, "DESede");   //DESde: Es parte de la sintaxsis
            Cipher cifrado= Cipher.getInstance("DESede");
            cifrado.init(Cipher.ENCRYPT_MODE,llaveSecreta);            
            byte[] textoPlanoBytes=  texto.getBytes("utf-8");
            byte[] buffer= cifrado.doFinal(textoPlanoBytes);    //contiene el cifrado
            byte[] BytesBase64=  Base64.encodeBase64(buffer);
            encriptacion= new String(BytesBase64);
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error la encriptar");
        }
        return encriptacion;
    }
    
    public String decodeMD5(String llave, String textoEncriptado){
        String desencirptacion="";
        try {
            byte[] mensaje= Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md5= MessageDigest.getInstance("MD5");
            byte[] digestOfpassword= md5.digest(llave.getBytes("utf-8"));
            byte[] LlaveBytes= Arrays.copyOf(digestOfpassword, 24);
            SecretKey llaveSecreta=new  SecretKeySpec(LlaveBytes, "DESede");
            Cipher decifrador= Cipher.getInstance("DESede");
            decifrador.init(Cipher.DECRYPT_MODE, llaveSecreta);
            byte[] textoPlano= decifrador.doFinal(mensaje);
            desencirptacion= new String(textoPlano, "UTF-8");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error la desencriptar");
        }
        
        return desencirptacion;
    }

    public Image Graficar() {
        Image img = null;
        try {
            String ruta = "Viaje.txt";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("digraph g {\n");
            String texto = getText();
            bw.write(texto);
            bw.write("}");

            bw.close();
            try {
                // Execute a command without arguments
                String command = "dot -Tjpg Viaje.txt -o Viaje.jpg";
                Process child = Runtime.getRuntime().exec(command);
                child = Runtime.getRuntime().exec(command);
                img = ImageIO.read(new File("Viaje.jpg"));
                String archivo = "Viaje.jpg";
                File objetofile = new File(archivo);
                Desktop.getDesktop().open(objetofile);

                return img;
            } catch (Exception e) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getText() {
        String EstiloVertice = " \"width = 1 style = filled, fillcolor = skyblue3];";
        String EstiloEnlace = " [width= 1 style = filled, fillcolor = skyblue3];";
        String label = "node [shape=record fontname=Arial];";

        nodoViaje Aux = new nodoViaje();
        Aux = ini;

        if (Aux !=null){
            do{
                label+="nodo"+Aux.getIndex()+"[label=\""+ Aux.getLlaveEncriptada()+", "+Aux.getOrigen()+", "+Aux.getDestino()+", Cliente: "+Aux.getPtrCliente().getDPI()+", Conductor: "+Aux.getPtrConductor().getDPI()+", Tiempo: "+Aux.getPtrRutaSimple().getPesoCamino()+EstiloVertice+"\n";
                if(Aux.Sig != ini){
                    label+="nodo"+Aux.getIndex()+"->"+"nodo"+Aux.Sig.getIndex()+EstiloEnlace+"\n"; 
                }
                Aux=Aux.Sig;
            }while(Aux != ini);
            
        }else{
            label = "nodoNodo [label = \"No hay Nodos en la lista\"   width = 1 style = filled, fillcolor = \"#ccffcc\"];\n";
        }
        
        return label;
    }

    public Image GraficaGeneral() {
        Image img = null;
        try {
            String ruta = "GraficaGeneral.edd";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("digraph g {\n");
            String texto = getTextEstructura();
            bw.write(texto);
            bw.write("}");

            bw.close();

            try {
                // Execute a command without arguments
                String command = "dot -Tjpg GraficaGeneral.edd -o GraficaGeneral.jpg";
                Process child = Runtime.getRuntime().exec(command);
                child = Runtime.getRuntime().exec(command);
                img = ImageIO.read(new File("GraficaGeneral.jpg"));
                String archivo = "GraficaGeneral.jpg";
                File objetofile = new File(archivo);
                Desktop.getDesktop().open(objetofile);

                return img;

            } catch (Exception e) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getTextEstructura() {

        String Imprimir = "";
        String conexiones = "";
        String labelRutaMin = "";
        String labelConductor = "subgraph clusterLDC_conductor{\n rankdir=MR;\n" + conduc.getText() + "\n}" + "\n";
        String labelCliente = "subgraph clusterTH_Cliente{\n rankdir=MR; \n " + tabl.getText() + "\n} \n";
        String labelDatos = "node [shape=record fontname=Arial fillcolor = skyblue3];";
        String labelArbol = "subgraph clusterTH_Vehiculo{\n rankdir=MR; \n "+arbolitoB.getText()+"\n} \n";  
//        
        int i=0;
        
        //------------------------------------------------------------------------------------------------------------------------------------------------VIAJES
        String EstiloVertice =" \"width = 1 style = filled, fillcolor = skyblue3];";
        String EstiloEnlace=" [width= 1 style = filled, fillcolor = skyblue3];";
        String label="node [shape=record fontname=Arial];";
        
        nodoViaje Aux = new nodoViaje();
        
        
        Aux = ini;
        if(Aux !=null){
            do{
                label+="nodo"+Aux.getIndex()+"[label=\""+ Aux.getLlaveEncriptada()+", "+Aux.getOrigen()+", "+Aux.getDestino()+", Cliente: "+Aux.getPtrCliente().getDPI()+", Conductor: "+Aux.getPtrConductor().getDPI()+", Tiempo: "+Aux.getPtrRutaSimple().getPesoCamino()+EstiloVertice+"\n";
                
                
                if(i==0){
                    labelRutaMin+="subgraph cluster_Grafo{ \n rankdir=MR; \n"+Aux.getPtrRutaSimple().getLstNodosDetino().getTxtCaminoMin()+"\n }\n";//--------------------------------------------------------------------------------------------------------------------------GRAFO 
                    conexiones +="nodo"+Aux.getIndex()+"->"+"nodo"+Aux.getPtrRutaSimple().getLstNodosDetino().getIni().getNombreDestino()+"\n";
                }                
                i++;
                conexiones+="nodo"+Aux.getIndex()+"->"+"Conductor"+Aux.getPtrConductor().getDPI()+"\n";
                conexiones+="nodo"+Aux.getIndex()+"->"+"Cliente"+Aux.getPtrCliente().getDPI()+"\n";
                conexiones+="nodo"+Aux.getIndex()+"->"+Aux.getPtrVehiculo().getPlaca()+"\n";
                
                if(Aux.Sig != ini){
                    label+="nodo"+Aux.getIndex()+"->"+"nodo"+Aux.Sig.getIndex()+EstiloEnlace+"\n"; 
                }
                Aux=Aux.Sig;
            }while(Aux != ini);
            
        }else{
            label = "nodoNodo [label = \"No hay Nodos en la lista\"   width = 1 style = filled, fillcolor = \"#ccffcc\"];\n";
        }
        //----------------------------------------------------------------------------------------------------------------------------------------------FIN VIAJES
        
        Imprimir =labelCliente+label+labelArbol+labelRutaMin+labelConductor+conexiones;
                //label+labelRutaMin+labelCliente+labelConductor+labelArbol+conexiones;
        
        return Imprimir;
    }
    
    public void InsertarVectorReporte(String Placa, int contador) {
        int Aux = BuscarUsuarioVector(Placa);
        if (Aux != 1000) {
            ArrayReporteVehi[Aux].setContador(contador);
        } else if (Aux == 1000) {
            for (int i = 0; i < ArrayReporteVehi.length; i++) {
                if (ArrayReporteVehi[i] == null) {
                    ArrayReporteVehi[i] = new nodoVehiculo(Placa, contador);
                    break;
                }
            }
        }
    }

    public int BuscarUsuarioVector(String Placa) {
        for (int i = 0; i < ArrayReporteVehi.length; i++) {
            if (ArrayReporteVehi[i] != null) {
                if (ArrayReporteVehi[i].getPlaca().equals(Placa)) {
                    return i;
                }
            }
        }
        return 1000;
    }
    
    public int BuscarContadorVector(String Placa) {
        for (int i = 0; i < ArrayReporteVehi.length; i++) {
            if (ArrayReporteVehi[i] != null) {
                if (ArrayReporteVehi[i].getPlaca().equals(Placa)) {
                    return ArrayReporteVehi[i].getContador();
                }
            }
        }
        return 1005;
    }

    public void Ordenar() {
        for (int i = 0; i < ArrayReporteVehi.length; i++) {
            if (ArrayReporteVehi[i] == null) {
                ArrayReporteVehi[i] = new nodoVehiculo("-", 0);
            }
        }
        for(int i = 0; i < ArrayReporteVehi.length - 1; i++)
        {
            for(int j = 0; j < ArrayReporteVehi.length - 1; j++)
            {
                if (ArrayReporteVehi[j].getContador() < ArrayReporteVehi[j + 1].getContador())
                {
                    int tmp = ArrayReporteVehi[j+1].getContador();
                    String tmps = ArrayReporteVehi[j+1].getPlaca();
                    ArrayReporteVehi[j+1].setPlaca(ArrayReporteVehi[j].getPlaca());
                    ArrayReporteVehi[j+1].setContador(ArrayReporteVehi[j].getContador());
                    ArrayReporteVehi[j].setContador(tmp);
                    ArrayReporteVehi[j].setPlaca(tmps);
                }
            }
        }
    }

    public void ImprimirArray() {
        String Texto="";
        Ordenar();
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + "  PLACA:    " + ArrayReporteVehi[i].getPlaca()+ "    NO VIAJES:     " + ArrayReporteVehi[i].getContador());
        }
        try {
            String ruta = "TopVehiculos.EDD";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            Texto+="-----TOP 10 VEHICULOS CON MAYOR CANTIDAD DE VIAJES-----\n";
            for (int i = 0; i < 10; i++) {
                Texto+=(i + 1) + "  PLACA:    " + ArrayReporteVehi[i].getPlaca() + "    NO VIAJES:     " + ArrayReporteVehi[i].getContador() + "\n";
            }
            
            System.out.println(Texto);
            String TextoComprimido=ArbolHuffman.comprimirHuffman(Texto, "TopVehiculosDescomprimido");
            bw.write(TextoComprimido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}

