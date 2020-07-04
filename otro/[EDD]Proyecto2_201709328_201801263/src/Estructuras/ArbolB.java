/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;
import Extra.Listas;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import Nodos.NodoB;
import Extra.Ordenar;
import Extra.Separar;
import Nodos.nodoVehiculo;
import java.awt.Desktop;
/**
 *
 * @author Audrie
 */
public class ArbolB {
    public NodoB Raiz = null; //mraiz
    private int K = 2; // orden por defecto con formula 2K+1 siendo orden 5 default //mk
    private int Altura = 0;
    LinkedList<NodoB> punteros = new LinkedList<NodoB>();

    public ArbolB() // constructor vacio**orden default
    {

    }

    public ArbolB(int orden) //recibe orden tomando en cuenta formula 2K+1
    {
        this.K = orden;
    }

    public ArbolB(NodoB raiz) {
        K = raiz.getK();
        this.Raiz = raiz;
        Altura = 1;
    }

    public void insertar(Ordenar clave, Object o) {
        if (this.Altura == 0) {
            this.Raiz = new NodoB(this.K, clave, o);
            this.Altura = 1;
            return;
        }
        Separar separar = Insertar(this.Raiz, clave, o, 1);
        if (separar == null) {
        } else {
            NodoB ptr = this.Raiz;
            this.Raiz
                    = new NodoB(this.K, separar.getClave(), separar.getDato());
            this.Raiz.Punteros[0] = ptr;
            this.Raiz.Punteros[1] = separar.getPuntero();
            this.Altura++;
        }
    }

    public boolean ArbolVacio() {
        return Raiz == null;
    }

    protected Separar Insertar(NodoB nodo, Ordenar clave, Object o, int nivel) {
        Separar separar = null;
        NodoB puntero = null;

        int i = 0;
        while ((i < nodo.NoDeClaves) && (clave.mayorQue(nodo.Claves[i]))) {
            i++;
        }
        if ((i < nodo.NoDeClaves) && clave.igualA(nodo.Claves[i])) {
            nodo.datos[i] = o;
            return null;
        }

        if (nivel < this.Altura) {

            separar = Insertar(nodo.Punteros[i], clave, o, nivel + 1);

            if (separar == null) {
                return null;
            } else {
                clave = separar.Clave;
                o = separar.Dato;
                puntero = separar.Puntero;
            }
        }

        i = nodo.NoDeClaves - 1;
        while ((i >= 0)
                && (nodo.Claves[i] == null || clave.menorQue(nodo.Claves[i]))) {
            nodo.Claves[i + 1] = nodo.Claves[i];
            nodo.datos[i + 1] = nodo.datos[i];
            nodo.Punteros[i + 2] = nodo.Punteros[i + 1];
            i--;
        }

        nodo.Claves[i + 1] = clave;
        nodo.datos[i + 1] = o;
        if (separar != null) {
            nodo.Punteros[i + 2] = separar.Puntero;
        }
        nodo.NoDeClaves++;

        if (nodo.NoDeClaves > 2 * K) {

            NodoB newnode = new NodoB(this.K);
            newnode.Punteros[this.K] = nodo.Punteros[nodo.NoDeClaves];
            nodo.Punteros[nodo.NoDeClaves] = null;
            nodo.NoDeClaves = this.K + 1;
            for (i = 0; i < this.K; i++) {
                newnode.Claves[i] = nodo.Claves[i + nodo.NoDeClaves];
                nodo.Claves[i + nodo.NoDeClaves] = null;
                newnode.datos[i] = nodo.datos[i + nodo.NoDeClaves];
                nodo.datos[i + nodo.NoDeClaves] = null;
                newnode.Punteros[i] = nodo.Punteros[i + nodo.NoDeClaves];
                nodo.Punteros[i + nodo.NoDeClaves] = null;
            }
            nodo.NoDeClaves--;

            separar
                    = new Separar(newnode, nodo.Claves[nodo.NoDeClaves], nodo.datos[nodo.NoDeClaves]);
            nodo.Claves[nodo.NoDeClaves] = null;
            nodo.datos[nodo.NoDeClaves] = null;
            newnode.NoDeClaves = this.K;
            nodo.NoDeClaves = this.K;

            return separar;
        }
        return null;
    }

    public Object Buscar(Ordenar clave) {
        return Buscar(clave, Raiz);
    }

