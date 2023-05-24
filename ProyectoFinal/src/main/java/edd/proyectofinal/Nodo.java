/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd.proyectofinal;

/**
 *
 * @author jeffmenca
 */
public class Nodo {

    private MapaHash data;
    private Nodo next;

    public Nodo(MapaHash data) {
        this.data = data;
    }

    public MapaHash getData() {
        return data;
    }

    public void setData(MapaHash data) {
        this.data = data;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }
}
