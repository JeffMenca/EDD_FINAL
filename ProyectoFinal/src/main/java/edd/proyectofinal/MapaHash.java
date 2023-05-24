package edd.proyectofinal;

import java.util.Collection;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jeffmenca
 */
public class MapaHash {

    private int capacity = 20;  // Capacidad inicial
    Entry[] table;  // Tabla de hash

    private class Entry {

        String key;
        String tipo;
        String foranea;
        Object value;
        Entry next;

        Entry(String key, String tipo, Object value, String foranea) {
            this.key = key;
            this.tipo = tipo;
            this.value = value;
            this.foranea = foranea;
        }
    }

    public MapaHash() {
        table = new Entry[capacity];
    }

    private int hash(String key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public void put(String key, String tipo, Object value, String foranea) {
        int hash = hash(key);
        Entry entry = table[hash];

        if (entry == null) {
            table[hash] = new Entry(key, tipo, value, foranea);
        } else {
            while (entry.next != null && !entry.key.equals(key)) {
                entry = entry.next;
            }

            if (entry.key.equals(key)) {
                entry.value = value;
            } else {
                entry.next = new Entry(key, tipo, value, foranea);
            }
        }
    }

    public Object get(String key) {
        int hash = hash(key);
        Entry entry = table[hash];

        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }

            entry = entry.next;
        }

        return null;
    }

    public Object getAttribute(String key) {
        int hash = hash(key);
        Entry entry = table[hash];

        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.key;
            }

            entry = entry.next;
        }

        return null;
    }

    public String getForanea(String key) {
        int hash = hash(key);
        Entry entry = table[hash];

        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.foranea;
            }

            entry = entry.next;
        }

        return null;
    }

    public String checkForaneas() {
        for (Entry entry : table) {
            while (entry != null) {
                if (entry.foranea != null) {
                    return entry.foranea;
                }
                entry = entry.next;
            }
        }
        return "null";
    }

    public void printValues() {
        for (Entry entry : table) {
            while (entry != null) {
                System.out.println(entry.key + ": " + entry.value);
                entry = entry.next;
            }
        }
    }

    public String getValues() {
        StringBuilder values = new StringBuilder();
        for (Entry entry : table) {
            while (entry != null) {
                values.append("\n").append(entry.key).append(": ").append(entry.tipo);
                entry = entry.next;
            }
        }
        return values.toString();
    }

    public boolean checkValuesNotNull() {
        for (Entry entry : table) {
            while (entry != null) {
                if (entry.value == null) {
                    return false;
                }
                entry = entry.next;
            }
        }
        return true;
    }

    public ResultadoQuery editValue(String key, Object newValue) {

        int hash = hash(key);
        Entry entry = table[hash];

        while (entry != null) {
            if (entry.key.equals(key)) {
                if (getType(newValue.toString()) == entry.tipo) {
                    entry.value = newValue;
                    return null;
                }
                return new ResultadoQuery("\n\nEl valor:" + newValue.toString() + " no es de tipo:" + entry.tipo, false);
            }
            entry = entry.next;
        }
        return new ResultadoQuery("\n\nNo se encontro el atributo:" + key, false);
    }

    public MapaHash ObtainCopy() {
        MapaHash copia = new MapaHash();

        // Copiamos los valores de cada entrada de la tabla de hash
        for (int i = 0; i < capacity; i++) {
            Entry entry = table[i];
            while (entry != null) {
                // Creamos una nueva instancia de Entry con los mismos valores
                Entry newEntry = new Entry(entry.key, entry.tipo, entry.value, entry.foranea);
                // Agregamos la nueva entrada a la copia
                copia.put(newEntry.key, newEntry.tipo, newEntry.value, newEntry.foranea);
                entry = entry.next;
            }
        }

        return copia;
    }

    public String getType(String valor) {
        // Comprobación si es un int
        try {
            int numero = Integer.parseInt(valor);
            return "int";
        } catch (NumberFormatException e) {
            // No es un int
        }
        // Comprobación si es un boolean
        if (valor.equalsIgnoreCase("true") || valor.equalsIgnoreCase("false")) {
            return "bool";
        }

        // Comprobación si es un double
        try {
            double decimal = Double.parseDouble(valor);
            return "double";
        } catch (NumberFormatException e) {
            // No es un double
        }

        // Comprobación si es un String
        if (valor instanceof String) {
            return "string";
        }

        return "none";
    }

    public DefaultTableModel toTableModel() {
        // Creamos un modelo de tabla vacío
        DefaultTableModel model = new DefaultTableModel();

        // Recorremos las entradas de la tabla de hash
        for (Entry entry : table) {
            while (entry != null) {
                // Agregamos la columna correspondiente a la clave de esta entrada
                model.addColumn(entry.key + " (" + entry.tipo + ")");

                entry = entry.next;
            }
        }

        // Creamos un array con los valores de todas las entradas
        Object[] rowData = new Object[table.length];
        int i = 0;
        for (Entry entry : table) {
            while (entry != null) {
                rowData[i++] = entry.value;
                entry = entry.next;
            }
        }

        // Agregamos la fila de datos completa al modelo de tabla
        model.addRow(rowData);

        return model;
    }

}
