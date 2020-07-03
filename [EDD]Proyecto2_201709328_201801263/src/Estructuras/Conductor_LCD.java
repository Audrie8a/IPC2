/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author Audrie
 */
import Nodos.nodoConductor;
import java.awt.Desktop;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import javax.imageio.ImageIO;

public class Conductor_LCD {

    nodoConductor ini, fin;
    public nodoConductor[] ArrayReporteConduc;

    public Conductor_LCD() {
        ini = null;
        fin = null;
        this.ArrayReporteConduc = new nodoConductor[1000];
    }

    public void InsertarVectorReporte(String Dpi, int contador) {
        int Aux = BuscarUsuarioVector(Dpi);
        if (Aux != 1000) {
            ArrayReporteConduc[Aux].setContador(contador);
        } else if (Aux == 1000) {
            for (int i = 0; i < ArrayReporteConduc.length; i++) {
                if (ArrayReporteConduc[i] == null) {
                    ArrayReporteConduc[i] = new nodoConductor(Dpi, contador);
                    break;
                }
            }
        }
    }

    public int BuscarUsuarioVector(String Dpi) {
        for (int i = 0; i < ArrayReporteConduc.length; i++) {
            if (ArrayReporteConduc[i] != null) {
                if (ArrayReporteConduc[i].getDPI().equals(Dpi)) {
                    return i;
                }
            }
        }
        return 1000;
    }

    public void Ordenar() {
        for (int i = 0; i < ArrayReporteConduc.length; i++) {
            if (ArrayReporteConduc[i] == null) {
                ArrayReporteConduc[i] = new nodoConductor("--", 0);
            }
        }
        for (int i = 0; i < ArrayReporteConduc.length - 1; i++) {
            for (int j = 0; j < ArrayReporteConduc.length - 1; j++) {
                if (ArrayReporteConduc[j].getContador() < ArrayReporteConduc[j + 1].getContador()) {
                    int tmp = ArrayReporteConduc[j + 1].getContador();
                    String tmps = ArrayReporteConduc[j + 1].getDPI();
                    ArrayReporteConduc[j + 1].setContador(ArrayReporteConduc[j].getContador());
                    ArrayReporteConduc[j].setContador(tmp);
                    ArrayReporteConduc[j].setDPI(tmps);
                }
            }
        }
    }

