/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.awt.Desktop;
import java.awt.Image;
import javax.imageio.ImageIO;
import Nodos.nodoCliente;
import Nodos.nodoClienteLista;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.math.BigInteger;
import javax.swing.JOptionPane;
import java.util.Arrays;
import static java.util.Comparator.nullsLast;

/**
 *
 * @author Audrie
 */
public class TablaHash {

    private int tamanio;
    private static int tamanioss[];
    private int indiceTam;
    private int ocupados;
    private float porcentajeUtil;
    private float factorUtil;
    private nodoCliente[] vectorHash;
    public nodoCliente[] ArrayReporte;

    public TablaHash() {
        this.tamanioss = new int[]{37, 73, 111, 185, 221, 259, 295, 333, 369, 407, 443, 481, 515, 555, 591, 629, 665, 703, 739, 777, 813, 851, 887, 925, 961, 999};
        this.indiceTam = 0;
        this.ocupados = 0;
        this.factorUtil = 75.0f;
        this.tamanio = tamanioss[indiceTam];
        this.vectorHash = new nodoCliente[tamanio];
        this.porcentajeUtil = calcularPorcentajeUtil();
            this.ArrayReporte = new nodoCliente[tamanio];
    }

    public void InsertarVectorReporte(String Dpi, int contador) {
        int Aux = BuscarUsuarioVector(Dpi);
        if (Aux != 1000) {
            ArrayReporte[Aux].setContador(contador);
        } else if (Aux == 1000) {
            for (int i = 0; i < ArrayReporte.length; i++) {
                if (ArrayReporte[i] == null) {
                    ArrayReporte[i] = new nodoCliente(Dpi, contador);
                    break;
                }
            }
        }
    }

    public int BuscarUsuarioVector(String Dpi) {
        for (int i = 0; i < ArrayReporte.length; i++) {
            if (ArrayReporte[i] != null) {
                if (ArrayReporte[i].getDPI().equals(Dpi)) {
                    return i;
                }
            }
        }
        return 1000;
    }

    public void Ordenar() {
        for (int i = 0; i < ArrayReporte.length; i++) {
            if (ArrayReporte[i] == null) {
                ArrayReporte[i] = new nodoCliente("--", 0);
            }
        }
        for (int i = 0; i < ArrayReporte.length - 1; i++) {
            for (int j = 0; j < ArrayReporte.length - 1; j++) {
                if (ArrayReporte[j].getContador() < ArrayReporte[j + 1].getContador()) {
                    int tmp = ArrayReporte[j + 1].getContador();
                    String tmps = ArrayReporte[j + 1].getDPI();
                    ArrayReporte[j + 1].setDPI(ArrayReporte[j].getDPI());
                    ArrayReporte[j + 1].setContador(ArrayReporte[j].getContador());
                    ArrayReporte[j].setContador(tmp);
                    ArrayReporte[j].setDPI(tmps);
                }
            }
        }
    }

