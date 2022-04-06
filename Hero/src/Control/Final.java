package Control;

import Aseem.Personaje.Aseem;
import Brianda.Personaje.Brianda;
import Enemigos.PersonajesJefe;
import Nada.Nada;
import Pueblo.Pueblo;
import Serbal.Personaje.Serbal;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Final extends JPanel implements Runnable, ActionListener {

    private Image buffer;
    private Graphics dbf;
    private boolean ejecutar = true;
    private boolean caida = false;
    private boolean bandera = false;
    public Personaje per;
    public Aseem enem;
    public final Nada pueblo;
    private int x;
    private Timer cron;

    public Final() {
        per = new Brianda();
        enem = new Aseem(per);
        enem.start();
        pueblo = new Nada(per);
        cron = new Timer(10, this);
        setSize(1360, 768);
    }

    @Override
    public void run() {
        while (ejecutar) {
            paint(getGraphics());
            per.UpdateEstado();
            pueblo.Colision1();
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(Primer_Nivel.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (per.getY() + 120 > pueblo.getY()) {
                per.setY(pueblo.getY() - 119);
            }
            if (per.getVida() <= 0) {
                ejecutar = false;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        buffer = createImage(getWidth(), getHeight());
        dbf = buffer.getGraphics();
        updatePaint(dbf);
        g.drawImage(buffer, 0, 0, 1360, 768, this);
    }

    public void updatePaint(Graphics g) {
        g.setColor(Color.white);
        pueblo.paint(g, this);
        per.paint(g, this);
        pueblo.paint2(g, this);if (enem.isVivo()) {
            enem.paint(g, this);
        } else {
            ejecutar = false;
        }
    }

    public JPanel getP() {
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (!enem[i].isVivo()) {
//
//        }
    }

    private int Random() {
        return ((int) (Math.random() * 1000));
    }

    private int Random2() {
        return ((int) (Math.random() * 7));
    }

    private int Random3() {
        return ((int) (Math.random() * 2));
    }

    public synchronized void setEjecutar(boolean ejecutar) {
        this.ejecutar = ejecutar;
    }

    public boolean isEjecutar() {
        return ejecutar;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

}
