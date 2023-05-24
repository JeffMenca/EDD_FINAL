package edd.proyectofinal;

/**
 *
 * @author jeffmenca
 */
public class ListaEnlazada {

    private Nodo head;
    private Nodo tail;
    private int size;

    public ListaEnlazada() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(MapaHash data) {
        Nodo newNode = new Nodo(data);

        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }

        size++;
    }

    public MapaHash get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        Nodo current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getData();
    }

    public int size() {
        return size;
    }

    public ResultadoQuery removeByAttribute(String atributo, String value) {
        Nodo current = head;
        Nodo previous = null;

        while (current != null) {
            MapaHash data = current.getData();

            // Obtener el valor del atributo en la HashTable
            try {
                String attributeValue = data.get(atributo).toString();

                // Comparar si el valor del atributo es igual al valor buscado
                if (attributeValue != null && attributeValue.equals(value)) {
                    // Eliminar el nodo correspondiente
                    if (previous == null) {
                        head = current.getNext();
                        if (head == null) {
                            tail = null;
                        }
                    } else {
                        previous.setNext(current.getNext());
                        if (current.getNext() == null) {
                            tail = previous;
                        }
                    }
                    size--;
                    return new ResultadoQuery("\n\nSe elimino la tupla donde: " + atributo + " es: " + value, true);
                }
            } catch (Exception e) {

                return new ResultadoQuery("\n\nNo se pudo eliminar, el atributo: " + atributo + " no existe en la tabla", false);
            }

            previous = current;
            current = current.getNext();
        }
        return new ResultadoQuery("\n\nNo se encontro la tupla donde: " + atributo + " es: " + value, false);
    }

    public boolean checkByAttribute(String atributo, String value) {
        Nodo current = head;
        Nodo previous = null;

        while (current != null) {
            MapaHash data = current.getData();

            // Obtener el valor del atributo en la HashTable
            try {
                String attributeValue = data.get(atributo).toString();

                // Comparar si el valor del atributo es igual al valor buscado
                if (attributeValue != null && attributeValue.equals(value)) {
                    return true;
                }
            } catch (Exception e) {

                return false;
            }

            previous = current;
            current = current.getNext();
        }
        return false;
    }

}