    public void ImprimirArray() {
        String Texto="";
        Ordenar();
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + "  DPI:    " + ArrayReporteConduc[i].getDPI() + "    NO VIAJES:     " + ArrayReporteConduc[i].getContador());
        }
        try {
            String ruta = "TopConductores.EDD";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            Texto+="-----TOP 10 CONDUCTORES CON MAYOR CANTIDAD DE VIAJES-----\n";
            for (int i = 0; i < 10; i++) {
                Texto +=(i + 1) + "  DPI:    " + ArrayReporteConduc[i].getDPI() + "    NO VIAJES:     " + ArrayReporteConduc[i].getContador() + "\n";
            }
            
            System.out.println(Texto);
            String TextoComprimido=ArbolHuffman.comprimirHuffman(Texto, "TopConductoresDescomprimido");
            bw.write(TextoComprimido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertarConductor(String DPI, String Nombre, String Apellido, char tipoLicencia, String genero, String Telefono, String Direccion) {
        nodoConductor Nuevo = new nodoConductor(DPI, Nombre, Apellido, tipoLicencia, genero, Telefono, Direccion);

        if (ini == null) {
            ini = Nuevo;
            ini.Sig = ini;
            Nuevo.Ant = fin;
            fin = Nuevo;
        } else {
            fin.Sig = Nuevo;
            Nuevo.Sig = ini;
            Nuevo.Ant = fin;
            fin = Nuevo;
            ini.Ant = fin;
        }

    }

    public nodoConductor Buscar(String dpi) {
        nodoConductor actual = new nodoConductor();
        actual = ini;
        if (actual != null) {
            do {
                if (actual.getDPI().equals(dpi)) {
                    System.out.println(actual.toString());
                    break;
                }
                actual = actual.Sig;
            } while (actual != ini);
        }

        return actual;
    }

    public boolean BuscarBoolean(String dpi) {
        nodoConductor actual = new nodoConductor();
        actual = ini;
        if (ini != null) {
            do {
                if (actual.getDPI().equals(dpi)) {
                    System.out.println(actual.toString());
                    return true;
                }
                actual = actual.Sig;
            } while (actual != ini);
        }
        return false;
    }

    public void Editar(String dpi, String Nombre, String Apellido, char tipoLicencia, String genero, String Telefono, String Direccion) {
        nodoConductor actual = new nodoConductor();
        actual = ini;
        do {
            if (actual.getDPI().equals(dpi)) {
                actual.setNombre(Nombre);
                actual.setApellidos(Apellido);
                actual.setTipoLicencia(tipoLicencia);
                actual.setGenero(genero);
                actual.setTelefono(Telefono);
                actual.setDirección(Direccion);
                break;
            }
            actual = actual.Sig;
        } while (actual != ini);
    }

    public void Eliminar(String dpi) {
        nodoConductor anterior = new nodoConductor();
        nodoConductor actual = new nodoConductor();
        actual = ini;
        anterior = fin;
        do {
            if (actual.getDPI().equals(dpi)) {

                if (actual == ini) {
                    ini = ini.Sig;
                    fin.Sig = ini;
                    ini.Ant = fin;
                } else if (actual == fin) {
                    fin = anterior;
                    ini.Ant = fin;
                    fin.Sig = ini;
                } else {
                    anterior.Sig = actual.Sig;
                    actual.Sig.Ant = anterior;
                }
            }
            anterior = actual;
            actual = actual.Sig;
        } while (actual != ini);
    }

    public void MostrarC() {
        nodoConductor actual = new nodoConductor();
        actual = ini;
        do {
            System.out.println(actual.toString());
            actual = actual.Sig;
        } while (actual != ini);
    }

    public void OrdenarLista() {
        nodoConductor actual;
        nodoConductor Sig = null;

        String DPI, Nombre, Apellido, Genero, telefono, direccion;
        char TipoLicencia;
        int contador;

        nodoConductor Ini = ini;
        if (Ini != null) {
            actual = Ini;
            do {
                Sig = actual.Sig;
                do {
                    BigInteger actualdpi= new BigInteger(actual.getDPI());
                    BigInteger Sigdpi= new BigInteger(Sig.getDPI());
                    if (ComparacionDPI(actualdpi, Sigdpi).equals("1")) {
                        DPI = actual.getDPI();
                        Nombre = actual.getNombre();
                        Apellido = actual.getApellidos();
                        Genero = actual.getGenero();
                        telefono = actual.getTelefono();
                        direccion = actual.getDirección();
                        TipoLicencia = actual.getTipoLicencia();
                        contador = actual.getContador();

                        actual.setDPI(Sig.getDPI());
                        actual.setNombre(Sig.getNombre());
                        actual.setApellidos(Sig.getApellidos());
                        actual.setGenero(Sig.getGenero());
                        actual.setTelefono(Sig.getTelefono());
                        actual.setDirección(Sig.getDirección());
                        actual.setTipoLicencia(Sig.getTipoLicencia());
                        actual.setContador(Sig.getContador());

                        Sig.setDPI(DPI);
                        Sig.setNombre(Nombre);
                        Sig.setApellidos(Apellido);
                        Sig.setGenero(Genero);
                        Sig.setTelefono(telefono);
                        Sig.setDirección(direccion);
                        Sig.setTipoLicencia(TipoLicencia);
                        Sig.setContador(contador);
                    }
                    Sig = Sig.Sig;
                }while (Sig != ini);
                actual = actual.Sig;

            } while (actual != ini);
        }

    }

    public String ComparacionDPI(BigInteger number1, BigInteger number2) {
        String relation="";
        switch (number1.compareTo(number2)) {
            case -1:
                relation = "-1";//"<";
                break;
            case 0:
                relation = "0";//"=";
                break;
            case 1:
                relation = "1"; //">";
                break;
        }
        return relation;
    }

    public Image Graficar() {
        Image img = null;
        try {
            String ruta = "ListaConductor.txt";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("digraph g {");
            bw.write(getText());
            bw.write("}");

            bw.close();
            try {
                // Execute a command without arguments
                String command = "dot -Tjpg ListaConductor.txt -o ListaConductor.jpg";
                Process child = Runtime.getRuntime().exec(command);
                child = Runtime.getRuntime().exec(command);
                img = ImageIO.read(new File("ListaConductor.jpg"));
                String archivo = "ListaConductor.jpg";
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
        nodoConductor actual = new nodoConductor();
        String EstiloVertice = " \"width = 1 style = filled, fillcolor = orange];";
        String label = "node [shape=record fontname=Arial];\n";
        if (ini != null) {
            actual = ini;
            do {
                label += "Conductor" + actual.getDPI() + "[label=\"------ " + actual.getDPI() + "-------- " + actual.getNombre() + EstiloVertice + "\n";
                label += "Conductor" + actual.getDPI() + "->" + "Conductor" + actual.Sig.getDPI() + "\n";
                label += "Conductor" + actual.Sig.getDPI() + "->" + "Conductor" + actual.getDPI() + "\n";
                actual = actual.Sig;
            } while (actual != ini);

        } else {
            label = "nodoNodo [label = \"No hay Nodos en la lista\"   width = 1 style = filled, fillcolor = \"#ccffcc\"];\n";
        }

        return label;
    }

}
