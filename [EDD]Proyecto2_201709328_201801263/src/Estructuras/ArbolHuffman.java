/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import static Estructuras.grafoRutas.Vertice;
import Extra.Diccionario;
import Nodos.nodoHuffman;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Audrie
 */
public class ArbolHuffman {

    public static nodoHuffman node;
    public static nodoHuffman newRoot;
    public static String codedText = "";
    public static ArrayList<Diccionario> Biblioteca = new ArrayList<>();
    public static String Diccionario[][];

    public static void TreeMaker(nodoHuffman root, nodoHuffman end) {
        node = new nodoHuffman(end.linkerBack.texo + end.texo, end.linkerBack.count + end.count);
        node.izq = end.linkerBack;
        node.der = end;
        end.linkerBack.linkerBack.linker = node;
        node.linkerBack = end.linkerBack.linkerBack;
        end = node;
        end.linker = null;
        nodoHuffman actual = root;

        while (actual.linker != null) {
            //System.out.println(actual.texo + "->");
            actual = actual.linker;
        }
        //System.out.println(actual.texo);

        if (root.linker == end) {
            node = new nodoHuffman(root.texo + end.texo, root.count + end.count);
            node.izq = root;
            node.der = end;
            node.linker = null;
            node.linkerBack = null;
            //System.out.println(node.texo);
            newRoot = node;
        } else {
            TreeMaker(root, end);
        }
    }

    public static void inOrder(nodoHuffman root) {
        if (root != null) {
            inOrder(root.izq);
            System.out.println(root.texo + "->");
            inOrder(root.der);
        }
    }

    public static void preOrder(nodoHuffman root) {
        if (root != null) {
            System.out.println(root.texo + "->");
            preOrder(root.izq);
            preOrder(root.der);
        }
    }

    public static String comprimirHuffman(String TextoComprimir, String Nombre) {
        String espacio = "";
        char[] msgChar = TextoComprimir.toCharArray();
        ArrayList<Character> caracteres = new ArrayList<Character>();
        for (int i = 0; i < msgChar.length; i++) {
            if (!(caracteres.contains(msgChar[i]))) { //Guarda una cadena de caracteres que no esten repetidos en el texto
                caracteres.add(msgChar[i]);
            }
        }
        Diccionario = new String[caracteres.size()][2];
        //Collections.sort(caracteres);
        int[] conteoCaracter = new int[caracteres.size()];

        for (int x = 0; x < conteoCaracter.length; x++) {
            conteoCaracter[x] = 0;                    //Inicializa un arreglo para almacenar la cantidad de veces que se repite un caracter
        }

        for (int i = 0; i < caracteres.size(); i++) {
            char checker = caracteres.get(i);
            for (int x = 0; x < msgChar.length; x++) {
                if (checker == msgChar[x]) {            //Cuenta las veces que se repite un caracter y lo guarda en el array a la misma posición que el
                    conteoCaracter[i]++;                //caracter que se encuentra almacenado en el arreglo de caracteres
                }
            }
        }

        for (int i = 0; i < conteoCaracter.length - 1; i++) {
            for (int j = 0; j < conteoCaracter.length - 1; j++) {
                if (conteoCaracter[j] < conteoCaracter[j + 1]) {  //Lo ordena del que tiene más caracteres repetidos al que tiene menos y no 
                    int temp = conteoCaracter[j];            //lo ordena en orden alfabetico, sino que en orde de como fueron ingresando
                    conteoCaracter[j] = conteoCaracter[j + 1];
                    conteoCaracter[j + 1] = temp;

                    char tempChar = caracteres.get(j);
                    caracteres.set(j, caracteres.get(j + 1));
                    caracteres.set(j + 1, tempChar);
                }
            }
        }

        /*for (int x = 0; x < conteoCaracter.length; x++) {
            System.out.println(caracteres.get(x) + "-" + conteoCaracter[x]);
        }*/

        nodoHuffman root = null;
        nodoHuffman actual = null;
        nodoHuffman end = null;

        for (int i = 0; i < conteoCaracter.length; i++) {
            nodoHuffman node = new nodoHuffman(caracteres.get(i).toString(), conteoCaracter[i]);
            if (root == null) {
                root = node;
                end = node;
            } else {
                actual = root;
                while (actual.linker != null) {
                    actual = actual.linker;
                }
                actual.linker = node;
                actual.linker.linkerBack = actual;
                end = node;
            }
        }

        TreeMaker(root, end);

       /* System.out.println();
        inOrder(node);

        System.out.println();
        preOrder(node);*/

        char[] textoArray = TextoComprimir.toCharArray();
        char checker;

        for (int i = 0; i < textoArray.length; i++) {
            actual = node;
            checker = textoArray[i];
            String code = "";

            while (true) {
                if (actual.izq.texo.toCharArray()[0] == checker) {
                    code += "0";
                    break;
                } else {
                    code += "1";
                    if (actual.der != null) {
                        if (actual.der.texo.toCharArray()[0] == caracteres.get(conteoCaracter.length - 1)) {
                            break;
                        }
                        actual = actual.der;
                    } else {
                        break;
                    }
                }
            }

            codedText += code;
            codedText += " ";
            //System.out.println(code);
            if (checker == ' ') {
                espacio = code;
            }
            if (!BuscarEnDiccionario(Diccionario, checker, code, caracteres.size())) {
                int Posicion = PosicionDiccionario(caracteres.size(), Diccionario);
                if (Diccionario[0][0] == null) {
                    Diccionario[0][0] = String.valueOf(checker);       //Letra
                    Diccionario[0][1] = code;                          //Codigo
                } else if (Posicion != -1) {
                    Diccionario[Posicion][0] = String.valueOf(checker);       //Letra
                    Diccionario[Posicion][1] = code;                          //Codigo

                } else {
                    System.out.println("Revisar AH");
                }
            }

        }
        Diccionario diccionario = new Diccionario(Nombre, Diccionario);
        diccionario.setCodigoEspacio(espacio);
        Biblioteca.add(diccionario);
        System.out.println();
        //System.out.println("Texto comprimido: \n" + codedText);
        return codedText;

    }

