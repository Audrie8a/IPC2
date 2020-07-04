/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extra;

import Estructuras.ArbolB;
import Estructuras.BlockChain_Viajes;
import Estructuras.Conductor_LCD;
import Estructuras.TablaHash;
import Nodos.nodoVehiculo;

/**
 *
 * @author Audrie
 */
public class Listas {
    
    public static TablaHash tabl=new TablaHash();
    public static Conductor_LCD conduc = new Conductor_LCD();    
    public static BlockChain_Viajes BC = new BlockChain_Viajes();
    public static nodoVehiculo vehi=new nodoVehiculo();
    public static ArbolB arbolitoB = new ArbolB(2);
    public static nodoVehiculo[] Prueba = new nodoVehiculo[10000];
}
