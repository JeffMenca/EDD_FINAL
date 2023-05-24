package edd.proyectofinal;

import javax.swing.JOptionPane;

/**
 *
 * @author jeffmenca
 */
public class ArbolBPlus {

    private NodoBPlus raiz;
    private int orden;

    public ArbolBPlus(int orden) {
        this.orden = orden;
        raiz = new NodoBPlus(orden, true);
    }

    public ResultadoQuery insertar(Tabla tabla) {
        try {
            NodoBPlus nodoActual = raiz;

            // Si la raíz está llena, creamos un nuevo nodo raíz y dividimos la raíz actual:
            if (nodoActual.getNumClaves() == 2 * orden - 1) {
                NodoBPlus nuevaRaiz = new NodoBPlus(orden, false);
                nuevaRaiz.setHijo(0, nodoActual);
                nodoActual = nuevaRaiz;
                dividirNodoBPlus(0, nodoActual, raiz);
            }
            // Insertamos la tabla en el nodo adecuado:
            insertarEnHoja(tabla, nodoActual);
            return new ResultadoQuery("\n\nSe agrego la tabla: " + tabla.getNombre() + " correctamente", true);

        } catch (Exception e) {
            System.out.println(e);
            return new ResultadoQuery("\n\nNo se logro agregar la tabla: " + tabla.getNombre() + " correctamente", false);
        }
    }

    public Tabla buscar(String nombre) {
        NodoBPlus nodoActual = raiz;

        while (!nodoActual.esHoja()) {
            int i = 0;
            while (i < nodoActual.getNumClaves() && nombre.compareTo(((Tabla) nodoActual.getClave(i)).getNombre()) > 0) {
                i++;
            }
            nodoActual = nodoActual.getHijo(i);
        }

        for (int i = 0; i < nodoActual.getNumClaves(); i++) {
            if (nombre.equals(nodoActual.getTabla(i).getNombre())) {
                return nodoActual.getTabla(i);
            }
        }

        return null;
    }

    private void dividirNodoBPlus(int indice, NodoBPlus nodoPadre, NodoBPlus nodoHijo) {
        NodoBPlus nuevoNodoBPlus = new NodoBPlus(orden, nodoHijo.esHoja());
        nuevoNodoBPlus.setNumClaves(orden - 1);

        for (int i = 0; i < orden - 1; i++) {
            nuevoNodoBPlus.setClave(i, nodoHijo.getClave(i + orden));
        }

        if (!nodoHijo.esHoja()) {
            for (int i = 0; i < orden; i++) {
                nuevoNodoBPlus.setHijo(i, nodoHijo.getHijo(i + orden));
            }
        }

        nodoHijo.setNumClaves(orden - 1);

        for (int i = nodoPadre.getNumClaves(); i >= indice + 1; i--) {
            nodoPadre.setHijo(i + 1, nodoPadre.getHijo(i));
        }

        nodoPadre.setHijo(indice + 1, nuevoNodoBPlus);

        for (int i = nodoPadre.getNumClaves() - 1; i >= indice; i--) {
            nodoPadre.setClave(i + 1, nodoPadre.getClave(i));
        }

        nodoPadre.setClave(indice, nuevoNodoBPlus.getClave(0));
        nodoPadre.setNumClaves(nodoPadre.getNumClaves() + 1);
    }

    private void insertarEnHoja(Tabla tabla, NodoBPlus nodoActual) {
        int indice = nodoActual.getNumClaves() - 1;
        while (indice >= 0 && tabla.getNombre().compareTo(nodoActual.getTabla(indice).getNombre()) < 0) {
            nodoActual.setTabla(indice + 1, nodoActual.getTabla(indice));
            indice--;
        }
        nodoActual.setTabla(indice + 1, tabla);
        nodoActual.setNumClaves(nodoActual.getNumClaves() + 1);
    }

    public Tabla[] obtenerTablas() {
        Tabla[] tablas = new Tabla[calcularTamano()];
        obtenerTablasAux(raiz, tablas, 0);
        return tablas;
    }

    private int calcularTamano() {
        NodoBPlus nodoActual = raiz;
        while (!nodoActual.esHoja()) {
            nodoActual = nodoActual.getHijo(0);
        }
        return nodoActual.getNumClaves();
    }

    private int obtenerTablasAux(NodoBPlus nodoActual, Tabla[] tablas, int indice) {
        if (nodoActual.esHoja()) {
            for (int i = 0; i < nodoActual.getNumClaves(); i++) {
                tablas[indice++] = nodoActual.getTabla(i);
            }
        } else {
            for (int i = 0; i < nodoActual.getNumClaves() + 1; i++) {
                indice = obtenerTablasAux(nodoActual.getHijo(i), tablas, indice);
            }
        }
        return indice;
    }

}
