package edd.proyectofinal;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author jeffmenca
 */
public class Tabla {

    public Tabla(String nombre) {
        this.nombre = nombre;
    }

    private String nombre;
    private MapaHash atributos = new MapaHash();
    private ListaEnlazada tuplas = new ListaEnlazada();

    public String getNombre() {
        return nombre;
    }

    public void agregarAtributo(String nombre, String tipo, Object valor, String foranea) {
        if (tipo.equals("byte") || tipo.equals("short") || tipo.equals("int") || tipo.equals("long")) {
            tipo = "int";
        } else if (tipo.equals("float") || tipo.equals("double") || tipo.equals("decimal")) {
            tipo = "double";
        } else if (tipo.equals("bool") || tipo.equals("boolean")) {
            tipo = "bool";
        } else if (tipo.equals("char") || tipo.equals("string")) {
            tipo = "string";
        } else {
            if (nombre.equals("clave")) {
                atributos.put(nombre, "string", tipo, foranea);
            }
            return;
        }
        atributos.put(nombre, tipo, valor, foranea);
    }

    public Object obtenerAtributo(String nombre) {
        return atributos.get(nombre);
    }

    public ResultadoQuery AgregarTupla(MapaHash valores) {
        tuplas.add(valores);
        return new ResultadoQuery("\n\nSe agrego correctamente la tupla", true);
    }

    public void ImprimirTuplas() {
        for (int i = 0; i < tuplas.size(); i++) {
            MapaHash map = tuplas.get(i);
            map.printValues();
        }
    }

    public void ImprimirTuplasTabla(JTable tabla) {
        DefaultTableModel modeloTabla = new DefaultTableModel(); // crear un nuevo modelo de tabla vacío
        if (tuplas.size() > 0) {
            for (int i = 0; i < tuplas.size(); i++) {
                MapaHash map = tuplas.get(i);
                TableModel modelo = map.toTableModel();
                if (i == 0) { // agregar las columnas solo una vez antes del bucle
                    modeloTabla.addColumn("No.");
                    for (int j = 0; j < modelo.getColumnCount(); j++) {
                        modeloTabla.addColumn(modelo.getColumnName(j));
                    }
                }
                for (int j = 0; j < modelo.getRowCount(); j++) {
                    Object[] fila = new Object[modelo.getColumnCount() + 1];
                    fila[0] = i + 1; // Agregar el número de iteración en la primera columna
                    for (int k = 0; k < modelo.getColumnCount(); k++) {
                        fila[k + 1] = modelo.getValueAt(j, k);
                    }
                    modeloTabla.addRow(fila); // agregar la fila al modelo de la tabla
                }
            }
        } else {
            MapaHash map = atributos;
            TableModel modelo = map.toTableModel();
            modeloTabla.addColumn("No.");
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                modeloTabla.addColumn(modelo.getColumnName(j));
            }
        }

        tabla.setModel(modeloTabla); // asignar el modelo de la tabla actualizado a la tabla

        // Establecer el ancho fijo para los encabezados después de que se haya generado la tabla
        TableColumn column;
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            column = tabla.getColumnModel().getColumn(i);
            column.setPreferredWidth(200); // Establece aquí el ancho deseado en píxeles
        }
    }

    public MapaHash ObtenerAtributosTupla() {
        MapaHash copia = new MapaHash();
        copia = atributos.ObtainCopy();
        return copia;
    }

    public ListaEnlazada getTuplas() {
        return tuplas;
    }

}