    public Object Buscar(Ordenar clave, NodoB nodo) {

        if ((nodo == null) || (nodo.NoDeClaves < 1)) {
            System.err.println("NO EXISTE");
            return null;
        }
        if (clave.menorQue(nodo.Claves[0])) {
            return Buscar(clave, nodo.Punteros[0]);
        }
        if (clave.mayorQue(nodo.Claves[nodo.NoDeClaves - 1])) {
            return Buscar(clave, nodo.Punteros[nodo.NoDeClaves]);
        }
        int i = 0;
        while ((i < nodo.NoDeClaves - 1) && (clave.mayorQue(nodo.Claves[i]))) {
            i++;
        }
        if (clave.igualA(nodo.Claves[i])) {
            System.out.println("SI EXISTE");
            return nodo.datos[i];
        }
        return Buscar(clave, nodo.Punteros[i]);
    }
    
    

    public void EliminarClave(Ordenar clave) {
        if (EliminarClave(clave, Raiz) != null) {
            System.out.println("CLAVE ELIMINADA");
        } else {
            System.out.println("CLAVE NO ENCONTRADA");
        }
    }

    public Object EliminarClave(Ordenar clave, NodoB nodo) {
        punteros.addLast(nodo);
        if ((nodo == null) || (nodo.NoDeClaves < 1)) {
            System.err.println("NO EXISTE");
            return null;
        }
        if (clave.menorQue(nodo.Claves[0])) {
            //punteros.addLast(nodo.Punteros[0]);
            return EliminarClave(clave, nodo.Punteros[0]);
        }
        if (clave.mayorQue(nodo.Claves[nodo.NoDeClaves - 1])) {
            //punteros.addLast(nodo.Punteros[nodo.NoDeClaves]);
            return EliminarClave(clave, nodo.Punteros[nodo.NoDeClaves]);
        }
        int i = 0;
        while ((i < nodo.NoDeClaves - 1) && (clave.mayorQue(nodo.Claves[i]))) {
            i++;
        }
        if (clave.igualA(nodo.Claves[i])) {
            System.out.println("SI EXISTE");
            if (punteros.size() > 1) {
                punteros.removeLast();
            }

            return OrdenarEliminado(nodo, (i));
        }
        return EliminarClave(clave, nodo.Punteros[i]);
    }

    public Object OrdenarEliminado(NodoB n, int index) {
        int i = index;
        if (n.NoDeClaves > 2) //CASO SENCILLO ELIMINACION DIRECTA
        {
            for (; i < n.Claves.length - 1; i++) {
                n.Claves[i] = n.Claves[i + 1];
            }
            //n.Punteros[i].Punteros[i]
            n.Claves[i] = null;
            n.NoDeClaves--;
            punteros.clear();
        } else {
            for (; i < n.Claves.length - 1; i++) {
                n.Claves[i] = n.Claves[i + 1];
            }

            n.Claves[i] = null;
            n.NoDeClaves--;
            System.out.println(punteros.size());
            Rebalanceo(n, punteros);
            punteros.clear();
        }
        return n;
    }

