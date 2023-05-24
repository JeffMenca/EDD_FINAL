package edd.proyectofinal;

/**
 *
 * @author jeffmenca
 */
public class NodoBPlus {

    private int orden;
    private int numClaves;
    private Tabla[] tablas;
    private Object[] claves;
    private NodoBPlus[] hijos;
    private boolean esHoja;

    public NodoBPlus(int orden, boolean esHoja) {
        this.orden = orden;
        this.esHoja = esHoja;
        this.numClaves = 0;
        this.tablas = new Tabla[orden - 1];
        this.claves = new Object[orden - 1];
        this.hijos = new NodoBPlus[orden];
    }

    public Tabla getTabla(int indice) {
        return tablas[indice];
    }

    public void setTabla(int indice, Tabla tabla) {
        tablas[indice] = tabla;
    }

    public Object getClave(int indice) {
        return claves[indice];
    }

    public void setClave(int indice, Object clave) {
        claves[indice] = clave;
    }

    public NodoBPlus getHijo(int indice) {
        return hijos[indice];
    }

    public void setHijo(int indice, NodoBPlus nodo) {
        hijos[indice] = nodo;
    }

    public int getNumClaves() {
        return numClaves;
    }

    public void setNumClaves(int numClaves) {
        this.numClaves = numClaves;
    }

    public boolean esHoja() {
        return esHoja;
    }
}
