package Control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Teclado implements KeyListener {

    private final static int numeroTeclas = 120;
    private final boolean[] teclas = new boolean[numeroTeclas];

    public boolean arriba, abajo, izquierda, derecha, salir, letra_A, letra_S, letra_D, Izq, Der, Up;

    public void actualizar() {
        salir = teclas[KeyEvent.VK_P];
        letra_A = teclas[KeyEvent.VK_A];
        letra_S = teclas[KeyEvent.VK_S];
        letra_D = teclas[KeyEvent.VK_D];
        Izq = teclas[KeyEvent.VK_LEFT];
        Der = teclas[KeyEvent.VK_RIGHT];
        Up = teclas[KeyEvent.VK_UP];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            teclas[e.getKeyCode()] = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            teclas[e.getKeyCode()] = false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