    private Object Rebalanceo(NodoB n, LinkedList<NodoB> s) {
        NodoB copia = s.removeLast();
        if (copia != null && copia.Punteros != null && copia.Punteros.length > 1 && n != Raiz) {
            for (int i = 0; i < copia.Punteros.length; i++) {
                if (copia.Punteros[i].NoDeClaves < 2) //NODO PADRE VERIFICA SI ALGUN HIJO TIENE MENOS DE DOS CLAVES
                {
                    if (i == 0) //si es el puntero izquierdo, primer nodo
                    {
                        if (copia.Punteros[i + 1].NoDeClaves > 2) //NODO PROXIMO DERECHO DEL DEFICIENTE PUEDE DAR UNA CLAVE 
                        {
                            if (copia == Raiz) {
                                int posicionPadre = ObtenerClaveCabeza(Raiz, n);
                                n.Claves[1] = Raiz.Claves[posicionPadre]; // quito una clave del nodo padre y la agrego al nodo deficiente
                                n.NoDeClaves++; // actualizo el numero de claves del nodo deficiente
                                Raiz.Claves[posicionPadre] = copia.Punteros[i + 1].Claves[0];//se debe reordenar puntero que se quita la clave y actauzlizar no de claves
                                ReordenarClaves(copia.Punteros[i + 1], 0);
                                return null;
                            } else if (copia != Raiz) //NODO PADRE
                            {
                                int posicionPadre = ObtenerClaveCabeza(copia, n);
                                n.Claves[1] = copia.Claves[posicionPadre];//quito la primero clave del nodo padre y la agrego al nodo deficiente
                                n.NoDeClaves++;//actualizo el numero de claves del nodo deficiente
                                copia.Claves[posicionPadre] = copia.Punteros[i + 1].Claves[0];
                                ReordenarClaves(copia.Punteros[i + 1], 0);
                                return null;
                            }
                        } else {
                            if (copia != Raiz && copia.NoDeClaves > 2) //NINGUN NODO HERMANO PUEDE PRESTAR UNA CLAVE, TODOS ESTAN CON EL MINIMO DE CLAVES PERO PADRE TIENE MAS DE DOS CLAVES
                            {
                                n.Claves[1] = copia.Claves[0]; //copio clave del nodo padre
                                n.NoDeClaves++;//actualizo el numero de claves del nodo deficiente, ahora eficiente
                                //copio claves del nodo hermano a nodo deficiente
                                n.Claves[2] = copia.Punteros[i + 1].Claves[0];
                                n.NoDeClaves++;
                                n.Claves[3] = copia.Punteros[i + 1].Claves[1];
                                n.NoDeClaves++;
                                //reordeno claves del nodo padre
                                ReordenarClaves(copia, 0); //reordeno las claves del nodo padre
                                //elimino nodo hermano
                                int index = (i + 1);
                                //reordeno punteros de nodo padre
                                ReordenarPunteros(copia, index);
                                return null;
                            } else if (copia == Raiz && copia.NoDeClaves >= 2) /// si es raiz puede quedar solo con una clave
                            {
                                n.Claves[1] = copia.Claves[0]; //copio clave del nodo padre
                                n.NoDeClaves++;//actualizo el numero de claves del nodo deficiente, ahora eficiente
                                //copio claves del nodo hermano a nodo deficiente
                                n.Claves[2] = copia.Punteros[i + 1].Claves[0];
                                n.NoDeClaves++;
                                n.Claves[3] = copia.Punteros[i + 1].Claves[1];
                                n.NoDeClaves++;
                                //reordeno claves del nodo padre
                                ReordenarClaves(copia, 0); //reordeno las claves del nodo padre
                                //elimino nodo hermano
                                int index = (i + 1);
                                //reordeno punteros de nodo padre
                                ReordenarPunteros(copia, index);
                                return null;
                            } else // reagrupacion de nodos, padre tambien esta con el minimo de claves   
                            {
                                if (copia.Punteros[i + 1] != null) {
                                    int posicionPadre = EncontrarClaveMayor(n);//encuentro la clave que sigue del nodo eliminado en el padre
                                    n.Claves[1] = copia.Claves[posicionPadre]; //ingreso clave de padre en nodo deficiente ** ahora padre esta deficiente
                                    n.NoDeClaves++;
                                    //copio claves del nodo proximo derecho a nodo deficiente, ahora eficiente
                                    n.Claves[2] = copia.Punteros[i + 1].Claves[0];
                                    n.NoDeClaves++;
                                    n.Claves[3] = copia.Punteros[i + 1].Claves[1];
                                    n.NoDeClaves++;
                                    //copiarPunteros de nodo a eliminar
                                    CopiarPunteros(n, copia.Punteros[i + 1]);
                                    //reordeno claves de nodo padre
                                    ReordenarClaves(copia, posicionPadre);
                                    //elimino nodo hermano
                                    int index = (i + 1);
                                    ReordenarPunteros(copia, index);
                                    //elimino una posicion de la lista para obtener padre
                                    if (copia == Raiz) {
                                        Raiz = n;
                                        return null;
                                    } else {
                                        return Rebalanceo(copia, punteros);//rebalanceo nuevamente
                                    }

                                } else {
                                    int posicionPadre = EncontrarClaveMayor(copia.Punteros[i - 1]);
                                    copia.Punteros[i - 1].Claves[2] = copia.Claves[posicionPadre];
                                    copia.Punteros[i - 1].NoDeClaves++;
                                    copia.Punteros[i - 1].Claves[3] = n.Claves[0];
                                    copia.Punteros[i - 1].NoDeClaves++;
                                    //copiarPunteros de nodo a eliminar
                                    CopiarPunteros(copia.Punteros[i - 1], n);
                                    //reordeno claves del nodo padre
                                    ReordenarClaves(copia, posicionPadre);
                                    //elimino nodo hermanno
                                    int index = i;
                                    ReordenarPunteros(copia, index);
                                    //elimino una posicion de la lista para obtener nodo padre
                                    if (copia == Raiz) {
                                        Raiz = n;
                                        return null;
                                    } else {
                                        return Rebalanceo(copia, punteros);//rebalanceo nuevamente
                                    }
                                }

                            }
                        }
                    } else if (i == 1 || i == 2 || i == 3) {
                        if (copia.Punteros[i - 1] != null && copia.Punteros[i - 1].NoDeClaves > 2) {
                            if (copia == Raiz) {
                                n.Claves[1] = n.Claves[0]; // se corre una posicion la clave para luego insertar la clave del nodo  hermano en la posicion 0
                                int posicionPadre = ObtenerClaveCabeza(Raiz, copia.Punteros[i - 1]);
                                n.Claves[0] = Raiz.Claves[posicionPadre];
                                n.NoDeClaves++;
                                int posicionNodoPrestar = EncontrarClaveMayor(copia.Punteros[i - 1]);
                                Raiz.Claves[posicionPadre] = copia.Punteros[i - 1].Claves[posicionNodoPrestar];
                                ReordenarClaves(copia.Punteros[i - 1], posicionNodoPrestar);
                                return null;
                            } else if (copia != Raiz) {
                                n.Claves[1] = n.Claves[0];//muevo clave a segunda posicion
                                int posicionPadre = ObtenerClaveCabeza(copia, copia.Punteros[i - 1]);
                                n.Claves[0] = copia.Claves[posicionPadre]; //obtengo clave de nodo padre
                                n.NoDeClaves++;//actualizo el no de claves de nodo deficiente ahora eficiente
                                int posicionNodoPrestar = EncontrarClaveMayor(copia.Punteros[i - 1]); //encuentro clave para prestar a padre
                                copia.Claves[posicionPadre] = copia.Punteros[i - 1].Claves[posicionNodoPrestar]; //copio clave en nodo padre
                                ReordenarClaves(copia.Punteros[i - 1], posicionNodoPrestar); //actualizo nodo que presto clave
                                return null;
                            }
                        } else if (copia.Punteros[i + 1] != null && copia.Punteros[i + 1].NoDeClaves > 2) {
                            if (copia == Raiz) {
                                int posicionPadre = ObtenerClaveCabeza(Raiz, n);//obtengo posicion de nodo padre para enviar clave a nodo deficiente
                                n.Claves[1] = Raiz.Claves[posicionPadre];//muevo clave de nodo padre a nodo deficiente
                                n.NoDeClaves++;//actualizo el no de claves de nodo deficiente, ahora eficiente
                                Raiz.Claves[posicionPadre] = copia.Punteros[i + 1].Claves[0]; // copio clave prestada a nodo derecho
                                ReordenarClaves(copia.Punteros[i + 1], 0); //actualizo nodo derecho por la clave que presto
                                return null;
                            } else if (copia != Raiz) {
                                int posicionPadre = ObtenerClaveCabeza(copia, n);
                                n.Claves[1] = copia.Claves[posicionPadre];
                                n.NoDeClaves++;
                                copia.Claves[posicionPadre] = copia.Punteros[i + 1].Claves[0];
                                ReordenarClaves(copia.Punteros[i + 1], 0);
                                return null;
                            }
                        } else {
                            if (copia != Raiz && copia.NoDeClaves > 2) //NINGUN NODO HERMANO PUEDE PRESTAR UNA CLAVE, TODOS ESTAN CON EL MINIMO DE CLAVES PERO PADRE TIENE MAS DE DOS CLAVES
                            {
                                if (copia.Punteros[i + 1] != null) {
                                    int posicionPadre = ObtenerClaveCabeza(copia, n);
                                    n.Claves[1] = copia.Claves[posicionPadre]; //copio clave del nodo padre
                                    n.NoDeClaves++;//actualizo el numero de claves del nodo deficiente, ahora eficiente
                                    //copio claves del nodo hermano a nodo deficiente
                                    n.Claves[2] = copia.Punteros[i + 1].Claves[0];
                                    n.NoDeClaves++;
                                    n.Claves[3] = copia.Punteros[i + 1].Claves[1];
                                    n.NoDeClaves++;
                                    //reordeno claves del nodo padre
                                    ReordenarClaves(copia, posicionPadre); //reordeno las claves del nodo padre
                                    //elimino nodo hermano
                                    int index = (i + 1);
                                    //reordeno punteros de nodo padre
                                    ReordenarPunteros(copia, index);
                                    return null;
                                } else if (copia.Punteros[i - 1] != null) {
                                    int posicionPadre = EncontrarClaveMayor(copia);
                                    n.Claves[3] = n.Claves[0];
                                    n.Claves[2] = copia.Claves[posicionPadre]; //copio clave del nodo padre
                                    n.NoDeClaves++;//actualizo el numero de claves del nodo deficiente, ahora eficiente
                                    //copio claves del nodo hermano a nodo deficiente
                                    n.Claves[1] = copia.Punteros[i - 1].Claves[1];
                                    n.NoDeClaves++;
                                    n.Claves[0] = copia.Punteros[i - 1].Claves[0];
                                    n.NoDeClaves++;
                                    //reordeno claves del nodo padre
                                    ReordenarClaves(copia, posicionPadre); //reordeno las claves del nodo padre
                                    //elimino nodo hermano
                                    int index = (i - 1);
                                    //reordeno punteros de nodo padre
                                    ReordenarPunteros(copia, index);
                                    return null;
                                }

                            } else if (copia == Raiz && copia.NoDeClaves >= 2) /// si es raiz puede quedar solo con una clave
                            {
                                if (copia.Punteros[i + 1] != null) {
                                    int posicionPadre = ObtenerClaveCabeza(copia, n);
                                    n.Claves[1] = copia.Claves[posicionPadre]; //copio clave del nodo padre
                                    n.NoDeClaves++;//actualizo el numero de claves del nodo deficiente, ahora eficiente
                                    //copio claves del nodo hermano a nodo deficiente
                                    n.Claves[2] = copia.Punteros[i + 1].Claves[0];
                                    n.NoDeClaves++;
                                    n.Claves[3] = copia.Punteros[i + 1].Claves[1];
                                    n.NoDeClaves++;
                                    //reordeno claves del nodo padre
                                    ReordenarClaves(copia, posicionPadre); //reordeno las claves del nodo padre
                                    //elimino nodo hermano
                                    int index = (i + 1);
                                    //reordeno punteros de nodo padre
                                    ReordenarPunteros(copia, index);
                                    return null;
                                } else if (copia.Punteros[i - 1] != null) {
                                    int posicionPadre = EncontrarClaveMayor(copia);
                                    n.Claves[3] = n.Claves[0];
                                    n.Claves[2] = copia.Claves[posicionPadre]; //copio clave del nodo padre
                                    n.NoDeClaves++;//actualizo el numero de claves del nodo deficiente, ahora eficiente
                                    //copio claves del nodo hermano a nodo deficiente
                                    n.Claves[1] = copia.Punteros[i - 1].Claves[1];
                                    n.NoDeClaves++;
                                    n.Claves[0] = copia.Punteros[i - 1].Claves[0];
                                    n.NoDeClaves++;
                                    //reordeno claves del nodo padre
                                    ReordenarClaves(copia, posicionPadre); //reordeno las claves del nodo padre
                                    //elimino nodo hermano
                                    int index = (i - 1);
                                    //reordeno punteros de nodo padre
                                    ReordenarPunteros(copia, index);
                                    return null;
                                } else // reagrupacion de nodos, padre tambien esta con el minimo de claves   
                                {
                                    if (copia.Punteros[i + 1] != null) {
                                        int posicionPadre = EncontrarClaveMayor(n);//encuentro la clave que sigue del nodo eliminado en el padre
                                        n.Claves[1] = copia.Claves[posicionPadre]; //ingreso clave de padre en nodo deficiente ** ahora padre esta deficiente
                                        n.NoDeClaves++;
                                        //copio claves del nodo proximo derecho a nodo deficiente, ahora eficiente
                                        n.Claves[2] = copia.Punteros[i + 1].Claves[0];
                                        n.NoDeClaves++;
                                        n.Claves[3] = copia.Punteros[i + 1].Claves[1];
                                        n.NoDeClaves++;
                                        //copiarPunteros de nodo a eliminar
                                        CopiarPunteros(n, copia.Punteros[i + 1]);
                                        //reordeno claves de nodo padre
                                        ReordenarClaves(copia, posicionPadre);
                                        //elimino nodo hermano
                                        int index = (i + 1);
                                        ReordenarPunteros(copia, index);
                                        //elimino una posicion de la lista para obtener padre
                                        if (copia == Raiz) {
                                            Raiz = n;
                                            return null;
                                        } else {
                                            return Rebalanceo(copia, punteros);//rebalanceo nuevamente
                                        }

                                    } else {
                                        int posicionPadre = EncontrarClaveMayor(copia.Punteros[i - 1]);
                                        copia.Punteros[i - 1].Claves[2] = copia.Claves[posicionPadre];
                                        copia.Punteros[i - 1].NoDeClaves++;
                                        copia.Punteros[i - 1].Claves[3] = n.Claves[0];
                                        copia.Punteros[i - 1].NoDeClaves++;
                                        //copiarPunteros de nodo a eliminar
                                        CopiarPunteros(copia.Punteros[i - 1], n);
                                        //reordeno claves del nodo padre
                                        ReordenarClaves(copia, posicionPadre);
                                        //elimino nodo hermanno
                                        int index = i;
                                        ReordenarPunteros(copia, index);
                                        //elimino una posicion de la lista para obtener nodo padre
                                        if (copia == Raiz) {
                                            Raiz = n;
                                            return null;
                                        } else {
                                            return Rebalanceo(copia, punteros);//rebalanceo nuevamente
                                        }
                                    }

                                }

                            }
                        }
                    } else if (i == 4) {
                        if (copia.Punteros[i - 1].NoDeClaves > 2) {
                            if (copia == Raiz) {
                                int posicionPadre = ObtenerClaveCabeza(Raiz, copia.Punteros[i - 1]); //obtengo posicion de nodo padre para enviar clave a nodo deficiente
                                n.Claves[1] = n.Claves[0];//muevo clave para ingresar clave de nodo padre
                                n.Claves[0] = Raiz.Claves[posicionPadre];
                                n.NoDeClaves++; // actualizo el no de claves de nodo deficiente, ahora eficiente
                                int posicionNodoPrestar = EncontrarClaveMayor(copia.Punteros[i - 1]); //encuentro clave para prestar a padre
                                Raiz.Claves[posicionPadre] = copia.Punteros[i - 1].Claves[posicionNodoPrestar];
                                ReordenarClaves(copia.Punteros[i - 1], posicionNodoPrestar);
                                return null;
                            } else if (copia != Raiz) {
                                int posicionPadre = ObtenerClaveCabeza(Raiz, copia.Punteros[i - 1]); //obtengo posicion de nodo padre para enviar clave a nodo deficiente
                                n.Claves[1] = n.Claves[0];//muevo clave para ingresar clave de nodo padre
                                n.Claves[0] = copia.Claves[posicionPadre];
                                n.NoDeClaves++; // actualizo el no de claves de nodo deficiente, ahora eficiente
                                int posicionNodoPrestar = EncontrarClaveMayor(copia.Punteros[i - 1]); //encuentro clave para prestar a padre
                                copia.Claves[posicionPadre] = copia.Punteros[i - 1].Claves[posicionNodoPrestar];
                                ReordenarClaves(copia.Punteros[i - 1], posicionNodoPrestar);
                                return null;
                            }
                        } else {
                            if (copia != Raiz && copia.NoDeClaves > 2) //NINGUN NODO HERMANO PUEDE PRESTAR UNA CLAVE, TODOS ESTAN CON EL MINIMO DE CLAVES PERO PADRE TIENE MAS DE DOS CLAVES
                            {
                                int posicionPadre = EncontrarClaveMayor(copia);
                                n.Claves[3] = n.Claves[0];
                                n.Claves[2] = copia.Claves[posicionPadre]; //copio clave del nodo padre
                                n.NoDeClaves++;//actualizo el numero de claves del nodo deficiente, ahora eficiente
                                //copio claves del nodo hermano a nodo deficiente en este caso, el hermano izquierdo
                                n.Claves[1] = copia.Punteros[i - 1].Claves[1];
                                n.NoDeClaves++;
                                n.Claves[0] = copia.Punteros[i - 1].Claves[0];
                                n.NoDeClaves++;
                                //reordeno claves del nodo padre
                                ReordenarClaves(copia, posicionPadre); //reordeno las claves del nodo padre
                                //elimino nodo hermano
                                int index = (i - 1);
                                //reordeno punteros de nodo padre
                                ReordenarPunteros(copia, index);
                                return null;
                            } else if (copia == Raiz && copia.NoDeClaves >= 2) {
                                int posicionPadre = EncontrarClaveMayor(copia);
                                n.Claves[3] = n.Claves[0];
                                n.Claves[2] = copia.Claves[posicionPadre]; //copio clave del nodo padre
                                n.NoDeClaves++;//actualizo el numero de claves del nodo deficiente, ahora eficiente
                                //copio claves del nodo hermano a nodo deficiente en este caso, el hermano izquierdo
                                n.Claves[1] = copia.Punteros[i - 1].Claves[1];
                                n.NoDeClaves++;
                                n.Claves[0] = copia.Punteros[i - 1].Claves[0];
                                n.NoDeClaves++;
                                //reordeno claves del nodo padre
                                ReordenarClaves(copia, posicionPadre); //reordeno las claves del nodo padre
                                //elimino nodo hermano
                                int index = (i - 1);
                                //reordeno punteros de nodo padre
                                ReordenarPunteros(copia, index);
                                return null;
                            } else // reagrupacion de nodos, padre tambien esta con el minimo de claves   
                            {
                                if (copia.Punteros[i + 1] != null) {
                                    int posicionPadre = EncontrarClaveMayor(n);//encuentro la clave que sigue del nodo eliminado en el padre
                                    n.Claves[1] = copia.Claves[posicionPadre]; //ingreso clave de padre en nodo deficiente ** ahora padre esta deficiente
                                    n.NoDeClaves++;
                                    //copio claves del nodo proximo derecho a nodo deficiente, ahora eficiente
                                    n.Claves[2] = copia.Punteros[i + 1].Claves[0];
                                    n.NoDeClaves++;
                                    n.Claves[3] = copia.Punteros[i + 1].Claves[1];
                                    n.NoDeClaves++;
                                    //copiarPunteros de nodo a eliminar
                                    CopiarPunteros(n, copia.Punteros[i + 1]);
                                    //reordeno claves de nodo padre
                                    ReordenarClaves(copia, posicionPadre);
                                    //elimino nodo hermano
                                    int index = (i + 1);
                                    ReordenarPunteros(copia, index);
                                    //elimino una posicion de la lista para obtener padre
                                    if (copia == Raiz) {
                                        Raiz = n;
                                        return null;
                                    } else {
                                        return Rebalanceo(copia, punteros);//rebalanceo nuevamente
                                    }

                                } else {
                                    int posicionPadre = EncontrarClaveMayor(copia.Punteros[i - 1]);
                                    copia.Punteros[i - 1].Claves[2] = copia.Claves[posicionPadre];
                                    copia.Punteros[i - 1].NoDeClaves++;
                                    copia.Punteros[i - 1].Claves[3] = n.Claves[0];
                                    copia.Punteros[i - 1].NoDeClaves++;
                                    //copiarPunteros de nodo a eliminar
                                    CopiarPunteros(copia.Punteros[i - 1], n);
                                    //reordeno claves del nodo padre
                                    ReordenarClaves(copia, posicionPadre);
                                    //elimino nodo hermanno
                                    int index = i;
                                    ReordenarPunteros(copia, index);
                                    //elimino una posicion de la lista para obtener nodo padre
                                    if (copia == Raiz) {
                                        Raiz = n;
                                        return null;
                                    } else {
                                        return Rebalanceo(copia, punteros);//rebalanceo nuevamente
                                    }
                                }

                            }
                        }
                    }
                }
            }
        } else ///////BORRAR ELEMENTO DE MATRIZ CON SOLO UNA CLAVE
        {
            if (n == Raiz) {
                if (n.Punteros[0] == null) {
                    Raiz = null;
                } else if (BuscarPunterosRaizMasDeDosClaves(n) != -1) {
                    int puntero = BuscarPunterosRaizMasDeDosClaves(n);
                    if (puntero == 0) {
                        int clave = EncontrarClaveMayor(n.Punteros[puntero]);
                        Raiz.Claves[0] = n.Punteros[puntero].Claves[clave];
                        ReordenarClaves(n.Punteros[puntero], clave);
                        return null;
                    } else {
                        Raiz.Claves[0] = n.Punteros[1].Claves[0];
                        ReordenarClaves(n.Punteros[1], 0);
                        return null;
                    }
                } else {
                    n.Punteros[0].Claves[2] = n.Punteros[1].Claves[0];
                    n.Punteros[0].NoDeClaves++;
                    n.Punteros[0].Claves[3] = n.Punteros[1].Claves[1];
                    n.Punteros[0].NoDeClaves++;
                    Raiz = n.Punteros[0];
                    n.Punteros[1] = null;
                    return null;
                }
            }
        }
        return null;
    }