    public static Boolean BuscarEnDiccionario(String[][] array, char letra, String codigo, int max) {
        Boolean Existencia = false;
        String Letra = String.valueOf(letra);
        if (array[0][0] != null) {
            for (int i = 0; i < max; i++) {
                if (array[i][0] != null && array[i][1] != null) {
                    if (array[i][0].equals(Letra) && array[i][1].equals(codigo)) {
                        Existencia = true;
                        break;
                    }
                }
            }
        } else {
            Existencia = false;
        }

        return Existencia;
    }

    public static int PosicionDiccionario(int max, String[][] array) {
        int posicion = 0;
        for (int j = 0; j < max; j++) {
            if (array[j][0] == null) {
                posicion = j;
                break;
            } else {
                posicion = -1;
            }
        }

        return posicion;
    }

    public static String Descomprimir(String TextoComprimido, String Nombre) {
        String Texto = "";
        char[] TextoChar = TextoComprimido.toCharArray();
        if (BuscarDiccionario(Nombre) != null) {
            String array[][] = BuscarDiccionario(Nombre).getDiccionario();
            Texto = BuscarPalabra(TextoComprimido, array);      //Aquí es donde Se Buscan las palabras con el diccionario generado después de comprimir
        }

        return Texto;
    }

    public static String EliminarEspaciosTextoComprimido(String texto, String espacio) {
        String nuevaCadena = "";
        if (texto.toLowerCase().contains(espacio.toLowerCase())) {
            nuevaCadena = texto.replaceAll(espacio, " ");

        }

        return nuevaCadena;
    }

    public static String BuscarPalabra(String Cadena, String[][] array) {
        String palabra = "";
        String[] CadenaNueva = Cadena.split(" ");
        for (String word : CadenaNueva) {
            for (int i = 0; i < array.length; i++) {
                if (word.equals(array[i][1])) {
                    palabra += array[i][0];
                }

            }
        }
        return palabra;
    }

    public static Diccionario BuscarDiccionario(String nombre) {
        Diccionario diccionario = null;
        for (Diccionario dicc : Biblioteca) {
            if (dicc.getNombreDiccionario().equals(nombre)) {
                diccionario = dicc;
                break;
            }
        }
        return diccionario;
    }
    
    public static void GenerarReporteDescomprimido(String texto, String nombre){
         try {
             String ruta = nombre+".edd";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.close();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error al Descomprimir el Top, "+e);
        }
    
    }
}