    public void ImprimirArray() {
        String Texto="";
        Ordenar();
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + "  DPI:    " + ArrayReporte[i].getDPI() + "    NO VIAJES:     " + ArrayReporte[i].getContador());
        }
        try {
            String ruta = "TopClientes.EDD";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            Texto="-----TOP 10 CLIENTES CON MAYOR CANTIDAD DE VIAJES-----\n";
            for (int i = 0; i < 10; i++) {
                Texto +=(i + 1) + "  DPI:    " + ArrayReporte[i].getDPI() + "    NO VIAJES:     " + ArrayReporte[i].getContador() + "\n";
            }
            
            System.out.println(Texto);
            String TextoComprimido=ArbolHuffman.comprimirHuffman(Texto, "TopClientesDescomprimido");
            bw.write(TextoComprimido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int funcion1(int clave) {
        return clave % tamanio;
    }

    private float calcularPorcentajeUtil() {
        return (ocupados * 100) / tamanio;
    }

    public void Insertar(String DPI, String Nombre, String Apellidos, String Genero, String Fecha, int Telefono, String Direccion) {
        boolean insertado = false;

        BigInteger bigIntegerCasteo = new BigInteger(DPI);
        int CasteoDPIPosicion = bigIntegerCasteo.mod(BigInteger.valueOf(tamanio)).intValue();
        if (porcentajeUtil <= 75.0f) {
            for (int i = 0; i < tamanio; i++) {
                if (CasteoDPIPosicion > tamanio) {
                    CasteoDPIPosicion -= tamanio;
                }
                if (vectorHash[CasteoDPIPosicion] == null || vectorHash[CasteoDPIPosicion].estado == 'b') {
                    LS_Cliente listaCliente = new LS_Cliente();
                    vectorHash[CasteoDPIPosicion] = new nodoCliente(DPI, Nombre, Apellidos, Genero, Fecha, Telefono, Direccion, listaCliente);
                    ocupados += 1;
                    porcentajeUtil = calcularPorcentajeUtil();
                    insertado = true;
                    break;
                } else if (vectorHash[CasteoDPIPosicion] != null) {
                    vectorHash[CasteoDPIPosicion].getListaDeCliente().InsertarCliente(DPI, Nombre, Apellidos, Genero, Fecha, Telefono, Direccion);
                    insertado = true;
                    System.out.println("SE INSERTO CORRECTAMENTE EN LA LISTA SIMPLE: " + CasteoDPIPosicion + "  " + DPI + "  CON NOMBRE:   " + Nombre);

                    break;
                } else {
                    if (vectorHash[CasteoDPIPosicion].DPI.equals(DPI)) {
                        JOptionPane.showMessageDialog(null, "YA EXISTE ESTE DATO DENTRO DE LA TABLA", "CUIDADO", JOptionPane.ERROR_MESSAGE);

                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "COLISION EN:  " + CasteoDPIPosicion, "RAYOS", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            if (insertado == true) {
                System.out.println("SE INSERTO CORRECTAMENTE:  " + DPI + "  CON NOMBRE:   " + Nombre);
            } else {
                JOptionPane.showMessageDialog(null, "NO SE INSERTO   " + DPI + "CON NOMBRE:   " + Nombre, "RAYOS", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "SE NECESITA HACER UN REHASHING  " + porcentajeUtil, "UY KIETO", JOptionPane.WARNING_MESSAGE);
            Rehashing();
            Insertar(DPI, Nombre, Apellidos, Genero, Fecha, Telefono, Direccion);
        }

    }

    private void Rehashing() {
        nodoCliente[] tmp = vectorHash;
        int tamanioTmp = tamanio;
        if (indiceTam < tamanioss.length) {
            indiceTam += 1;
            if (indiceTam == tamanioss.length - 1) {
                JOptionPane.showMessageDialog(null, "SE ALCANZO EL VALOR MAXIMO DEL ARREGLO HASH D:", "UY KIETO", JOptionPane.WARNING_MESSAGE);
            }
        }
        tamanio = tamanioss[indiceTam];
        vectorHash = new nodoCliente[tamanio];
        ocupados = 0;
        porcentajeUtil = calcularPorcentajeUtil();
        for (int i = 0; i < tamanioTmp; i++) {
            if (tmp[i] != null) {
                Insertar(tmp[i].DPI, tmp[i].Nombre, tmp[i].Apellidos, tmp[i].Genero, tmp[i].Fecha, tmp[i].Telefono, tmp[i].Direccion);
            }
        }
        JOptionPane.showMessageDialog(null, "REHASHING CORRECTAMENTE  ", "TODO SALIO BIEN", JOptionPane.OK_OPTION);
    }

    public nodoCliente extraerNodo(String ModificarDPI) {
        boolean encontrado = false;
        nodoCliente tmp = null;
        int pos = 0;
        BigInteger bigIntegerCasteo = new BigInteger(ModificarDPI);
        int CasteoDPIPosicion = bigIntegerCasteo.mod(BigInteger.valueOf(tamanio)).intValue();
        for (int i = 0; i < tamanio; i++) {
            if (CasteoDPIPosicion >= tamanio) {
                CasteoDPIPosicion -= tamanio;
                pos = CasteoDPIPosicion;
            }
            if (vectorHash[CasteoDPIPosicion] != null) {
                if (vectorHash[CasteoDPIPosicion].DPI.equals(ModificarDPI)) {
                    tmp = vectorHash[CasteoDPIPosicion];
                    encontrado = true;
                    break;
                } else if (vectorHash[CasteoDPIPosicion].getListaDeCliente() != null) {
                    if (vectorHash[CasteoDPIPosicion].getListaDeCliente().Buscar(ModificarDPI)) {
                        tmp = vectorHash[CasteoDPIPosicion];
                        encontrado = true;
                        break;
                    }
                }
            }
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "SE ENCONTRO   " + ModificarDPI);
        } else {
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRO   " + ModificarDPI, "RAYOS", JOptionPane.WARNING_MESSAGE);
        }
        return tmp;
    }

    public void Imprimir() {
        for (int i = 0; i < tamanio; i++) {
            int k = i + 1;
            if (vectorHash[i] != null) {
                String DPI = vectorHash[i].DPI;
                String Nombre = vectorHash[i].Nombre;
                System.out.println(i + " [label=\"numero ------- " + i + "----- Usuario - " + DPI + "  contraseña - " + Nombre + " \"];");

            } else {
                System.out.println(i + " [label=\"numero ------- " + i + "----- Usuario - vacio  contraseña - vacio \"];");

            }

        }
        System.out.println("**************************************************************************************************************+");
    }

    public Image Graficar() {
        Image img = null;
        try {
            String ruta = "tablahash.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("digraph g {");
            //bw.write(getText());
            bw.write("node[shape=record]");
            nodoClienteLista auxiliar;
            LS_Cliente listita;
            String conexiones = "";
            String rank = "";

            for (int i = 0; i < tamanio; i++) {
                int k = i + 1;
                if (vectorHash[i] != null) {
                    String us = vectorHash[i].DPI;
                    String contra = vectorHash[i].Nombre;
                    bw.write(i + " [label=\"numero ------- " + i + "----- Usuario - " + us + "  contraseña - " + contra + " \",style= filled,fillcolor = green];\n");
                    listita = vectorHash[i].getListaDeCliente();

                    if (i < tamanio - 1) {

                        conexiones += i + "->" + k + ";\n";
                    }
                } else {
                    bw.write(i + " [label=\"numero ------- " + i + "-------------- VACIO-------------- \",style= filled,fillcolor = red];\n");
                    conexiones += i + "->" + k + ";\n";
                }
            }
            bw.write(conexiones);
            for (int i = 0; i < tamanio; i++) {
                if (vectorHash[i] != null) {
                    listita = vectorHash[i].getListaDeCliente();
                    if (listita.getInicio() != null) {
                        auxiliar = listita.getInicio();
                        String label = "";
                        label += i + "->" + auxiliar.getDPI() + "\n";
                        bw.write(label);
                        rank = "{ rank = same;" + i + ";";
                        do {
                            label = "";
                            label += auxiliar.getDPI() + "[label=\"------ " + auxiliar.getDPI() + "-------- " + auxiliar.getNombre() + " \",style=filled,fillcolor = orange];\n";

                            if (auxiliar.Siguiente != null) {

                                label += auxiliar.getDPI() + "->" + auxiliar.Siguiente.getDPI() + ";\n";
                            } else {
                                //label+=auxiliar.getDPI()+";\n";
                            }
                            rank += auxiliar.getDPI() + ";";
                            auxiliar = auxiliar.Siguiente;
                            bw.write(label);
                        } while (auxiliar != null);
                        rank += "}\n";
                        bw.write(rank);
                    }
                }
            }
            bw.write("}");

            bw.close();
            try {
                // Execute a command without arguments
                String command = "dot -Tjpg tablahash.txt -o HashTable.jpg";
                Process child = Runtime.getRuntime().exec(command);
                child = Runtime.getRuntime().exec(command);
                img = ImageIO.read(new File("HashTable.jpg"));
                String archivo = "HashTable.jpg";
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
        String Texto = "node[shape=record]\n";
        nodoClienteLista auxiliar;
        LS_Cliente listita;
        String conexiones = "";
        String rank = "";

        for (int i = 0; i < tamanio; i++) {
            int k = i + 1;
            if (vectorHash[i] != null) {
                String us = vectorHash[i].DPI;
                String contra = vectorHash[i].Nombre;
                Texto += i + " [label=\"numero ------- " + i + "----- Usuario - " + us + "  contraseña - " + contra + " \",style= filled,fillcolor = green];\n";
                listita = vectorHash[i].getListaDeCliente();

                if (i < tamanio - 1) {

                    conexiones += i + "->" + k + ";\n";
                }
            } else {
                Texto += i + " [label=\"numero ------- " + i + "-------------- VACIO-------------- \",style= filled,fillcolor = red];\n";
                conexiones += i + "->" + k + ";\n";
            }
        }
        Texto += conexiones;
        for (int i = 0; i < tamanio; i++) {
            if (vectorHash[i] != null) {
                listita = vectorHash[i].getListaDeCliente();
                if (listita.getInicio() != null) {
                    auxiliar = listita.getInicio();
                    String label = "";
                    label += i + "->" + "Cliente" + auxiliar.getDPI() + "\n";
                    Texto += label;
                    rank = "{ rank = same;" + i + ";";
                    do {
                        label = "";
                        label += "Cliente" + auxiliar.getDPI() + "[label=\"------ " + auxiliar.getDPI() + "-------- " + auxiliar.getNombre() + " \",style=filled,fillcolor = orange];\n";

                        if (auxiliar.Siguiente != null) {

                            label += "Cliente" + auxiliar.getDPI() + "->" + "Cliente" + auxiliar.Siguiente.getDPI() + ";\n";
                        } else {
                            //label+=auxiliar.getDPI()+";\n";
                        }
                        rank += "Cliente" + auxiliar.getDPI() + ";";
                        auxiliar = auxiliar.Siguiente;
                        Texto += label;
                    } while (auxiliar != null);
                    rank += "}\n";
                    Texto += rank;
                }
            }
        }
        return Texto;
    }

}