    private void ReordenarPunteros(NodoB n, int index) {
        int i = index;
        for (; i < n.Punteros.length - 1; i++) {
            n.Punteros[i] = n.Punteros[i + 1];
        }
        n.Punteros[i] = null;
    }

    private void CopiarPunteros(NodoB n, NodoB copia) {
        int indiceCopia = 0;
        for (int i = 0; i < n.Punteros.length; i++) {
            if (n.Punteros[i] == null) {
                if (copia.Punteros[indiceCopia] != null) {
                    n.Punteros[i] = copia.Punteros[indiceCopia];
                    if (indiceCopia < 4) {
                        indiceCopia++;
                    }
                }
            }
        }
    }

    private void ReordenarClaves(NodoB n, int index) {
        int i = index;
        for (; i < n.Claves.length - 1; i++) {
            n.Claves[i] = n.Claves[i + 1];
        }
        n.Claves[i] = null;
        n.NoDeClaves--;
    }

    private int BuscarPunterosRaizMasDeDosClaves(NodoB n) {
        for (int i = 0; i < n.Punteros.length; i++) {
            if (n.Punteros[i] != null) {
                if (n.Punteros[i].NoDeClaves > 2) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int EncontrarClaveMayor(NodoB n) {
        int i = 4;
        for (; i >= 0; i--) {
            if (n.Claves[i] != null) {
                return i;
            }
        }
        return i;
    }

    private int ObtenerClaveCabeza(NodoB cabeza, NodoB prestar) {
        int j = EncontrarClaveMayor(prestar);
        for (int i = 0; i < cabeza.Claves.length; i++) {
            if (cabeza.Claves[i].getClave().toString().compareTo(prestar.Claves[j].getClave().toString()) > 0) {
                return i;
            }
        }
        return 0;
    }

    public int getAltura() {
        return Altura;
    }

    public void toDot() {
        StringBuilder a = new StringBuilder();
        a.append("digraph G { \n");
        a.append("node [shape=record];\n");
        a.append(Raiz.toDot());
        a.append("}");
        try {
            FileWriter s = new FileWriter("ArbolB.txt");
            BufferedWriter salida = new BufferedWriter(s);
            salida.write(a.toString());
            salida.close();
            Process p = Runtime.getRuntime().exec("cmd /c dot -Tpng ArbolB.txt -o ArbolB.png");
            Process p1 = Runtime.getRuntime().exec("cmd /c ArbolB.png");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public Image toDotReporte() {
        StringBuilder a = new StringBuilder();
        a.append("digraph G { \n");
        a.append("node [shape=record];\n");
        a.append(Raiz.toDotReporte());
        a.append("}");
        try {
            FileWriter s = new FileWriter("ArbolB.txt");
            BufferedWriter salida = new BufferedWriter(s);
            salida.write(a.toString());
            salida.close();
            Process p = Runtime.getRuntime().exec("cmd /c dot -Tjpg ArbolB.txt -o ArbolB.jpg");
            Thread.sleep(700);
            Image img = ImageIO.read(new File("ArbolB.jpg"));
            String archivo = "ArbolB.jpg";
            File objetofile = new File(archivo);
            Desktop.getDesktop().open(objetofile);
            return img;
            // Process p1 = Runtime.getRuntime().exec("cmd /c ArbolB.png");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
        
    }

    public String getText(){
        String label = "node [shape=record];\n";
        label += Raiz.toDotReporte();
        return label;
    }
    
    
    public String DibujarPorNivel() {
        StringBuilder s = new StringBuilder();
        Raiz.getH(getAltura());
        s.append(Raiz.graficarPorNivel(Raiz));
        return s.toString();
    }

}
