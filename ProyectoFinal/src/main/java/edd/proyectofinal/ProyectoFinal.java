package edd.proyectofinal;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import views.Main;

/**
 *
 * @author jeffmenca
 */
public class ProyectoFinal {

    public static void main(String[] args) {
        Main main = new Main();
        main.pack();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null);
        main.setVisible(true);

    }
}
