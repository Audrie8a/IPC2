/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;
import java.util.LinkedList;
import java.util.ListIterator;
import Extra.Ordenar;
/**
 *
 * @author Audrie
 */
public class NodoB {
    int K; //mK
    LinkedList<NodoB> ptr = new LinkedList<NodoB>();
    int h;
    int n = 0;

    
    
    String a = "";
    public int NoDeClaves; //mB
    public Ordenar[] Claves; //mLlaves
    public Object[] datos; //mDato
    public NodoB[] Punteros; //mPunteros

    private static int noNodo = 1;

    public NodoB(int pK) {
        this.K = pK;
        // this.NoLibros =0;
        NoDeClaves = 0;
        Claves = new Ordenar[2 * pK + 1];
        datos = new Object[2 * pK + 1];
        Punteros = new NodoB[(2 * pK) + 2];
    }

    public NodoB(int pK, Ordenar Clave, Object Dato) {
        this(pK);
        // this.NoLibros=0;
        NoDeClaves = 1;
        Claves[0] = Clave;
        datos[0] = Dato;
    }

    public void setK(int pK) {
        this.K = pK;
    }

    public int getK() {
        return K;
    }

    public String getDotName() {
        return "Nodo" + this.hashCode();
    }

    public void getH(int a) {
        this.h = a;
    }

    public int noVehiculos() {
        int NoLibros = 0;
        for (int i = 0; i < NoDeClaves; i++) {
            NoLibros++;
        }
        for (int i = 0; i <= NoDeClaves; i++) {
            if (Punteros[i] != null) {
                NoLibros += Punteros[i].noVehiculos();
            }
        }
        return NoLibros;
    }

    public String toDot() {
        StringBuilder a = new StringBuilder();
        a.append(getDotName());
        a.append("[label=\"<P0>");
        for (int i = 0; i < NoDeClaves; i++) {
            a.append("|" + Claves[i].getClave().toString());
            a.append("|<P" + (i + 1) + ">");
        }
        a.append("\"];\n");
        for (int i = 0; i <= NoDeClaves; i++) {
            if (Punteros[i] != null) {
                a.append(Punteros[i].toDot());
                a.append(getDotName() + ":P" + i + " -> " + Punteros[i].getDotName() + ";\n");
            }
        }
        return a.toString();
    }

    public String toDotReporte() {
        StringBuilder a = new StringBuilder();
        a.append(getDotName());
        a.append("[label=\"<P0>");
        for (int i = 0; i < NoDeClaves; i++) {
            a.append("|" + Claves[i].getClave().toString() + "  SHIT" + Claves[i].getVehiculo().getMarca());
            a.append("|<P" + (i + 1) + ">");
        }
        a.append("\"];\n");
        for (int i = 0; i <= NoDeClaves; i++) {
            if (Punteros[i] != null) {
                a.append(Punteros[i].toDotReporte());
                a.append(getDotName() + ":P" + i + " -> " + Punteros[i].getDotName() + ";\n");
            }
        }
        return a.toString();
    }

    
    public String graficarPorNivel(NodoB x) //recibo como primer parametro la raiz del arbol
    {
        StringBuilder a = new StringBuilder();

        for (int i = 0; i < x.NoDeClaves; i++) {
            a.append(x.Claves[i].getClave().toString() + " --> "); //imprimo las claves que tenga la raiz
        }
        if (x.Punteros != null) {
            // n++;                  // si el nodo actual tiene punteros los mando a imprimir en el metodo dibujarNivelRecursivo
            a.append(dibujarNivelRecursivo(x));
        }
        return a.toString();
    }

    public String dibujarNivelRecursivo(NodoB x) {

        for (int i = 0; i <= x.NoDeClaves; i++) {
            if (x.Punteros[i] != null) {
                for (int j = 0; j < x.Punteros[i].NoDeClaves; j++) {
                    a = a + x.Punteros[i].Claves[j].getClave().toString() + " --> ";
                }
                if (n < h) {
                    ptr.addLast(x.Punteros[i]);
                }
            }
        }
        //n++;
        if (ptr != null) {
            ListIterator s = ptr.listIterator();
            while (s.hasNext()) {
                dibujarNivelRecursivo(ptr.pop());

            }
        }

        return a;
    }
}
